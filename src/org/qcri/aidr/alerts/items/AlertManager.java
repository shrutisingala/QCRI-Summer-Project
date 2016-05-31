/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.items;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.qcri.aidr.alerts.*;
import java.util.List;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.qcri.aidr.alerts.items.AlertMessage;
import org.qcri.aidr.alerts.sql.DBManager;
import static org.qcri.aidr.alerts.sql.DBManager.createmaster_alerts;
import static org.qcri.aidr.alerts.sql.DBManager.deletealert;
import static org.qcri.aidr.alerts.sql.DBManager.readalert;
import static org.qcri.aidr.alerts.sql.DBManager.readtime;

/**
 *
 * @author shrutisingala
 */
public class AlertManager {

    public static void getAlerts() throws FileNotFoundException {

        String lastAlertTime = finder();
        String thisAlertTime = null;
        //triggerig the parser
        RSSFeedParser parser = new RSSFeedParser("http://www.gdacs.org/xml/rss.xml");
        Alerts alert = parser.readAlert();
        //saving the file in the proper directory
        String publish_time = alert.alertTime.toString();
        for (AlertMessage message : alert.getMessages()) {
            thisAlertTime = message.alertTime;
            break;
        }
        SaveFile sf = new SaveFile("http://www.gdacs.org/xml/rss.xml", publish_time, thisAlertTime);

        //check if the xml has been updated..
        //if it has call persistAlerts(alert)
        if (!thisAlertTime.equals(lastAlertTime)) {
            System.out.println("persisting alerts");
            persistAlerts(alert);
        }

        //persistAlerts(alert);
    }

    public static void persistAlerts(Alerts alert) {
        //checking mechanism for new alerts only
        //if new alert is found
        
 
        for (AlertMessage message : alert.getMessages()) {
          
            //System.out.println(message);
            
            //readtime();
            
          // if (message.getAlertTime()==DBManager.gettime())

            createmaster_alerts(message.getAlertID(), message.getAlertType(), message.getAlertTime(), message.getAlertSeverity(), message.getAlertPopulation());
        }

    }

    public static String finder() throws FileNotFoundException {
        
        String content = new Scanner(new File("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\QCRI-Summer-Project\\XML Files\\Latest.txt")).useDelimiter("\\Z").next();
        System.out.println(content);
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
                    
                    System.out.println("Beep!");
                    //numWarningBeeps++;
                    //} else {
                    getAlerts();
                    readalert();
                    readtime();
                    
                    
                   
        
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
     */
    public static void main(String args[]) throws FileNotFoundException {

        //put the 5 minute timer here.
        //getAlerts();
        fiveMinuteScheduler run = new fiveMinuteScheduler();
        
        //readalert();
        
        



        readalert();


        deletealert(0);
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
