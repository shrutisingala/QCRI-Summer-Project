/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qcri.aidr.alerts.logic;

/**
 *
 * @author shrutisingala
 */
public class EQSignificanceChecker {

    int pop;
    float mag;
    String cou;
    String[] UD;
    String[] dev;
    String[] DEV;

    public EQSignificanceChecker(int p, float m, String c) {
        pop = p;
        mag = m;
        cou = c;
        String[] UD = {"Myanmar", "Burma", "Libya", "Lebanon", "Haiti", "Guinea", "Angola", "Chad", "Bosnia", "Mongolia", "Nepal", "Russia"};
        String[] dev = {"India", "Pakistan", "Jordan", "Srilanka", "Malaysia", "Thailand", "South Africa", "Brazil", "China"};
        String[] DEV = {"Hong Kong", "Singapore", "UAE", "Japan", "Qatar", "Switzerland", "Germany", "France", "United Kingdom"};

    }

    public String[] setSignificance() {

        for (String UD1 : UD) {
            if (cou.equals(UD1)) {
                if (pop >= 1000 && pop < 10000) {
                    if (mag >= 4 && mag < 6) {
                        return new String[]{"green", "Pop b/w 10000 and 50000 with Mag<=4"};

                    } else if (mag >= 6) {
                        return new String[]{"orange", "Pop b/w 10000 and 50000 with Mag<=4"};

                    }
                }
                if (pop >= 10000 && pop < 50000) {
                    if (mag >= 4 && mag < 5) {
                        return new String[]{"green", "Pop b/w 10000 and 50000 with Mag<=4"};
                    } else if (mag >= 5 && mag < 6.5) {
                        return new String[]{"orange", "Pop b/w 10000 and 50000 with Mag b/w 4 & 6.5"};
                    } else if (mag >= 6.5) {
                        return new String[]{"red", "Pop b/w 10000 and 50000 with Mag>6.5"};
                    }
                }
                if (pop >= 50000 && pop < 100000) {
                    if (mag >= 3.5 && mag < 5) {
                        return new String[]{"green", "Pop b/w 50000 and 100000 with Mag<=3.5"};
                    } else if (mag >= 5 && mag < 6) {
                        return new String[]{"orange", "Pop b/w 50000 and 100000 with Mag b/w 3.5 & 5"};
                    } else if (mag >= 6) {
                        return new String[]{"red", "Pop b/w 50000 and 100000 with Mag>5"};
                    }
                }
                if (pop >= 100000) {
                    if (mag >= 3.5 && mag < 5) {
                        return new String[]{"orange", "Pop>100000 with Mag b/w 4 & 6"};
                    } else if (mag >= 5 && mag < 6) {
                        return new String[]{"red", "Pop>100000 with Mag>6"};
                    } else if (mag >= 6) {
                        return new String[]{"red", "Pop>100000 with Mag>6"};

                    }
                }
                return new String[]{"blue", "Does not fit into significant criteria"};
            }
        }

        for (String dev1 : dev) {
            if (cou.equals(dev1)) {
                if (pop >= 1000 && pop < 10000) {
                    if (mag >= 4 && mag < 6) {
                        return new String[]{"green", "Pop b/w 10000 and 50000 with Mag<=4"};

                    } else if (mag >= 6) {
                        return new String[]{"orange", "Pop b/w 10000 and 50000 with Mag<=4"};

                    }
                }
                if (pop >= 10000 && pop < 50000) {
                    if (mag >= 4 && mag < 5) {
                        return new String[]{"green", "Pop b/w 10000 and 50000 with Mag<=4"};
                    } else if (mag >= 5 && mag < 6.5) {
                        return new String[]{"green", "Pop b/w 10000 and 50000 with Mag b/w 4 & 6.5"};
                    } else if (mag >= 6.5) {
                        return new String[]{"orange", "Pop b/w 10000 and 50000 with Mag>6.5"};
                    }
                }
                if (pop >= 50000 && pop < 100000) {
                    if (mag >= 3.5 && mag < 5) {
                        return new String[]{"green", "Pop b/w 50000 and 100000 with Mag<=3.5"};
                    } else if (mag >= 5 && mag < 6) {
                        return new String[]{"green", "Pop b/w 50000 and 100000 with Mag b/w 3.5 & 5"};
                    } else if (mag >= 6) {
                        return new String[]{"red", "Pop b/w 50000 and 100000 with Mag>5"};
                    }
                }
                if (pop >= 100000) {
                    if (mag >= 3.5 && mag < 5) {
                        return new String[]{"green", "Pop>100000 with Mag b/w 4 & 6"};
                    } else if (mag >= 5 && mag < 6) {
                        return new String[]{"orange", "Pop>100000 with Mag>6"};
                    } else if (mag >= 6) {
                        return new String[]{"red", "Pop>100000 with Mag>6"};

                    }
                }
                return new String[]{"blue", "Does not fit into significant criteria"};
            }
        }
        for (String DEV1 : DEV) {
            if (cou.equals(DEV1)) {
                if (pop >= 1000 && pop < 10000) {
                    if (mag >= 4 && mag < 6) {
                        return new String[]{"blue", "Pop b/w 10000 and 50000 with Mag<=4"};

                    } else if (mag >= 6) {
                        return new String[]{"green", "Pop b/w 10000 and 50000 with Mag<=4"};

                    }
                }
                if (pop >= 10000 && pop < 50000) {
                    if (mag >= 4 && mag < 5) {
                        return new String[]{"blue", "Pop b/w 10000 and 50000 with Mag<=4"};
                    } else if (mag >= 5 && mag < 6.5) {
                        return new String[]{"green", "Pop b/w 10000 and 50000 with Mag b/w 4 & 6.5"};
                    } else if (mag >= 6.5) {
                        return new String[]{"green", "Pop b/w 10000 and 50000 with Mag>6.5"};
                    }
                }
                if (pop >= 50000 && pop < 100000) {
                    if (mag >= 3.5 && mag < 5) {
                        return new String[]{"blue", "Pop b/w 50000 and 100000 with Mag<=3.5"};
                    } else if (mag >= 5 && mag < 6) {
                        return new String[]{"green", "Pop b/w 50000 and 100000 with Mag b/w 3.5 & 5"};
                    } else if (mag >= 6) {
                        return new String[]{"orange", "Pop b/w 50000 and 100000 with Mag>5"};
                    }
                }
                if (pop >= 100000) {
                    if (mag >= 3.5 && mag < 5) {
                        return new String[]{"green", "Pop>100000 with Mag b/w 4 & 6"};
                    } else if (mag >= 5 && mag < 6) {
                        return new String[]{"orange", "Pop>100000 with Mag>6"};
                    } else if (mag >= 6) {
                        return new String[]{"red", "Pop>100000 with Mag>6"};

                    }
                }
                return new String[]{"blue", "Does not fit into significant criteria"};
            }
        }
        return new String[]{"blue", "Does not fit into significant criteria"};

    }
}
