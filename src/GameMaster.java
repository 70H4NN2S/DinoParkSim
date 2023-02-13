import java.util.*;
import java.lang.Thread;

public class GameMaster extends Thread
{
	private final static int MAP_SIZE = 20;
	private static int NUM_HERBIVORES = 20;
	private static int NUM_CARNIVORES = 5;
	private static Map map = new Map(MAP_SIZE, NUM_HERBIVORES, NUM_CARNIVORES);
	private final static Random random = new Random();

	//stats
	private static int casesHerbivoresEscaped = 0;

	/**
	 * Starting point of the simulation.
	 *
	 * @param args	arguments given to the program
	 */
	public static void main(String[] args)
	{
		gameLoop();
	}

	/**
	 * The main game loop.
	 */
	private static void gameLoop()
	{
		int day = 0;

		while (NUM_HERBIVORES > 0)
		{
			day++;
			carnivoresEat();
			moveDinos();
			printDayResults(day);

			// slow down the game
			try
			{
				Thread.sleep(1000); // 1000 milliseconds is one second.
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.print("\033[H\033[2J"); // clears the screen
		}
		printGameResults(day);
	}

	/**
	 * searches for carnivores and calls the method to search for food and eat.
	 */
	private static void carnivoresEat()
	{
		for (int x = 0; x < MAP_SIZE; x++)
		{
			for (int y = 0; y < MAP_SIZE; y++)
			{
				if (map.checkCoordinates(x, y) == MapObjectType.CARNIVORE)
				{
					carnivoresSearchFoodAndEat(x, y);
				}
			}
		}
	}

	/**
	 * Searches the radius of the carnivore for food and eats it if it finds any.
	 * @param posX	the x coordinate of the carnivore
	 * @param posY	the y coordinate of the carnivore
	 */
	private static void carnivoresSearchFoodAndEat(int posX, int posY)
	{
		int[] xCoords = {posX - 1, posX, posX + 1};
		int[] yCoords = {posY - 1, posY, posY + 1};

		for (int x = 0; x < 3; x++)
		{
			for (int y = 0; y < 3; y++)
			{
				if (map.checkCoordinates(xCoords[x], yCoords[y]) == MapObjectType.HERBIVORE) // if there is a herbivore in the radius
				{
					if (((Carnivore) map.getCoordinates(posX, posY)).attemptEat()) // 33% chance of eating
					{
						String speciesName = ((Dino) map.getCoordinates(xCoords[x], yCoords[y])).getSpeciesName();
						map.reduceDinoCount(speciesName);
						map.setCoordinates(xCoords[x], yCoords[y], new Empty());
						NUM_HERBIVORES--;
					}
					else
					{
						casesHerbivoresEscaped++;
					}
					break;
				}
			}
		}
	}

	/**
	 * 	moves the dinos to a random empty space next to them.
	 */
	private static void moveDinos()
	{
		resetDinosMove();
		for (int x = 0; x < MAP_SIZE; x++)
		{
			for (int y = 0; y < MAP_SIZE; y++)
			{
				if (isDino(x, y))
				{
					if (spaceToMoveExists(x, y) && map.getCoordinates(x, y).canMove()) // if there is space to move and the dino has not moved yet
					{
						moveDino(x, y);
					}
				}
			}
		}
	}

	/**
	 * resets the moved boolean of all dinos to false.
	 */
	private static void resetDinosMove()
	{
		for (int x = 0; x < MAP_SIZE; x++)
		{
			for (int y = 0; y < MAP_SIZE; y++)
			{
				if (isDino(x, y))
				{
					((Dino) map.getCoordinates(x, y)).setMoved(false);
				}
			}
		}
	}

	/**
	 * checks if the object at the given coordinates is a dino.
	 *
	 * @param x	the x coordinate
	 * @param y	the y coordinate
	 * @return
	 */
	private static boolean isDino(int posX, int posY)
	{
		return map.checkCoordinates(posX, posY) == MapObjectType.CARNIVORE || map.checkCoordinates(posX, posY) == MapObjectType.HERBIVORE;
	}

	/**
	 * checks if there is space to move for the dino at the given coordinates.
	 *
	 * @param posX	the current x coordinate of the dino
	 * @param y	the current y coordinate of the dino
	 * @return	true if there is space to move, false if not
	 */
	private static boolean spaceToMoveExists(int posX, int posY)
	{
		int[] xCoords = {posX - 1, posX, posX + 1};
		int[] yCoords = {posY - 1, posY, posY + 1};

		for (int x = 0; x < 3; x++)
		{
			for (int y = 0; y < 3; y++)
			{
				// if there is an empty space in the radius
				if (map.checkCoordinates(xCoords[x], yCoords[y]) == MapObjectType.EMPTY && !outOfBorder(xCoords[x], yCoords[y]))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * moves the dino at the given coordinates to a random empty space next to it.
	 *
	 * @param currentX	the current x coordinate of the dino
	 * @param currentY	the current y coordinate of the dino
	 */
	private static void moveDino(int currentX, int currentY)
	{
		int newX = random.nextInt(3) - 1 + currentX;
		int newY = random.nextInt(3) - 1 + currentY;

		// if the new coordinates are out of bounds or occupied, generate new coordinates
		while (map.checkCoordinates(newX, newY) != MapObjectType.EMPTY || (newX == currentX && newY == currentY) || outOfBorder(newX, newY))
		{
			newX = random.nextInt(3) - 1 + currentX;
			newY = random.nextInt(3) - 1 + currentY;
		}

		map.setCoordinates(newX, newY, map.getCoordinates(currentX, currentY));
		map.setCoordinates(currentX, currentY, new Empty());
		((Dino) map.getCoordinates(newX, newY)).setMoved(true);
	}

	/**
	 * checks if the given coordinates are out of bounds.
	 *
	 * @param x	x position to be checked if it is out of bounds
	 * @param y	y position to be checked if it is out of bounds
	 * @return	true if the coordinates are out of bounds, false if not
	 */
	private static boolean outOfBorder(int x, int y)
	{
		return x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE;
	}

	/**
	 * prints the results of the day.
	 *
	 * @param day	the current day (used for stats)
	 */
	private static void printDayResults(int day)
	{
		// title
		System.out.println("\u001B[35m"); // purple color
		System.out.println("\u001B[1m"); // bold
		System.out.println( " ____  _               ____            _    \n" +
						    "|  _ \\(_)_ __   ___   |  _ \\ __ _ _ __| | __\n" +
							"| | | | | '_ \\ / _ \\  | |_) / _` | '__| |/ /\n" +
							"| |_| | | | | | (_) | |  __/ (_| | |  |   < \n" +
							"|____/|_|_| |_|\\___/  |_|   \\__,_|_|  |_|\\_\\ \n");

		System.out.println("\u001B[36m"); // cyan color
		System.out.println("\u001B[35mDay: " + day + " \u001B[36m| \u001B[31mCarnivores: \u001B[35m" + NUM_CARNIVORES + " \u001B[36m| \u001B[32mHerbivores: \u001B[35m" + NUM_HERBIVORES);

		//table
		System.out.println("\n\u001B[36m#################################################################");
		System.out.println("#\t\u001B[35mHerbivores\t\t\u001B[36m#\t\u001B[35mCarnivores\t\t\u001B[36m#");
		System.out.println("#===============================#===============================#");
		System.out.printf("#\t\u001B[31mTRex\t\t\u001B[35m%d\t\u001B[36m#\t\u001B[32mTrizeratops\t\u001B[35m%d\t\u001B[36m#\n", map.getTRexCount(), map.getTriceratopsCount());
		System.out.printf("#\t\u001B[31mVelociraptor\t\u001B[35m%d\t\u001B[36m#\t\u001B[32mStegosaurus\t\u001B[35m%d\t\u001B[36m#\n", map.getVelociraptorCount(), map.getStegosaurusCount());
		System.out.printf("#\t\u001B[31mSpinosaurus\t\u001B[35m%d\t\u001B[36m#\t\u001B[32mDiplodocus\t\u001B[35m%d\t\u001B[36m#\n", map.getSpinosaurusCount(), map.getDiplodocusCount());
		System.out.println("#################################################################");

		// map
		System.out.println("\n\u001B[47m");// green background
		System.out.println(map.toString());
		System.out.println("\u001B[0m"); // reset color
		System.out.println("\u001B[49m");// reset background
	}

	/**
	 * prints the results of the game.
	 *
	 * @param day	the amount of days the game lasted
	 */
	private static void printGameResults(int day)
	{
		System.out.println("\u001B[35m"); // purple color
		System.out.println("\u001B[1m"); // bold
		System.out.println( " ____  _               ____            _    \n" +
						    "|  _ \\(_)_ __   ___   |  _ \\ __ _ _ __| | __\n" +
							"| | | | | '_ \\ / _ \\  | |_) / _` | '__| |/ /\n" +
							"| |_| | | | | | (_) | |  __/ (_| | |  |   < \n" +
							"|____/|_|_| |_|\\___/  |_|   \\__,_|_|  |_|\\_\\ \n");

		System.out.println("\n\u001B[47m");// green background
		System.out.println(map.toString());
		System.out.println("\u001B[0m"); // reset color
		System.out.println("\u001B[49m");// reset background

		System.out.println("\n\nThe simulation of the dino park has ended. Here are the results:\n");
		System.out.println("It took " + day + " days for the herbivores to go extinct.");
		System.out.println("The herbivores escaped " + casesHerbivoresEscaped + " times which left the carnivores starving for another day.");
		System.out.println("But that is everything that happened in the park. The carnivores are still alive and well. Until they are not.");
		System.out.println("The end.");

	}
}
