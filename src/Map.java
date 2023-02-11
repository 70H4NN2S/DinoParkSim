import java.util.*;

public class Map {
	private MapObject[][] map;
	private final Random random = new Random();

	public Map() {
		this.map = new MapObject[Settings.MAP_HEIGHT][Settings.MAP_WIDTH];
		createMap();
	}

	private void createMap() {
		createNaturalMap();
		//placeFences();
		//createDinos();
	}

	private void createNaturalMap()
	{
		for (int i = 0; i < Settings.MAP_HEIGHT; i++) {
			for (int j = 0; j < Settings.MAP_WIDTH; j++) {
				if (canPlaceWater(i, j)) {
					map[i][j] = new Water();
				} else {
					map[i][j] = new Ground();
				}
			}
		}
	}

	private boolean canPlaceWater(int i, int j) {
		if ((Math.pow(1/3d, (double)i) * Settings.MAP_WIDTH + random.nextInt(2)) > j ||
		(Settings.MAP_WIDTH - 1 - Math.pow(1/3d, (double)i) * Settings.MAP_WIDTH) < j)
		{
			return true;
		}
		else if ((Math.pow(1/3d, (double)(Settings.MAP_HEIGHT - i - 1)) * Settings.MAP_WIDTH) > j ||
		(Settings.MAP_WIDTH - 1 - Math.pow(1/3d, (double)(Settings.MAP_HEIGHT - i - 1)) * Settings.MAP_WIDTH - random.nextInt(2)) < j)
		{
			return true;
		}
		else if ((i < Settings.MAP_HEIGHT / 3) &&
		((map[i-1][j].getType() == MapObjectType.WATER && random.nextDouble(1d) < Math.pow(0.5d, (double)i * 2)) ||
		(map[i-1][j-1].getType() == MapObjectType.WATER || map[i-1][j+1].getType() == MapObjectType.WATER) &&
		random.nextDouble(1d) < Math.pow(0.3d, (double)i * 2)))
		{
			return true;
		}
		else if ((i > Settings.MAP_HEIGHT / 3 * 2) &&
		((canPlaceWaterMinimal(i+1, j) && random.nextDouble(1d) < Math.pow(0.5d, (double)(Settings.MAP_HEIGHT - i - 1) * 2))) ||
		((canPlaceWaterMinimal(i+1, j-1) || canPlaceWaterMinimal(i+1, j+1)) && random.nextDouble(1d) < Math.pow(0.3d, (double)(Settings.MAP_HEIGHT - i - 1) * 2)))
		{
			return true;
		}
		return false;
	}

	private boolean canPlaceWaterMinimal(int i, int j) {
		if ((Math.pow(1/3d, (double)i) * Settings.MAP_WIDTH + random.nextInt(2)) > j ||
		(Settings.MAP_WIDTH - 1 - Math.pow(1/3d, (double)i) * Settings.MAP_WIDTH) < j)
		{
			return true;
		}
		else if ((Math.pow(1/3d, (double)(Settings.MAP_HEIGHT - i - 1)) * Settings.MAP_WIDTH) > j ||
		(Settings.MAP_WIDTH - 1 - Math.pow(1/3d, (double)(Settings.MAP_HEIGHT - i - 1)) * Settings.MAP_WIDTH - random.nextInt(2)) < j)
		{
			return true;
		}
		return false;
	}

	public void testPrint() {
		for (int i = 0; i < Settings.MAP_HEIGHT; i++) {
			for (int j = 0; j < Settings.MAP_WIDTH; j++) {
				System.out.print(map[i][j].print());
			}
			System.out.println();
		}
	}
}
