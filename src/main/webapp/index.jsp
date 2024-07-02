<%--Allows Me To Do Templating And Display Data From DB On Frontend--%>
<%@ page import="com.example.basicspringmvcwebapp.basicspringmvcwebappmaven.model.Car" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <body>
        <h2>Car Form</h2>

        <a href="/cars">View Cars In DB</a><br><br>

        <form id="carForm" action="/cars/post" method="POST" onsubmit="submitForm(e)">
            <label for="id">Id: ( Optional, Unless Trying To Update Or Delete )</label><br>
            <input type="number" id="id" name="id"><br>

            <label for="make">Make:</label><br>
            <input type="text" id="make" name="make" value="Lamborghini"><br>

            <label for="model">Model:</label><br>
            <input type="text" id="model" name="model" value="Aventador"><br>

            <label for="engineType">Engine Type ( V4, V6, V8 ):</label><br>
            <input type="text" id="engineType" name="engineType" value="v12"><br>

            <label for="mpg">MPG:</label><br>
            <input type="text" id="mpg" name="mpg" value="10"><br><br>

            <input type="submit" value="Submit"><br><br>
        </form>

        <button onclick="deleteCar()">Delete Car</button>
        <button onclick="updateCar()">Update Car</button>
        <button onclick="generateRandomCar()">Generate Random Car</button>

        <%-- Show Cars Fetched From The DB On The Frontend --%>

            <%
                List<Car> cars = (List<Car>) request.getAttribute("cars");
                for (Car car: cars) { %>
                    <p>Car: Id #: <%= car.getId() %> <%= car.getMake() %> <%= car.getModel() %> <%= car.getEngineType() %> <%= car.getMpg() %></p>
            <% } %>


        <script>
            async function submitForm(e) {
                e.preventDefault();
                var form = document.getElementById('carForm');
                var id = form.id.value;
                var make = form.make.value;
                var model = form.model.value;
                var engineType = form.engineType.value;
                var mpg = form.mpg.value;

                var car = {
                    id: id,
                    make: make,
                    model: model,
                    engineType: engineType,
                    mpg: mpg
                };

                await fetch('/cars/post/', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(car)
                })
                window.location.reload();
            }

            async function deleteCar() {
                var id = document.getElementById('id').value;
                await fetch('/cars/delete/' + id, {
                    method: 'DELETE',
                })
                window.location.reload();
            }

            async function updateCar() {
                var id = document.getElementById('id').value;
                var make = document.getElementById('make').value;
                var model = document.getElementById('model').value;
                var engineType = document.getElementById('engineType').value;
                var mpg = document.getElementById('mpg').value;

                await fetch('/cars/update/' + id, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({make: make, model: model, engineType: engineType, mpg: mpg}),
                })
                window.location.reload();
            }

            function generateRandomCar() {
                var cars = {
                    'Toyota': ['Corolla', 'Camry', 'Avalon'],
                    'Chevy': ['Corvette', 'Camaro'],
                    'Ford': ['Mustang', 'F150'],
                    'Nissan': ['Accord', 'Civic', 'GTR']
                };

                var engineTypes = ['V4', 'V6', 'V8', 'V12'];

                var makes = Object.keys(cars);
                var make = makes[Math.floor(Math.random() * makes.length)];
                var model = cars[make][Math.floor(Math.random() * cars[make].length)];

                var engineType = engineTypes[Math.floor(Math.random() * engineTypes.length)];
                var mpg = Math.floor(Math.random() * 50) + 10;

                document.getElementById('make').value = make;
                document.getElementById('model').value = model;
                document.getElementById('engineType').value = engineType;
                document.getElementById('mpg').value = mpg;
            }
        </script>
    </body>
</html>
