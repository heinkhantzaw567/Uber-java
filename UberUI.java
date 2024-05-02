
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;



public class UberUI
{
  public static void main(String[] args)
  {
    

    UberSystemManager uber = new TMUberSystemManager();
    
    Scanner scanner = new Scanner(System.in);
    System.out.print(">");

    // Process keyboard actions
    while (scanner.hasNextLine())

    {
      try{
      String action = scanner.nextLine();

      if (action == null || action.equals("")) 
      {
        System.out.print("\n>");
        continue;
      }
      // Quit the App
      else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
        return;
      // Print all the registered drivers
      else if (action.equalsIgnoreCase("DRIVERS"))  // List all drivers
      {
        uber.listAllDrivers(); 
      }
      // Print all the registered users
      else if (action.equalsIgnoreCase("USERS"))  // List all users
      {
        uber.listAllUsers(); 
      }
      // Print all current ride requests or delivery requests
      else if (action.equalsIgnoreCase("REQUESTS"))  // List all requests
      {
        uber.listAllServiceRequests(); 
      }
      // Register a new driver
      else if (action.equalsIgnoreCase("REGDRIVER")) 
      {
        String name = "";
        System.out.print("Name: ");
        if (scanner.hasNextLine())
        {
          name = scanner.nextLine();
        }
        String carModel = "";
        System.out.print("Car Model: ");
        if (scanner.hasNextLine())
        {
          carModel = scanner.nextLine();
        }
        String license = "";
        System.out.print("Car License: ");
        if (scanner.hasNextLine())
        {
          license = scanner.nextLine();
        }
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          license = scanner.nextLine();
        }
        uber.registerNewDriver(name, carModel, license,address);
      }
      // Register a new user
      else if (action.equalsIgnoreCase("REGUSER")) 
      {
        String name = "";
        System.out.print("Name: ");
        if (scanner.hasNextLine())
        {
          name = scanner.nextLine();
        }
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        double wallet = 0.0;
        System.out.print("Wallet: ");
        if (scanner.hasNextDouble())
        {
          wallet = scanner.nextDouble();
          scanner.nextLine(); // consume nl!! Only needed when mixing strings and int/double
        }
        uber.registerNewUser(name, address, wallet);
      }
      // Request a ride
      else if (action.equalsIgnoreCase("REQRIDE")) 
      {
        
        // Prompt the user for the necessary details to request a ride:
        System.out.print("User Account ID: ");
        String Id = scanner.nextLine();

        System.out.print("From Address: ");
        String From = scanner.nextLine();

        System.out.print("To Address: ");
        String To = scanner.nextLine();

        // Attempt to request a ride using the provided information
        uber.requestRide(Id, From, To);
            // If unsuccessful, print the error message obtained from 'tmuber'
            
       
      }
      // Request a food delivery
      else if (action.equalsIgnoreCase("REQDLVY")) 
      {
        // Prompt for User Account Id
      System.out.print("User Account Id: ");
      String id = scanner.nextLine();

      // Prompt for From Address
      System.out.print("From Address: ");
      String from = scanner.nextLine();

      // Prompt for To Address
      System.out.print("To Address: ");
      String to = scanner.nextLine();

      // Prompt for Restaurant
      System.out.print("Restaurant: ");
      String restaurant = scanner.nextLine();

      // Prompt for Food Order #
      System.out.print("Food Order #: ");
      String foodorderid = scanner.nextLine();
      

      // Attempt to process the delivery request using the provided information
      uber.requestDelivery(id, from, to, restaurant, foodorderid);
      
      
      }
      // Sort users by name
      else if (action.equalsIgnoreCase("SORTBYNAME")) 
      {
        uber.sortByUserName();
      }
      // Sort users by number of ride they have had
      else if (action.equalsIgnoreCase("SORTBYWALLET")) 
      {
        uber.sortByWallet();
      }
      // Sort current service requests (ride or delivery) by distance
      else if (action.equalsIgnoreCase("SORTBYDIST")) 
      {
        // tmuber.sortByDistance();
      }
      // Cancel a current service (ride or delivery) request
      else if (action.equalsIgnoreCase("CANCELREQ")) 
      {
        int request = -1;
        System.out.print("Request #: ");
        if (scanner.hasNextInt())
        {
          request = scanner.nextInt();
          scanner.nextLine(); // consume nl character
        }
        int zone = -1;
        System.out.print("Zone #: ");
        if (scanner.hasNextInt())
        {
          zone = scanner.nextInt();
          scanner.nextLine(); // consume nl character
        }
        
        uber.cancelServiceRequest(request,zone);
        System.out.println("Cancel successfully");
         
      }
      // Drop-off the user or the food delivery to the destination address
      else if (action.equalsIgnoreCase("DROPOFF")) 
      {
        String driverID = "";
        System.out.print("DriverID: ");
        if (scanner.hasNextInt())
        {
          driverID = scanner.next();
          scanner.nextLine(); // consume nl
        }
        uber.dropOff(driverID);
      }
      // Get the Current Total Revenues
      else if (action.equalsIgnoreCase("REVENUES")) 
      {
        System.out.println("Total Revenue: " + uber.totalRevenue);
      }
      // Unit Test of Valid City Address 
      else if (action.equalsIgnoreCase("ADDR")) 
      {
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        System.out.print(address);
        if (CityMap.validAddress(address))
          System.out.println("\nValid Address"); 
        else
          System.out.println("\nBad Address"); 
      }
      // Unit Test of CityMap Distance Method
      else if (action.equalsIgnoreCase("DIST")) 
      {
        String from = "";
        System.out.print("From: ");
        if (scanner.hasNextLine())
        {
          from = scanner.nextLine();
        }
        String to = "";
        System.out.print("To: ");
        if (scanner.hasNextLine())
        {
          to = scanner.nextLine();
        }
        System.out.print("\nFrom: " + from + " To: " + to);
        System.out.println("\nDistance: " + CityMap.getDistance(from, to) + " City Blocks");
      }
      else if (action.equalsIgnoreCase("Zone")) 
      {
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        System.out.println(address);
        System.out.println(CityMap.getCityZone(address));
      }
      
      else if (action.equalsIgnoreCase("PICKUP")) 
      {
        String driverID = "";
        System.out.print("Driver ID: ");
        if (scanner.hasNextLine())
        {
          driverID = scanner.nextLine();
        }
        uber.pickup(driverID);
      }
      else if (action.equalsIgnoreCase("LOADUSERS")) 
      {
        String filename = "";
        System.out.print("User File: ");
        if(scanner.hasNextLine())
        {
          filename = scanner.nextLine();
        }
        uber.setUser(UberRegistered.loadPreregisteredUsers(filename));
        
      }
      else if (action.equalsIgnoreCase("LOADDRIVERS")) 
      {
        String filename = "";
        System.out.print("Driver File: ");
        if(scanner.hasNextLine())
        {
          filename = scanner.nextLine();
        }
        uber.setDrivers(UberRegistered.loadPreregisteredDrivers(filename));
      }
      else if (action.equalsIgnoreCase("DRIVETO")) 
      {
        String driverid = "";
        System.out.print("Driver ID: ");
        if(scanner.hasNextLine())
        {
          driverid = scanner.nextLine();
        }
        String address = "";
        System.out.print("Address: ");
        if(scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        uber.driveto(driverid,address);


      }
    
    }
    
    catch(Exception e)
    {
      System.out.println(e.getMessage());
    }
    System.out.print("\n>");
    }


  }
}

