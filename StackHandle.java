import java.nio.BufferOverflowException;
import java.util.EmptyStackException;

/** A stack of Person objects.
 * 
 * <p>The stack is implemented as an array with a maximum
 * size of "maxSize" elements. The size limitation is from the
 * maximum size of a simulated elevator.  
 * 
 * This class creates a simulation of Stacks using array.
 * Implementing stack using array.
 * 
 * @author Bryan Cheung
 * 
 */
public class StackHandle implements StackADT
{
    //String name;
    //int FloorEntered;
    //int FloorExit;
    
    //Person person = new Person(name, FloorEntered, FloorExit);
    
    private int maxSize;
    private Person[] data; 
    //make array with elements type Person
    private int top;
    
    
    /**
     * constructor
     * @param size
     */
    public StackHandle(int size)
    {
        maxSize = size;
        data = new Person[maxSize];
        top = 0;
        
    }
    /**
     * push element into top position of stack
     * @param Person
     */
    public void push(Person human) throws BufferOverflowException
    {
        if (top == maxSize)
        {
            throw new BufferOverflowException();
        }
        
        data[top] = human;
        top++;

    }
    /**
     * pops top element from stack and returns
     * @return Person
     */
    public Person pop() throws EmptyStackException
    {
        Person personToReturn;
        
        if(isEmpty())
        {
            throw new EmptyStackException();
        }
        
        personToReturn = data[top-1];
        data[top-1] = null;
        top--;
        
        
        return personToReturn;        

    }
    /**
     * check if stack is empty
     * @return boolean
     */
    public boolean isEmpty()
    {
        if(top == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * check top element on stack
     * @return Person
     */
    public Person peek() throws EmptyStackException
    {
        Person temp;
        
        if(isEmpty())
        {
            throw new EmptyStackException();
        }
        
        temp = pop();
        push(temp);
        
        return temp;
    }
    /**
     * Check if stack is full
     * @return boolean
     * @see StackADT#isFull()
     */
    public boolean isFull()
    {
        if (top >= maxSize)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * 
     * @return size of stack
     */
    public int getSize()
    {
        return top;
    }
}
