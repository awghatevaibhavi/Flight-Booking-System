
package pa2_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/* 
 * bookAFlight.java 
 * 
 * Version: 
 *     $1.0$ 
 * 
 * Revisions: 
 *     $initial$ 
 *     
 * This program implements a function to book flight. The user enters the flight id
 * and checks if the entered flight id is in the database. If the flight is in database
 * then it returns the string "Flight booked succesfully", else it returns "Please enter
 * a valid flight".
 *
 * @author	Vaibhavi Awghate
 */
public class bookAFlight {
    /**
     * This function books a flight seat if its id is present in database.
     * @param   id  flight id entered by user
     * @return  res String "Flight booked succesfully" or "Please enter a valid flight"
     */
    public String bookAFlight(int id){
        String res = null;
        // username and password for database
        String username = "test";
        String password = "test";
        try{
            //query to get all flight ids from database
            String sql = "select flight_id from a2_db";
            //code to connect to database and execute query
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/a2_database", username, password);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(sql);
            //checking if the entered id is present in database
            while(result.next()){
                if (id == (int)(result.getInt("flight_id"))){
                    res = "Flight booked succesfully";
                    break;
                }
                else{
                    res = "Please enter a valid flight";
                }
            }
        con.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return res;
    }
    
}
