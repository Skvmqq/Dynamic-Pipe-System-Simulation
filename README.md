# Dynamic Pipe System Simulation

A two-player Java Swing game set in a desert pipe network. The Plumber repairs and builds the water system while the Saboteur breaks pipe elements. Players must work against the 90-second countdown.

## Features

- Tile-based desert map with springs, pumps, pipes, and a cistern.
- Two simultaneous players with collision detection.
- Plumber actions for building, moving, repairing, picking up, and placing pipe elements.
- Saboteur actions for moving and breaking pipe elements.
- Sprite and map resources loaded from the project classpath.

## Requirements

- Java Development Kit (JDK) 8 or newer.
- A desktop environment capable of displaying Java Swing windows.

The project does not use Maven or Gradle. It can be compiled directly with `javac`.

## Compile and Run

From the repository root in PowerShell:

```powershell
$project = "."
$output = Join-Path $project "out"
$source = Join-Path $project "src"

New-Item -ItemType Directory -Force -Path $output | Out-Null
$files = Get-ChildItem $source -Recurse -Filter "*.java" | Select-Object -ExpandProperty FullName
javac -encoding UTF-8 -d $output $files
java -cp "$output;$source" main.Main
```

The `src` directory is included in the runtime classpath because the game loads images and `map.txt` from `src/main/res`.

## Controls

### Plumber

| Key | Action |
| --- | --- |
| Arrow keys | Move |
| `P` | Repair the tile currently occupied by the Plumber |
| `M` | Build a pump next to a cistern in the Plumber's facing direction |
| `N` | Build a pipe next to a cistern in the Plumber's facing direction |
| `Shift` | Pick up an adjacent pickable pipe or pump |
| `Ctrl` | Place the held pipe element on the adjacent tile |

### Saboteur

| Key | Action |
| --- | --- |
| `W` `A` `S` `D` | Move |
| `X` | Break the tile currently occupied by the Saboteur |

## Project Layout

The Java module is located at the repository root:

```text
src/
	Entity/       Player entity models
	GUI/          Player rendering and input behavior
	PipeElement/  Pipe, pump, spring, and cistern models
	Tile/         Tile definitions and map loading
	main/         Game loop, input, collision, and resources
```

Important files:

- `src/main/Main.java`: application entry point.
- `src/main/GamePanel.java`: Swing game panel and 60 FPS loop.
- `src/main/res/map.txt`: 16-column by 12-row level layout.
- `src/main/res/`: sprites, background images, and map data.



