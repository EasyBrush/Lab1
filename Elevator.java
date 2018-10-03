/** This class is meant to handle movement of elevator
 * 
 * 
 * writes to outfile
 * 
 * process person, manage elevator movement
 * 
 * @author Bryan Cheung
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Elevator
{
    //private int maxFloors = 5;
    private int curr_position;
    int total;//total number of people who rode elevator
    BufferedWriter outFile;
    Person temp;
    button buttons; 
    boolean goingup;
    StackHandle elevatorStack;

    /**
     * constructor, initialize param, creates header and writer
     */
    public Elevator()
    {
        curr_position = 1;
        total = 0;               
        
        //boolean flag for movement
        goingup = true;
        //button object
        buttons = new button(6);
        //stack object
        elevatorStack = new StackHandle(5);
        
        try
        {
            //writer and output file
            outFile = new BufferedWriter(new FileWriter("outPut.txt"));
            //header
            outFile.write("//true = elevator, false = stairs");
            outFile.newLine();
            outFile.write("//name        floor entered         floor exit             number of times exit              elevator or stairs");
            
            outFile.newLine();
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * simulates a person exiting an elevator. Writes to an
     * outFile, adjusts destination array and adds to the count
     * of number of people who've ridden the elevator. 
     */
    public void ExitElevator() throws IOException
    {
        //System.out.println("Checking to see if exiting top is :" + elevatorStack.getSize() + " current floor: " + curr_position);
        
        StackHandle dummyStack = new StackHandle(5);
        
        //gives location of person farthest in stack wanting to exit
        int n = buttons.SearchFloorExit(curr_position, elevatorStack.getSize());  
        //int n =5;
        //System.out.println("this is n " + n);
        
        for(int i=0; i<n; i++)
        {
            //System.out.println("enter check exit loop");
            if(!elevatorStack.isEmpty())
            {
                Person person = elevatorStack.pop();
                if(person.getFloorExit() != curr_position)
                {
                    dummyStack.push(person);
                }
                else
                {
                    //System.out.println(person + " current floor " + curr_position + "THEY LEFT");
                    
                    //writes people who've exited into file
                    outFile.write(person.toString());
                    outFile.newLine();
                    
                    total++;
                    buttons.exit(curr_position);
                }
            }
        }
        
        //transfer from dummy stack to elevator stack
        for(int i=0; i<n;i++)
        {
            if(!dummyStack.isEmpty())
            {
                
                Person human = dummyStack.pop();
                //counts number of times person leaves elevator
                human.setCount(human.getCount() + 1);
                elevatorStack.push(human);
            }
        }     
    }
    
    /** 
     * determines if person take the elevator or stairs, deals with 
     * person waiting, and elevator environment (inside)
     * 
     * @param person
     */
    public void processPerson(Person person) throws IOException
    {
        //System.out.println("now processing" +  person.toString());
        
        if(elevatorStack.isEmpty())
        {
            outFile.write("Elevator is Empty");
            outFile.newLine();
        }
        
        if(curr_position == person.getFloorEnter())
        {
            if( !elevatorStack.isFull())
            {
                elevatorStack.push(person);
                buttons.load(person.getFloorExit(), elevatorStack.getSize());
                
            
            }
            else
            {
                outFile.write("Elevator is full");
                outFile.newLine();
                person.setTravel("stairs");
                //documents person taking stairs
                outFile.write(person.toString());
                outFile.newLine();
                
                //System.out.println("Elevator is full");
                
                
                
 
            }
          
        }
        else
        {
            temp = person;
            buttons.loadWaiting(person.getFloorEnter());
            ProcessElevator();
            
            if(!elevatorStack.isFull() && curr_position == temp.floorEnter)
            {
                elevatorStack.push(temp);
                buttons.flushWaiting();
                buttons.load(temp.getFloorExit(),elevatorStack.getSize());
                temp = null;
                
            }
            else if(curr_position == temp.floorEnter && elevatorStack.isFull())
            {
                outFile.write("Elevator is full");
                outFile.newLine();
                person.setTravel("stairs");
                //documents person taking stairs
                outFile.write(person.toString());
                outFile.newLine();
                
                //System.out.println("Elevator is full");              
                
                buttons.flushWaiting();
                temp = null;
            }
            
        }  
        
    }
    
    
    /**
     * handle movements, picking people up, kicking people off
     * elevator stops running when people are being loaded on and off
     * 
     */
    public void ProcessElevator() throws IOException
    {
        //System.out.println("Elevator is on the move " + temp + " " + buttons.destinationExist() + " " + curr_position);
        
        while ( buttons.destinationExist() && ((temp!= null && curr_position != temp.getFloorEnter()) || temp == null) )
        {
            
            //elevator moves to next floor
            if(goingup)
            {
                
                if(buttons.nextUp(curr_position) == 0)
                {
                    curr_position = buttons.nextDown(curr_position);
                    goingup = false;
                }
                else
                {
                    curr_position = buttons.nextUp(curr_position);
                }
                //System.out.println("Going upp");
                
                ExitElevator();                      
                
                //System.out.println(curr_position + " current position" + "is there a temp? " + temp + "check destination" );
            }
            else
            {
                //System.out.println("Going downnnn");
                
                if(buttons.nextDown(curr_position) == 0)
                {
                    curr_position = buttons.nextUp(curr_position);
                    goingup = true;
                    
                }  
                else
                {
                    curr_position = buttons.nextDown(curr_position);
                }

                ExitElevator();
                
                //System.out.println(curr_position + "current position");
                
            }
        }
    }
    /**
     * closes local writer
     */
    public void closeWriter()
    {
        try
        {
            outFile.close();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * prints total passengers to outFile
     */
    public void printTotal()
    {
        try
        {
            outFile.newLine();
            outFile.write("Total People who rode elevator: " + total);
            outFile.newLine();
        } 
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
