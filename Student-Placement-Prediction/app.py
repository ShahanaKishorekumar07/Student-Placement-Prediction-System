from flask import Flask, request, jsonify, render_template, send_from_directory
import pickle

app = Flask(__name__)

# Load ML model
try:
    with open("placement_model.pkl", "rb") as file:
        model = pickle.load(file)

    print("ML model loaded successfully!")

except Exception as e:
    print("ERROR loading model:", e)
    model = None


@app.route("/")
def home():
    return render_template("index.html")


@app.route("/web/<path:filename>")
def web_files(filename):
    return send_from_directory("web", filename)


@app.route("/predict", methods=["POST"])
def predict():

    try:

        data = request.get_json()

        print("Received data:", data)

        features = [[
            float(data["cgpa"]),
            float(data["attendance"]),
            int(data["internships"]),
            int(data["projects"]),
            int(data["backlogs"])
        ]]

        print("Features:", features)

        prediction = model.predict(features)[0]

        print("Prediction:", prediction)

        if prediction == 1:
            result = "LIKELY TO BE PLACED"
        else:
            result = "NOT LIKELY TO BE PLACED"

        return jsonify({
            "prediction": result
        })

    except Exception as e:

        print("PREDICTION ERROR:", e)

        return jsonify({
            "error": str(e)
        }), 500


if __name__ == "__main__":
    app.run(debug=True)