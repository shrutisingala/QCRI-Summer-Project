/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.logic;

import org.qcri.aidr.alerts.resources.AlertMessage;
import org.qcri.aidr.alerts.logic.EQSignificanceChecker;
import org.qcri.aidr.alerts.resources.Alerts;

/**
 *
 * @author shrutisingala
 */
public class SignificanceChecker {

    EQSignificanceChecker EQ = null;
    String[] arr = new String[2];

    public SignificanceChecker(AlertMessage message) {

        if (message.getAlertType().equalsIgnoreCase("EQ")) {
            EQ = new EQSignificanceChecker(message.getAlertPopulationValue(), message.getAlertSeverityValue());
            arr = EQ.setSignificance();
            message.setAlertSignificance(arr[0]);
            message.setAlertDescription(arr[1]);
        }

    }
}
