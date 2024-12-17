from flask import Flask, render_template, jsonify
import requests

app = Flask(__name__)

# Backend URL
BACKEND_URL = "http://localhost:8080/mammals"

@app.route("/")
def home():
    try:
        # Fetch data from backend
        response = requests.get(BACKEND_URL)
        mammals = response.json()
        return render_template("index.html", mammals=mammals)
    except requests.exceptions.RequestException as e:
        return f"Error fetching data from backend: {e}", 500

@app.route("/api/mammals")
def api_mammals():
    try:
        # Fetch data from backend
        response = requests.get(BACKEND_URL)
        mammals = response.json()
        return jsonify(mammals)
    except requests.exceptions.RequestException as e:
        return jsonify({"error": f"Error fetching data from backend: {e}"}), 500

# HTML Template
HTML_TEMPLATE = """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mammals List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
    </style>
</head>
<body>
    <h1>Mammals Information</h1>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Species</th>
                <th>Habitat</th>
                <th>Diet</th>
                <th>Lifespan</th>
                <th>DNA Sequence</th>
            </tr>
        </thead>
        <tbody>
            {% for mammal in mammals %}
            <tr>
                <td>{{ mammal.name }}</td>
                <td>{{ mammal.species }}</td>
                <td>{{ mammal.habitat }}</td>
                <td>{{ mammal.diet }}</td>
                <td>{{ mammal.lifespan }}</td>
                <td>{{ mammal.dnaSequence }}</td>
            </tr>
            {% endfor %}
        </tbody>
    </table>
</body>
</html>
"""

# Save temp file yy
with open("templates/index.html", "w") as file:
    file.write(HTML_TEMPLATE)

if __name__ == "__main__":
    app.run(debug=True)
