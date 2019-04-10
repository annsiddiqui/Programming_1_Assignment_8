/**
 * 
 * Class that processes the data
 * @ author Qurrat-al-Ain Siddiqui
 */

import java.io.*;
import java.util.*;

/**
 * 
 * Radon processor is the class that contains the actual steps for the selection that the user makes in Client Application.
 * 
 */
public class RadonProcessor
{    
    private ArrayList<House> houseList;
    private final String streetName = "Rue Marie Curie";

    /**
     * Null Constructor creates an empty houseList
     */
    public RadonProcessor()
    {
        houseList = new ArrayList<House>();
    }

    /**
     * Function that searches houseList for houseNumber
     * @param houseNumber
     * @return index if found otherwise -1
     */
    private int searchHouseNumber(int houseNumber)
    {
        int index = -1;
        for (int i = 0; i < houseList.size(); i++)
        {
            if (houseList.get(i).getHouseNo() == houseNumber)
            {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Print the house number and street name for every house in houseList
     */
    public void printBlockData()
    {
        System.out.println("Printing Block Data");
        for (House obj : houseList)
        {
            System.out.println(obj.getHouseNo() + "  " + obj.getStreet());
        }
    }

    /**
     * Prints the house details for every house in houseList
     */
    public void printHouseDetails(int houseNumber)
    {
        int index = this.searchHouseNumber(houseNumber);
        if (index == -1)
        {
            System.out.println("No house found");
        } else
        {
            System.out.println(houseList.get(index));
        }
    }

    /**
     * @param houseNumber
     * Function removes the house specified by the user from the houselist
     */
    public void demolishHouse(int houseNumber)
    {
        int index = this.searchHouseNumber(houseNumber);
        if (index == -1)
        {
            System.out.println("Demolishion Error: No House Found");    
        } else
        {
            System.out.println("Removed House "+houseNumber);
            houseList.remove(index);
        }
    }

    /**
     * @param  fileName
     * @return Boolean (true if loading is successful else false)
     */
    public Boolean loadData(String fileName)
    {
        File file = new File(fileName);
        FileReader in = null;
        BufferedReader br = null;
        String line1 = null;
        String line2 = null;
        String line3 = null;
        try
        {
            in = new FileReader(file);
            br = new BufferedReader(in);
            while ((line1 = br.readLine()) != null &&
            (line2 = br.readLine()) != null &&
            (line3 = br.readLine()) != null)
            {
                House newHouse = new House(Integer.parseInt(line1), 
                        this.streetName,
                        Double.parseDouble(line2),
                        Double.parseDouble(line3));
                houseList.add(newHouse);
            }
        } catch (FileNotFoundException fnfe)
        {
            System.out.println("Data file not found " + fnfe);
            return false;
        }
        catch(IOException ioe)
        {
            System.out.println("I/O Exception: " + ioe);
            return false;
        }
        finally {
            try
            {
                if (br != null)
                {
                    br.close();
                }
                if (in != null)
                {
                    in.close();
                }
            } catch (IOException iex)
            {
                System.out.println("I/O Exception: " + iex);
                return false;
            }
        }
        return true;
    }

    /**
     * @param outputFileName
     * Quits the program and writes the current houseList into outputFileName
     */
    public void exit(String outputFileName)
    {
        File file = new File(outputFileName);
        PrintStream fileOut = null;
        try
        {
            if (!file.exists())
            {
                file.createNewFile();
            }
            fileOut = new PrintStream(file);
            for (House obj : houseList)
            {
                fileOut.println(obj.getHouseNo());
                fileOut.println(obj.getBaseVol());
                String dailyRead = String.format("%.05f", obj.getDailyRead());
                fileOut.println(dailyRead);
            }
        } catch (IOException ie)
        {
            System.out.println("IO Exception " + ie);
        } finally {
            if (fileOut != null)
            {
                fileOut.close();
            }
        }
    }

    /**
     * @param Yearly radon level
     * @return String that contains remediation information
     */
    private String getRating(double radonLevel)
    {
        final int SAFE = 200;
        final int DANGER = 800;
        if (radonLevel < SAFE)
        {
            return "Safe – no remediation required";
        } else 
        {
            if (radonLevel < DANGER)
            {
                return "Hazardous – remediation required";
            } else
            {
                return "Dangerous – immediate remediation required";
            }
        }    
    }

    /**
     * @param lotNumber
     * function that inserts two objects into the houseList
     */
    private int insertInfill(int lotNumber)
    {
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < houseList.size(); i++)
        {
            if (houseList.get(i).getHouseNo() < lotNumber && houseList.get(i).getHouseNo() < (lotNumber + 2))
            {
                index1 = i;
            } else {
                if (houseList.get(i).getHouseNo() > lotNumber && houseList.get(i).getHouseNo() > (lotNumber + 2)) {
                    index2 = i;
                    break;
                } else {
                    if (houseList.get(i).getHouseNo() == lotNumber) {
                        System.out.println("Error: Lot is not vacant");
                    } else {
                        System.out.println("Error: Not a valid house number for this block");
                    }
                    return -1;
                }
            } 
        }
        if (index2 != -1) {
            return index2;
        } 

        return index1 + 1;
    }

    /**
     * Function that puts infill in the houseList
     * @param lotNumber
     */
    public void putInfill(int lotNumber, Scanner input)
    {
        int index = insertInfill(lotNumber);
        // Check for available spaces with lotNumber and lotNumber + 2
        if (index == -1)
        {
            return;
        } 
        System.out.println("Enter basement volume for house1");
        String base1 = input.nextLine();
        System.out.println("Enter basement volume for house2");
        String base2 = input.nextLine();
        double radonLevel;
        if (index == 0)
        {
            radonLevel = houseList.get(index).getDailyRead();

        } else {
            if (index == houseList.size()) {
                radonLevel = houseList.get(index - 1).getDailyRead();
            } else {
                radonLevel = (houseList.get(index).getDailyRead() + houseList.get(index-1).getDailyRead())/2;
            }
        }
        House newhouse1 = new House(lotNumber, this.streetName, Double.parseDouble(base1), radonLevel);
        House newhouse2 = new House(lotNumber + 2, this.streetName, Double.parseDouble(base2), radonLevel);
        this.houseList.add(index, newhouse2);
        this.houseList.add(index, newhouse1);
    }

    /**
     * Print Remediation Report
     */
    public void printReport()
    {
        System.out.println("Printing Remediation Report");
        System.out.println("House Address\t\tLevel\t\tRating");
        for (House obj : houseList)
        {
            double yearlyLevel = obj.calcYearlyLevel();
            String yearlyLevelString = String.format("%.04f", yearlyLevel);
            System.out.println(obj.getHouseNo() + "  " + obj.getStreet() +
                "\t" + yearlyLevelString + "\t" + this.getRating(yearlyLevel));
        }
    }
}