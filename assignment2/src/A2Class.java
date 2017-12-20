
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
            
            private final int MAX = 10;        //constant for max races
            private final int secInMin = 60;   //constant for seconds in minutes
            private final int distance = 5000; //constant for distance
            private final double MPStoKMPH = 3.6; //constant for converting m/s to km/h
            private final int OFFSET = 1;      //constant for offset
            
            
 	
 	// ********** instance variable **********
 	
            private int id = 1000;  //set base id to 1000
            private int min = 0;    //int for minutes
            private int sec = 0;    //int for seconds
            private int Races = 0;  //int for amount of races
            private int timeSec[] = new int[MAX]; //array for times
            private double speed[] = new double[MAX]; //array for speeds
            private String delim = "[ ]+";  //string for delim
            
     
 	// ********** constructors ***********
 	
            /********************************************************
            * Purpose:       create a new skater 
            *         
            * Interface:
            *     in:         int n for id, input for setting races
            *     out:        none
            ********************************************************/ 
            
            public A2Class(int n, String input[]) {
                id = this.getID(n); //set id
                System.out.println("Skater: " + id); //output skater and id
                
                
                Races = input.length; //set races to number of times in input
            }//end A2Class
            
 	// ********** accessors **********
            
            /********************************************************
            * Purpose:        get the ID for the skater object
            *         
            * Interface:
            *     in:         int n for incrementing ID
            *     out:        ID for skater object
            ********************************************************/
            public int getID (int n) {
                int ID = 1000 + n;  //set id
                return ID;  //return id
            }//end getID
            
            
            /********************************************************
            * Purpose:        parse the time of the skater
            *         
            * Interface:
            *     in:         none
            *     out:        
            ********************************************************/ 
            public int singleTime (String input, int j) {
                
                String[] time = input.split("[:]+"); //split time
                min = Integer.parseInt(time[0]); //set minutes
                sec = Integer.parseInt(time[1]); //set seconds
                
                sec = sec + (min * secInMin); //set time in seconds
                
                timeSec[j] = sec; //store time in seconds
                return sec; //return total seconds
            }//end singleTime
            
            
            /********************************************************
            * Purpose:        find the average time of the skater
            *         
            * Interface:
            *     in:         string of times
            *     out:        average time 
            ********************************************************/ 
            public int avgTime() {
                int avgTime = 0; //int for average time
                int totalSec = 0; //int for total seconds
                int min = 0; //int for minutes
                int sec = 0; //int for seconds
                
                for (int n = 0; n < Races; n++) { //loop for getting total time
                    totalSec += timeSec[n]; //add time to total time
                }//end for loop
                
                avgTime = totalSec / Races; //calculate average time
                
                min = avgTime / secInMin; //set average time minutes
                sec = avgTime % secInMin; //set average time seconds
                
                if (sec>=10) { //if less than 10 seconds
                System.out.print("Average time: "); //output average time
                System.out.print( min + ":");
                System.out.print(sec + "\n");
                }//end if statement
                else { //more than 9 seconds
                System.out.print("Average time: "); //output average time
                System.out.print( min + ":");
                System.out.print("0" + sec + "\n");
                }//end else statment
                
                return avgTime; //return average time in seconds
            }//end avgTime
            
            
            
            
            /********************************************************
            * Purpose:        return the average speed of the skater
            *         
            * Interface:
            *     in:         times
            *     out:        average speed (double)
            ********************************************************/ 
            public double avgSpeed() {
                double avgSpeed = 0;   //double for average speed
                double totalSpeed = 0; //double for total speed
                
                
                
                for (int n = 0; n < Races; n++) { //loop for getting total speed
                    totalSpeed += speed[n]; //add speed to total speed
                }//end for loop
                
                avgSpeed = totalSpeed / Races; //calculate average speed
                
                System.out.print("Average speed: "); //output average speed
                System.out.format("%.2f", avgSpeed);
                System.out.println("Km/h\n");
                
                return avgSpeed; //return average speed
            }//end avgSpeed
            
            
            /********************************************************
            * Purpose:        find the speed of the skater
            *         
            * Interface:
            *     in:         time of race
            *     out:        speed of skater in race (double)
            ********************************************************/ 
            public double singleSpeed(double n, int j) {
                double singleSpeed = 0; //double for speed
                
                singleSpeed = distance/n * MPStoKMPH; //calculate speed
                
                speed[j] = singleSpeed; //store speed in array
                
                return singleSpeed; //return speed
            }//end singleSpeed

            
            
            
 	// ********** mutators **********
            
            /********************************************************
            * Purpose:        change or add time
            *         
            * Interface:
            *     in:         none
            *     out:        time that was added/changed
            ********************************************************/ 
            public int changeTime(){
                String chanTo = ""; //string for time input
                int chanMin = 0;    //int for changed time minutes
                int chanSec = 0;    //int for changed time seconds
                int chanTotSec = 0; //int for changed time total seconds
                int min = 0;    //int for outputting time in minutes
                int sec = 0;    //int for outputting time in seconds
                
                
                chanTo = JOptionPane.showInputDialog(
                        "What time do you want to add (ex: 7:15)");
                //input the time to be added/changed
                
                String[] chan = chanTo.split("[:]+"); //split input
                
                chanMin = Integer.parseInt(chan[0]); //set changed time minutes
                chanSec = Integer.parseInt(chan[1]); //set changed time seconds
                chanTotSec = (chanMin * secInMin) + chanSec; //set total seconds
                
                if (Races < 10){ //if there are less than 10 races
                    timeSec[Races] = chanTotSec; //add time
                    
                    min = timeSec[Races] / secInMin; //set minutes for output
                    sec = timeSec[Races] % secInMin; //set seconds for output
                    
                    System.out.print("You have changed race time " 
                        + (Races + OFFSET) + " to " ); //output race number
                    
                    
                    if (sec >= 10) { //if there are more than 9 seconds
                    System.out.print(min + ":"); 
                    System.out.print("0" + sec + "\n"); //output time
                    }//end if statement
                    else { //less than 10 seconds
                    System.out.print(min + ":"); 
                    System.out.print(sec + "\n"); //output time
                    }//end else
                    
                    System.out.print("Average race " + (Races + OFFSET) + " speed: ");
                    //output start of race speed
                    
                    Races = Races + OFFSET; //set number of races
                }//end if
                
                else {
                    for (int n = 1; n < Races; n++) { 
                    //loop for moving race times down one place
                        timeSec[(Races - OFFSET) - n] = timeSec[Races - OFFSET];
                        //set race times down one place
                    }//end for loop
                    
                    timeSec[Races - OFFSET] = chanTotSec;//set changed race time
                    
                    min = timeSec[Races-OFFSET] / secInMin; //set changed time minutes
                    sec = timeSec[Races-OFFSET] % secInMin; //set changed time seconds
                    
                    System.out.print("You have changed race time " 
                        + (Races) + " to " ); //output race number
                    
                    if (sec >= 10) { //if there are more than 9 seconds
                    System.out.print(min + ":"); 
                    System.out.print("0" + sec + "\n"); //output time
                    }//end if statement
                    else { //if there are less than 10 seconds
                    System.out.print(min + ":"); 
                    System.out.print(sec + "\n"); //output time
                    }//end else 
                    
                    
                    System.out.print("Average race " + Races + " speed: "); 
                    //start output of race speed
                }//end else
                
                return chanTotSec; //return changed time
            }//end changeTime
 
 }  // end class