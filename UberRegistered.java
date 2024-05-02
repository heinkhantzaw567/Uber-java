/*
 * Hein Khant Zaw
 * 501266416
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UberRegistered
{
    // These variables are used to generate user account and driver ids
    private static int firstUserAccountID = 900;
    private static int firstDriverId = 700;

    // Generate a new user account id
    public static String generateUserAccountId(ArrayList<User> current)
    {
        return "" + firstUserAccountID + current.size();
    }

    // Generate a new driver id
    public static String generateDriverId(ArrayList<Driver> current)
    {
        return "" + firstDriverId + current.size();
    }

    // Database of Preregistered users
    // In Assignment 2 these will be loaded from a file
    // The test scripts and test outputs included with the skeleton code use these
    // users and drivers below. You may want to work with these to test your code (i.e. check your output with the
    // sample output provided). 
    public static ArrayList loadPreregisteredUsers(String filename)throws FileNotFoundException 

    {
       
        File f  = new File(filename);
        if (!f.exists()) { // Check if the file exists
            throw new FileNotFoundException("User file: "+filename + " not found");
        }
        Scanner s = new Scanner(f);
        ArrayList <User> users = new ArrayList<>();
        while (s.hasNextLine())
        {
            String name =s.nextLine();
            String address=s.nextLine();
            String temp = s.nextLine();
            Double wallet = Double.parseDouble(temp);
            users.add(new User(generateUserAccountId(users),name, address,wallet));
        }
       return users;
    //     users.add(new User(generateUserAccountId(users), "McInerney, T.", "34 5th Street", 25));
    //     users.add(new User(generateUserAccountId(users), "Valenzano, R.", "71 1st Street", 55));
    // //     users.add(new User(generateUserAccountId(users), "Lugez, E.", "55 9th Avenue", 125));
    //     users.add(new User(generateUserAccountId(users), "Miranskyy, A.", "15 2nd Avenue", 15));
    //     users.add(new User(generateUserAccountId(users), "Raman, P.", "32 3rd Street", 13));
    //     users.add(new User(generateUserAccountId(users), "Woungang, I.", "64 6th Avenue", 27));
    //     users.add(new User(generateUserAccountId(users), "Soutchanski, M.", "28 7th Avenue", 22));
    //     users.add(new User(generateUserAccountId(users), "Harley, E.", "10 7th Avenue", 34));
    //     users.add(new User(generateUserAccountId(users), "Mason, D.", "48 8th Street", 11));
    //     users.add(new User(generateUserAccountId(users), "Santos, M.", "83 4th Street", 41));
     }

    // Database of Preregistered users
    // In Assignment 2 these will be loaded from a file
    public static ArrayList loadPreregisteredDrivers(String filename)throws FileNotFoundException
    {   
        File f  = new File(filename);
        if (!f.exists()) { // Check if the file exists
            throw new FileNotFoundException("User file: "+filename + " not found");
        }
        Scanner s = new Scanner(f);
        ArrayList <Driver> drivers = new ArrayList<>();
        while (s.hasNextLine())
        {
            String name =s.nextLine();
            String carmodel=s.nextLine();
            String licence = s.nextLine();
            String address = s.nextLine();
            drivers.add(new Driver(generateDriverId(drivers),name,carmodel,licence,CityMap.getCityZone(address),address));
        }
       return drivers;
        // drivers.add(new Driver(generateDriverId(drivers), "Tom Cruise", "Toyota Corolla", "MAVERICK"));
        // drivers.add(new Driver(generateDriverId(drivers), "Brad Pitt",  "Audi S4", "FGDR 983"));
        // drivers.add(new Driver(generateDriverId(drivers), "Millie Brown",  "Tesla", "STRNGRTHGS"));
        // drivers.add(new Driver(generateDriverId(drivers), "Tim Chalamet",  "Thopter", "DUNE"));
        // drivers.add(new Driver(generateDriverId(drivers), "John Boyega",  "X-Wing", "REBEL"));
    }
}

