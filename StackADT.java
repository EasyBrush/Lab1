/** Interface for StackHandle class
 * 
 * @author Bryan Cheung
 */
public abstract interface StackADT
{
    //checks if empty
    public boolean isEmpty();
    //adds to stack
    public void push(Person human);
    //remove from stack
    public Person pop();
    //check first element of stack
    public Person peek();
    //check if stack is full
    public boolean isFull();

}
