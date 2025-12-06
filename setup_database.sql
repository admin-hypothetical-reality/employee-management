-- Run this in MySQL Workbench or MySQL Command Line

CREATE DATABASE ems_db;

-- Optional: Create a dedicated user
CREATE USER 'ems_user'@'localhost' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON ems_db.* TO 'ems_user'@'localhost';
FLUSH PRIVILEGES;
