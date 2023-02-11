public class Ground implements MapObject {
	private final MapObjectType type = MapObjectType.GROUND;
	private final String COLOR_GREEN = "\u001B[32m";

	@Override
	public MapObjectType getType() {
		return type;
	}

	@Override
	public String print() {
		return COLOR_GREEN + "G";
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
