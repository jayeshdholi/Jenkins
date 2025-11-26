# User Management in Jenkins
### Comprehensive Guide for Multi-Team Access Control

---

## 1. Overview
In a project environment, multiple teams such as Development, Testing, and Operations may need access to Jenkins.  
To maintain security and prevent unauthorized changes, Jenkins provides role-based access control (RBAC) using plugins and built-in features.

This document explains how to:

- Create users in Jenkins  
- Configure roles  
- Assign permissions  
- Implement team-based access control  

---

## 2. Teams & Access Requirements

### **a) Development Team**
- Must log in using their own account  
- Should only run jobs  
- Should **not** modify job configuration  
- Should **not** delete jobs  

### **b) Testing Team**
- Same access as Development  
- Allowed to run jobs only  

### **c) Operations Team**
- Must have full control  
- Allowed to:
  - Create jobs  
  - Edit jobs  
  - Delete jobs  
  - Run jobs  
  - Manage Jenkins configuration (optional)  

---

## 3. Jenkins User Creation Process

### **Step 1: Open Jenkins Dashboard**
Login with the admin account and navigate to the dashboard.

### **Step 2: Go to User Management**
`Manage Jenkins → Manage Users`

### **Step 3: Create New User**
Click **Create User** and enter:

- Username  
- Password  
- Full Name  
- Email Address  

Each team member must have their own user account.

---

## 4. Enable Security and Role Management

To manage permissions effectively, install the plugin:

👉 **Role-Based Authorization Strategy Plugin**

### Steps:
Go to:

`Manage Jenkins → Manage Plugins → Available`

Search for:

