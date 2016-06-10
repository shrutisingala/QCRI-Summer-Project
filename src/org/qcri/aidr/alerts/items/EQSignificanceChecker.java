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
    
    public EQSignificanceChecker(int i, int p, float m)
    {
        id=i;
        pop=p;
        mag=m;
    }

    public void Rule1() {
        if (pop >= 10000 && pop <= 50000) {
            if (mag >= 5) {
                createsignificant_alerts(id, "pop btwn 10k & 50k with mag>=5");
            }
        }
    }

    public void Rule2() {
        if (pop > 50000 && pop <= 100000) {
            if (mag >= 4.5) {
                createsignificant_alerts(id, "pop btwn 50k & 100k with mag>=4.5");
            }
        }
    }
    
    public void Rule3() {
        if (pop > 100000) {
            if (mag >= 4) {
                createsignificant_alerts(id, "pop>100k with mag>=4");
            }
        }
    }

    
}
