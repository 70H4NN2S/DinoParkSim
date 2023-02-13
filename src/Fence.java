public class Fence implements MapObject
{
	private final String COLOR_BLACK = "\u001B[30m"; // Black

	/**
	 * Returns the type of the object
	 *
	 * @return	MapObjectType	the type of the object
	 */
    @Override
    public MapObjectType getType() {
        return MapObjectType.FENCE;
    }

	/**
	 * Prints the object on the map
	 *
	 * @return	String	fence character X in black with a length of 5
	 */
    @Override
    public String print() {
        return COLOR_BLACK + "XXXXX";
    }

	/**
	 * Checks if the object is edible
	 *
	 * @return boolean	false
	 */
    @Override
    public boolean isEdible() {
        return false;
    }

	/**
	 * Checks if the object can move
	 *
	 * @return boolean	false because a fence can't move
	 */
    @Override
    public boolean canMove() {
        return false;
    }
}
