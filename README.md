# Flight-Booking-System
Developed and hosted a web service to get flight information.
Designed a database in DB2 to store the flight information.
Implemented coordination handling among various stages of web application.

HOW TO RUN THIS APPLICATION

Creating a web application (server):

> Create a web application and give appropriate name. Create a package in this project.
> Create a java class in this package i.e. getFlightInfo. This class queries database for the required    information.
> Create another java class in this package i.e. bookAFlight. This class book a seat in flight.
> Create a web service and add both the operations to this web service.

Creating a Java application (client): 

> Create a java application and give appropriate name. Create a package in this project.
> Right click on project name, click on new -> Web Service Client.
> To link to server, a WSDL needs to be specified. Select "project" from given options and click on "Browse".    It will display the name server and select the web service created on the server side.
> This will appear in web services references. Drag the two funtionalities into java class. This will    automatically create the code.
> Call these functions in the main function.
> Run the client.

Implementing Coordination handler:

> To implement, create message handlers for both client and server side. Configure them.  

Creating a database:

> On the services tab, right click on Java DB -> Create Database. Give appropriate database name,    username and password.
> A JDBC coonection link will appear. Click on plus sign -> {username} -> Tables.
> Right click on Tables, click on create Table..
> Give appropriate table name and add columns.
> The table name will appear inside "tables" section. Right click on table name, click on view data.
> SQL editor will open and write insert query to enter data in the database. Now the database is populated    and is all ready to query.

Establishing the database connection to server:

> Right click on server project name -> Other -> Glassfish -> JDBC Connection Pool.
> In JDBC Connection Pool, specify the name of connection pool.
> Right click on server project name -> Other -> Glassfish -> JDBC Connection Resource.
> In JDBC Connection Resource, select the name of connection pool created in the previous step. Assign name to data resource. This would be same everytime you choose to connect to this database. Click Finish.
> A jar file called derbyclient.jar, needs to be included in classpath.
> Deploy the server.
