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
  
     int alertID;
     String alertType;
     String alertTime;
     String alertSeverity;
     String alertPopulation;

  final List<AlertMessage> entries = new ArrayList<AlertMessage>();

  public Alerts(int alertID, String alertType, String alertTime, String alertSeverity, String alertPopulation) {
    this.alertID = alertID;
    this.alertType = alertType;
    this.alertTime = alertTime;
    this.alertSeverity = alertSeverity;
    this.alertPopulation = alertPopulation;
  }

  public List<AlertMessage> getMessages() {
    return entries;
  }

  public int getAlertID() {
    return alertID;
  }
  
  public String getAlertType() {
    return alertType;
  }
  
  public String getAlertTime() {
    return alertTime;
  }
  
  public String getAlertSeverity() {
    return alertSeverity;
  }
  
  public String getAlertPopulation() {
    return alertPopulation;
  }

  @Override
  public String toString() {
    
      /*return "\nID=\"" + alertID + "\", Type=\"" + alertType
        + "\", Time=\"" + alertTime + "\", Severity=\"" + alertSeverity + "\", Population=\"" + alertPopulation + "\"";
            */
  
    
    return "\n\"" + alertID + "\", \"" + alertType + "\", \"" + alertTime + "\", \"" + alertSeverity + "\", \"" + alertPopulation + "\"";
  }

}




