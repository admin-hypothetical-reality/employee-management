# 📋 Git Setup & Push Guide

## Quick Setup (5 Steps)

### Step 1: Initialize Git Repository
```bash
cd "d:/employee management"
git init
```

### Step 2: Configure Git (Replace with your info)
```bash
git config user.name "Your Name"
git config user.email "your.email@example.com"
```

### Step 3: Add All Files
```bash
git add .
```

### Step 4: Create First Commit
```bash
git commit -m "Initial commit: Employee Management System"
```

### Step 5: Push to GitHub

#### Option A: Create New Repository on GitHub
1. Go to https://github.com/new
2. Repository name: `employee-management-system`
3. Description: `Spring Boot Employee Management System with MySQL`
4. Keep it **Public** or **Private** (your choice)
5. **DO NOT** initialize with README (we already have one)
6. Click **Create repository**

#### Option B: Use the commands GitHub provides
After creating the repo, GitHub will show you commands like:
```bash
git remote add origin https://github.com/YOUR_USERNAME/employee-management-system.git
git branch -M main
git push -u origin main
```

## ⚠️ IMPORTANT: Protect Your Password!

Before pushing, we need to remove your MySQL password from Git history!

### Create application-local.properties
```bash
# Copy your current config
cp src/main/resources/application.properties src/main/resources/application-local.properties
```

### Update application.properties (for Git)
Change the password line to:
```properties
spring.datasource.password=${DB_PASSWORD:root}
```

### Add to .gitignore
```
application-local.properties
```

This way:
- `application.properties` goes to Git with placeholder
- `application-local.properties` stays on your machine with real password

## 🚀 Complete Commands

```bash
# 1. Initialize
git init

# 2. Configure
git config user.name "Your Name"
git config user.email "your.email@example.com"

# 3. Add files
git add .

# 4. Commit
git commit -m "Initial commit: Employee Management System"

# 5. Add remote (replace with your GitHub URL)
git remote add origin https://github.com/YOUR_USERNAME/employee-management-system.git

# 6. Push
git branch -M main
git push -u origin main
```

## 📝 Future Commits

After making changes:
```bash
git add .
git commit -m "Description of changes"
git push
```

## 🔐 Alternative: Use Environment Variables

Instead of hardcoding password, use environment variable:

**In application.properties:**
```properties
spring.datasource.password=${MYSQL_PASSWORD}
```

**Set environment variable:**
- Windows: `setx MYSQL_PASSWORD "your_password"`
- Linux/Mac: `export MYSQL_PASSWORD="your_password"`

## ✅ Verification

Check status:
```bash
git status
```

View commit history:
```bash
git log
```

Check remote:
```bash
git remote -v
```
