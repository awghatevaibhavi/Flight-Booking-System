
package pa2_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
 * PA2_Client.java 
 * 
 * Version: 
 *     $1.0$ 
 * 
 * Revisions: 
 *     $initial$ 
 *     
 * This program is a client side java application that performs two rounds of web service invocation.
 * First round: the client firstplacesan order. This request will be rejected by the coordination handler
 * at the servicesideas the invocation order violates the coordination protocol.
 * Second round: the client first performs a query, and then placesanorderusing the information returned 
 * by the query. The coordination handler will accept the requestsas this conforms to the protocol and the
 * client will get the response from the service.
 *
 * @author	Vaibhavi Awghate
 */
public class PA2_Client {
    //variables needed to keep track of sequence
    static String client_id = "C1";
    static String operation_no = "";
    public static void main(String[] args) throws IOException {
        // client invoking web services
        ArrayList res = new ArrayList();
        while (true){
            try{
                System.out.println("1. Get flight information");
                System.out.println("2. Book a flight");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int input = Integer.parseInt(br.readLine());
                //invoking first operation of web service
                if (input == 1){
                    operation_no = "1";
                    System.out.println("Enter the flight name");
                    String val = br.readLine();
                    
                    res = (ArrayList) getFlightInfo(val);
                    System.out.println("Flight ID: " +res.get(0));
                    System.out.println("Flight Name: " +res.get(1));
                    System.out.println("From: " +res.get(2));
                    System.out.println("To: " +res.get(3));
                    System.out.println("Cost: " +res.get(4));
                }
                //invoking second operation of web service
                else if(input == 2){
                    operation_no = "2";
                    System.out.println("Booking a seat");
                    int id = (int)res.get(0);
                    System.out.println(bookAFlight(id));
                }
                else
                    System.out.println("Please enter a valid choice");
                System.out.println("Do you want to continue?(Y/N)");
                String response = br.readLine();
                if(response.equals("Y"))
                    continue;
                else
                    System.exit(0);
        
            }
            //error thrown if second operation is invoked first
            catch(RuntimeException e){
                System.out.println("Get flight information first");
            }
        }
    }
    // call to bookAFlight operation of web service
    private static String bookAFlight(int flightId) {
        pa2_server.FlightBookingSystem_Service service = new pa2_server.FlightBookingSystem_Service();
        pa2_server.FlightBookingSystem port = service.getFlightBookingSystemPort();
        return port.bookAFlight(flightId);
    }
    // call to getFlightInfo operation of web service
    private static java.util.List<java.lang.Object> getFlightInfo(java.lang.String flightName) {
        pa2_server.FlightBookingSystem_Service service = new pa2_server.FlightBookingSystem_Service();
        pa2_server.FlightBookingSystem port = service.getFlightBookingSystemPort();
        return port.getFlightInfo(flightName);
    }
    
}
