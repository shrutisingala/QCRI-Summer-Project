/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.items;

import org.qcri.aidr.alerts.*;
import java.util.List;

/**
 *
 * @author shrutisingala
 */
public class AlertManager {

    public static List<AlertMessage> getAlerts() {

        RSSFeedParser parser = new RSSFeedParser("http://www.gdacs.org/xml/rss.xml");
        Alerts alert = parser.readAlert();

        String publish_time = alert.alertTime.toString();
        SaveFile sf = new SaveFile("http://www.gdacs.org/xml/rss.xml", publish_time);

        return alert.entries;

    }

    public static void main(String args[]) {
        List<AlertMessage> ListOfAlerts = getAlerts();

        String publish_time = ListOfAlerts.toString();
        System.out.println(publish_time);
    }

}
