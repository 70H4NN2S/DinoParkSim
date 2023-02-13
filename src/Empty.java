public class Empty implements MapObject
{
	/**
	 * Gets the type of the object
	 *
	 * @return	MapObjectType	the type of the object
	 */
    @Override
    public MapObjectType getType()
	{
        return MapObjectType.EMPTY;
    }

	/**
	 * Prints 5 spaces
	 *
	 * @return	String	the string representation of the object
	 */
    @Override
    public String print()
	{
        return " ";
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
	 * Says if the object can move or not
	 *
	 * @return	boolean	true if the object can move, false otherwise
	 */
    @Override
    public boolean canMove()
	{
        return false;
    }
}
