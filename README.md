# Capstone Project

![DeliverEase logo](https://github.com/ThomasLoughran/DeliverEase_app/blob/main/client/src/assets/dark-mode-logo.png)

## 🎸 Developer Team 🎸 
- [Albert](https://github.com/Al-B-code) 
- [Billie](https://github.com/billieredwood) 
- [Piraven](https://github.com/PiravenNan) 
- [Suzi](https://github.com/sctowers) 
- [Tom](https://github.com/ThomasLoughran) 

## Table of Contents 📜
1. [Introduction](#introduction)
   - 1.1 [Scenario](#11-scenario)
   - 1.2 [Business Case & Risk Register](#12-business-case--risk-register)   
2. [Technologies & Libraries](#2-technologies--libraries)
3. [Setup Instructions](#3-setup-instructions)
   - 3.1 [Server Side Setup](#31-server-side-setup)
   - 3.2 [Client Side Setup](#32-client-side-setup)
4. [Diagrams](#4-diagrams)
   - 4.1 [Component Diagram](#41-component-diagram)
   - 4.2 [Wireframe Diagram](#42-wireframe-diagram)
   - 4.3 [UML Diagram](#43-uml-diagram)
   - 4.4 [ER Diagram](#44-er-diagram)
   - 4.5 [Retrospective](#45-retrospective)
5. [App Functionality](#5-app-functionality)
   - 5.1 [MVP](#51-mvp)
   - 5.2 [Extensions](#52-extensions)
   - 5.3 [Routes](#53-routes-used-in-the-frontend)


## Introduction
Welcome to our full stack application allowing delivery drivers and managers to better communicate.

## 1.1 Scenario

The well-known mail-order business RainForestRetail have been expanding a lot recently and they have reached the limit of what their current systems can support.
Our app solves these challenges currently faced by RainForestRetail:
- A single warehouse creates a bottleneck in the delivery system
- The fleet of delivery vehicles is being poorly utilised
- As more employees are added it becomes harder to get an overview of availability and where everyone is working on a given day

Assumptions we have made with this scenario:
- The warehouses are in stock with the order's products
- There is a van for every driver
- Driver will only use an iPhone 12 Pro to login to account
- Manager will only use a laptop to login to account

## 1.2 Business Case & Risk Register

**Risk Register**

- [Risk register](https://github.com/ThomasLoughran/DeliverEase_app/blob/README/risk_register.pdf)

**Business Case**

The application provides a user-friendly solution that assists managers with distribution centre optimisation, fleet utilisation and workforce management through its real-time communications functionality with drivers and automated workload allocation & tracking features. 

The following Business Plan details the advantages of DeliverEase for RainForest Retail:

- [Business case](https://github.com/ThomasLoughran/DeliverEase_app/blob/README/business_case.pdf)

## 2. Technologies & Libraries
- Java 17
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

## 3. Setup Instructions 

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
3. Start running the app in the terminal with:
```bash
npm start
```

## 4. Diagrams 

## 4.1 Component Diagram:

![component diagram](https://github.com/ThomasLoughran/DeliverEase_app/blob/README/DeliverEaseComponent.drawio.png)

## 4.2 Wireframe Diagram:

![wireframe diagram](https://github.com/ThomasLoughran/DeliverEase_app/blob/README/DeliverEaseWireframe.png)

## 4.3 UML Diagram:

![uml diagram](https://github.com/ThomasLoughran/DeliverEase_app/blob/README/DeliverEaseUML.drawio.png)

## 4.4 ER Diagram:

![er diagram](https://github.com/ThomasLoughran/DeliverEase_app/blob/README/DeliverEaseER.drawio.png)

## 4.5 Retrospective:

![retrospective diagram](https://github.com/ThomasLoughran/DeliverEase_app/blob/README/Retropective.png)

## 5. App Functionality 

## 5.1. MVP 

- Drivers can update availability in a calendar
- Drivers can see the address and postcode of the next order to deliver
- Driver marks delivery as successful or unsuccessful with issue
- All employees can change their password
- Managers can create a new driver
- Managers generate routes
- Managers have a calendar log of all the create routes
- Managers can see all distribution centres with contact details
- Managers receives message of issue with delivery

## 5.2. Extensions

- Undelivered items given priority for tomorrow delivery
- Drivers can see their route of orders to deliver on a map
- Make app compatible with a mobile device

## 5.3. Routes used in the frontend:

1. **Display the route of a driver**
- Method: `GET`
- URL: `http://localhost:8080/routes/driver/{driverId}`
- This will get the route for the current day by driver id and date.

2. **Update the order as complete**
- Method: `PATCH`
- URL: `http://localhost:8080/orders/complete/{id}/`
- This will update the order by order id as the order being complete (true or false).

3. **Display order by id**
- Method: `GET`
- URL: `http://localhost:8080/orders/{id}`
- This will get an order by its id number.

4. **Update the issue attached to a incomplete order**
- Method: `PATCH`
- URL: `http://localhost:8080/orders/issue`
- This will update the order by adding the selected issue with the date the issue was submitted by the order id.

5. **Update driver's availability**
- Method: `PATCH`
- URL: `http://localhost:8080/drivers/change-available/{id}`
- This will update an driver's availability on a selected date.

6. **Create new driver**
- Method: `POST`
- URL: `http://localhost:8080/drivers/new-driver`
- This will create a new driver record and add it to the list of drivers. 
- Example JSON: 
```bash
{
    "name" : "Albert",
    "password" : "drowssap",
    "distributionCentreId" : 1,
    "vanCapacity" : 50,
    "vanMaxWeight" : 1000,
    "vanName" : "The Albimobile"
}
```

7. **Create routes**
- Method: `POST`
- URL: `http://localhost:8080/routes/new-routes/{disCentreId}`
- This will generate routes by distribution centre given a selected date.

8. **Display all issue**
- Method: `GET`
- URL: `http://localhost:8080/orders/issue/all`
- This will get all the issues of a given distribution centre if the manager has not reviewed them.

9. **Update if the manager has reviewed an issue**
- Method: `PATCH`
- URL: `http://localhost:8080/orders/manager-review/{id}`
- This will update the order issue by order id and change manager to reviewed true.

10. **Display all routes by date**
- Method: `GET`
- URL: `http://localhost:8080/routes/all/{distCentreId}`
- This will get all the routes of a given distribution centre by date.

11. **Login**
- Method: `POST`
- URL: `http://localhost:8080/employees/login`
- This will log an employee into the app.

12. **Update employee password**
- Method: `PATCH`
- URL: `http://localhost:8080/employees/update-password`
- This will let the employee change their password if they can enter their current password and the new password matches the confirm new password.
- Example JSON:
```bash
{
    "id" : 10,
    "oldPassword" : "password",
    "newPassword" : "drowssap1"
}
```