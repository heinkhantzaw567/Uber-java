/*
 * Hein Khant Zaw
 * 501266416
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;


import java.util.Iterator;


/*
 * 
 * This class contains the main logic of the system.
 * 
 *  It keeps track of all users, drivers and service requests (RIDE or DELIVERY)
 * 
 */
public class UberSystemManager
{
  private Map<String, User>users;
  private Queue []zones ;
  private Queue<UberService> zone0;
  private Queue<UberService> zone1;
  private Queue<UberService> zone2;
  private Queue<UberService> zone3;
  private ArrayList<Driver>   drivers;


  public double totalRevenue; // Total revenues accumulated via rides and deliveries
  
  // Rates per city block
  private static final double DELIVERYRATE = 1.2;
  private static final double RIDERATE = 1.5;
  // Portion of a ride/delivery cost paid to the driver
  private static final double PAYRATE = 0.1;

  //These variables are used to generate user account and driver ids
  int userAccountId = 900;
  int driverId = 700;

  public TMUberSystemManager()
  {
    users   = new TreeMap <>();
    drivers = new ArrayList<Driver>();
    zone0 = new LinkedList<UberService> (); 
    zone1 = new LinkedList<UberService> (); 
    zone2 = new LinkedList<UberService> (); 
    zone3 = new LinkedList<UberService> (); 
    zones = new Queue[4];
    
    zones[0]=zone0;
    zones[1]=zone1;
    zones[2]=zone2;
    zones[3]=zone3;
    

    
    
    totalRevenue = 0;
  }

  // General string variable used to store an error message when something is invalid 
  // (e.g. user does not exist, invalid address etc.)  
  // The methods below will set this errMsg string and then return false
  String errMsg = null;

  public String getErrorMessage()
  {
    return errMsg;
  }
  
  public void setUser(ArrayList<User> userList)
{
  for(int i=0; i<userList.size();i++)
  {
    users.put(userList.get(i).getAccountId(), userList.get(i));
  }
  System.out.println("Users Loaded");
}
public void setDrivers(ArrayList<Driver> drivers)
{
  this.drivers = drivers;
  System.out.println("Drivers Loaded");
}
  // Given user account id, find user in list of users
  // Return null if not found
  public User getUser(String accountId) {
    // Check if the map contains the provided account ID as a key.
    if (users.containsKey(accountId)) {
        // If a matching user is found, return it.
        return users.get(accountId);
    }
    // If no matching user is found, return null.
    return null;
}

  
  public Driver getDriver(String accountId)
  {
    // Iterate through the list of users.
    for (int i = 0; i < drivers.size(); i++) {
      // Check if the account ID of the current user matches the provided account ID.
      if (drivers.get(i).getId().equals(accountId)) {
          // If a matching user is found, return it.
          return drivers.get(i);
      }
    }
    // If no matching user is found, return null.
    return null;
  }
  public static void removeElement(Queue<UberService> zone, UberService d )
  {
    for (UberService element : zone) {
      if (element.equals(d) ) {
          // Remove the target element
          zone.remove(element);
          break; // Break the loop once the element is removed
      }
  }
  
  }
  

  // Check for duplicate user
  private boolean userExists(User user)
  {
    if (user ==null)
    {
      return false;
    }
    // Return true if the list of users contains the provided user.
    for (Map.Entry<String, User> entry : users.entrySet()) {
      if (entry.getValue().equals(user)) 
      return true;
    }
    return false;
    // Fill in the code

  }
  // public int servicerequest(Driver a)

  // {
  //   for(int i=0;i<serviceRequests.size();i++)
  //   {
  //     if(serviceRequests.get(i).getDriver().equals(a))
  //     {
  //       return i;
  //     }
  //   }
  //   return -1;
  // }
  
 // Check for duplicate driver
 private boolean driverExists(Driver driver)
 {
  if (driver ==null)
  {
    return false;
  }
    // Check if the provided driver exists in the list of drivers.
    for(int i=0; i<drivers.size();i++)
    {
      if(drivers.get(i).equals(driver))
      return true;
    }
    return false;
   // Fill in the code
   
 }
  
  // Given a user, check if user ride/delivery request already exists in service requests
  private boolean existingRequest(UberService req, Queue<UberService> zone)
  {
    for (UberService element : zone) {
      if (element.equals(req) ) {
          
          
          return true;
      }
  }
  return false;
    
    // Fill in the code
  }

  // Calculate the cost of a ride or of a delivery based on distance 
  private double getDeliveryCost(int distance)
  {
    return distance * DELIVERYRATE;
  }

