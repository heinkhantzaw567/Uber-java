Sure, here's a basic README file for your TMUberUI project:

---

# TMUberUI

TMUberUI is a simulation of a simple command-line based Uber-like application. It allows users to register as drivers or passengers, request rides or food deliveries, and manage service requests.

## Features

- **Registration**: Users can register as drivers or passengers by providing their details such as name, car model, license plate, address, and wallet balance.
- **Request Services**: Passengers can request rides or food deliveries by specifying their current location, destination, and additional details such as food order number and restaurant name.
- **Management**: Registered users can view all drivers, passengers, and current service requests. They can also cancel service requests or drop off passengers/food deliveries once completed.
- **Sorting**: Users and service requests can be sorted by name, wallet balance, or distance.
- **Unit Tests**: Includes unit tests for validating city addresses and calculating distances between two locations.

## Usage

1. Compile the `TMUberUI.java` file using a Java compiler.
2. Run the compiled Java program using the command `java TMUberUI`.
3. Follow the on-screen prompts to interact with the application.
4. Enter commands such as `DRIVERS`, `USERS`, `REQUESTS`, `REGDRIVER`, `REGUSER`, `REQRIDE`, `REQDLVY`, etc., to perform specific actions.

## Commands

- `DRIVERS`: List all registered drivers.
- `USERS`: List all registered users.
- `REQUESTS`: List all current service requests.
- `REGDRIVER`: Register a new driver.
- `REGUSER`: Register a new user.
- `REQRIDE`: Request a ride.
- `REQDLVY`: Request a food delivery.
- `SORTBYNAME`: Sort users by name.
- `SORTBYWALLET`: Sort users by wallet balance.
- `SORTBYDIST`: Sort current service requests by distance.
- `CANCELREQ`: Cancel a current service request.
- `DROPOFF`: Complete a service request.
- `REVENUES`: Display the total revenue.
- `ADDR`: Unit test for validating a city address.
- `DIST`: Unit test for calculating the distance between two locations.

## Contributing

Contributions are welcome! Feel free to submit issues or pull requests if you have any suggestions, bug fixes, or feature enhancements.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Feel free to adjust the content according to your project's specifics and requirements!