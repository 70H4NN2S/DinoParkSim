public abstract class Dino
{
    private final int ID;
    private final String SPECIES_NAME;
    private boolean moved; // true if the dino has moved this turn

	/**
	 * Constructor of the class Dino where the id and species name are set
	 *
	 * @param ID			unique id of the dino
	 * @param speciesName	species name of the dino
	 */
    public Dino(int ID, String SPECIES_NAME)
	{
        this.ID = ID;
        this.SPECIES_NAME = SPECIES_NAME;
        this.moved = false;
    }

	/**
	 * Returns the ID of the dino
	 *
	 * @return	int	the ID of the dino
	 */
    public int getId()
	{
        return ID;
    }

	/**
	 * Returns the species name of the dino
	 *
	 * @return	String	the species name of the dino
	 */
    public String getSpeciesName()
	{
        return SPECIES_NAME;
    }

	/**
	 * Method to averiguate if the dino has moved or not
	 *
	 * @return	boolean	true if the dino has moved, false otherwise
	 */
    public boolean getMoved()
	{
        return moved;
    }

	/**
	 * Method to set the moved attribute of the dino
	 *
	 * @param moved	true if the dino has moved a certain day, false otherwise
	 */
    public void setMoved(boolean moved)
	{
        this.moved = moved;
    }

	/**
	 * Returns the string representation of the object
	 *
	 * @return	String	the string representation of the object that has always the same length
	 */
    @Override
    public String toString()
	{
        return SPECIES_NAME.substring(0,1);
    }
}
