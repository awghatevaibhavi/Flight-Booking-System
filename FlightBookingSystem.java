/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa2_server;

import java.util.ArrayList;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Vaibhavi
 */
@WebService(serviceName = "FlightBookingSystem")
@HandlerChain(file = "FlightBookingSystem_handler.xml")
public class FlightBookingSystem {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getFlightInfo")
    public ArrayList getFlightInfo(@WebParam(name = "flight_name") String flight_name) {
        //TODO write your implementation code here:
        getFlightInfo info = new getFlightInfo();
        return info.getFlightInfo(flight_name);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "bookAFlight")
    public String bookAFlight(@WebParam(name = "flight_id") int flight_id) {
        //TODO write your implementation code here:
        bookAFlight book = new bookAFlight();
        return book.bookAFlight(flight_id);
    }

    /**
     * This is a sample web service operation
     */
    
}
