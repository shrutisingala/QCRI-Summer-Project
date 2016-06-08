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
public class EQSignificanceChecker {
    
    int pop;
    float mag;
    
    public EQSignificanceChecker(int p, int m)
    {
        pop=p;
        mag=m;
    }

    public void Rule1() {
        if (pop >= 10000 && pop <= 50000) {
            if (mag >= 5) {
                //write to significant table
                //with description
            }
        }
    }

    public void Rule2() {
        if (pop > 50000 && pop <= 100000) {
            if (mag >= 4.5) {
                //write to significant table
                //with description
            }
        }
    }
    
    public void Rule3() {
        if (pop > 100000) {
            if (mag >= 4) {
                //write to significant table
                //with description
            }
        }
    }
}
