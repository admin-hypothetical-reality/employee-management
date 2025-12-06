@echo off
echo ========================================
echo  Starting Employee Management System
echo ========================================
echo.
echo Make sure MySQL is running with:
echo - Database: ems_db
echo - Username: root
echo - Password: root
echo.
echo Starting server...
echo.

java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java not found!
    echo Please install Java 17+ from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

cd /d "%~dp0"

echo Compiling and running...
javac -cp "target/classes;%USERPROFILE%\.m2\repository\*" src/main/java/com/ems/EmsApplication.java 2>nul

if exist "target\classes" (
    echo Using Maven build...
    java -jar target\*.jar
) else (
    echo Please build the project first with: mvn clean install
    echo.
    echo OR use your IDE's Run button
    pause
)
