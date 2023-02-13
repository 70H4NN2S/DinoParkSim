public class Settings {
	public static final int MAP_WIDTH = 200; // min 10
	public static final int MAP_HEIGHT = 40; // min 10
	public static final int INITIAL_HERBIVORES_COUNT = 10; // min 1
	public static final int INITIAL_CARNIVORES_COUNT = 5; // min 1
	public static final int INITIAL_FENCES_DENSITY = 10; // min 0, has to be smaller than ((MAP_WIDTH * MAP_HEIGHT * 0.7) - (INITIAL_HERBIVORES_COUNT + INITIAL_CARNIVORES_COUNT))
}
