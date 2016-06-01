package org.qcri.aidr.alerts.sql;

//heyyaa
import java.sql.*;


public class DBManager {

//import mysql-connector-java-5.1.32.jar 
//http://www.tutorialspoint.com/jdbc/jdbc-create-tables.html 
    final private static String JDBC_URL = "jdbc:mysql://localhost:3306/alerts";
    final private static String USER = "root";

    final private static String PASSWORD = "salvivado123";

    //final private static String PASSWORD = "salvivado123";
    
    
    public static void createmaster_alerts(int id, String type, String time, String severityunit, float severityvalue, String populationunit, int populationvalue, float latitude, float longitude, String calculationtype, String country) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
       // System.out.println("ENTERED MASTER ALERTS");

        //checking
        if (!check(time)) {
            return;
        }

        try {
            // Class.forName(JDBC_DRIVER);
            //System.out.println("ENTERED TRY BLOCK");
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO master_alerts VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, time);
            preparedStatement.setString(4, severityunit);
            preparedStatement.setFloat(5, severityvalue);
            preparedStatement.setString(6, populationunit);
            preparedStatement.setInt(7, populationvalue);
            preparedStatement.setFloat(8, latitude);
            preparedStatement.setFloat(9, longitude);
            preparedStatement.setString(10, calculationtype);
            preparedStatement.setString(11, country);
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
                System.out.println("its not WRKING!");
                System.exit(1);
            }
        }
        
        
    }

    /*public static void updatetype(int alert_id, String alert_type) {
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
    }*/
    public static void readalert() {

        Connection conn = null;
        Statement stmt = null;
        try {

            System.out.println("Connecting to a selected database to read alert...");
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM master_alerts";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String time = rs.getString("time");
                String severityunit = rs.getString("severityunit");
                Float severityvalue = rs.getFloat("severityvalue");
                String populationunit = rs.getString("populationunit");
                int populationvalue = rs.getInt("populationvalue");
                Float latitude = rs.getFloat("latitude");
                Float longitude = rs.getFloat("longitude");
                String calculationtype = rs.getString("calculationtype");
                String country = rs.getString("country");

                System.out.println("alert_id  alert_type       alert_time                             alert_severity                                 alert_population");
                //Display values
                System.out.print("\n" + id);
                System.out.print("        " + type);
                System.out.print("        " + time);
                System.out.print("        " + severityunit);
                System.out.print("        " + severityvalue);
                System.out.print("                 " + populationunit);
                System.out.print("                 " + populationvalue);
                System.out.print("                 " + latitude);
                System.out.print("                 " + longitude);
                System.out.print("                 " + calculationtype);
                System.out.print("                 " + country);

            }
            rs.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main

    public static String readtime() {
        // static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        // static final String JDBC_URL = "jdbc:mysql://localhost/STUDENTS";

        //  Database credentials
        // static final String USER = "username";
        //static final String PASS = "password";
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");
             System.out.println("IN READ TIME");
            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database IN READ TIME...");
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT time FROM master_alerts";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String t = rs.getString("time");
                System.out.println("TIME: " + t);

                return t;
            }
            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {

            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        System.out.println("Goodbye!");
        //return db.t;

        return "";
    }

    /*public static String gettime(String time) {
    return time;
     }*/
    public static boolean check(String time) {

        if (DBManager.readtime() == time) {
            return true;
        } else {
            return false;
        }

    }

    /*  public static void deletealert(int alert_id) {
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
}*/
}
