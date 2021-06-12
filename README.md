# Freecell-Solitaire

This project is a Java implementation of Freecell Solitaire. Some notes:
- It uses the MVC design pattern to organize the functionality into distinct areas (rules + functionality, view). 
- Testing has been implemented through Junit; all test files can be found within the test folder.
- This implementation works through the console, and allows the player to play by typing commands. For example, "C3 1 F1" would be a move from the 1st card of the 3rd cascade pile to the 1st foundation pile. If a invalid command is given, the program reprompts the user.
- Both single moves and multiple moves are possible depending on the mode that you give the controller. 
