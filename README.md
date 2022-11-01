# Telecommunication Services Company Online Store
## Overview
The program is a functioning implementation in `Java Enterprise Edition`, using `EclipseLink JPA` persistence and `MySQL Database` with triggers, of an <b>online store</b> for a <b>telecommunication services company</b>, where administrators can create and modify packages which are in turn purchasable by customers.

### Authors
- <b>Andrea Alesani</b> (andrea.alesani@mail.polimi.it)
- <b>Tommaso Brumani</b> (tommaso.brumani@mail.polimi.it)

### License
The project was carried out as part of the 2021/2022 '<b>Data Bases II</b>' course at <b>Politecnico of Milano</b>, where it was awarded a score of 16/16. 

## Project Specifications
The project required the development of a web platform for the sale of service packages for a telecommunication company, composed by two client applications interacting with the same database.

The first application is a consumer application where customers can view available service packages and, upon logging in, can purchase a specific offering and view their active plans.

The second application is an employee application from which authorized users can create new service packages with associated products and perks, as well as view a sales report summarizing all essential data about sales and users.

The application was to be developed using `JPA` persistence, and certain features were explicitly required to be implemented through database triggers.

## File System Structure
* `db2-JPA-2021`: the code for the web application, as well as a database dump including the implemented triggers
* `documentation`: the project documentation specifying in greater detail several aspects of the implementation
* `specifications`: specifications for the project
