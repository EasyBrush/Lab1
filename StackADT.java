/** Interface for StackHandle class
 * 
 * @author Bryan Cheung
 */
public abstract interface StackADT
{
    public boolean isEmpty();
    
    public void push(Person human);
    
    public Person pop();
    
    public Person peek();
    
    public boolean isFull();

}
