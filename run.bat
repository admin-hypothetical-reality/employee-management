@echo off
echo ========================================
echo  Employee Management System - Launcher
echo ========================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 17 or higher from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

echo [1/3] Checking MySQL connection...
echo Please ensure MySQL is running and database 'ems_db' is created
echo.

echo [2/3] Building the application...
call mvnw.cmd clean install -DskipTests
if %errorlevel% neq 0 (
    echo.
    echo ERROR: Build failed. Installing Maven wrapper...
    echo Downloading Maven wrapper...
    curl -o mvnw.cmd https://raw.githubusercontent.com/takari/maven-wrapper/master/mvnw.cmd
    curl -o mvnw https://raw.githubusercontent.com/takari/maven-wrapper/master/mvnw
    mkdir .mvn\wrapper
    curl -o .mvn\wrapper\maven-wrapper.properties https://raw.githubusercontent.com/takari/maven-wrapper/master/.mvn/wrapper/maven-wrapper.properties
    curl -o .mvn\wrapper\maven-wrapper.jar https://raw.githubusercontent.com/takari/maven-wrapper/master/.mvn/wrapper/maven-wrapper.jar
    
    echo Retrying build...
    call mvnw.cmd clean install -DskipTests
)

echo.
echo [3/3] Starting the application...
echo.
echo ========================================
echo  Application will start on: http://localhost:8080
echo  Admin Login: admin@ems.com / admin123
echo  Employee Login: employee@ems.com / emp123
echo ========================================
echo.

call mvnw.cmd spring-boot:run

pause
