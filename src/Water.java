public class Water implements MapObject {
	private final MapObjectType type = MapObjectType.WATER;
	private final String COLOR_BLUE = "\u001B[34m";
	private final String BACKGROUND_BLUE = "\u001B[44m";
	private String wave;

	public Water(int index) {
		this.wave = (index % 2 == 0) ? "~" : "-";
	}

	@Override
	public MapObjectType getType() {
		return type;
	}

	@Override
	public String print() {
		this.wave = (this.wave == "~") ? "-" : "~";
		return this.COLOR_BLUE + this.BACKGROUND_BLUE + this.wave;
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
