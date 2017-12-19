
import javax.swing.JOptionPane;

/* **********************************************************
 * Programmer:	C. Seeley
 * Class:       CS30S
 * 
 * Assignment:	Assignment 2
 *
 * Description:	calculate the average time and speed, and race 
                times and speeds, and change race time of a 
                skater object
 *
 * 
 * *************************************************************
 */
 
 // import files here as needed
 
 
 public class A2Class
 {  // begin class
 	
 	// *********** class constants **********
            
            private final int MAX = 10;
            private final int secInMin = 60;
            private final int distance = 5000;
            private final double MPStoKMPH = 3.6;
            private final int OFFSET = 1;
            
            
 	
 	// ********** instance variable **********
 	
            private int id = 1000;
            private int min = 0;
            private int sec = 0;
            private int Races = 0;
            private int timeSec[] = new int[MAX];
            private double speed[] = new double[MAX];
            private String delim = "[ ]+";
            
     
 	// ********** constructors ***********
 	
            /********************************************************
            * Purpose:       create a new skater 
            *         
            * Interface:
            *     in:         none
            *     out:        none
            ********************************************************/ 
            
            public A2Class(int n, String input[]) {
                id = this.getID(n);
                System.out.println("Skater: " + id);
                
                
                Races = input.length;
            }
            
 	// ********** accessors **********
            
            public int getID (int n) {
                int ID = 1000 + n;
                return ID;
            }
            
            
            /********************************************************
            * Purpose:        parse the time of the skater
            *         
            * Interface:
            *     in:         none
            *     out:        
            ********************************************************/ 
            public int singleTime (String input, int j) {
                
                String[] time = input.split("[:]+");
                min = Integer.parseInt(time[0]);
                sec = Integer.parseInt(time[1]);
                
                sec = sec + (min * secInMin);
                
                timeSec[j] = sec;
                return sec;
            }
            
            
            /********************************************************
            * Purpose:        find the average time of the skater
            *         
            * Interface:
            *     in:         string of times
            *     out:        average time 
            ********************************************************/ 
            public int avgTime() {
                int avgTime = 0;
                int totalSec = 0;
                
                for (int n = 0; n < Races; n++) {
                    totalSec += timeSec[n];
                }
                
                avgTime = totalSec / Races;
                
                
                System.out.print("Average time: " + (avgTime/secInMin) 
                        + ":");
                System.out.format( (avgTime%secInMin) + "\n");
                
                return avgTime;
            }
            
            
            
            
            /********************************************************
            * Purpose:        return the average speed of the skater
            *         
            * Interface:
            *     in:         times
            *     out:        average speed (double)
            ********************************************************/ 
            public double avgSpeed() {
                double avgSpeed = 0;
                double totalSpeed = 0;
                
                
                
                for (int n = 0; n < Races; n++) {
                    totalSpeed += speed[n];
                }
                
                avgSpeed = totalSpeed / Races;
                
                System.out.print("Average speed: ");
                System.out.format("%.2f", avgSpeed);
                System.out.println("Km/h\n");
                
                return avgSpeed;
            }
            
            
            /********************************************************
            * Purpose:        find the speed of the skater
            *         
            * Interface:
            *     in:         time of race
            *     out:        speed of skater in race (double)
            ********************************************************/ 
            public double singleSpeed(double n, int j) {
                double singleSpeed = 0;
                
                singleSpeed = distance/n * MPStoKMPH;
                
                speed[j] = singleSpeed;
                
                return singleSpeed;
            }

            
            
            
 	// ********** mutators **********
            
            public int changeTime(){
                String chanTo = "";
                int chanMin = 0;
                int chanSec = 0;
                int chanTotSec = 0;
                
                
                chanTo = JOptionPane.showInputDialog(
                        "What time do you want to add (ex: 7:15)");
                
                String[] chan = chanTo.split("[:]+");
                
                chanMin = Integer.parseInt(chan[0]);
                chanSec = Integer.parseInt(chan[1]);
                chanTotSec = (chanMin * secInMin) + chanSec;
                
                if (Races < 10){ 
                    timeSec[Races] = chanTotSec;
                    
                    min = timeSec[Races] / secInMin;
                    sec = timeSec[Races] % secInMin;
                    
                    System.out.print("You have changed race time " 
                        + (Races + OFFSET) + " to " );
                    System.out.format("%2s", (timeSec[Races]/secInMin) + ":"); 
                    System.out.format("%2s", (timeSec[Races]%secInMin) + "\n");
                    
                    System.out.print("Average race " + (Races + OFFSET) + " speed: ");
                    
                    Races = Races + OFFSET;
                }//end if
                
                else {
                    for (int n = 1; n < Races; n++) {
                        timeSec[(Races - OFFSET) - n] = timeSec[Races - OFFSET];
                    }
                    timeSec[Races - OFFSET] = chanTotSec;
                    
                    min = timeSec[Races-OFFSET] / secInMin;
                    sec = timeSec[Races-OFFSET] % secInMin;
                    
                    System.out.print("You have changed race time " 
                        + (Races) + " to " );
                    System.out.format("%2s", 
                            (timeSec[Races-OFFSET]/secInMin) + ":"); 
                    System.out.format("%2s", 
                            (timeSec[Races-OFFSET]%secInMin) + "\n");
                    
                    System.out.print("Average race " + Races + " speed: ");
                }//end else
                
                return chanTotSec;
            }
 
 }  // end class