import java.util.*;

public class Map
{
    private MapObject[][] map;
    private ArrayList<Dino> dinos;
	private int[] amountDinoSpecies = {0,0,0,0,0,0}; // T-Rex, Velociraptor, Spinosaurus, Triceratops, Stegosaurus, Diplodocus
	private final Random random = new Random();

	/**
	 * Creates a new map with the given size and number of dinos.
	 */
    public Map()
	{
        this.map = new MapObject[Settings.MAP_HEIGHT][Settings.MAP_WIDTH];
        this.dinos = new ArrayList<>();
        createMap();
    }

	/**
	 * Calls every method needed to create the map.
	 */
    private void createMap()
	{
        createDinos();
        placeDinosRandomly();
        placeFencesRandomly();
        fillMapWithEmpty();
    }

	/**
	 * Creates the dinos and adds them to the list.
	 */
    private void createDinos()
	{

        String[] carnivoreNames = {"T-Rex", "Velociraptor", "Spinosaurus"};
        String[] herbivoreNames = {"Triceratops", "Stegosaurus", "Diplodocus"};
		String tmpName;

        for (int i = 1; i <= Settings.INITIAL_HERBIVORES_COUNT; i++)
		{
			tmpName = herbivoreNames[random.nextInt(herbivoreNames.length)];
			incrementAmountDinoSpecies(tmpName);
            dinos.add(new Herbivore(i, tmpName));
        }

        for (int i = 1; i <= Settings.INITIAL_CARNIVORES_COUNT; i++)
		{
			tmpName = carnivoreNames[random.nextInt(carnivoreNames.length)];
			incrementAmountDinoSpecies(tmpName);
            dinos.add(new Carnivore(i, tmpName));
        }
    }

	/**
	 *	Increments the amount of a specific dino species.

	 * @param name	the name of the dino species
	 */
	private void incrementAmountDinoSpecies(String name)
	{
		switch (name)
		{
			case "T-Rex":
				amountDinoSpecies[0]++;
				break;
			case "Velociraptor":
				amountDinoSpecies[1]++;
				break;
			case "Spinosaurus":
				amountDinoSpecies[2]++;
				break;
			case "Triceratops":
				amountDinoSpecies[3]++;
				break;
			case "Stegosaurus":
				amountDinoSpecies[4]++;
				break;
			case "Diplodocus":
				amountDinoSpecies[5]++;
				break;
		}
	}

	/**
	 * places a dino randomly on the map.
	 */
    private void placeDinosRandomly()
	{
        for (Dino dino : dinos)
		{
            int x = random.nextInt(Settings.MAP_WIDTH);
            int y = random.nextInt(Settings.MAP_HEIGHT);

            while (map[y][x] != null)
			{
                x = random.nextInt(Settings.MAP_WIDTH);
                y = random.nextInt(Settings.MAP_HEIGHT);
            }
            map[y][x] = (MapObject) dino;
        }
    }

	/**
	 * places fences randomly on the map.
	 */
    private void placeFencesRandomly()
	{
        int numFences = (int) (Settings.MAP_WIDTH * Settings.MAP_HEIGHT * 0.1);

        for (int i = 0; i < numFences; i++)
		{
            int x = random.nextInt(Settings.MAP_WIDTH);
            int y = random.nextInt(Settings.MAP_HEIGHT);

            while (map[y][x] != null)
			{
                x = random.nextInt(Settings.MAP_WIDTH);
                y = random.nextInt(Settings.MAP_HEIGHT);
            }
            map[y][x] = new Fence();
        }
    }

	/**
	 * fills the map with empty objects.
	 */
    private void fillMapWithEmpty()
    {
        for (int y = 0; y < Settings.MAP_HEIGHT; y++)
		{
            for (int x = 0; x < Settings.MAP_WIDTH; x++)
			{
                if (map[y][x] == null)
				{
                    map[y][x] = new Empty();
                }
            }
        }
    }

	/**
	 * returns the Type of the object at the given coordinates.
	 *
	 * @param x	the x coordinate
	 * @param y	the y coordinate
	 * @return	the type of the object at the given coordinates
	 */
	public MapObjectType checkCoordinates(int x, int y)
	{
		if (x < 0 || x >= Settings.MAP_WIDTH || y < 0 || y >= Settings.MAP_HEIGHT)
		{
			return MapObjectType.OUT_OF_BOUNDS;
		}
		return map[y][x].getType();
	}

	/**
	 * returns the object at the given coordinates.
	 *
	 * @param x	the x coordinate
	 * @param y	the y coordinate
	 * @return	the object at the given coordinates
	 */
	public MapObject getCoordinates(int x, int y)
	{
		return map[y][x];
	}

	/**
	 * replaces the object at the given coordinates with the given object.
	 *
	 * @param x			the x coordinate
	 * @param y			the y coordinate
	 * @param mapObject	the object to replace the old object with
	 */
	public void setCoordinates(int x, int y, MapObject mapObject)
	{
		map[y][x] = mapObject;
	}

	/**
	 * returns the map as a string.
	 */
	@Override
	public String toString()
	{
		String output = "";

		for (int y = 0; y < Settings.MAP_HEIGHT; y++)
		{
			for (int x = 0; x < Settings.MAP_WIDTH; x++)
			{
				output += map[y][x].print();
			}
			output += "\n";
		}
		return output;
	}

	/**
	 * returns the amount of living T-Rexes.
	 *
	 * @return	the amount of T-Rexes placed on the map
	 */
	public int getTRexCount()
	{
		return amountDinoSpecies[0];
	}

	/**
	 * returns the amount of living Velociraptors.
	 *
	 * @return	the amount of Velociraptors placed on the map
	 */
	public int getVelociraptorCount()
	{
		return amountDinoSpecies[1];
	}

	/**
	 * returns the amount of living Spinosaurus.
	 *
	 * @return	the amount of Spinosaurus placed on the map
	 */
	public int getSpinosaurusCount()
	{
		return amountDinoSpecies[2];
	}

	/**
	 * returns the amount of living Triceratops.
	 *
	 * @return	the amount of Triceratops placed on the map
	 */
	public int getTriceratopsCount()
	{
		return amountDinoSpecies[3];
	}

	/**
	 * returns the amount of living Stegosaurus.
	 *
	 * @return	the amount of Stegosaurus placed on the map
	 */
	public int getStegosaurusCount()
	{
		return amountDinoSpecies[4];
	}

	/**
	 * returns the amount of living Diplodocus.
	 *
	 * @return	the amount of Diplodocus placed on the map
	 */
	public int getDiplodocusCount()
	{
		return amountDinoSpecies[5];
	}

	/**
	 * reduces the amount of living dinosaurs of the given species by one.
	 *
	 * @param name	the name of the species
	 */
	public void reduceDinoCount(String name)
	{
		switch (name)
		{
			case "T-Rex":
				amountDinoSpecies[0]--;
				break;
			case "Velociraptor":
				amountDinoSpecies[1]--;
				break;
			case "Spinosaurus":
				amountDinoSpecies[2]--;
				break;
			case "Triceratops":
				amountDinoSpecies[3]--;
				break;
			case "Stegosaurus":
				amountDinoSpecies[4]--;
				break;
			case "Diplodocus":
				amountDinoSpecies[5]--;
				break;
		}
	}
}