  private double getRideCost(int distance)
  {
    return distance * RIDERATE;
  }


  private Driver getAvailableDriver()

  {
    // Iterate through the list of drivers.
    for (int i = 0; i < drivers.size(); i++) {
      // Check if the status of the current driver is AVAILABLE.
      if (drivers.get(i).getStatus() == Driver.Status.AVAILABLE) {
          // If an available driver is found, return it.
          return drivers.get(i);
      }
    }
    // If no available driver is found, return null.
    return null;

  }

  // Print Information (printInfo()) about all registered users in the system
  public void listAllUsers() {
    // Print a newline to separate previous output.
    System.out.println();

    // Iterate through the values of the map.
    int index = 1;
    for (User user : users.values()) {
        // Print index followed by user information.
        System.out.printf("%-2s.", index);
        user.printInfo();
        
        // Increment index for display.
        index++;
        
        // Print a newline for formatting.
        System.out.println(); 
    }
}


  // Print Information (printInfo()) about all registered drivers in the system
  public void listAllDrivers()
  {
    for (int i = 0; i < drivers.size(); i++)
    {
        // Increment index for display.
        int index = i + 1;
        
        // Print index followed by driver information.
        System.out.printf("%-2s. ", index);
        drivers.get(i).printInfo();
        
        // Print a newline for formatting.
        System.out.println();
    }
    // Fill in the code
  }
  public void printrequest(String zone, Queue<UberService> zone1) {
    System.out.println(zone);
    System.out.println("=====");

    int index = 1; // Initialize index

    // Iterate through the elements in the queue
    for (UberService service : zone1) {
        // Print index followed by element information
        System.out.printf("%-2d. ", index++);
        service.printInfo(); // Assuming printInfo() prints information to the console
        System.out.println(); // Print a new line after printing service info
    }
}


  // Print Information (printInfo()) about all current service requests
  public void listAllServiceRequests()
  {
    printrequest("ZONE 0", zone0);
    System.out.println();
    printrequest("ZONE 1", zone1);
    System.out.println();
    printrequest("ZONE 2", zone2);
    System.out.println();
    printrequest("ZONE 3", zone3);
    System.out.println();


    // Fill in the code
  }

  // Add a new user to the system
  public void registerNewUser(String name, String address, double wallet)
  {
    
    // Check if the name is null or empty.
    if (name == null || name.length() == 0)
    {
        // Set error message and return false if name is invalid.
        throw new InvalidnameException("Invalid User Name");
    }

    // Check if the address is valid using CityMap's validAddress method.
    if (!CityMap.validAddress(address))
    {
        // Set error message and return false if address is invalid.
        
        throw new InvalidAddressException("Invalid User Address");
    }

    // Check if wallet amount is negative.
    if (wallet < 0)
    {
        // Set error message and return false if wallet amount is invalid.
        
        throw new WalletInvalidException("Invalid Money in Wallet");
    }

    // Generate a unique user ID and create a new User object.
    String id = String.valueOf(userAccountId);
    User user = new User(id+""+users.size(), name, address, wallet);

    // Check if the user already exists.
    if (userExists(user)) {
        // Set error message and return false if the user already exists.
        
        throw new UserAlreadyExistException("User Already Exists in System");
    }
    // Add the new user to the list of users and return true.
    users.put(userAccountId+""+users.size(),user);
    
    System.out.printf("User: %-15s Address: %-15s Wallet: %2.2f", name, address, wallet);
    

    

    // Fill in the code. Before creating a new user, check paramters for validity
    // See the assignment document for list of possible erros that might apply
    // Write the code like (for example):
    // if (address is *not* valid)
    // {
    //    set errMsg string variable to "Invalid Address "
    //    return false
    // }
    // If all parameter checks pass then create and add new user to array list users
    // Make sure you check if this user doesn't already exist!
    
  }

  // Add a new driver to the system
  
