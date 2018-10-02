/*
 * button class in charge of keeping track of destinations of people loaded 
 * into the stack and the person waiting for the elevator.
 */
public class button
{
    int destination[];
    int maxsize;
    
    public button(int size)
    {
        maxsize = size;
        destination = new int[maxsize];
        for(int i=0; i< destination.length-1; i++)
        {
            destination[i] =0;
        }
    }
    
    /*
     * want to load people's floorexit in empty space
     * only load the elevator
     */
    public void load(int floor, int stackSize)
    {
        
        destination[stackSize-1] = floor;
        
    }
    
    /*
     * loads person waiting into position [5] always
     */
    public void loadWaiting(int floor)
    {
        destination[5] = floor;
    }
    
    /*
     * removes waiting person
     */
    public void flushWaiting()
    {
        destination[5] = 0;
    }
    /*
     * removes floor destination based on floori
     */
    public void exit(int floor)
    {
        for(int i=0; i<maxsize-1; i++)
        {//remove destination when popped
            if(destination[i] == floor)
            {
                destination[i] = 0; //but this removes all
            }
        }
        shift();//shift everything to make space
        

    }
    
    /*
     * spits out highest index value of the person wanting to exit
     */
    public int SearchFloorExit(int floor)
    { 
        int index =0;
        
        //shift(); //first shift array
        
        //want to catch only in beginning of array
        //end of array is earliest
        for(int i= 4; i<=0; i--)//dis-include person waiting for elevator
        {//0,1,2,3,4
         //4,3,2,1,0   
            if(destination[i] == floor)
            {
                
                index = 4 - i;
                
                //smallest index is the top
                //aligns with stack position logic
                
            }
        }
        
        return index;
        
    }
    
    /*
     * shifts values in array forward to create empty space in back.
     * smaller indexes dictates earlier entry to elevator
     * ignores temp spot
     */
    public void shift()
    {
        int c = 0;        
        for (int i =0; i<maxsize; i++ )
        {
            if(destination[i] != 0)
            {
                destination[c++] = destination[i];
            }
        }
        for(int i = c; i<maxsize-1; i++)
        {
            destination[i]=0;
        }
       
    }
    
    /*
     * highest floor in entire list relative to current floor
     */
    public int nextUp(int curFloor)
    {
        int min = Integer.MAX_VALUE;
        for(int i =0; i<maxsize; i++)
        {
            if((destination[i] < min) && (destination[i] > curFloor))
            {
                min = destination[i];
            }
            
        }
        
        return min > 6 ? 0 : min; //if m>6 return 0, else return min

    }
    
    /*
     * lowest floor in entire list relative to current floor
     */
    public int nextDown(int curFloor)
    {
        int max = Integer.MIN_VALUE;
        for(int i =0; i<maxsize; i++)
        {
            if((destination[i] > max) && (destination[i] < curFloor))
            {
                max = destination[i];
            }
        }
        
        return max < 0 ? 0 : max;            
    }
    
    /*
     * checks to see if there are non-zero values in array
     * non-zero means there are destinations
     */
    public boolean destinationExist()
    {
        for(int i =0; i<maxsize; i++)
        {
            if(destination[i] != 0)
                return true;         
        }
        return false;
    }
    


}
