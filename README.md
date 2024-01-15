# DeliverEase 🚛📦
### Capstone Project

## 🎸 Developer Team 🎸 
- [Albert](https://github.com/Al-B-code) 
- [Billie](https://github.com/billieredwood) 
- [Piraven](https://github.com/PiravenNan) 
- [Suzi](https://github.com/sctowers) 
- [Tom](https://github.com/ThomasLoughran) 

## Table of Contents 📜
1. [Introduction](#introduction)
   - 1.1 Scenario 
   - 1.2 Business Case & Risk Register   
2. Technologies & Libraries
3. Setup Instructions
   - 3.1. Server Side Setup
   - 3.2. Client Side Setup
4. Component & Wireframe Diagrams
5. App Functionality
   - 5.1. MVPs
   - 5.2. Extensions

## Introduction
Welcome to our full stack application allowing delivery drivers and managers to better communicate.
Our app solves these challenges currently faced by RainForestRetail:
- A single warehouse creates a bottleneck in the delivery system


## 1.1 Scenario

The well-known mail-order business RainForestRetail have been expanding a lot recently and they have reached the limit of what their current systems can support.
Our app solves these challenges currently faced by RainForestRetail:
- A single warehouse creates a bottleneck in the delivery system
- The fleet of delivery vehicles is being poorly utilised
- As more employees are added it becomes harder to get an overview of availability and where everyone is working on a given day

## 1.2 Business Case & Risk Register

**Risk Register**

**Business Case**

## 2. Technologies & Libraries
- Java
- JavaScript
- HTML
- CSS
- React
  - [Router Dom 6.21.0](https://www.npmjs.com/package/react-router-dom)
- Spring Boot
- Spring Data JPA
- Spring Web
- Postgresql
- Visual Studio Code
- GitHub

## 3. Setup Instructions 🛠️

## 3.1 Server Side Setup
1. Clone the project from the [DeliverEase GitHub Repository](https://github.com/ThomasLoughran/DeliverEase_app)
```bash
git  clone git@github.com:ThomasLoughran/DeliverEase_app.git
```
2. Open the project in IntelliJ
3. Create a database 
```bash
createdb deliverEase
```
4. Run the application in IntelliJ

## 3.2 Client Side Setup
1. Open the project in Visual Studio Code
2. In the terminal to install the node modules enter:
```bash 
npi i
```
3. In the terminal to install the React Router enter:
```bash
npm install react-router-dom
```
4. Start running the app in the terminal with:
```bash
npm start
```

## 4. Component, UML & Wireframe Diagrams 🎨

**Component Diagram:**

**Wireframe Diagram:**

**UML Diagram:**

## 5. App Functionality 

## 5.1. MVP 

- Drivers can update availability
- Drivers can see their route of orders to deliver by date
- Driver marks delivery as successful or unsuccessful with issue
- Manager allocates driver to route
- Managers can find drivers by distribution centre
- Manager receives message of issue with delivery

## 5.2. Extensions

- Undelivered items given priority for tomorrow delivery
- Drivers can see their route of orders to deliver by date

## 5.3. Routes

1. **Display all drivers**
* Method: GET
* URL: localhost:8080/drivers/all
* This will get the full list of driver employees.

<br>

2. **Display driver by ID number**
* Method: GET
* URL: localhost:8080/drivers/id
* This will get a driver by their employee ID number.

<br>

3. **Display all available drivers**
* Method: GET
* URL: localhost:8080/drivers/available
* This will get the full list of driver employees that have a status of ‘available’.

<br>

4. **Display all managers**
* Method: GET
* URL: localhost:8080/managers/all
* This will get the full list of driver employees.

<br>

5. **Display manager by their ID number**
* Method: GET
* URL: localhost:8080/managers/id
* This will get the full list of driver employees.

<br>

6. **Display all orders**
* Method: GET
* URL: localhost:8080/orders/all
* This will display the current list of all orders.

<br>

7. **Display all completed orders**
* Method: GET
* URL: localhost:8080/orders/completion
* This will get the full list of completed orders (i.e. they have been delivered to the customer).

<br>

8. **Display order by ID**
* Method: GET
* URL: localhost:8080/orders/id
* This will get an order by its ID number.

<br>

9. **Update orders’ status**
* Method: PATCH
* URL: localhost:8080/orders/id
* This will update an order’s delivered, manager-reviewed and/or issue and issue timing status by order ID.

<br>

10. **Create new driver**
* Method: POST
* URL: localhost:8080/orders/id
* This will update an order’s delivered, manager-reviewed and/or issue and issue timing status by order ID. 



