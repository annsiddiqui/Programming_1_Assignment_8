/**
 * House class that is used in the analysis of Radon levels to determine the relative safety of the houses.
 * @ author Qurrat-al-Ain Siddiqui
 */



public class House
{
    // instance variables
    private int houseNo; //house number field
    private String streetName; // street name field
    private double basementVol; // basement volume field
    private double dailyReading; // daily Radon level field

    /**
     * Parameterized constructor that sets values to all fields
     */
    public House(int no, String st, double vol, double daily)
    {
        this.houseNo = no;
        this.streetName = st;
        this.basementVol = vol;
        this.dailyReading = daily;
    }

    /**
     * Processing method for calculating the yearly Radon level
     */
    public double calcYearlyLevel()
    {
        final int DAYS_IN_YEARS = 365;
        return this.dailyReading * this.basementVol * DAYS_IN_YEARS;
    }

    /**
     * Accessor to the house basement volume
     */
    public double getBaseVol()
    {
        return this.basementVol;
    }

    /**
     * Accessor to the daily Radon level field 
     */
    public double getDailyRead()
    {
        return this.dailyReading;
    }

    /**
     * Accessor to the house number field
     */
    public int getHouseNo()
    {
        return this.houseNo;
    }

    /**
     * Accessor to the street name field
     */
    public String getStreet()
    {
        return this.streetName;
    }

    /**
     * Mutator to the basement volume field 
     */
    public void setBaseVol(double vol)
    {
        this.basementVol = vol;
    }

    /**
     * Mutator to the daily Radon level field
     */
    public void setDailyRead(double daily)
    {
        this.dailyReading = daily;
    }

    /**
     * Mutator to the house number field
     */
    public void setHouseNo(int no)
    {
        this.houseNo = no;
    }

    /**
     * Mutator to the street name field
     */
    public void setStreet(String str)
    {
        this.streetName = str;
    }

    /**
     * Print the values of all data members
     */
    public String toString()
    {
        String dailyreading = String.format("%.04f", this.dailyReading);
        return "House Number\t:  " + this.houseNo + "\n" +
        "Street\t\t:  " + this.streetName+ "\n" +
        "Basement Volume\t:  " + this.basementVol + "\n" +
        "Daily Reading\t:  " + dailyreading + "\n" +
               "Radon Level\t:  " + this.calcYearlyLevel();

    }
}
