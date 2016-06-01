/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.items;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.qcri.aidr.alerts.items.AlertMessage;
import org.qcri.aidr.alerts.sql.DBManager;
import static org.qcri.aidr.alerts.sql.DBManager.createmaster_alerts;
//import static org.qcri.aidr.alerts.sql.DBManager.deletealert;

import static org.qcri.aidr.alerts.sql.DBManager.readalert;
import static org.qcri.aidr.alerts.sql.DBManager.readtime;

/**
 *
 * @author shrutisingala
 */
public class AlertManager {

    public static void getAlerts() throws FileNotFoundException {

        //System.out.println("entered getalerts");
        String lastAlertTime = finder();
        String thisAlertTime = null;
        //System.out.println("back to getalerts");
        
        //triggering the parser
        //System.out.println("triggering the parser");
        RSSFeedParser parser = new RSSFeedParser("http://www.gdacs.org/xml/rss.xml");
        //System.out.println("back to getalerts");
        Alerts alert = parser.readAlert();
        //saving the file in the proper directory
        String publish_time = alert.alertTime;
        for (AlertMessage message : alert.getMessages()) {
            thisAlertTime = message.alertTime;
            break;
        }
        SaveFile sf = new SaveFile("http://www.gdacs.org/xml/rss.xml", publish_time, thisAlertTime);

        //check if the xml has been updated..
        //if it has call persistAlerts(alert)
        if (!lastAlertTime.equals(thisAlertTime)) {
            //System.out.println("persisting alerts");
            //persistAlerts(alert);
        }

        persistAlerts(alert);
    }

    public static void persistAlerts(Alerts alert) {
        //checking mechanism for new alerts only
        //if new alert is found
        
        //System.out.println("in persistalerts");

        for (AlertMessage message : alert.getMessages()) {

            //System.out.println(message);
            //readtime();

            
          // if (message.getAlertTime()==DBManager.gettime())
          //System.out.println("creating the alert in the table");

            createmaster_alerts(message.getAlertID(), message.getAlertType(), message.getAlertTime(), message.getAlertSeverityUnit(), message.getAlertSeverityValue(), message.getAlertPopulationUnit(), message.getAlertPopulationValue(), message.getAlertPointLat(), message.getAlertPointLong(), message.getAlertCalculationType(), message.getAlertCountry() );

        }

    }

    public static String finder() throws FileNotFoundException {

        //System.out.println("entered finder");
        String content = new Scanner(new File("/Users/shrutisingala/NetBeansProjects/QCRI-Summer-Project/XML Files/Latest.txt")).useDelimiter("\\Z").next();
        //System.out.println(content);
        //System.out.println("that was the latest alert time");
        return content;
    }

    public static class fiveMinuteScheduler {

        Timer timer;

        public fiveMinuteScheduler() {
            timer = new Timer();
            timer.schedule(new RemindTask(),
            0, //initial delay
            1 * 1000 * 60 *5 );  //subsequent rate
        }

        class RemindTask extends TimerTask {

            //int numWarningBeeps = 0;
            public void run() {
                try {
                    //if (numWarningBeeps >= 0) {

                    //System.out.println("Beep! inside the scheduler");
                    //numWarningBeeps++;
                    //} else {
                    getAlerts();
                    //System.out.println("back to scheduler\nreading alerts");
                    readalert();
                    //readtime();

                    System.out.println("Time's up! Run over.");
                    //timer.cancel(); //Not necessary because we call System.exit
                    //System.exit(0);   //Stops the AWT thread (and everything else)
                    //}
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AlertManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    /**
     *
     * @param args
     * @throws java.io.FileNotFoundException
     */
    public static void main(String args[]) throws FileNotFoundException {

        //put the 5 minute timer here.
        //getAlerts();
        //System.out.println("in main");
        fiveMinuteScheduler run = new fiveMinuteScheduler();

        
        //readalert();


       // deletealert(0);

        //deletealert(1000262);

        /*  ResultSet rs=null;

        List list = new ArrayList();
        while (rs.next()) {
         list.add(rs.getString("alert_time"));
        }
        
        System.out.println(list);*/
        //String publish_time = ListOfAlerts.toString();
        //System.out.println(publish_time);
    }

}
