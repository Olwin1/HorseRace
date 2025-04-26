
# Horse Race Simulator

  A Simple Horse Racing Simulator
Contains Two versions:

- A text-based horse racing simulator

- A GUI based horse racing game

  

## Installation

  

1. Clone the repository:

  

```bash

git  clone  https://github.com/Olwin1/HorseRaceSimulator.git

cd  HorseRaceSimulator

```

  

2. Choose which version to run (Part 1 or Part 2):

	 ### For Part 1 - The textual version.
	 1. Switch to the correct directory
		```bash
		cd Part1
		```

	2. Compile the project
		```bash
		gradle build
		```
	3. Run the program
		```bash
		gradle run
		```

	 ### For Part 2 - The GUI version.
	 1. Switch to the correct directory
		```bash
		cd Part2
		```

	2. Compile the project
		```bash
		gradle build
		```
	3. Run the program
		```bash
		gradle run
		```

## Dependencies

This project requires the following dependencies:

- **JDK 24+**: This was developed with OpenJDK version 24.  Earlier versions may run into issues.  
  [Download JDK](https://openjdk.org//)

- **Gradle 8.14+** For JDK 24 version 8.14 of Gradle is required. Although you could probably get away with a lower version if you're also running a older version of JDK.  
	[Download Gradle](https://gradle.org/install/)

For **Part 1**:
- No additional dependencies are required.  

For **Part 2**:
- No additional dependencies are required.  


# How to use

1. To begin with you will be faced with various different horses when you start the game.  This is your lane setup.  You can press the blue up and down arrow to move a horse up or down a lane position.  You can also press the green button to add a lane to the bottom of the list along with the red button to delete the current lane. 

2. When you click a lane a horse options menu will appear on the right hand side of the screen. This will allow you to adjust various different aspects of the horse such as its name, colour, or even accessories.  This can be toggled by the "Has saddle" button which gives the horse a saddle and bumps up its confidence by a bit.  

3. Once you are happy with your lane setup you can adjust the number of races to run along with the distance of those races at the bottom of the screen.

4. Following that, you can press "Start Race" to be sent to the betting screen.  Here you will be able to place bets on the horses.  Up to three users can place bets and all winnings are calculated at the end of all races.  This screen will be shown after each race so the bets are taken per race and not for the whole round.  

5. Once a race begins the camera will scroll to follow the horses.  The weather will be randomly chosen from three options which also affect betting odds.  A horse may fall or outrun the camera or even be too slow for the camera but rest assured, the race is still running.  When the race completes and you are sent back to the betting page the winners of the race that just ran will be displayed on the right hand side.  To start the next race simply press "Start Race".  

6. When all races have finished you will be presented with the finishing screen.  This will display the position history of each horse along with each User's total winnings.  
Additionally like with the betting the last race results can be seen in the same location. 
7. To start a round again simply reload the program.   