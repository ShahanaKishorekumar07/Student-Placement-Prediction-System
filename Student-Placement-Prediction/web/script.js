document.getElementById("placementForm").addEventListener("submit", async function(event) {

    event.preventDefault();

    const result = document.getElementById("result");

    result.innerHTML = "⏳ Predicting...";
    result.style.backgroundColor = "#e0f2fe";
    result.style.color = "#0369a1";

    const studentData = {
        cgpa: document.getElementById("cgpa").value,
        attendance: document.getElementById("attendance").value,
        internships: document.getElementById("internships").value,
        projects: document.getElementById("projects").value,
        backlogs: document.getElementById("backlogs").value
    };

    try {

        const response = await fetch("/predict", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(studentData)
        });

        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.error || "Prediction failed");
        }

        if (data.prediction === "LIKELY TO BE PLACED") {

            result.innerHTML = "✅ LIKELY TO BE PLACED";
            result.style.backgroundColor = "#dcfce7";
            result.style.color = "#166534";

        } else {

            result.innerHTML = "❌ NOT LIKELY TO BE PLACED";
            result.style.backgroundColor = "#fee2e2";
            result.style.color = "#991b1b";
        }

    } catch (error) {

        console.error("Prediction Error:", error);

        result.innerHTML = "⚠️ Prediction failed: " + error.message;
        result.style.backgroundColor = "#fef3c7";
        result.style.color = "#92400e";
    }

});