package StringandArrayPrograms;
import java.sql.*;
import java.util.*;
	public class OlaApplication 
	{
	    private String userName;
	    private String userLocation;
	    private String destination;
	    private String rideType;
	    private String driverName;
	    private String vehicleNumber;
	    private double fare;
	    private double distance;
	    private static Map<String, String> userDatabase = new HashMap<>();
	    private static List<String> rideHistory = new ArrayList<>();
	    private static boolean loggedIn = false;
	    
	    public void register(String username, String password) 
	    {
	        if (userDatabase.containsKey(username)) 
	        {
	            System.out.println("User already exists!");
	        } else 
	        {
	            userDatabase.put(username, password);
	            System.out.println("Registration successful!");
	        }
	        
	    }

	    public String getUserName() 
	    {
			return userName;
		}

		public String getUserLocation() 
		{
			return userLocation;
		}

		public String getDestination() 
		{
			return destination;
		}

		public String getRideType() 
		{
			return rideType;
		}

		public String getDriverName() 
		{
			return driverName;
		}

		public String getVehicleNumber() 
		{
			return vehicleNumber;
		}

		public double getFare() 
		{
			return fare;
		}

		public double getDistance() 
		{
			return distance;
		}

		public static Map<String, String> getUserDatabase() 
		{
			return userDatabase;
		}

		public static List<String> getRideHistory() 
		{
			return rideHistory;
		}

		public static boolean isLoggedIn() 
		{
			return loggedIn;
		}
		public void login(String username, String password) 
		{
	        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
	            loggedIn = true;
	            userName = username;
	            System.out.println("Login successful!");
	        } else 
	        {
	            System.out.println("Invalid username or password!");
	        }
	    }

	    
	    public void availableCabs() 
	    {
	        if (!loggedIn) 
	        {
	            System.out.println("Please log in to view available cabs.");
	            return;
	        }
	        System.out.println("Available cabs:");
	        System.out.println("1. Mini");
	        System.out.println("2. Micro");
	        System.out.println("3. Prime");
	        System.out.println("--------");
	    }

	   
	    public void bookRide(String location, String destination, String rideType, double distance) 
	    {
	        if (!loggedIn) 
	        {
	            System.out.println("Please log in to book a ride.");
	            return;
	        }
	        this.userLocation = location;
	        this.destination = destination;
	        this.rideType = rideType;
	        this.distance = distance;
	        this.driverName = "Vijay"; 
	        this.vehicleNumber = "5708"; 
	        this.fare = calculateFare(distance); 
	        rideHistory.add("Ride from " + userLocation + " to " + destination + " by " + driverName + " (" + rideType + ") - " + distance + " km - Rs." + fare);
	        System.out.println("Ride booked successfully!");
	    }

	    
	    private double calculateFare(double distance) 
	    {
	        double baseFare = 5.0; // Base fare
	        double costPerKm = 20; // Cost per kilometer
	        return baseFare + (costPerKm * distance);
	    }

	    public void showRideHistory() 
	    {
	        if (!loggedIn) 
	        {
	            System.out.println("Please log in to view ride history.");
	            return;
	        }
	        System.out.println("Ride History:");
	        for (String ride : rideHistory) 
	        {
	            System.out.println(ride);
	        }
	    }

	    
	    public void makePayment() 
	    {
	        if (!loggedIn) 
	        {
	            System.out.println("Please log in to make a payment.");
	            return;
	        }
	        System.out.println("Payment of Rs." + fare + " successful for ride from " + userLocation + " to " + destination);
	        clearRideDetails();
	    }

	   
	    private void clearRideDetails() 
	    {
	        userLocation = null;
	        destination = null;
	        rideType = null;
	        driverName = null;
	        vehicleNumber = null;
	        fare = 0;
	        distance = 0;
	    }

	    
	    public void exit() 
	    {
	        System.out.println("Exiting the application. Goodbye!");
	        System.exit(0);
	    }

	   
	    public static void main(String[] args) 
	    {
	        OlaApplication app = new OlaApplication();
	        Scanner scanner = new Scanner(System.in);
	        
	        while (true) 
	        {
	            System.out.print("\n Ola Application ");
	            System.out.println("\n1. Register");
	            System.out.println("2. Login");
	            System.out.println("3. Available Cabs");
	            System.out.println("4. Book Ride");
	            System.out.println("5. Make Payment");
	            System.out.println("6. Ride History");
	            System.out.println("7. Exit");
	            System.out.print("Choose an option: ");
	            
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 
	            
	            switch (choice) 
	            {
	                case 1:
	                    System.out.print("Enter username: ");
	                    String username = scanner.nextLine();
	                    System.out.print("Enter password: ");
	                    String password = scanner.nextLine();
	                    app.register(username, password);
	                    break;
	                case 2:
	                    System.out.print("Enter username: ");
	                    String loginUser = scanner.nextLine();
	                    System.out.print("Enter password: ");
	                    String loginPass = scanner.nextLine();
	                    app.login(loginUser, loginPass);
	                    break;
	                case 3:
	                    app.availableCabs();
	                    break;
	                case 4:
	                    System.out.print("Enter pick-up location: ");
	                    String location = scanner.nextLine();
	                    System.out.print("Enter destination: ");
	                    String destination = scanner.nextLine();
	                    System.out.print("Enter ride type: ");
	                    String rideType = scanner.nextLine();
	                    System.out.print("Enter distance in kilometers: ");
	                    double distance = scanner.nextDouble();
	                    app.bookRide(location, destination, rideType, distance);
	                    break;
	                case 6:
	                    app.showRideHistory();
	                    break;
	                case 5:
	                    app.makePayment();
	                    break;
	                case 7:
	                    app.exit();
	                    break;
	                default:
	                    System.out.println("Invalid choice! Please try again.");
	            }
	        }
	    }
	}
