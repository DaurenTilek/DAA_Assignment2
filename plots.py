import os
import pandas as pd
import matplotlib.pyplot as plt

CSV_PATH = "results/results.csv"
PLOTS_DIR = "results/plots"

os.makedirs(PLOTS_DIR, exist_ok=True)

df = pd.read_csv(CSV_PATH)


def save_plot(data, x, y, group, title, ylabel, filename):
    plt.figure(figsize=(9, 6))

    for name, group_data in data.groupby(group):
        group_data = group_data.sort_values(x)

        plt.plot(
            group_data[x],
            group_data[y],
            marker="o",
            label=name
        )

    plt.xscale("log")

    plt.xlabel("n")
    plt.ylabel(ylabel)
    plt.title(title)
    plt.legend()
    plt.grid(True)

    plt.tight_layout()

    path = os.path.join(PLOTS_DIR, filename)

    plt.savefig(path, dpi=200)
    plt.close()

    print("Saved:", path)


# ============================================================
# W1 - RANDOM ACCESS
# ============================================================

w1 = df[df["workload"] == "W1"]

save_plot(
    w1,
    "n",
    "time_ms",
    "structure",
    "W1 - Random Access: Time vs n",
    "Median Time (ms)",
    "w1_time.png"
)

save_plot(
    w1,
    "n",
    "steps",
    "structure",
    "W1 - Random Access: Steps vs n",
    "Steps",
    "w1_steps.png"
)


# ============================================================
# W2 - SEARCH
# ============================================================

w2 = df[df["workload"] == "W2"]

save_plot(
    w2,
    "n",
    "time_ms",
    "structure",
    "W2 - Search: Time vs n",
    "Median Time (ms)",
    "w2_time.png"
)

save_plot(
    w2,
    "n",
    "comparisons",
    "structure",
    "W2 - Search: Comparisons vs n",
    "Comparisons",
    "w2_comparisons.png"
)


# ============================================================
# W3 - HEAD
# ============================================================

w3_head = df[
    (df["workload"] == "W3") &
    (df["variant"] == "head")
]

save_plot(
    w3_head,
    "n",
    "time_ms",
    "structure",
    "W3 Head - Insert & Remove: Time vs n",
    "Median Time (ms)",
    "w3_head_time.png"
)

save_plot(
    w3_head,
    "n",
    "moves",
    "structure",
    "W3 Head - Insert & Remove: Moves vs n",
    "Moves",
    "w3_head_moves.png"
)


# ============================================================
# W3 - MIDDLE
# ============================================================

w3_middle = df[
    (df["workload"] == "W3") &
    (df["variant"] == "middle")
]

save_plot(
    w3_middle,
    "n",
    "time_ms",
    "structure",
    "W3 Middle - Insert & Remove: Time vs n",
    "Median Time (ms)",
    "w3_middle_time.png"
)

save_plot(
    w3_middle,
    "n",
    "steps",
    "structure",
    "W3 Middle - Insert & Remove: Steps vs n",
    "Steps",
    "w3_middle_steps.png"
)


# ============================================================
# W4 - MIN HEAP
# ============================================================

w4 = df[df["workload"] == "W4"]

plt.figure(figsize=(9, 6))

plt.plot(
    w4["n"],
    w4["time_ms"],
    marker="o",
    label="MinHeap"
)

plt.xscale("log")

plt.xlabel("n")
plt.ylabel("Median Time (ms)")
plt.title("W4 - Priority Processing: Time vs n")
plt.legend()
plt.grid(True)

plt.tight_layout()

plt.savefig(
    os.path.join(PLOTS_DIR, "w4_time.png"),
    dpi=200
)

plt.close()

print("Saved: results/plots/w4_time.png")


# W4 operations

plt.figure(figsize=(9, 6))

plt.plot(
    w4["n"],
    w4["steps"],
    marker="o",
    label="Steps"
)

plt.plot(
    w4["n"],
    w4["moves"],
    marker="o",
    label="Moves"
)

plt.plot(
    w4["n"],
    w4["comparisons"],
    marker="o",
    label="Comparisons"
)

plt.xscale("log")

plt.xlabel("n")
plt.ylabel("Operations")
plt.title("W4 - Priority Processing: Operations vs n")
plt.legend()
plt.grid(True)

plt.tight_layout()

plt.savefig(
    os.path.join(PLOTS_DIR, "w4_operations.png"),
    dpi=200
)

plt.close()

print("Saved: results/plots/w4_operations.png")


print()
print("All plots generated successfully.")