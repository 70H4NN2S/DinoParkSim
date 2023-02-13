public class Herbivore extends Dino implements MapObject
{
	private final String COLOR_GREEN = "\u001B[32m"; // Green color

	/**
	 * Constructor of the class Herbivore where the id and species name are set
	 *
	 * @param id
	 * @param speciesName
	 */
    public Herbivore(int id, String speciesName)
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
        return MapObjectType.HERBIVORE;
    }

	/**
	 * Returns the string representation of the object
	 *
	 * @return	String	the string representation of the object with a custom color
	 */
    @Override
    public String print()
	{
        return COLOR_GREEN + "H:" + super.toString();
    }

	/**
	 * Says if the Dino is edible or not
	 *
	 * @return	boolean	true if the object is edible, false otherwise
	 */
    @Override
    public boolean isEdible()
	{
        return true;
    }

	/**
	 * Says if the dino can move(still hasn't moved) or not
	 *
	 * @return	boolean	true if the object can move, false if the object has already moved
	 */
    @Override
    public boolean canMove()
	{
        return !super.getMoved();
    }

	/**
	 * Sets the moved attribute of Dino
	 */
	public void setMoved(boolean hasMoved)
	{
		super.setMoved(hasMoved);
	}
}
