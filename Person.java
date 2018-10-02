/* Wrapper class for person data
 * *encapsulation
 */
public class Person
{
    String name;
    int floorEnter;
    int floorExit;
    int count;
    String Travel; //true:elevator, false:stairs

    //constructor
    public Person(String Name, int floorEnter, int floorExit, int count, String Travel)
    {
        name = Name;
        this.floorEnter = floorEnter;
        this.floorExit = floorExit;
        this.Travel = Travel;
        this.count = count;
        
    }
    
    @Override
    public String toString()
    {
        return name + " " + floorEnter + " " + floorExit + " " +  count + " " + Travel;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getFloorEnter()
    {
        return floorEnter;
    }

    public void setFloorEnter(int floorEnter)
    {
        this.floorEnter = floorEnter;
    }

    public int getFloorExit()
    {
        return floorExit;
    }

    public void setFloorExit(int floorExit)
    {
        this.floorExit = floorExit;
    }
    
    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public String isTravel()
    {
        return Travel;
    }

    public void setTravel(String travel)
    {
        Travel = travel;
    }
      
}
