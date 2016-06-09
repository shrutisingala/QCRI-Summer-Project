/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.items;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shrutisingala
 */
public class Alerts {

    final List<AlertMessage> entries = new ArrayList<AlertMessage>();

    public List<AlertMessage> getMessages() {
        //System.out.println(entries);
        return entries;
    }
}
