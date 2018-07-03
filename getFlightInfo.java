
package pa2_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/* 
 * getFlightInfo.java 
 * 
 * Version: 
 *     $1.0$ 
 * 
 * Revisions: 
 *     $initial$ 
 *     
 * This program implements a function to get flight information. The user enters the flight name
 * and gets all the information regarding that flight.
 *
 * @author	Vaibhavi Awghate
 */
public class getFlightInfo {

    /**
     * This function lets the user to enter flight name and returns all the information 
     * regarding that flight.
     * @param name  flight name entered by user
     * @return  rs  Arraylist containing all the information about that flight
     */
    public ArrayList getFlightInfo(String name){
        // username and password for database
        String username = "test";
        String password = "test";
        ArrayList rs = new ArrayList();
        try{
            //query to get flight information for entered flight name from database
            String sql = "select * from a2_db where CAST(flight_name as VARCHAR(128)) = '" + name +" '";
            //code to connect to database and execute query
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/a2_database", username, password);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(sql);
            //adding result to arraylist
            while(result.next()){
                rs.add(result.getInt("flight_id"));
                rs.add(result.getString("flight_name"));
                rs.add(result.getString("src"));
                rs.add(result.getString("destination"));
                rs.add(result.getInt("price"));
            }
        con.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return rs;
    }
}
