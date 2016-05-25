/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.items;

import org.qcri.aidr.alerts.*;
import java.util.List;
import org.qcri.aidr.alerts.items.AlertMessage;
import static org.qcri.aidr.alerts.sql.DBManager.createmaster_alerts;
import static org.qcri.aidr.alerts.sql.DBManager.deletealert;
import static org.qcri.aidr.alerts.sql.DBManager.readalert;

/**
 *
 * @author shrutisingala
 */
public class AlertManager {

    public static void getAlerts() {

        RSSFeedParser parser = new RSSFeedParser("http://www.gdacs.org/xml/rss.xml");
        Alerts alert = parser.readAlert();

        String publish_time = alert.alertTime.toString();
        SaveFile sf = new SaveFile("http://www.gdacs.org/xml/rss.xml", publish_time);

        //check if the xml has been updated..(use publish_time)
        //if it has call persistAlerts(alert)
        persistAlerts(alert);

    }

    public static void persistAlerts(Alerts alert) {
        //checking mechanism for new alerts only
        //if new alert is found
        for (AlertMessage message : alert.getMessages()) {
            //System.out.println(message);

            createmaster_alerts(message.getAlertID(), message.getAlertType(), message.getAlertTime(), message.getAlertSeverity(), message.getAlertPopulation());
        }

    }
//yoooooooo
    /**
     *
     * @param args
     */
    public static void main(String args[]) {

        //put the 5 minute timer here.
        getAlerts();
        //deletealert(1086489);
        //deletealert(1086512);


        readalert();


        //String publish_time = ListOfAlerts.toString();
        //System.out.println(publish_time);
    }

}
