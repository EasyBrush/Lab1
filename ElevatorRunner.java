/**
 * Application runner that reads txt file and runs methods
 * from Elevator class to simulate elevator.
 * @author Bryan Cheung
 *
 */
import java.io.*;

public class ElevatorRunner
{
    public static void main(String[] args)
    {
    
       //creates elevator object
        Elevator elevator = new Elevator(); 
             
        //create objects
        //System.out.println(System.getProperty("user.dir"));
        //String data = "input.txt"; //file path hardcoded for simplicity 
        String data = args[0];//file path
        
        try
        {
            BufferedReader inFile = new BufferedReader(new FileReader(data));
                
            int chr = inFile.read();
            //reads one char at a time
            while (chr != -1)  //reads entire txt file                   
            {
                String name ="";
                int floorEnter;
                int floorExit;                                                    
                
                //When it sees "/", skips line, 13 = /r
                if(chr == 47 || chr==13) //ascii : 47 = /
                {
                    //System.out.println("Skipping");
                    
                    //skip line          
                    while(chr != 10) //ascii : 10 = \n
                    {
                        chr = inFile.read();
                    }       
                }
                else
                {//asci : 9 = horizontal tab, 32 = space
                    while((chr != 9) && (chr != 32)) //while not space or tab
                    {                      
                        name = name + (char)chr;
                        chr = inFile.read();
                    }
                    // System.out.println(name);    
                    
                    chr = inFile.read();
                    
                    if((chr == 9) || (chr == 32))//skip if space or tab
                    {
                        chr = inFile.read();
                    }
                    
                    floorEnter = chr - 48; //store enter floor
                    
                    chr = inFile.read(); //read next char
                    if((chr == 9) || (chr == 32)) //parse space or tab
                    {
                        chr = inFile.read();
                    }
                    
                    floorExit = chr - 48; //store Exit floor
                    
                    //chr = inFile.read();
                                       
                    while(chr != 10 && chr!=-1) //skip rest of line
                    {
                        chr = inFile.read();  
                    }
                    
                    //System.out.println(floorEnter);
                    //System.out.println(floorExit);
                    
                    Person person = new Person(name, floorEnter, floorExit, 0, "elevator");
                    
                    elevator.processPerson(person);
                                        
                }                  
                chr = inFile.read();                
            }
            //close reader
            //process elevator one more time in case stack is not empty
            elevator.ProcessElevator();
            inFile.close();
            elevator.printTotal();
            elevator.closeWriter();                 
        }    
        catch (FileNotFoundException e)
        {              
            e.printStackTrace();
        } 
        catch (IOException e)
        {                
            e.printStackTrace();
        }
           
    }
    
}

