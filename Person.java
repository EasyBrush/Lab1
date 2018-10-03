/**
 *  Wrapper class for person data
 *  encapsulation
 *  
 *  @author Bryan Cheung
 */
public class Person
{
    String name;
    int floorEnter;
    int floorExit;
    int count;
    String Travel; //true:elevator, false:stairs

    /**
     * constructor
     * @param Name
     * @param floorEnter
     * @param floorExit
     * @param count
     * @param Travel
     */
    public Person(String Name, int floorEnter, int floorExit, int count, String Travel)
    {
        name = Name;
        this.floorEnter = floorEnter;
        this.floorExit = floorExit;
        this.Travel = Travel;
        this.count = count;
        
    }
    /**
     * @return String representation of this class' variables
     */
    @Override
    public String toString()
    {
        return name + " " + floorEnter + " " + floorExit + " " +  count + " " + Travel;
    }

    /**
     * 
     * @return String representation of name
     */
    public String getName()
    {
        return name;
    }

    /**
     * sets String to name
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 
     * @return int representation of entry floor
     */
    public int getFloorEnter()
    {
        return floorEnter;
    }

    /**
     * sets entry floor
     * @param floorEnter int
     */
    public void setFloorEnter(int floorEnter)
    {
        this.floorEnter = floorEnter;
    }

    /**
     * 
     * @return int representation of exit floor
     */
    public int getFloorExit()
    {
        return floorExit;
    }

    /**
     * sets exit floor
     * @param floorExit
     */
    public void setFloorExit(int floorExit)
    {
        this.floorExit = floorExit;
    }
    /**
     * 
     * @return int representation of count
     */
    public int getCount()
    {
        return count;
    }

    /**
     * sets count
     * @param count
     */
    public void setCount(int count)
    {
        this.count = count;
    }

    /**
     * 
     * @return string representation of travel
     */
    public String isTravel()
    {
        return Travel;
    }

    /**
     * sets travel 
     * @param travel
     */
    public void setTravel(String travel)
    {
        Travel = travel;
    }
      
}