  public void registerNewDriver(String name, String carModel, String carLicencePlate, String address) throws IllegalArgumentException {
    // Check if the name is null or empty.
    if (name == null || name.isEmpty()) {
        // Throw an exception if the name is invalid.
        throw new InvalidnameException("Invalid Driver Name");
    }

    // Check if the car model is null or empty.
    if (carModel == null || carModel.isEmpty()) {
        // Throw an exception if the car model is invalid.
        throw new CarModelInvalidException("Invalid Car Model");
    }

    // Check if the car license plate is null or empty.
    if (carLicencePlate == null || carLicencePlate.isEmpty()) {
        // Throw an exception if the car license plate is invalid.
        throw new CarLicencePlateException("Invalid Car Licence Plate");
    }

    // Check if the address is valid.
    if (!CityMap.validAddress(address)) {
        // Throw an exception if the address is invalid.
        throw new InvalidAddressException("Invalid Address");
    }

    // Generate a unique driver ID and create a new Driver object.
    String id = String.valueOf(driverId);
    Driver driver = new Driver(id + drivers.size(), name, carModel, carLicencePlate, CityMap.getCityZone(address), address);

    // Check if the driver already exists.
    if (driverExists(driver)) {
        // Throw an exception if the driver already exists.
        throw new DriverAlreadyExistException("Driver Already Exists in System");
    }

    // Add the new driver to the list of drivers.
    drivers.add(driver);
    System.out.printf("Driver: %-15s Car Model: %-15s License Plate: %-10s", name, carModel, carLicencePlate);

}

  // Request a ride. User wallet will be reduced when drop off happens
  public void requestRide(String accountId, String from, String to) {
    // Retrieve the user associated with the provided account ID.
    User user = getUser(accountId);

    // If user not found, return false with error message
    if (!userExists(user)) {
        errMsg = "User Account Not Found";
        throw new UserNotFoundException(errMsg);
    }

    // Check if addresses are valid
    if (!CityMap.validAddress(from) || !CityMap.validAddress(to)) {
        errMsg = "Invalid Address";
        throw new InvalidAddressException(errMsg);
    }

    // Calculate distance
    int distance = CityMap.getDistance(from, to);

    // Check if distance is sufficient
    if (distance < 1) {
        errMsg = "Insufficient Travel Distance";
        throw new InsufficentTravelDisException(errMsg);
    }
    double wallet = user.getWallet();
    double cost = getRideCost(distance);
    if (cost > wallet) {
        errMsg = "Insufficient Funds";
        throw new InsufficientmoneyException(errMsg);
    }

   

    
    // Create a new ride request
    UberRide rideRequest = new UberRide( from, to, user, distance, cost);
    int zone =CityMap.getCityZone(from);
    if (existingRequest(rideRequest, zones[zone])) {
      errMsg = "User Already Has Ride Request";
      throw new RequestAlredyExistException(errMsg);
  }
    if (zone ==0)
    {
      zone0.add(rideRequest);
    }
    if (zone ==1)
    {
      zone1.add(rideRequest);
    }
    if (zone ==2)
    {
      zone2.add(rideRequest);
    }
    if (zone ==3)
    {
      zone3.add(rideRequest);
    }
    // Add the ride request to the service requests list
    ;
    
    // Increment the number of rides the user has had
    user.addRide();
    System.out.printf("RIDE for: %-15s From: %-15s To: %-15s ",getUser(accountId).getName() , from, to);

    

  }


  // Request a food delivery. User wallet will be reduced when drop off happens
  public void requestDelivery(String accountId, String from, String to, String restaurant, String foodOrderId) {
    User user = getUser(accountId);

    // Search for the user with the given accountId
    

    // If user not found, return false with error message
    if (!userExists(user)) {
        errMsg = "User Account Not Found";
        throw new UserNotFoundException(errMsg);
    }

    // Check if addresses are valid
    if (!CityMap.validAddress(from) || !CityMap.validAddress(to)) {
        errMsg = "Invalid Address";
        throw new InvalidAddressException(errMsg);
    }

    // Calculate distance
    int distance = CityMap.getDistance(from, to);

    // Check if distance is sufficient
    if (distance < 1) {
        errMsg = "Insufficient Travel Distance";
        throw new InsufficentTravelDisException(errMsg);
    }

    // Check if there's already a delivery request with the same criteria
    double wallet = user.getWallet();
    double cost = getDeliveryCost(distance);
    if (cost > wallet) {
        errMsg = "Insufficient Funds";
        throw new InsufficientmoneyException(errMsg);
    }
    
    
    
    // Check user's wallet balance
    

    // Get an available driver
   

    // Update driver status
   

    // Create a new delivery request
    UberDelivery deliveryRequest = new UberDelivery( from, to, user, distance, cost, restaurant, foodOrderId);
    int zone = CityMap.getCityZone(from);
    if (existingRequest(deliveryRequest,zones[zone])) {
      errMsg = "User Already Has Delivery Request at Restaurant with this Food Order";
      throw new RequestAlredyExistException(errMsg);
  }
    // Add the delivery request to the service requests list
    if (zone ==0)
    {
      zone0.add(deliveryRequest);
    }
    if (zone ==1)
    {
      zone1.add(deliveryRequest);
    }
    if (zone ==2)
    {
      zone2.add(deliveryRequest);
    }
    if (zone ==3)
    {
      zone3.add(deliveryRequest);
    }
   


    // Increment the number of deliveries the user has had
    user.addDelivery();
    System.out.printf("RIDE for: %-15s From: %-15s To: %-15s ",getUser(accountId).getName() , from, to,restaurant,foodOrderId);
    
}




