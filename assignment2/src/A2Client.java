/********************************************************************
 * Programmer:	C. Seeley
 * Class:       CS30S
 *
 * Assignment: Assignment 2
 * Program Name:  A2Client.java
 *
 * Description: read in times from a text file, create a skater object,
 *              assign a skater ID, print out all of the skater's race
 *              times, find the speed of the individual races, find the
 *              average race speed, find the average race time
 *
 * Input:  race times from text file
 *
 * Output: skater id, average time, race times, average speed,
 *         single race speed
 ***********************************************************************/
 
 // import java libraries here as needed
 
 import javax.swing.*;
 import java.text.DecimalFormat;
 import java.io.*; 					// import file io libraries
 

public class A2Client {  // begin class
    
    public static void main(String[] args) throws IOException{  // begin main
    
    // ********* declaration of constants **********
    
        final int MAX = 10;         //constant for max number of races
        final int OFFSET = 1;       //constant for the offset for changing times
        final int SENTVAL = -1;     //constant for sentinel value
        final int secInMin = 60;    //constant for seconds in one minute
    
    // ********** declaration of variables **********
    	
    	String delim = "[ ]+";    // delimiter string for splitting input string
        String delim2 = "[;]+";   //delimiter for splitting race times
        String strin = "";        //string for input
        String input[] = null;    //string for splitting inpujt
        int n = 0;                //int for incrementing id
        int timesInt[] = new int[MAX];  //int for storing times in seconds
        int averageTime = 0;      //integer to store average time in seconds
        double singleSpeed = 0;   //double for storing speed of race
        int singleTime = 0;       //int for storing time of race in seconds
        double averageSpeed = 0;  //double for storing average speed
        int chanTime = 0;         //int for storing changed time in seconds
        int chan = 0;             //int for checking if a time will be changed
        int races = 0;            //int for storing number of races
        
        
    	
    // create instances of objects for i/o and formatting
    
    	//ConsoleReader console = new ConsoleReader(System.in);
    	//DecimalFormat df1 = new DecimalFormat("$##.00");
    	
    	BufferedReader fin = new BufferedReader(new FileReader("skaterData.txt"));
    	PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("textOutA2.txt")));
        
        ProgramInfo programInfo = new ProgramInfo("Assignment 2"); //create new program info
    	
    // ********** Print output Banner **********
    
    	System.out.println(programInfo.toString()); //output program info
        //fout.println(programInfo.toString());
        
 	    	
    // ************************ get input **********************

    // ************************ processing ***************************
    
        strin = fin.readLine(); //input first line
        
        while(strin != null){ //while there is still input
            chan = 0;  //reset chan value
            int j = 0; //reset j value
            input = strin.split(delim); //split race
            
            races = input.length; //set number of races
            
            A2Class skater = new A2Class(n, input); //create new skater object
          
            for(int i = 0; i < races; i++){
                timesInt[n] = skater.singleTime(input[i], j); 
                //store race time in seconds
                singleSpeed = skater.singleSpeed(timesInt[n], j);
                //store race speed
                
                System.out.print("Race " + (i + OFFSET) + " time: ");
                System.out.format(input[i] + "\n");
                //output race time
                System.out.print("Average race " + (i + OFFSET) + " speed: ");
                System.out.format("%.2f", singleSpeed);
                System.out.println("Km/h");
                //output race speed
                
                j++; //increment int for setting time and speed
            } // end for tokens
            
            while (chan != SENTVAL) { //while the user does not enter the sentinel value
                
                chan = Integer.parseInt(JOptionPane.showInputDialog(
                 "Enter any number to change a time for skater " 
                  + skater.getID(n) + "\nOr enter -1 to exit"));
                //input for changing time
                
                if (chan == SENTVAL) { //check if the entered the sentinel value
                    break;             //break while loop
                }//end if statement
                
                chanTime = skater.changeTime(); //output and store changed time
                
                if (races < MAX) { //if there are less than 10 races
                    singleSpeed = skater.singleSpeed(chanTime, races);
                    //output race speed
                }//end if statement
                else {
                    singleSpeed = skater.singleSpeed(chanTime, (races - OFFSET));
                    //output race speed
                }//end else statement
                
                System.out.format("%.2f", singleSpeed + "Km/h"); //output speed
                } //end while loop
            
            
            
            averageTime = skater.avgTime();   //output and store average time
            averageSpeed = skater.avgSpeed(); //output and store average speed
            //System.out.println(averageTime + "\n");
            
          //System.out.println(strin);
          n++; //increment n for id
          strin = fin.readLine(); //read in next line
        } // end eof loop
    
    // ************************ print output ****************************

    
        // ******** closing message *********
        
        System.out.println(programInfo.eoFile()); //output eof statement
        fout.println(programInfo.eoFile()); //output eof statement to file
        
        // ***** close streams *****
        
        fin.close();			// close input buffer
        fout.close();			// close output buffer
    }  // end main
}  // end class
