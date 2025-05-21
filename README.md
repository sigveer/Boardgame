[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/fcBMQdAa)
# idatx1005 ToDo'it

## Description and features
This project is a Java application that uses Maven for build automation and SQL for database interactions. 
The application is a simple and motivating task manager that allows users to create, read, update, and delete tasks. 
The application is built using the MVC design pattern. 
The graphic user interface allows smooth and simple use of the application.

## Technologies Used
- Java 
- Maven
- JavaFX
- SQL

## Prerequisites
- IntelliJ IDEA (recommended)
- Latest maven version
- Java 21

## Setup
1. Clone the repository: ```git clone https://github.com/NTNU-IDI/semester-project-2025-gruppe-6-systemutvikling-var.git```
2. Navigate to the project directory: ```cd semester-project-2025-gruppe-6-systemutvikling-var```
3. Open the project in IntelliJ IDEA.

4. Build the project using Maven: ```mvn clean install``` 
5. To get the database up and running, you’ll need to have Docker installed on your machine. If you don’t have it yet, follow the installation instructions for your operating system:


   	macOS: https://docs.docker.com/desktop/install/mac-install/

   	Windows: https://docs.docker.com/desktop/install/windows-install/

   	Linux: https://docs.docker.com/engine/install/

6. Navigate to ```docker-compose.yaml``` and run the file, or run this command int your terminal: ```docker compose up -d```
7. Connecting to the PostgreSQL Database:

Once the database container is running (via Docker), you can connect to it using **any PostgreSQL-compatible database client**. Some popular options include:

- [DBeaver](https://dbeaver.io/) – cross-platform
- [pgAdmin](https://www.pgadmin.org/) – web-based UI
- [TablePlus](https://tableplus.com/) – macOS & Windows
- **IntelliJ IDEA** – built-in database tool


Use the following credentials to connect to the database:

- **Host:** `localhost`
- **Port:** `5432`
- **Database:** `idatt1005-db`
- **Username:** `idatt1005user`
- **Password:** `idatt1005password`

If you're using IntelliJ:

1. Open the **Database** tool window.
2. Click the **+** icon → **Data Source** → **PostgreSQL**.
3. Enter the connection details listed above.
4. Click **Test Connection** to ensure it works.
5. Click **OK** to save and connect.


#### Creating the Database Schema

After you've connected to the database (see above), you can create the necessary tables and types by executing the provided SQL script.
#### How to Apply the SQL Schema

1. Open your database client (e.g., IntelliJ, DBeaver, pgAdmin).
2. Connect to the `idatt1005-db` database using the connection details.
3. Open a new SQL editor/query window.
4. Copy and paste the ``databaseCommand.sql`` script into the query window.
5. The database is now ready for use.


## Running the Application
Either run the ```MainApplication.java``` by clicking the run button, 
or use the terminal with this command: ```mvn javafx:run```
If this does not work try: ```mvn clean javafx:run```


## Authors
- Amadeus Syvertsen Berg
- Thomas Oliver Wallin Higgins
- Solfrid Emblem Holte
- Tri Tac Le
- Ingve Værnes


