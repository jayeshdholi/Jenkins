<p align="center">
    <img src="CoreCard.png" alt="CoreCard" />
</p>

# Jenkins Job with GitHub Repo + Maven – Integration

## Pre-Requisites
- Java
- Maven
- Git client

---

# Git Installation in VM

```bash
sudo apt install git -y
Maven Installation in Jenkins
Go to Jenkins Dashboard

Navigate to Manage Jenkins

Open Global Tools Configuration

Click Add Maven

Provide a name and Maven home (if needed)

Save the configuration




Sample Git Repo URL for Practice
GitHub Repo:
https://github.com/jayeshdholi/Jenkins/tree/main/maven-web-app.git

JOB-2: Steps to Create Jenkins Job with Git Repo + Maven
Connect to the EC2 instance where Jenkins is installed.

Start Jenkins Server.

Access Jenkins Dashboard and log in with your credentials.

Create Jenkins Job with GitHub Repository:

Click New Item

Enter Item Name (Job Name)

Select Freestyle Project → Click OK

Add a description (optional)

Go to Source Code Management → Select Git

Enter your Git Repository URL

Go to Build tab

Click Add Build Step → Select Invoke top-level Maven targets

Select Maven installation

Enter Maven goals:

go
Copy code
clean package
Click Apply → Save

Running the Job
Click Build Now to start execution.

Click the Build Number → Console Output to view logs.

Jenkins Workspace Directory (EC2)
swift
Copy code
/var/lib/jenkins/workspace/
Navigate to your job folder → target folder → find the generated .war file.

