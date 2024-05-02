
public class UberRide extends UberService
{
  private int numPassengers;
  private boolean requestedXL;
  
  public static final String TYPENAME = "RIDE";
  
  // Constructor to initialize all inherited and new instance variables 
  public UberRide( String from, String to, User user, int distance, double cost)
  {
    // Call the superclass constructor with specified parameters to initialize a TMUberRide object.
    // Set the number of passengers to 1 and requestedXL to false.
    super(from, to, user, distance, cost, "RIDE");
    numPassengers = 1;
    requestedXL = false;

    // Fill in the code - make use of the super method
  }
  
  public String getServiceType()
  {
    return TYPENAME;
  }

  public int getNumPassengers()
  {
    return numPassengers;
  }

  public void setNumPassengers(int numPassengers)
  {
    this.numPassengers = numPassengers;
  }

  public boolean isRequestedXL()
  {
    return requestedXL;
  }

  public void setRequestedXL(boolean requestedXL)
  {
    this.requestedXL = requestedXL;
  }
}
