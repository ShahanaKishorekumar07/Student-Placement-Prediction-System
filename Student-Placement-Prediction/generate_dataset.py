import pandas as pd
import random

random.seed(42)

data = []

# Generate 500 students
for i in range(500):

    cgpa = round(random.uniform(5.0, 10.0), 2)
    attendance = round(random.uniform(50, 100), 1)
    internships = random.randint(0, 3)
    projects = random.randint(0, 4)
    backlogs = random.randint(0, 4)

    score = 0

    # CGPA
    if cgpa >= 8:
        score += 3
    elif cgpa >= 7:
        score += 2
    elif cgpa >= 6:
        score += 1

    # Attendance
    if attendance >= 80:
        score += 2
    elif attendance >= 70:
        score += 1

    # Internships
    score += internships

    # Projects
    if projects >= 3:
        score += 2
    elif projects >= 1:
        score += 1

    # Backlogs
    score -= backlogs * 2

    # Placement decision
    if score >= 6:
        placement = "Yes"
    else:
        placement = "No"

    data.append([
        cgpa,
        attendance,
        internships,
        projects,
        backlogs,
        placement
    ])


df = pd.DataFrame(
    data,
    columns=[
        "CGPA",
        "Attendance",
        "Internships",
        "Projects",
        "Backlogs",
        "Placement"
    ]
)

df.to_csv("placement_dataset.csv", index=False)

print("Dataset created successfully!")
print("Total students:", len(df))

print("\nPlacement distribution:")
print(df["Placement"].value_counts())

print("\nSample data:")
print(df.head(10))