# DinoParkSim

A historical Java console simulation of a dinosaur ecosystem on a two-dimensional grid. Carnivores search neighbouring cells for prey, dinosaurs move through open spaces and the simulation reports population statistics over successive days.

## Run locally

Use a JDK with `javac` and `java` available. From the repository root:

```sh
mkdir -p out
javac -d out src/*.java
java -cp out GameMaster
```

The simulation uses terminal escape sequences to redraw the display. Press Ctrl+C to stop it.

## Configuration

Edit `src/Settings.java` before compiling:

| Setting | Default |
| --- | ---: |
| Map width | 60 |
| Map height | 20 |
| Day interval (milliseconds) | 800 |
| Initial herbivores | 20 |
| Initial carnivores | 5 |
| Obstacle density (%) | 10 |

Keep the map large enough to accommodate all animals and obstacles.

## Code overview

- `GameMaster`: simulation loop, interaction, movement and results.
- `Map`: grid initialization, random placement and population tracking.
- `Dino`, `Carnivore`, `Herbivore`: animal model.
- `MapObject`, `MapObjectType`, `Empty`, `Obstacle`, `Water`: grid object model.

## Status

Educational project from 2023, preserved as historical work. Random outcomes are not deterministic and the repository has no automated test suite. The commands above are provided from source inspection; the current version has not been independently built or run during this documentation update.
