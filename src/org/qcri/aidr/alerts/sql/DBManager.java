package org.qcri.aidr.alerts.sql;


import java.sql.*;
import java.io.*;
import java.lang.*;

public class DBManager {

//import mysql-connector-java-5.1.32.jar 
//http://www.tutorialspoint.com/jdbc/jdbc-create-tables.htm 
    final private static String JDBC_URL = "jdbc:mysql://localhost:3306/mudra";
    final private static String USER = "root";
    final private static String PASSWORD = "shruti";

    public static void createmaster_alerts(int alert_id, String alert_type, String alert_time, String alert_severity, String alert_population) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO master_alerts VALUES(?,?,?,?,?)");
            preparedStatement.setFloat(1, alert_id);
            preparedStatement.setString(2, alert_type);
            preparedStatement.setString(3, alert_time);
            preparedStatement.setString(4, alert_severity);
            preparedStatement.setString(5, alert_population);
            boolean b = preparedStatement.execute();
            if (b == true) {
                System.out.println("1 record inserted...");
            }
        } catch (SQLException sqlEx) {
            // sqlEx.printStackTrace();
            System.out.println("hey there");
            System.exit(1);
        } /*catch (ClassNotFoundException clsNotFoundEx) {
                    clsNotFoundEx.printStackTrace();
                    System.exit(1);
             }*/ finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                System.exit(1);
            }
        }
    }

    public static void updatetype(int alert_id, String alert_type) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE master_alerts SET alert_type=? WHERE alert_id=?");
            preparedStatement.setString(1, alert_type);
            preparedStatement.setInt(2, alert_id);
            boolean b = preparedStatement.execute();
            if (b == true) {
                System.out.println("alert type changed for alert id " + alert_id);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            System.exit(1);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                System.exit(1);
            }
        }
    }

    public static void readalert() {
        Connection connection = null;
        Statement statement = null;
        try {
            // Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, USER,
                    PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM master_alerts");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int noCols = metaData.getColumnCount();
            for (int i = 1; i <= noCols; i++) {
                if (i != 3) {
                    System.out.printf("%-10s\t", metaData.getColumnName(i).toUpperCase());
                }
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= noCols; i++) {
                    if (i != 3) {
                        System.out.printf("%-10s\t", resultSet.getObject(i));
                    }
                }
                System.out.println();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            System.exit(1);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                System.exit(1);
            }
        }
    }

    public static void deletealert(int alert_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM master_alerts WHERE alert_id=?");
            preparedStatement.setFloat(1, alert_id);
            boolean b = preparedStatement.execute();
            if (b == true) {
                System.out.println("1 record deleted...");
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            System.exit(1);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                System.exit(1);
            }
        }
    }


    /*public static void main(String[] args) throws IOException { 
    
             //createmaster_alerts(1, "abc","hello", "hi", "New Jersey");
             //createmaster_alerts(2, "Jones", "hshdd", "djbjd", "Jersey");
             //createmaster_alerts(3, "Kapil","ejdgdi","jdhjdh", "Los Angeles");
             updatetype(1,"pqr");
             updatetype(2,"heyyaa");
             deletealert(2);
             readalert();
             createmaster_alerts(2, "Jones", "hshdd", "djbjd", "Jersey");
             readalert();

        
    
    /*Connection myConnection = null; 
    Statement statement = null; 
    PreparedStatement preparedStatement = null; */

    /*try{ 
    //CONNECTING TO THE DATABASE 
    myConnection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD); 


System.out.println("Connected to database"); //CONNECTED 

//CREATING TABLE IN THE DATABASE	
System.out.println("Creating table in given database..."); 
statement = myConnection.createStatement(); 

String sql = "CREATE TABLE master_alerts " + 
"(alert_id FLOAT not NULL, " + 
" alert_type VARCHAR(50), " + 
" alert_time VARCHAR(50), " + 
" alert_severity VARCHAR(50), " + 
" alert_population VARCHAR(50))"; 


statement.executeUpdate(sql); 
System.out.println("Created table in given database..."); 
//statement.close(); //closing statement 


//CREATING A PREPARED STATEMENT 
String insertTableSQL = "INSERT INTO master_alerts" 
+ "(alert_id, alert_type, alert_time, alert_severity, alert_population) VALUES" 
+ "(?,?,?,?, ?)"; 


//preparedStatement = myConnection.prepareStatement(insertTableSQL); 

//RETRIEVING INFORMATION FROM CSV FILE 

//opening a file input stream 
//BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\example\\csv.CSV")); 

//String line = null; //line read from csv 
//Scanner scanner = null; //scanned line 

//SimpleDateFormat date = new SimpleDateFormat("mm/DD/yyyy"); 

//reader.readLine(); //omits the first line 



//READING FILE LINE BY LINE AND UPLOADING INFORMATION TO DATABASE 
while((line = reader.readLine()) != null){ 
scanner = new Scanner(line); 
scanner.useDelimiter(","); 
while(scanner.hasNext()){ 
preparedStatement.setFloat(1,Float.parseFloat(scanner.next())); 
preparedStatement.setString(2, scanner.next()); 
preparedStatement.setString(3, scanner.next()); 
preparedStatement.setString(4, scanner.next());
preparedStatement.setString(5, scanner.next()); 
try { 
java.util.Date d; 
d = date.parse(scanner.next()); 
preparedStatement.setDate(6, new java.sql.Date(d.getTime())); 
} catch (ParseException e) { 
e.printStackTrace(); 
}	

}
//preparedStatement.executeUpdate(); 
//} 

preparedStatement.close(); 
System.out.println("Data imported"); 

//reader.close(); //closing CSV reader 

} catch (SQLException e){ 
System.out.println("sorryy");
e.printStackTrace();

}
catch (NumberFormatException ne){ 
System.out.println("errrror");
}

finally{//CLOSING CONNECTION 
try{ 
if(statement!=null) 
myConnection.close(); 
}catch(SQLException se){ 
    System.out.println("SQLException!!!");
}
try{ 
if(myConnection!=null) 
myConnection.close(); 
}catch(SQLException se){ 
} 
System.out.println("Connection closed"); 
} */
} 

        
//}
