import tkinter as tk
from tkinter import messagebox, filedialog
import random
import os

# Global leaderboard
leaderboard = []


def generate_Random_DNA(length=10):
    """Generate a random DNA sequence of a given length."""
    return ''.join(random.choice("ACGT") for _ in range(length))


def lcs(seq1, seq2):
    """Calculate the LCS using DP and return the DP table and LCS string."""
    n, m = len(seq1), len(seq2)
    dp = [[0] * (m + 1) for _ in range(n + 1)]

    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if seq1[i - 1] == seq2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1] + 1
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

    # Backtrack to find the LCS string
    lcs = []
    i, j = n, m
    while i > 0 and j > 0:
        if seq1[i - 1] == seq2[j - 1]:
            lcs.append(seq1[i - 1])
            i -= 1
            j -= 1
        elif dp[i - 1][j] > dp[i][j - 1]:
            i -= 1
        else:
            j -= 1

    lcs.reverse()
    return dp, ''.join(lcs)


def validate_DNA(sequence):
    """Validate if the sequence contains only valid DNA bases."""
    return all(base in "ACGT" for base in sequence)


def visualize_LCS(seq1, seq2, canvas):
    """Visualize the LCS DP table dynamically."""
    dp, _ = lcs(seq1, seq2)
    n, m = len(seq1), len(seq2)

    canvas.delete("all")
    cell_size = 30

    # Draw grid with animations
    for i in range(n + 1):
        for j in range(m + 1):
            x1, y1 = j * cell_size, i * cell_size
            x2, y2 = x1 + cell_size, y1 + cell_size

            canvas.create_rectangle(x1, y1, x2, y2, fill="white", outline="black")
            canvas.create_text(x1 + 15, y1 + 15, text=str(dp[i][j]))
            canvas.update()

    # Display sequences along axes
    for i in range(n):
        canvas.create_text(15, (i + 1) * cell_size + 15, text=seq1[i], font=("Arial", 10))
    for j in range(m):
        canvas.create_text((j + 1) * cell_size + 15, 15, text=seq2[j], font=("Arial", 10))


def calculate_And_Display_LCS():
    """Calculate LCS and update the GUI with results."""
    seq1, seq2 = seq1_var.get().strip().upper(), seq2_var.get().strip().upper()

    if not seq1 or not seq2:
        messagebox.showerror("Input Error", "Please enter both DNA sequences.")
        return

    if not validate_dna(seq1) or not validate_dna(seq2):
        messagebox.showerror("Input Error", "DNA sequences can only contain A, C, G, T.")
        return

    dp, lcs_string = lcs(seq1, seq2)
    match_percentage = (len(lcs_string) / max(len(seq1), len(seq2))) * 100
    result_var.set(f"LCS: {lcs_string} | Match: {match_percentage:.2f}%")

    visualize_lcs(seq1, seq2, canvas)

    # Update leaderboard
    leaderboard.append({"player": player_name_var.get(), "score": match_percentage})
    leaderboard.sort(key=lambda x: x["score"], reverse=True)


def generate_Random_Sequences():
    """Generate random DNA sequences."""
    seq1_var.set(generate_random_dna(10))
    seq2_var.set(generate_random_dna(10))


def save_To_File():
    """Save DNA matching results to a file."""
    filepath = filedialog.asksaveasfilename(defaultextension=".txt", filetypes=[("Text Files", "*.txt")])
    if filepath:
        with open(filepath, "w") as file:
            file.write(f"Player: {player_name_var.get()}\n")
            file.write(f"Sequence 1: {seq1_var.get()}\n")
            file.write(f"Sequence 2: {seq2_var.get()}\n")
            file.write(f"{result_var.get()}\n")
        messagebox.showinfo("Save Successful", f"Results saved to {os.path.basename(filepath)}")


def load_From_File():
    """Load DNA sequences from a file."""
    filepath = filedialog.askopenfilename(filetypes=[("Text Files", "*.txt")])
    if filepath:
        with open(filepath, "r") as file:
            lines = file.readlines()
            seq1_var.set(lines[1].split(": ")[1].strip())
            seq2_var.set(lines[2].split(": ")[1].strip())


def show_Leader_board():
    """Display the leaderboard in a new window."""
    leaderboard_window = tk.Toplevel(root)
    leaderboard_window.title("Leaderboard")
    tk.Label(leaderboard_window, text="Player", font=("Arial", 12, "bold")).grid(row=0, column=0, padx=10, pady=5)
    tk.Label(leaderboard_window, text="Score (%)", font=("Arial", 12, "bold")).grid(row=0, column=1, padx=10, pady=5)

    for idx, entry in enumerate(leaderboard[:10]):
        tk.Label(leaderboard_window, text=entry["player"]).grid(row=idx + 1, column=0, padx=10, pady=5)
        tk.Label(leaderboard_window, text=f"{entry['score']:.2f}").grid(row=idx + 1, column=1, padx=10, pady=5)


# Create the main window
root = tk.Tk()
root.title("Advanced DNA Matching Game")

# Variables
seq1_var, seq2_var, player_name_var, result_var = tk.StringVar(), tk.StringVar(), tk.StringVar(value="Player"), tk.StringVar()

# GUI Layout
frame = tk.Frame(root, padx=10, pady=10)
frame.pack()

tk.Label(frame, text="Player Name:").grid(row=0, column=0, sticky="w")
tk.Entry(frame, textvariable=player_name_var).grid(row=0, column=1, columnspan=2, sticky="we")

tk.Label(frame, text="Sequence 1:").grid(row=1, column=0, sticky="w")
tk.Entry(frame, textvariable=seq1_var).grid(row=1, column=1)

tk.Label(frame, text="Sequence 2:").grid(row=2, column=0, sticky="w")
tk.Entry(frame, textvariable=seq2_var).grid(row=2, column=1)

tk.Button(frame, text="Generate Random Sequences", command=generate_random_sequences).grid(row=1, column=2, rowspan=2, padx=5)
tk.Button(frame, text="Calculate LCS", command=calculate_and_display_lcs).grid(row=3, column=0, columnspan=3, pady=10)

tk.Label(frame, textvariable=result_var, font=("Arial", 12, "bold")).grid(row=4, column=0, columnspan=3)

canvas = tk.Canvas(root, width=600, height=300, bg="white")
canvas.pack(pady=10)

tk.Button(root, text="Save Results", command=save_to_file).pack(side="left", padx=5)
tk.Button(root, text="Load DNA Sequences", command=load_from_file).pack(side="left", padx=5)
tk.Button(root, text="Show Leaderboard", command=show_leaderboard).pack(side="right", padx=5)

root.mainloop()

