# Employee Management System - Run Instructions

## Prerequisites
- Java 17 or higher
- MySQL Server running
- Database 'ems_db' created

## Method 1: Using the Batch Script (EASIEST)
1. Double-click `run.bat`
2. The script will automatically build and run the application

## Method 2: Using VS Code (RECOMMENDED)
1. Install "Extension Pack for Java" from VS Code marketplace
2. Open `src/main/java/com/ems/EmsApplication.java`
3. Click the "Run" button above the main method
   OR
4. Press F5

## Method 3: Using Maven (if installed)
```bash
mvn clean install
mvn spring-boot:run
```

## Method 4: Using IntelliJ IDEA
1. Open the project in IntelliJ
2. Right-click on `EmsApplication.java`
3. Select "Run 'EmsApplication.main()'"

## After Starting
- Application runs on: http://localhost:8080
- Auto-redirects to login page
- Demo credentials are shown on the login page

## Troubleshooting

### "Java not found"
- Install Java 17: https://www.oracle.com/java/technologies/downloads/
- Add Java to PATH

### "Cannot connect to database"
1. Start MySQL service
2. Create database: `CREATE DATABASE ems_db;`
3. Update password in `src/main/resources/application.properties`

### "Port 8080 already in use"
- Change port in `application.properties`: `server.port=8081`
- Or stop the application using port 8080
