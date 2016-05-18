/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.items;


/**
 *
 * @author shrutisingala
 */

//import java.util.Date;


public class AlertMessage {
    
    int alertID;
    String alertType;
    String alertTime;
    String alertSeverity;
    String alertPopulation;

  public int getAlertID() {
    return alertID;
  }

  public void setAlertID(int alertID) {
    this.alertID = alertID;
  }

  public String getAlertType() {
    return alertType;
  }

  public void setAlertType(String alertType) {
    this.alertType = alertType;
  }

  public String getAlertTime() {
    return alertTime;
  }

  public void setAlertTime(String alertTime) {
    this.alertTime = alertTime;
  }
  
  public String getAlertSeverity() {
    return alertSeverity;
  }

  public void setAlertSeverity(String alertSeverity) {
    this.alertSeverity = alertSeverity;
  }
  
  public String getAlertPopulation() {
    return alertPopulation;
  }

  public void setAlertPopulation(String alertPopulation) {
    this.alertPopulation = alertPopulation;
  }

  @Override
  public String toString() {
    
  
    return "\n\"" + alertID + "\", \"" + alertType + "\", \"" + alertTime + "\", \"" + alertSeverity + "\", \"" + alertPopulation + "\"";
  
  }
}
