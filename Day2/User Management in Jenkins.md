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


Install and restart Jenkins.

---

## 5. Configure Global Security

Navigate to:

`Manage Jenkins → Configure Global Security`

## Role-Based Authorization Strategy

### Steps:
Under **Authorization**, select:

✔ **Role-Based Strategy**

Save the changes.

This enables access to **Manage Roles** and **Assign Roles**.

---

## 6. Manage Roles

Navigate:

`Manage Jenkins → Manage and Assign Roles → Manage Roles`

### Create Required Roles:

---

### **A. Admin Role**
*(Usually created by default)*

Permissions include:

- Full system administration  
- Create/Edit/Delete jobs  
- Credentials management  
- Node management  
- Plugin/config access  

**Assigned to:** Operations Team lead or Jenkins admin

---

### **B. Operations Role**
**Permissions:**

| Permission     | Access |
|----------------|--------|
| Job Create     | ✔      |
| Job Configure  | ✔      |
| Job Delete     | ✔      |
| Job Build      | ✔      |
| Job Cancel     | ✔      |
| View Read      | ✔      |
| Run Scripts (optional) | ✔ |

**Assigned to:** Operations Team

---

### **C. Developer Role**
**Permissions:**

| Permission        | Access |
|-------------------|--------|
| Job Build         | ✔      |
| Job Read          | ✔      |
| Job Cancel (opt.) | ✔      |
| Workspace Access (opt.) | ✔ |

**No access to:**

- Create jobs  
- Edit jobs  
- Delete jobs  
- Jenkins configuration  

**Assigned to:** Development Team

---

### **D. Testing Role**
Same access as Developer Role:

| Permission | Access |
|-----------|--------|
| Job Build | ✔      |
| Job Read  | ✔      |

**Assigned to:** Testing Team

---

## 7. Assign Roles to Users

Navigate:

`Manage Jenkins → Manage and Assign Roles → Assign Roles`

### **Global Roles Section:**

Assign roles as:

- **admin** → Admin user(s)  
- **operations** → Operations team  
- **developer** → Development team  
- **testing** → QA/Testing team  

Each user is mapped based on responsibility.

---

## 8. Summary of Access Control

| Team         | Login | Run Job | Create Job | Edit Job | Delete Job |
|--------------|-------|---------|------------|----------|------------|
| Development  | ✔     | ✔       | ✘          | ✘        | ✘          |
| Testing      | ✔     | ✔       | ✘          | ✘        | ✘          |
| Operations   | ✔     | ✔       | ✔          | ✔        | ✔          |
| Admin        | ✔     | ✔       | ✔          | ✔        | ✔ + system config |

---

## 9. Best Practices

✔ Give **minimum permissions** necessary  
✔ Do **not** share user accounts  
✔ Regularly audit users and roles  
✔ Enable **CSRF protection** in Global Security  
✔ Use **LDAP or SSO** for large teams  
✔ Store credentials **securely**, not inside job scripts  

---


