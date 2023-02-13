public class Settings {
	public static final int MAP_WIDTH = 60; // min 10
	public static final int MAP_HEIGHT = 20; // min 10
	public static final int UPDATE_DAY_SPEED = 800; // time in milliseconds between each day
	public static final int INITIAL_HERBIVORES_COUNT = 20; // min 1
	public static final int INITIAL_CARNIVORES_COUNT = 5; // min 1
	public static final int INITIAL_OBSTACLES_DENSITY = 10; // min 0, has to be smaller than ((MAP_WIDTH * MAP_HEIGHT * 0.7) - (INITIAL_HERBIVORES_COUNT + INITIAL_CARNIVORES_COUNT))
}
