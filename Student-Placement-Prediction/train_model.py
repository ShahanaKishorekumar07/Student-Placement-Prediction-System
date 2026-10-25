import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import LabelEncoder
import pickle

# Load dataset
data = pd.read_csv("placement_dataset.csv")

print("Dataset loaded successfully!")
print("Number of records:", len(data))

# Convert Placement column
encoder = LabelEncoder()
data["Placement"] = encoder.fit_transform(data["Placement"])

# Use the actual 5 features in the dataset
features = [
    "CGPA",
    "Attendance",
    "Internships",
    "Projects",
    "Backlogs"
]

X = data[features]
y = data["Placement"]

# Split dataset
X_train, X_test, y_train, y_test = train_test_split(
    X,
    y,
    test_size=0.2,
    random_state=42
)

# Create Random Forest model
model = RandomForestClassifier(
    n_estimators=100,
    random_state=42
)

# Train model
model.fit(X_train, y_train)

# Calculate accuracy
accuracy = model.score(X_test, y_test)

print("\nModel trained successfully!")
print("Model Accuracy:", round(accuracy * 100, 2), "%")

# Save model
with open("placement_model.pkl", "wb") as file:
    pickle.dump(model, file)

print("\nplacement_model.pkl created successfully!")

print("\nFeatures used by model:")
for feature in features:
    print("-", feature)

print("\nTraining completed successfully!")