  // Cancel an existing service request. 
  // parameter int request is the index in the serviceRequests array list
  public void cancelServiceRequest(int request, int zone) {
    Queue<UberService> services =zones[zone];
    Iterator<UberService> iter = services.iterator();
    int num = 0;
    while(iter.hasNext()){
      iter.next();
      if (num == request - 1){
        iter.remove();
        return;
      }
      num++;
      
    }

    throw new RequestAlredyExistException("Invalid Request");

    

}

  
  // Drop off a ride or a delivery. This completes a service.
  // parameter request is the index in the serviceRequests array list
  public void dropOff(String driverID) {
    // Retrieve the driver object using the provided driver ID
    Driver driver = getDriver(driverID);
    
    // Check if the driver ID is valid
    if (driver == null) {
        errMsg = "Invalid ID";
        throw new DriverNotFoundException(errMsg);
    }
    if(driver.getStatus()!=Driver.Status.DRIVING)
    {
      throw new DriverNotFoundException("Driving is not working");
    }
    
    // Find the index of the service request associated with the driver
   
    UberService Service = driver.getService();
    // Check if the driver is currently handling a service request
    int zone = CityMap.getCityZone(Service.getTo());
    Queue removezone = zones[zone];
    removeElement(removezone, Service);
    // Process the service request by removing it from the list
   
    
    // Retrieve the cost of the service request
    double cost = Service.getCost();
    
    // Update the total revenue by adding the cost of the service
    totalRevenue += cost;

    // Calculate the fee to be paid to the driver
    double fee = cost * PAYRATE;
    
    // Pay the driver by deducting the fee from the total revenue
    driver.pay(fee);
    totalRevenue -= fee;
    
    // Set the driver's status to AVAILABLE
    driver.setStatus(Driver.Status.AVAILABLE);

    // Deduct the cost of the service from the user's account
    Service.getUser().payForService(cost);
    driver.setAddress(Service.getTo());
    driver.setzone(CityMap.getCityZone(Service.getTo()));
    System.out.println("Driver "+driverID +" Dropping Off");
    // Return true to indicate successful completion of the drop-off process
   
}
public void pickup(String driverid)
{
  Driver driver =getDriver(driverid);
  if (driver == null) {
    errMsg = "Invalid ID";
    throw new DriverNotFoundException(errMsg);
  }
  UberService service =null;
  String address = driver.getAddress();
  int zone =CityMap.getCityZone(address);
  if (zone ==0)
    {
      if (zone0.isEmpty())
      throw new RequestNotFoundException("No service request available for pickup in the zone.");
      else;
      service =zone0.poll();

      
    }
    if (zone ==1)
    {
      if (zone1 == null || zone1.isEmpty()) {
        throw new RequestNotFoundException("No service request available for pickup in the zone.");
       
    }
    else;
    service =zone1.poll();
    }
    if (zone ==2)
    {
      if (zone2 == null || zone2.isEmpty()) {
        throw new RequestNotFoundException("No service request available for pickup in the zone.");
      
    }
    else;
        service =zone2.poll();
   
    
    }
    if (zone ==3)
    {
      if (zone3 == null || zone3.isEmpty()) {
        throw new RequestNotFoundException("No service request available for pickup in the zone.");
    }
    else;
        service =zone3.poll();
   
      
    }

    driver.setService(service);
    driver.setStatus(Driver.Status.DRIVING);
    String from =service.getFrom();
    driver.setAddress(from);
    driver.setzone(zone);
    System.out.println("Driver "+driverid +" Picking Up in Zone "+zone);
}
public void driveto(String driverid, String address)
{
  Driver driver = getDriver(driverid);
  if (driver ==null)
  {
    throw new UserNotFoundException("Invalid ID");
  }
  if (driver.getStatus() !=Driver.Status.AVAILABLE)
  {
    throw new NotAvailableException("The driver is not available");
  }
  if(!CityMap.validAddress(address))
  {
    throw new InvalidAddressException("Invalid Address");
  }
  driver.setAddress(address);
  driver.setzone(CityMap.getCityZone(address));
  System.out.println("Driver "+driverid +" Now in Zone "+CityMap.getCityZone(address));
}
  // Sort users by name
  // Then list all users
public void sortByUserName() {
        // Convert the HashMap entries to a List
        List<Map.Entry<String, User>> userList = new ArrayList<>(users.entrySet());

        // Create a NameComparator instance
        NameComparator nameComparator = new NameComparator();

        // Sort the list of entries using the comparator
        Collections.sort(userList, nameComparator.com);

        // Reconstruct the HashMap with sorted entries
        HashMap<String, User> sortedUsers = new LinkedHashMap<>();
        for (Map.Entry<String, User> entry : userList) {
            sortedUsers.put(entry.getKey(), entry.getValue());
        }

        // Replace the original map with the sorted one
        users = sortedUsers;

        // List all users after sorting
        listAllUsers();
    }

