/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.logic;

import static org.qcri.aidr.alerts.database.DBManager.createSignificantAlert;

/**
 *
 * @author shrutisingala
 */
public class EQSignificanceChecker {

    int pop;
    float mag;

    public EQSignificanceChecker(int p, float m) {
        pop = p;
        mag = m;
    }

    public String[] setSignificance() {

        if (pop >= 1000 && pop < 10000) {
            if (mag < 5) {
                return new String[]{"green", "Pop b/w 1000 & 10000 with Mag<5"};
            } else if (mag >= 5) {
                return new String[]{"orange", "Pop b/w 1000 & 10000 with Mag>=5"};
            }
        }
        if (pop >= 10000 && pop < 50000) {
            if (mag <= 4) {
                return new String[]{"green", "Pop b/w 10000 and 50000 with Mag<=4"};
            } else if (mag > 4 && mag <= 6.5) {
                return new String[]{"orange", "Pop b/w 10000 and 50000 with Mag b/w 4 & 6.5"};
            } else if (mag > 6.5) {
                return new String[]{"red", "Pop b/w 10000 and 50000 with Mag>6.5"};
            }
        }
        if (pop >= 50000 && pop < 100000) {
            if (mag <= 3.5) {
                return new String[]{"green", "Pop b/w 50000 and 100000 with Mag<=3.5"};
            } else if (mag > 3.5 && mag <= 5) {
                return new String[]{"orange", "Pop b/w 50000 and 100000 with Mag b/w 3.5 & 5"};
            } else if (mag > 5) {
                return new String[]{"red", "Pop b/w 50000 and 100000 with Mag>5"};
            }
        }
        if (pop >= 100000) {
            if (mag >= 4 && mag < 6) {
                return new String[]{"orange", "Pop>100000 with Mag b/w 4 & 6"};
            } else if (mag >= 6) {
                return new String[]{"red", "Pop>100000 with Mag>6"};
            }
        }
        return new String[]{"blue", "Does not fit into significant criteria"};
    }

}
