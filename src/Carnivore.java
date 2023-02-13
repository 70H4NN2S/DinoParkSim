import java.util.*;

public class Carnivore extends Dino implements MapObject
{
    private final Random RANDOM = new Random();
	private final String COLOR_RED = "\u001B[31m"; // Red color

	/**
	 * Constructor of the class Carnivore where the id and species name are set
	 *
	 * @param id			unique id of the carnivore
	 * @param speciesName	species name of the carnivore
	 */
    public Carnivore(int id, String speciesName)
	{
        super(id, speciesName);
    }

	/**
	 * Returns the type of the object
	 *
	 * @return	MapObjectType	the type of the object
	 */
	@Override
    public MapObjectType getType()
	{
        return MapObjectType.CARNIVORE;
    }

	/**
	 * Returns the string representation of the object
	 *
	 * @return	String	the string representation of the object with a custom color
	 */
    @Override
    public String print()
	{
        return COLOR_RED + super.toString();
    }

	/**
	 * Says if the object is edible or not
	 *
	 * @return	boolean	true if the object is edible, false otherwise
	 */
    @Override
    public boolean isEdible()
	{
        return false;
    }

	/**
	 * Says if the object can move(still hasn't moved) or not
	 *
	 * @return	boolean	true if the object can move, false if the object has already moved
	 */
    @Override
    public boolean canMove()
	{
        return !super.getMoved();
    }

	/**
	 * Sets the moved attribute of the object
	 */
	public void setMoved(boolean hasMoved)
	{
		super.setMoved(hasMoved);
	}

	/**
	 * Says if the dino manages to kill a close herbivore or not
	 *
	 * @return	boolean	true if the dino manages to kill a close herbivore, false otherwise
	 */
    public boolean attemptEat()
	{
        return RANDOM.nextInt(3) == 1; // 1/3 chance of success
    }
}
