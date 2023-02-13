import java.util.*;

public class Map
{
    private MapObject[][] map;
    private ArrayList<Dino> dinos;
    private final int mapSize;
    private final int numHerbivores;
    private final int numCarnivores;
    private final Random random = new Random();
	private int[] amountDinoSpecies = {0,0,0,0,0,0}; // T-Rex, Velociraptor, Spinosaurus, Triceratops, Stegosaurus, Diplodocus

	/**
	 * Creates a new map with the given size and number of dinos.
	 *
	 * @param mapSize		the size of the map
	 * @param numHerbivores	the number of herbivores
	 * @param numCarnivores	the number of carnivores
	 */
    public Map(int mapSize, int numHerbivores, int numCarnivores)
	{
        this.mapSize = mapSize;
        this.numHerbivores = numHerbivores;
        this.numCarnivores = numCarnivores;
        this.map = new MapObject[mapSize][mapSize];
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
        testOutput();
    }

	/**
	 * Creates the dinos and adds them to the list.
	 */
    private void createDinos()
	{

        String[] carnivoreNames = {"T-Rex", "Velociraptor", "Spinosaurus"};
        String[] herbivoreNames = {"Triceratops", "Stegosaurus", "Diplodocus"};
		String tmpName;

        for (int i = 1; i <= numHerbivores; i++)
		{
			tmpName = herbivoreNames[random.nextInt(herbivoreNames.length)];
			incrementAmountDinoSpecies(tmpName);
            dinos.add(new Herbivore(i, tmpName));
        }

        for (int i = 1; i <= numCarnivores; i++)
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
            int x = random.nextInt(mapSize);
            int y = random.nextInt(mapSize);

            while (map[x][y] != null)
			{
                x = random.nextInt(mapSize);
                y = random.nextInt(mapSize);
            }
            map[x][y] = (MapObject) dino;
        }
    }

	/**
	 * places fences randomly on the map.
	 */
    private void placeFencesRandomly()
	{
        int numFences = (int) (mapSize * mapSize * 0.1);

        for (int i = 0; i < numFences; i++)
		{
            int x = random.nextInt(mapSize);
            int y = random.nextInt(mapSize);

            while (map[x][y] != null)
			{
                x = random.nextInt(mapSize);
                y = random.nextInt(mapSize);
            }
            map[x][y] = new Fence();
        }
    }

	/**
	 * fills the map with empty objects.
	 */
    private void fillMapWithEmpty()
    {
        for (int x = 0; x < mapSize; x++)
		{
            for (int y = 0; y < mapSize; y++)
			{
                if (map[x][y] == null)
				{
                    map[x][y] = new Empty();
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
		if (x < 0 || x >= mapSize || y < 0 || y >= mapSize)
		{
			return MapObjectType.OUT_OF_BOUNDS;
		}
		return map[x][y].getType();
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
		return map[x][y];
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
		map[x][y] = mapObject;
	}

	/**
	 * prints the starting map to the console.
	 * usefull for debugging.
	 * use toString() for everything else
	 * @see #toString()
	 */
	public void testOutput()
	{
        for (int i = 0; i < mapSize; i++)
		{
            for (int j = 0; j < mapSize; j++)
			{
                System.out.print(map[i][j].print());
            }
            System.out.println();
        }
    }

	/**
	 * returns the map as a string.
	 */
	@Override
	public String toString()
	{
		String output = "";

		for (int x = 0; x < mapSize; x++)
		{
			for (int y = 0; y < mapSize; y++)
			{
				output += map[x][y].print();
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
