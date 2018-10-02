/* Application runner, reads txt file, runs stuff from
 * StackHandle and Elevator. 
 * 
 * 
 * going to load stack here
 * want 2 stacks (one to store people when we unload, another for the elevator)
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
        System.out.println(System.getProperty("user.dir"));
        String data = "input.txt";
        //String data = args[0];
        
        try
        {
            BufferedReader inFile = new BufferedReader(new FileReader(data));
                
            int chr = inFile.read();
            
            while (chr != -1)  //reads entire txt file                   
            {
                String name ="";
                int floorEnter;
                int floorExit;                        
                
                //When it sees "/", skips line
                if(chr == 47) //ascii : 47 = /
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
                    
                    
                    while(chr != 10) //skip rest of line
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
            inFile.close();
             
                
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



//NEED TO READ ONE CHAR AT TIME
//need to build name into string one char at a time


//need to parse data and store in person wrapper
//if floor enter == curr floor, push

//if current floor != floorEnter, pause reading
//elevator moves (up or down) 
//check to see if FloorExit == cur Floor,
//pop into dummyStack -> dummyStack.push(Elevator.pop())
//then push it all back to elevator -> Elevator.push(dummyStack.pop())

//read next line, if FloorEnter == cur floor, push 


//some conditionals to consider: 

//need to record number of times a person needs to get off (pop) in order for a person to get out.                   
//upon exit, need to print the count and the name               
//if stack is full and a person fulfills a condition to get one (overflow), person is ignore, overflow doesn't happen, but need to keep count
//whenever event occurs
