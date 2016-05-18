/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.items;

import java.util.List;

/**
 *
 * @author shrutisingala
 */
public class Trigger {
    
    
   public List<AlertMessage> triggerParser(){

        //FileOutputStream out = null;

      

           // out = new FileOutputStream("/Users/shrutisingala/Desktop/output.csv");

            RSSFeedParser parser = new RSSFeedParser("http://www.gdacs.org/xml/rss.xml");
            Alerts alert = parser.readAlert();
            
           
            //System.out.println(alert);
            String publish_time = alert.alertTime.toString();

           // String test;
           /* test = alert.toString();
            int c, i = 0;

            while ((i != test.length())) {
                c = test.charAt(i);
                out.write(c);
                ++i;
            }

            for (AlertMessage message : alert.getMessages()) {
                //System.out.println(message);

                test = message.toString();
                i = 0;

                while ((i != test.length())) {
                    c = test.charAt(i);
                    out.write(c);
                    ++i;
                }
            }
*/
            SaveFile sf = new SaveFile("http://www.gdacs.org/xml/rss.xml", publish_time);
            //trying out git

        return (List<AlertMessage>) alert;

    }
}
