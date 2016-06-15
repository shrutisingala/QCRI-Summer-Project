package org.qcri.aidr.alerts.sql;

//heyyaa
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.qcri.aidr.alerts.items.*;

public class DBManager {

//import mysql-connector-java-5.1.32.jar 
//http://www.tutorialspoint.com/jdbc/jdbc-create-tables.html 
    final private static String JDBC_URL = "jdbc:mysql://localhost:3306/alerts";
    final private static String USER = "root";
    //final private static String PASSWORD = "shruti";
    final private static String PASSWORD = "salvivado123";

    public static void createmaster_alerts(int id, String type, String time, String severityunit, float severityvalue, String populationunit, int populationvalue, float latitude, float longitude, String calculationtype, String country) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Class.forName(JDBC_DRIVER);
            System.out.println("ENTERED TRY BLOCK");
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("connection made");
            preparedStatement = connection.prepareStatement("INSERT INTO master_alerts VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            System.out.println("insert statement");
            preparedStatement.setInt(1, id);
            System.out.println("set1");
            preparedStatement.setString(2, type);
            System.out.println("set2");
            preparedStatement.setString(3, time);
            System.out.println("set3");
            preparedStatement.setString(4, severityunit);
            System.out.println("set4");
            preparedStatement.setFloat(5, severityvalue);
            System.out.println("set5");
            preparedStatement.setString(6, populationunit);
            System.out.println("set6");
            preparedStatement.setInt(7, populationvalue);
            System.out.println("set7");
            preparedStatement.setFloat(8, latitude);
            System.out.println("set8");
            preparedStatement.setFloat(9, longitude);
            System.out.println("set9");
            preparedStatement.setString(10, calculationtype);
            System.out.println("set10");
            preparedStatement.setString(11, country);
            System.out.println("set11");
            boolean b = preparedStatement.execute();

            System.out.println("preparedstatement executed");

            if (b == true) {
                System.out.println("1 record inserted...");
            }
        } catch (SQLException sqlEx) {

            sqlEx.printStackTrace();
            System.out.println("hey there");
            System.exit(1);
        } finally {
            try {
                System.out.println("BEFORE ENTERING SIG CHECKER");
                SignificanceChecker(type, populationvalue, severityvalue);
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("its not WORKING!");
                System.exit(1);
            }
        }
        System.out.println("OUTSIDE FINALLY BLOCK");
        //SignificanceChecker(type, populationvalue, severityvalue);
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
    public static void readmasteralert() {

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
    
    
    public static void readsignificantalert() {

        Connection conn = null;
        Statement stmt = null;
        try {

            System.out.println("Connecting to a selected database to read SIGNIFICANT alert...");
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM significant_alerts";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String des = rs.getString("description");
                

                System.out.println("significantalert_id     description");
                //Display values
                System.out.print("\n" + id);
                System.out.print("        " + des);
               
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
    }
    
    

    public static void readtime(AlertMessage message) {
        // static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        // static final String JDBC_URL = "jdbc:mysql://localhost/STUDENTS";
        System.out.println(message);
        //  Database credentials
        // static final String USER = "username";
        //static final String PASS = "password";
        Connection conn = null;
        Statement stmt = null;

        try {
            //String t = null;

            //STEP 2: Register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");
            System.out.println("IN READ TIME");
            //STEP 3: Open a connection
            //System.out.println("Connecting to a selected database IN READ TIME...");
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            //System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT time FROM master_alerts";
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                System.out.println("CREATING FIRST ALERT");
                createmaster_alerts(message.getAlertID(), message.getAlertType(), message.getAlertTime(), message.getAlertSeverityUnit(), message.getAlertSeverityValue(), message.getAlertPopulationUnit(), message.getAlertPopulationValue(), message.getAlertPointLat(), message.getAlertPointLong(), message.getAlertCalculationType(), message.getAlertCountry());
                return;
            }
            int flag = 0;
            System.out.println("before while loop");

            do {

                System.out.println("ENTERED WHILE LOOOOP");
                //rs.next();
                String t = rs.getString("time");
                System.out.println("TIME: " + t);
                //rs.next();
                // rs.close();

                if (t.equals(message.getAlertTime())) {
                    System.out.println(message.getAlertTime());
                    System.out.println(t);
                    //System.out.println("CREATING!");
                    System.out.println("ALREADY EXISTING");
                    flag = 0;

                    //createmaster_alerts(message.getAlertID(), message.getAlertType(), message.getAlertTime(), message.getAlertSeverityUnit(), message.getAlertSeverityValue(), message.getAlertPopulationUnit(), message.getAlertPopulationValue(), message.getAlertPointLat(), message.getAlertPointLong(), message.getAlertCalculationType(), message.getAlertCountry());
                    return;
                } else {
                    flag = 1;
                    //System.out.println("ALERT REPEATING");
                    continue;
                }
            } while (rs.next());

            if (flag == 1) {
                System.out.println("SHRUTI");
                createmaster_alerts(message.getAlertID(), message.getAlertType(), message.getAlertTime(), message.getAlertSeverityUnit(), message.getAlertSeverityValue(), message.getAlertPopulationUnit(), message.getAlertPopulationValue(), message.getAlertPointLat(), message.getAlertPointLong(), message.getAlertCalculationType(), message.getAlertCountry());
                return;
            }

            System.out.println("after while loop");
            rs.close();

            /* if(t==message.getAlertTime())
             {System.out.println("going back to for loop");
             return;}*/
            //rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println("SQL EXC");
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("EXCEPTION");
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
            }

        }

        System.out.println("Goodbye!");

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

    /**
     *
     * @param id
     * @param description
     */
    public static void createsignificant_alerts(int id, String description) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // System.out.println("ENTERED MASTER ALERTS");
        //checking
        try {
            System.out.println("ENTERED SIGNIFICANT ALERTS");
            // Class.forName(JDBC_DRIVER);
            System.out.println("ENTERED SIGNIFICANT ALERTS TRY BLOCK");
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("connection made");
            preparedStatement = connection.prepareStatement("INSERT INTO significant_alerts VALUES(?,?)");
            System.out.println("insert statement");
            preparedStatement.setInt(1, id);
            System.out.println("set1");
            preparedStatement.setString(2, description);
            System.out.println("set2");

            boolean b = preparedStatement.execute();
            System.out.println("preparedstatement executed");
            if (b == true) {
                System.out.println("1 record inserted...");
            }
        } catch (SQLException sqlEx) {

            sqlEx.printStackTrace();
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

    public static void SignificanceChecker(String type, int pop_value, float sev_value) {

        Connection conn = null;
        Statement stmt = null;

        try {
            System.out.println("IN SIGNIFICANT ALERTS CHECKER");
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            stmt = conn.createStatement();

            String sql = "SELECT id FROM master_alerts";
            ResultSet rs = stmt.executeQuery(sql);

            rs.last();

            int id = rs.getInt("id");
            System.out.println(id);
            if ("EQ".equals(type)) {
                System.out.println("ENTERED IF");
                EQSignificanceChecker EQ = new EQSignificanceChecker(id, pop_value, sev_value);
                if (EQ.Rule1()) {
                    createsignificant_alerts(id, "pop btwn 10k & 50k with mag>=5");
                } else if (EQ.Rule2()) {
                    createsignificant_alerts(id, "pop btwn 50k & 100k with mag>=4.5");
                } else if (EQ.Rule3()) {
                    createsignificant_alerts(id, "pop>100k with mag>=4");
                }

            }
            rs.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println("SQL EXC");
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("EXCEPTION");
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
            }

        }

        System.out.println("Goodbye!");

    }

}
