/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.items;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shrutisingala
 */
public class SaveFile {

    public SaveFile(String link, String publish_time, String firstAlertTime) {

        try {

            System.out.println("in savefile");
            File file = new File("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\QCRI-Summer-Project\\XML Files\\tempXML.xml");
            //File file = new File("/Users/shrutisingala/NetBeansProjects/QCRI-Summer-Project/XML Files/tempXML.xml");

            if (file.createNewFile()) {
                System.out.println("Success!");
            } else {
                System.out.println("Error, file already exists.");
            }
        } catch (IOException ex) {
            Logger.getLogger(filewrite.class.getName()).log(Level.SEVERE, null, ex);
        }

        int i, c;

        try {
            URL webpage;
            webpage = new URL(link);
            URLConnection yc = webpage.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            BufferedWriter out = null;
            out = new BufferedWriter(new FileWriter("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\QCRI-Summer-Project\\XML Files\\tempXML.xml"));
            //out = new BufferedWriter(new FileWriter("/Users/shrutisingala/NetBeansProjects/QCRI-Summer-Project/XML Files/tempXML.xml"));
            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine);
                for (i = 0; i < inputLine.length(); i++) {
                    c = inputLine.charAt(i);
                    out.write(c);
                }
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String timestamp = convertStringToDate(publish_time);

        // File (or directory) with old name
        File file = new File("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\QCRI-Summer-Project\\XML Files\\tempXML.xml");
        //File file = new File("/Users/shrutisingala/NetBeansProjects/QCRI-Summer-Project/XML Files/tempXML.xml");
        
        // File (or directory) with new name
        File file2 = new File("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\QCRI-Summer-Project\\XML Files\\" + timestamp + ".xml");
        //File file2 = new File("/Users/shrutisingala/NetBeansProjects/QCRI-Summer-Project/XML Files/" + timestamp + ".xml");

        if (file2.exists()) {
            System.out.println("new file already exists");
        }

        // Rename file (or directory)
        boolean success = file.renameTo(file2);

        if (!success) {
            System.out.println("File was not successfully renamed");
        }

        try {
            //File Latest = new File("/Users/shrutisingala/NetBeansProjects/QCRI-Summer-Project/XML Files/Latest.txt");
            PrintWriter Latest = new PrintWriter("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\QCRI-Summer-Project\\XML Files\\Latest.txt");
            //PrintWriter Latest = new PrintWriter("/Users/shrutisingala/NetBeansProjects/QCRI-Summer-Project/XML Files/Latest.txt");
            Latest.println(firstAlertTime);
            Latest.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        

    }

    private static class filewrite {

        public filewrite() {
        }
    }

    public String convertStringToDate(String dateString) {
        Date date = null;

        System.out.println(dateString);
        DateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss zzz");

        //dateString = "Thu, 1 Jan 1970 00:01:00 GMT";
        System.out.println(dateString);
        try {
            date = df.parse(dateString);

            System.out.println(date);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        long stamp = date.getTime() / 1000;
        String time = String.valueOf(stamp);
        System.out.println(time);
        return time;
    }

}
