Jenkins Job with GitHub Repo + Maven – Integration
<p align="center"> <img src="CoreCard.png" alt="CoreCard" /> </p>
Pre-requisites

Java

Maven

Git client

Git Installation in EC2 VM
sudo apt install git -y

Maven Installation in Jenkins

Jenkins Dashboard → Manage Jenkins → Global Tool Configuration → Add Maven

Sample Git Repo URL for Practice

GitHub Repo:

https://github.com/ashokitschool/maven-web-app.git

JOB-2: Steps to Create Jenkins Job Using Git Repo + Maven
1. Connect to the EC2 instance

Where Jenkins server is installed.

2. Start Jenkins Server

Make sure the Jenkins service is running.

3. Access Jenkins Dashboard

Open the Jenkins UI and log in using your credentials.

4. Create Jenkins Job with GitHub Repository

Go to New Item

Enter Job Name

Select Freestyle Project → Click OK

Add a description (optional)

Go to Source Code Management

Select Git

Enter your Git Repository URL

Go to Build tab

Click Add Build Step

Select Invoke Top-Level Maven Targets

Select your Maven installation

Enter Goals:

clean package


Click Apply and Save

✔ With these steps, your first Jenkins Maven Job is ready.

5. Run the Job

Click Build Now to start execution.

6. View Job Logs

Click the Build Number

Click Console Output to see detailed logs.

Jenkins Workspace Path (EC2 Linux)
/var/lib/jenkins/workspace/


Inside your job folder → target/ → you will find the generated .war file.
