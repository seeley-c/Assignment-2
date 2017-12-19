/* **********************************************************
 * Programmer:	C. Seeley
 * Class:       CS30S
 * 
 * Assignment:	Assignment 2
 *
 * Description:	
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
                id = id + n;
                System.out.println("Skater: " + id);
                
                
                Races = input.length;
            }
            
 	// ********** accessors **********
            
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
            *     out:        average speed (int)
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
            *     out:        speed of skater in race
            ********************************************************/ 
            public double singleSpeed(double n, int j) {
                double singleSpeed = 0;
                
                singleSpeed = distance/n * MPStoKMPH;
                
                speed[j] = singleSpeed;
                
                return singleSpeed;
            }

            
            
            
 	// ********** mutators **********
 
 }  // end class