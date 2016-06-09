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
import org.qcri.aidr.alerts.sql.DBManager;

import static org.qcri.aidr.alerts.sql.DBManager.createmaster_alerts;
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
        RSSFeedParser parser = new RSSFeedParser("http://www.gdacs.org/xml/rss.xml");
        Alerts alert = parser.readAlert();
        String publish_time = alert.alertTime;
        for (AlertMessage message : alert.getMessages()) {
            thisAlertTime = message.alertTime;
            break;
        }
        SaveFile sf = new SaveFile("http://www.gdacs.org/xml/rss.xml", publish_time, thisAlertTime);

        if (!lastAlertTime.equals(thisAlertTime)) {
           //persistAlerts(alert);
        }
        persistAlerts(alert);
    }

    public static void persistAlerts(Alerts alert) {
        //System.out.println("MUDRA");
        for (AlertMessage message : alert.getMessages()) {
            System.out.println("in persist, before readtime");
             readtime(message);  
             System.out.println("in persist, after readtime");
            //createmaster_alerts(message.getAlertID(), message.getAlertType(), message.getAlertTime(), message.getAlertSeverityUnit(), message.getAlertSeverityValue(), message.getAlertPopulationUnit(), message.getAlertPopulationValue(), message.getAlertPointLat(), message.getAlertPointLong(), message.getAlertCalculationType(), message.getAlertCountry());
        
        }
    }

    public static String finder() throws FileNotFoundException {
        String content = new Scanner(new File("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\QCRI-Summer-Project\\XML Files\\Latest.txt")).useDelimiter("\\Z").next();
        //String content = new Scanner(new File("/Users/shrutisingala/NetBeansProjects/QCRI-Summer-Project/XML Files/Latest.txt")).useDelimiter("\\Z").next();
        return content;
    }

    public static class fiveMinuteScheduler {

        Timer timer;

        public fiveMinuteScheduler() {
            timer = new Timer();
            timer.schedule(new RemindTask(),
                    0, //initial delay
                    1 * 1000 * 60 * 5);  //subsequent rate
        }

        class RemindTask extends TimerTask {

            public void run() {
                try {
                    getAlerts();
                    readalert();
                    System.out.println("Time's up! Run over.");

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
        
       // DBManager db;

        fiveMinuteScheduler run = new fiveMinuteScheduler();
    }
}
