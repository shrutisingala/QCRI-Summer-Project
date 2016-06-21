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
    

    public EQSignificanceChecker(int p, float m, String c) {
        pop = p;
        mag = m;
        cou = c;
        

    }

    public String[] setSignificance() {
        
        String[] UD = {"Myanmar", "Guatemala", "Burma", "Libya", "Lebanon", "Haiti", "Guinea", "Angola", "Chad", "Bosnia", "Mongolia", "Nepal", "Russia", "Indonesia"};
        String[] dev = {"Mexico", "Vanuatu", "India", "Pakistan", "Jordan", "Srilanka", "Malaysia", "Thailand", "South Africa", "Brazil", "China"};
        String[] DEV = {"Antarctica", "Hong Kong", "Singapore", "UAE", "Japan", "Qatar", "Switzerland", "Germany", "France", "United Kingdom"};
        
        boolean flag=false;

        for (String UD1 : UD) {
            if (cou.equals(UD1)) {
                flag=true;
                if (pop >= 1000 && pop < 10000) {
                    if (mag >= 4 && mag < 6) {
                        return new String[]{"green", "Under-developed country with Pop b/w 1000 and 10000 with Mag b/w 4 & 6"};

                    } else if (mag >= 6) {
                        return new String[]{"orange", "Under-developed country with Pop b/w 1000 and 10000 with Mag>=6"};

                    }
                }
                if (pop >= 10000 && pop < 50000) {
                    if (mag >= 4 && mag < 5) {
                        return new String[]{"green", "Under-developed country with Pop b/w 10000 and 50000 with Mag b/w 4 & 5"};
                    } else if (mag >= 5 && mag < 6.5) {
                        return new String[]{"orange", "Under-developed country with Pop b/w 10000 and 50000 with Mag b/w 5 & 6.5"};
                    } else if (mag >= 6.5) {
                        return new String[]{"red", "Under-developed country with Pop b/w 10000 and 50000 with Mag>=6.5"};
                    }
                }
                if (pop >= 50000 && pop < 100000) {
                    if (mag >= 3.5 && mag < 5) {
                        return new String[]{"green", "Under-developed country with Pop b/w 50000 and 100000 with Mag b/w 3.5 & 5"};
                    } else if (mag >= 5 && mag < 6) {
                        return new String[]{"orange", "Under-developed country with Pop b/w 50000 and 100000 with Mag b/w 5 & 6"};
                    } else if (mag >= 6) {
                        return new String[]{"red", "Under-developed country with Pop b/w 50000 and 100000 with Mag>=6"};
                    }
                }
                if (pop >= 100000) {
                    if (mag >= 3.5 && mag < 5) {
                        return new String[]{"orange", "Under-developed country with Pop>100000 with Mag b/w 3.5 & 5"};
                    } else if (mag >= 5 && mag < 6) {
                        return new String[]{"red", "Under-developed country with Pop>100000 with Mag b/w 5 & 6"};
                    } else if (mag >= 6) {
                        return new String[]{"red", "Under-developed country with Pop>100000 with Mag>=6"};

                    }
                }
                return new String[]{"blue", "Does not fit into significant criteria"};
            }
        }
        
        for (String DEV1 : DEV) {
            if (cou.equals(DEV1)) {
                flag=true;
                if (pop >= 1000 && pop < 10000) {
                    if (mag >= 4 && mag < 6) {
                        return new String[]{"blue", "Developed country with Pop b/w 1000 and 10000 with Mag b/w 4 & 6"};

                    } else if (mag >= 6) {
                        return new String[]{"green", "Developed country with Pop b/w 1000 and 10000 with Mag>=6"};

                    }
                }
                if (pop >= 10000 && pop < 50000) {
                    if (mag >= 4 && mag < 5) {
                        return new String[]{"blue", "Developed country with Pop b/w 10000 and 50000 with Mag b/w 4 & 5"};
                    } else if (mag >= 5 && mag < 6.5) {
                        return new String[]{"green", "Developed country with Pop b/w 10000 and 50000 with Mag b/w 5 & 6.5"};
                    } else if (mag >= 6.5) {
                        return new String[]{"green", "Developed country with Pop b/w 10000 and 50000 with Mag>6.5"};
                    }
                }
                if (pop >= 50000 && pop < 100000) {
                    if (mag >= 3.5 && mag < 5) {
                        return new String[]{"blue", "Developed country with Pop b/w 50000 and 100000 with Mag b/w 3.5 & 5"};
                    } else if (mag >= 5 && mag < 6) {
                        return new String[]{"green", "Developed country with Pop b/w 50000 and 100000 with Mag b/w 5 & 6"};
                    } else if (mag >= 6) {
                        return new String[]{"orange", "Developed country with Pop b/w 50000 and 100000 with Mag>=6"};
                    }
                }
                if (pop >= 100000) {
                    if (mag >= 3.5 && mag < 5) {
                        return new String[]{"green", "Developed country with Pop>100000 with Mag b/w 3.5 & 5"};
                    } else if (mag >= 5 && mag < 6) {
                        return new String[]{"orange", "Developed country with Pop>100000 with Mag b/w 5 & 6"};
                    } else if (mag >= 6) {
                        return new String[]{"red", "Developed country with Pop>100000 with Mag>=6"};

                    }
                }
                return new String[]{"blue", "Does not fit into significant criteria"};
            }
        }

        for (String dev1 : dev) {
            if (cou.equals(dev1)) {
                label:
                flag=true;
                if (pop >= 1000 && pop < 10000) {
                    if (mag >= 4 && mag < 6) {
                        return new String[]{"green", "Developing country with Pop b/w 1000 and 10000 with Mag b/w 4 & 6"};

                    } else if (mag >= 6) {
                        return new String[]{"orange", "Developing country with Pop b/w 1000 and 10000 with Mag>=6"};

                    }
                }
                if (pop >= 10000 && pop < 50000) {
                    if (mag >= 4 && mag < 5) {
                        return new String[]{"green", "Developing country with Pop b/w 10000 and 50000 with Mag b/w 4 & 5"};
                    } else if (mag >= 5 && mag < 6.5) {
                        return new String[]{"green", "Developing country with Pop b/w 10000 and 50000 with Mag b/w 5 & 6.5"};
                    } else if (mag >= 6.5) {
                        return new String[]{"orange", "Developing country with Pop b/w 10000 and 50000 with Mag>=6.5"};
                    }
                }
                if (pop >= 50000 && pop < 100000) {
                    if (mag >= 3.5 && mag < 5) {
                        return new String[]{"green", "Developing country with Pop b/w 50000 and 100000 with Mag b/w 3.5 & 5"};
                    } else if (mag >= 5 && mag < 6) {
                        return new String[]{"green", "Developing country with Pop b/w 50000 and 100000 with Mag b/w 5 & 6"};
                    } else if (mag >= 6) {
                        return new String[]{"red", "Developing country with Pop b/w 50000 and 100000 with Mag>=6"};
                    }
                }
                if (pop >= 100000) {
                    if (mag >= 3.5 && mag < 5) {
                        return new String[]{"green", "Developing country with Pop>100000 with Mag b/w 3.5 & 5"};
                    } else if (mag >= 5 && mag < 6) {
                        return new String[]{"orange", "Developing country with Pop>100000 with Mag b/w 5 & 6"};
                    } else if (mag >= 6) {
                        return new String[]{"red", "Developing country with Pop>100000 with Mag>=6"};

                    }
                }
                return new String[]{"blue", "Does not fit into significant criteria"};
            }
        }
        
        if(!flag){
            flag=true;
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
     
       
        return new String[]{"blue", "Does not fit into significant criteria"};

    }
}
