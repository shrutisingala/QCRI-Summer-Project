/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.items;

import static org.qcri.aidr.alerts.sql.DBManager.createsignificant_alerts;

/**
 *
 * @author shrutisingala
 */
public class EQSignificanceChecker {

    int id;
    int pop;
    float mag;

    public EQSignificanceChecker(int i, int p, float m) {
        id = i;
        pop = p;
        mag = m;
    }

    public boolean Rule1() {
        if (pop >= 10000 && pop <= 50000) {
            if (mag >= 5) {
                System.out.println("in rule 1");
                return true;
            }
        }
        return false;
    }

    public boolean Rule2() {
        if (pop > 50000 && pop <= 100000) {
            if (mag >= 4.5) {
                System.out.println("in rule 2");

                return true;
            }
        }
        return false;
    }

    public boolean Rule3() {
        if (pop > 100000) {
            if (mag >= 4) {
                System.out.println("in rule 3");
                return true;
            }
        }
        return false;
    }

}
