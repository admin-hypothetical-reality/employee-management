# 🧪 Testing Guide - Employee Management System

## 🚀 How to Test the Application

### Step 1: Start the Server

1. **Stop any running server** (Ctrl+C in terminal)
2. **Start the application**:
   - Click "Run" above `main` method in `EmsApplication.java`
   - OR press F5

3. **Wait for this message**:
   ```
   ✓ Admin user created: admin@ems.com / admin123
   ✓ Demo employee created: employee@ems.com / emp123
   Started EmsApplication in X.XXX seconds
   ```

### Step 2: Open Browser

Go to: **http://localhost:8080**

It will automatically redirect to the login page.

---

## 🧪 Test Scenarios

### Test 1: Admin Login ✅

1. **Login** with:
   - Email: `admin@ems.com`
   - Password: `admin123`

2. **Expected**: Redirect to Admin Dashboard
3. **You should see**:
   - Add Employee form
   - List of all employees
   - Pending leave requests
   - Payroll generation form

### Test 2: Add New Employee ✅

1. **Fill the "Add New Employee" form**:
   - Name: `Jane Smith`
   - Email: `jane@ems.com`
   - Password: `jane123`
   - Department: `HR`
   - Salary: `45000`

2. **Click "Add Employee"**
3. **Expected**: New employee appears in the employee list

### Test 3: Employee Login ✅

1. **Logout** from admin (click Logout button)
2. **Login** with:
   - Email: `employee@ems.com`
   - Password: `emp123`

3. **Expected**: Redirect to Employee Dashboard
4. **You should see**:
   - Apply for Leave form
   - My Leave Requests table (empty initially)
   - Salary History table (empty initially)

### Test 4: Apply for Leave ✅

1. **As employee**, fill "Apply for Leave" form:
   - Start Date: Select a future date
   - End Date: Select a date after start date
   - Reason: `Vacation`

2. **Click "Submit Leave Request"**
3. **Expected**: Leave appears in "My Leave Requests" with status PENDING

### Test 5: Approve Leave (Admin) ✅

1. **Logout** and login as admin again
2. **Go to "Pending Leave Requests" section**
3. **Find the leave** you just created
4. **Click "Approve"**
5. **Expected**: Leave status changes to APPROVED

### Test 6: Generate Payroll (Admin) ✅

1. **As admin**, scroll to "Generate Payroll" section
2. **Fill the form**:
   - Employee: Select `John Doe` from dropdown
   - Month: `December 2024`
   - Amount: `50000`

3. **Click "Generate"**
4. **Expected**: Payroll record created

### Test 7: View Salary (Employee) ✅

1. **Logout** and login as employee
2. **Check "Salary History" section**
3. **Expected**: You should see the payroll record with:
   - Month: December 2024
   - Amount: 50000
   - Status: PENDING

---

## ✅ Complete Test Checklist

- [ ] Admin can login
- [ ] Admin can add new employee
- [ ] Admin can view all employees
- [ ] Employee can login
- [ ] Employee can apply for leave
- [ ] Admin can see pending leaves
- [ ] Admin can approve/reject leaves
- [ ] Admin can generate payroll
- [ ] Employee can view salary history
- [ ] Logout works for both roles

---

## 🎯 Quick Access URLs

- **Login**: http://localhost:8080/login
- **Admin Dashboard**: http://localhost:8080/admin/dashboard
- **Employee Dashboard**: http://localhost:8080/employee/dashboard
- **Test Page**: http://localhost:8080/test

---

## 📝 Demo Credentials

**Admin**:
- Email: `admin@ems.com`
- Password: `admin123`

**Employee**:
- Email: `employee@ems.com`
- Password: `emp123`

---

## 🐛 Troubleshooting

**Can't login?**
- Check MySQL is running
- Check database `ems_db` exists
- Check terminal for "Demo user created" messages

**Page not loading?**
- Check server is running (look for "Started EmsApplication")
- Check port 8080 is not blocked

**Database errors?**
- Check MySQL password in `application.properties`
- Restart MySQL service

---

**Happy Testing!** 🎉
