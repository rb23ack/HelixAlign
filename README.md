## HelixAlign

The HelixAlign is an advanced DNA Matching Game is an interactive Python-based game that combines dynamic programming, DNA sequence matching, and visualization to create a fun and educational experience. The game uses the Longest Common Subsequence (LCS) algorithm to compare DNA sequences and visualize the matching process step-by-step. Players can compete, track scores on a leaderboard, and interact with the game using a GUI built with Tkinter.

## Features
- Interactive GUI
- Built with Tkinter for user-friendly interaction.
- Visualizes the DNA matching process dynamically.
- DNA Sequence Matching

- Uses the Longest Common Subsequence (LCS) algorithm to calculate the similarity between DNA sequences.
- Displays the percentage match and the LCS string.
- Random DNA Sequence Generator
- Generate random DNA sequences for quick gameplay.
  
## Leaderboard System

- Keeps track of player scores based on matching percentage.
- Displays sorted scores for competitive gameplay.
- Real DNA File Import/Export

- Load DNA sequences from a file.
- Save matching results (player name, sequences, matching percentage) to a file.
- Dynamic Visualization

- Step-by-step visualization of the LCS DP matrix for better understanding.

## How to Play

Input DNA Sequences

Enter two DNA sequences manually or generate random ones using the "Generate Random Sequences" button.
Calculate LCS

Click "Calculate LCS" to find the Longest Common Subsequence and the matching percentage.
The result will be displayed below the input fields.
Visualize Matching

The game will display the dynamic programming (DP) table step-by-step on the canvas.
Save and Load DNA Sequences

Use the "Save Results" button to save your results to a file.
Use the "Load DNA Sequences" button to load sequences from a file.
Track Scores

Play multiple rounds and check the Leaderboard to see top scores.

## Usage
Main Buttons
Generate Random Sequences: Auto-generate two random DNA sequences.
Calculate LCS: Compute the longest common subsequence and display results.
Save Results: Save your results to a text file.
Load DNA Sequences: Load sequences from a file for matching.
Show Leaderboard: View the top scores.
Visualization
A dynamic programming (DP) matrix will appear in the canvas, showing the matching process step-by-step.

## Leaderboard System
Scores are calculated based on the percentage of similarity between the two DNA sequences. The higher the percentage, the better your score! The leaderboard ranks players in descending order of scores.

## File Import/Export
Save Results:
Save the player name, DNA sequences, and match details to a .txt file for later reference.

Load Sequences:
Import DNA sequences from a .txt file and compare them.

Example File Format for Loading DNA Sequences:


Player: Player1
Sequence 1: ACGTACGT
Sequence 2: GTCACGTA

## Game Rules
The DNA sequences must only contain characters from the set {A, C, G, T}.
The sequences can be of any length, but shorter sequences will calculate faster.
Compete to achieve the highest matching percentage and top the leaderboard!

## License
This project is licensed under the MIT License. See the LICENSE file for details.
