import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/* This class is meant to handle movement of elevator
 * 
 * 
 * writes to outfile
 * 
 * process person, manage elevator movement
 */
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
            //writer
            outFile = new BufferedWriter(new FileWriter("outPut.txt"));
            //header
            outFile.write("true = elevator, false = stairs");
            outFile.write("name        floor entered         floor exit             number of times exit              elevator or stairs");
            
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /*
     * simulates a person exiting an elevator. Writes to an
     * outFile, adjusts destination array and adds to the count
     * of number of people who've ridden the elevator. 
     */
    public void ExitElevator() throws IOException
    {
        System.out.println("Checking to see if exiting");
        
        StackHandle dummyStack = new StackHandle(5);
        
        //gives location of person farthest in stack wanting to exit
        int n = buttons.SearchFloorExit(curr_position);  
        
        System.out.println("this is n " + n);
        //System.out.println(elevatorStack.top);
        
        for(int i=0; i<=n; i++)
        {
            System.out.println("enter check exit loop");
            
            Person person = elevatorStack.pop();
            if(person.getFloorExit() != curr_position)  //got a null pointer
            {
                dummyStack.push(person);
            }
            else
            {
                System.out.println(person + " current floor " + curr_position + "THEY LEFT");
                //System.out.println("Success they left");
                
                //writes people who've exited into file
                outFile.write(person.toString());
                total++;
                buttons.exit(curr_position);
                //elevatorStack.pop();
                
                //System.out.println(person);
                //System.out.println("Success they left");
            }
        }
        
        for(int i=0; i<n;i++)
        {
            Person human = dummyStack.pop();
          //counts number of times person leaves elevator
            human.setCount(human.getCount() + 1);
            elevatorStack.push(human);
        }
      
    }
    
    /* loading onto elevator
     * process if elevator is full 
     */
    public void processPerson(Person person) throws IOException
    {
        System.out.println("now processing" +  person.toString());
        
        if(elevatorStack.isEmpty())
        {
            outFile.write("Elevator is Empty");
        }
        
        if(curr_position == person.getFloorEnter())
        {
            if( !elevatorStack.isFull())
            {
                elevatorStack.push(person);
                buttons.load(person.getFloorExit(), elevatorStack.top);
                ProcessElevator();
            
            }
            else
            {
                outFile.write("Elevator is full");
                person.setTravel("stairs");
                //documents person taking stairs
                outFile.write(person.toString());
                
                System.out.println("Elevator is full");
         
            }
          
        }
        else
        {
            temp = person;
            buttons.loadWaiting(person.getFloorEnter());
            ProcessElevator();
            
            if(!elevatorStack.isFull())
            {
                elevatorStack.push(temp);
                buttons.flushWaiting();
                buttons.load(temp.getFloorEnter(),elevatorStack.top);
                temp = null;
                
            }
            else
            {
                outFile.write("Elevator is full");
                person.setTravel("stairs");
                //documents person taking stairs
                outFile.write(person.toString());
                
                System.out.println("Elevator is full");              
                
                buttons.flushWaiting();
                temp = null;
            }
        }
     
        
    }
    
    
    /*
     * handle movements, picking people up, kicking people off
     * elevator needs to stop running when people are being loaded on and off
     * 
     * adjust destination list
     */
    public void ProcessElevator() throws IOException
    {
        System.out.println("Elevator is on the move " + temp + " " + buttons.destinationExist() + " " + curr_position);
        
        while ( buttons.destinationExist() && ((temp!= null && curr_position != temp.getFloorEnter()) || temp == null) )
        {
            
            //elevator moves to next floor
            if(goingup)
            {
                
                if(buttons.nextUp(curr_position) == 0)
                {
                    curr_position = buttons.nextDown(curr_position);
                    goingup = false;
                    //ExitElevator();
                }
                else
                {
                    curr_position = buttons.nextUp(curr_position);
                    ExitElevator();
                }
                System.out.println("Going upp");
                
                //curr_position = buttons.nextUp(curr_position);
               //ExitElevator();                      
                
                System.out.println(curr_position + " current position");
                


            }
            else
            {
                System.out.println("Going downnnn");
                
                if(buttons.nextDown(curr_position) == 0)
                {
                    curr_position = buttons.nextUp(curr_position);
                    goingup = true;
                    //ExitElevator();
                }  
                else
                {
                    curr_position = buttons.nextDown(curr_position);
                    ExitElevator();
                }
                //curr_position = buttons.nextDown(curr_position);
                //ExitElevator();
                
                System.out.println(curr_position + "current position");
                
           
            }
   
        }
             
    }
    
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
    
    
    
    
    
    
    
    
    

    

}
