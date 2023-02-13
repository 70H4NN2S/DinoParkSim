/**
 * The interface for all placable objects on the map.
 */
public interface MapObject
{
    public MapObjectType getType();
    public String print();
    public boolean isEdible();
    public boolean canMove();
}
