public class Water implements MapObject {
	private final MapObjectType type = MapObjectType.WATER;
	private final String COLOR_BLUE = "\u001B[34m";

	@Override
	public MapObjectType getType() {
		return type;
	}

	@Override
	public String print() {
		return COLOR_BLUE + "W";
	}

	@Override
	public boolean isEdible() {
		return false;
	}

	@Override
	public boolean canMove() {
		return false;
	}
}
