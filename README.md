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
   - 1.1 [Scenario](#11-scenario)
   - 1.2 [Business Case & Risk Register](#12-business-case--risk-register)   
2. [Technologies & Libraries](#2-technologies--libraries)
3. [Setup Instructions](#3-setup-instructions)
   - 3.1 [Server Side Setup](#31-server-side-setup)
   - 3.2 [Client Side Setup](#32-client-side-setup)
4. [Component & Wireframe Diagrams](#4-component-uml--wireframe-diagrams)
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

## 1.2 Business Case & Risk Register

**Risk Register**

```
*insert doc here*

```

**Business Case**

The application provides a user-friendly solution that assists managers with distribution centre optimisation, fleet utilisation and workforce management through its real-time communications functionality with drivers and automated workload allocation & tracking features. 

The following Business Plan details the advantages of DeliverEase for RainForest Retail:

```
*insert doc here*

```

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
3. In the terminal to install the React Router enter:
```bash
npm install react-router-dom
```
4. Start running the app in the terminal with:
```bash
npm start
```

## 4. Component, UML & Wireframe Diagrams 

**Component Diagram:**

**Wireframe Diagram:**

**UML Diagram:**

## 5. App Functionality 

## 5.1. MVP 

- Drivers can update availability in a calendar
- Drivers can see the address and postcode of the next order to deliver
- Driver marks delivery as successful or unsuccessful with issue
- Managers can create a new driver
- Managers generate routes
- Managers have a calendar log of all the create routes
- Managers can see all distribution centres with contact details
- Managers receives message of issue with delivery

## 5.2. Extensions

- Undelivered items given priority for tomorrow delivery
- Drivers can see their route of orders to deliver on a map

## 5.3. Routes used in the frontend:

1. **Display all drivers**
* Method: `GET`
* URL: `http://localhost:8080/drivers/all`
* This will get the full list of driver employees.

<br>

2. **Display driver by ID number**
* Method: `GET`
* URL: `http://localhost:8080/drivers/id`
* This will get a driver by their employee ID number.

<br>

3. **Display all available drivers**
* Method: `GET`
* URL: `http://localhost:8080/drivers/available`
* This will get the full list of driver employees that have a status of ‘available’.

<br>

4. **Display all managers**
* Method: `GET`
* URL: `http://localhost:8080/managers/all`
* This will get the full list of driver employees.

<br>

5. **Display manager by their ID number**
* Method: `GET`
* URL: `http://localhost:8080/managers/id`
* This will get the full list of driver employees.

<br>

6. **Display all orders**
* Method: `GET`
* URL: `http://localhost:8080/orders/all`
* This will display the current list of all orders.

<br>

7. **Display all completed orders**
* Method: `GET`
* URL: `http://localhost:8080/orders/completion`
* This will get the full list of completed orders (i.e. they have been delivered to the customer). 

<br>

8. **Display order by ID**
* Method: `GET`
* URL: `http://localhost:8080/orders/id`
* This will get an order by its ID number.

<br>

9. **Display all distribution centres**
* Method: `GET`
* URL: `http://localhost:8080/distribution-centres`
* This will get the full list of distribution centres.

<br>

10. **Update orders’ status**
* Method: `PATCH`
* URL: `http://localhost:8080/orders/{id}`
* This will update an order’s delivered, manager-reviewed and/or issue and issue timing status by order ID.

<br> 

11. **Create new driver**
* Method: `POST`
* URL: `http://localhost:8080/drivers/new-driver`
* This will create a new driver record and add it to the list of drivers. 

<br> 

12. **Update driver's availability**
* Method: `PATCH`
* URL: `http://localhost:8080/drivers/change-available/{id}`
* This will update an driver's availability.

