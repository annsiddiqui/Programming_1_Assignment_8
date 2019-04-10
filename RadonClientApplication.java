/**
 * This is the Client Interface to the Radon Processing Application. It
 * presents a menu to the user, reads in any required values from the user,
 * and processes the choice that the user makes.
 * 
 * @ author Qurrat-al-Ain Siddiqui
 */

import java.util.*;

public class RadonClientApplication
{

    public void run()
    {
        final int PRINT_BLOCK_DATA = 1;
        final int PRINT_HOUSE_DETAILS = 2;
        final int DEMOLISH = 3;
        final int INFILL = 4;
        final int PRINT_REPORT = 5;
        final int EXIT = 6;

        Scanner input = new Scanner(System.in);
        RadonProcessor radon = new RadonProcessor();

        System.out.println("Welcome to the Radon Level Analysis Application");
        System.out.println("Enter the file to be loaded");
        String fileName = input.nextLine();
        if (!radon.loadData(fileName))
        {
            System.out.println("Error in loading data from file " + fileName);
            return;
        }

        boolean quit = false;
        while (!quit)
        {
            this.showMenu();
            int selection = input.nextInt();
            input.nextLine();
            switch (selection) {
                    case PRINT_BLOCK_DATA:
                    radon.printBlockData();
                    break;
                    case PRINT_HOUSE_DETAILS:
                    System.out.println("Enter the house number to be demolished:");
                    int houseNumber = input.nextInt();
                    input.nextLine();
                    radon.printHouseDetails(houseNumber);
                    break;
                    case DEMOLISH:
                    System.out.println("Enter the house number to be demolished:");
                    houseNumber = input.nextInt();
                    input.nextLine();
                    radon.demolishHouse(houseNumber);
                    break;
                    case INFILL:
                    System.out.println("Enter the lot to create an infill:");
                    int lotNumber = input.nextInt();
                    input.nextLine();
                    radon.putInfill(lotNumber,input);
                    break;
                    case PRINT_REPORT:
                    radon.printReport();
                    break;
                    case EXIT:
                    System.out.println("Enter the output file name:");
                    String outputFileName = input.nextLine();
                    radon.exit(outputFileName);
                    quit = true;
                    break;
                    default:
                    System.out.println("Illegal Choice: Please try again");
                    break;
            }
        }
    }

    /**
     * Displays the menu for the user.
     */
    private void showMenu()
    {
        System.out.println ("Radon Analysis Menu");
        System.out.println ("1 - Print Block data");
        System.out.println ("2 - Print the information about a house");
        System.out.println ("3 - Demolish a house");
        System.out.println ("4 - Build an infill");
        System.out.println ("5 - Print remediation report");
        System.out.println ("6 - Quit the application");
        System.out.println ("\nPlease make your choice");
    }

}