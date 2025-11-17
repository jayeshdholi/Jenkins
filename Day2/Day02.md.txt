  <p align="center">
    <img src="document\CoreCard.png" alt="CoreCard" />
  </p>

# Jenkins Job with GIT Hub Repo + Maven – Integration

Pre-Requisites: Java, Maven and Git client

# Git installation In EC2 VM

$ sudo apt install git -y

==================================
Maven Installation in Jenkins: 
==================================

Jenkins Dashboard -> Manage Jenkins --> Global Tools Configuration -> Add maven

==================================
Sample Git Repo URLS For Practice
==================================

Git Hub Repo URL  : https://github.com/ashokitschool/maven-web-app.git


---







============================================================
JOB-2: Steps to Create Jenkins Job with Git Repo + Maven
============================================================

1) Connect to EC2 instance in which jenkins server got installed

2) Start Jenkins Server

3) Access Jenkins Server Dashboard and Login with your jenkins credentials

4) Create Jenkins Job with Git Hub Repo 

		-> New Item
		-> Enter Item Name (Job Name)
		-> Select 'Free Style Project' & Click OK
		-> Enter some description
		-> Go to "Source Code Management" Tab and Select "Git"
		-> Enter Project "Git Repo URL"
		-> Go to "Build tab"
		-> Click on Add Build Step and Select 'Invoke Top Level Maven Targets'
		-> Select Maven and enter goals 'clean package'
		-> Click on Apply and Save

Note: With above steps we have created JENKINS Job

5) Click on 'Build Now' to start Job execution

6) Click on 'Build Number' and then click on 'Console Output' to see job execution details.

=> Jenkins Home Directory in EC2 : /var/lib/jenkins/workspace/

=> Go to Jenkins workspace and then go to job folder then goto target folder there we see war file created.* **Administrator/root privileges**

---