    // Helper class for method sortByUserName
    private class NameComparator {
        // Define a comparator for sorting users by their names.
        Comparator<Map.Entry<String, User>> com = new Comparator<Map.Entry<String, User>>() {
            // Compare two map entries based on their key (username)
            public int compare(Map.Entry<String, User> entry1, Map.Entry<String, User> entry2) {
                // Compare usernames and return the result
                return entry1.getValue().getName().compareTo(entry2.getValue().getName());
            }
        };
    }

  // Sort users by number amount in wallet
  // Then ist all users
  public void sortByWallet()
  {
   // Create a UserWalletComparator instance
   List<Map.Entry<String, User>> userList = new ArrayList<>(users.entrySet());

   // Create a NameComparator instance
   WalletComparator walletComparator = new WalletComparator();

   // Sort the list of entries using the comparator
   Collections.sort(userList, walletComparator.com);

   // Reconstruct the HashMap with sorted entries
   HashMap<String, User> sortedUsers = new LinkedHashMap<>();
   for (Map.Entry<String, User> entry : userList) {
       sortedUsers.put(entry.getKey(), entry.getValue());
   }

   // Replace the original map with the sorted one
   users = sortedUsers;

   // List all users after sorting
   listAllUsers();
}

// Helper class for method sortByUserName
private class WalletComparator {
   // Define a comparator for sorting users by their names.
   Comparator<Map.Entry<String, User>> com = new Comparator<Map.Entry<String, User>>() {
       // Compare two map entries based on their key (username)
       public int compare(Map.Entry<String, User> entry1, Map.Entry<String, User> entry2) {
           // Compare usernames and return the result
           
          if (entry1.getValue().getWallet()>entry2.getValue().getWallet())
          return 1;
          else;
          return -1;
          
       }
   };
}

  public class DriverNotFoundException extends RuntimeException {
   
    public DriverNotFoundException(String message) {
        super(message);
    }
  
  }
  public class InsufficientmoneyException extends RuntimeException
  {
    public InsufficientmoneyException (String message)
    {
      super(message);
    }

  }
  public class DriverAlreadyExistException extends RuntimeException{
    public DriverAlreadyExistException(String message)
    {
      super(message);
    }
  }
  public class RequestAlredyExistException extends RuntimeException{
    public RequestAlredyExistException (String message)
    {
      super(message);
    }
  }
  public class InsufficentTravelDisException extends RuntimeException{
    public InsufficentTravelDisException(String message)
    {
      super(message);
    }
  }
  public class UserNotFoundException extends RuntimeException
  {
    public UserNotFoundException(String message)
    {
      super(message);
    }
  }
  public class InvalidAddressException extends RuntimeException
  {
    public InvalidAddressException(String message)
    {
      super(message);
    }
  }
  public class NameInvalidException extends RuntimeException {
    public NameInvalidException(String message) {
        super(message);
    }
}

public class WalletInvalidException extends RuntimeException {
    public WalletInvalidException(String message) {
        super(message);
    }
}

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}

public class CarModelInvalidException extends RuntimeException {
    public CarModelInvalidException(String message) {
        super(message);
    }
}

public class CarLicencePlateException extends RuntimeException {
    public CarLicencePlateException(String message) {
        super(message);
    }
}

public class NotAvailableException extends RuntimeException {
    public NotAvailableException(String message) {
        super(message);
    }
}
public class InvalidnameException extends RuntimeException{
  public InvalidnameException(String message)
  {
    super(message);
  }
}
public class InvalidrequestCancelException extends RuntimeException{
  public InvalidrequestCancelException(String message)
  {
    super(message);
  }
}
public class RequestNotFoundException extends RuntimeException{
  public RequestNotFoundException(String message)
  {
    super(message);
  }
}

}




