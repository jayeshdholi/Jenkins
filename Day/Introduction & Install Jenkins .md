<p align="center"> <img src="CoreCard.png" alt="CoreCard" /> </p>
What is a Jenkins Node?

A Jenkins Node (or Agent) is a machine used by Jenkins to run jobs.

Jenkins Controller assigns jobs to connected Nodes.

Nodes can be Linux, Windows, or Mac.

A Windows Node allows Jenkins to execute:

PowerShell scripts

Batch commands

Builds requiring Windows tools

Why Use Windows Nodes?

To run PowerShell scripts

To deploy Windows applications

To access Windows file shares

To run Windows-specific automation

⭐ Overview: What We Will Do

Install Java on Windows

Create a Windows Jenkins Node

Connect Node to Jenkins via Agent

Create a Pipeline Job

Execute PowerShell script from Pipeline

(Optional) Run script on remote Windows server

✅ 1. Install Java on Windows (Required for Agent)

Download JDK 17 from:
👉 https://adoptium.net/

Install JDK and set environment variables:

JAVA_HOME = C:\Program Files\Eclipse Adoptium\jdk-17
PATH = %JAVA_HOME%\bin


Verify Java:

java -version

✅ 2. Enable Required Features on Windows Node

Run PowerShell as Administrator:

Enable-PSRemoting -Force
Set-ExecutionPolicy Unrestricted -Force

✅ 3. Create Windows Node in Jenkins
Step 1 — Go to:
Jenkins Dashboard → Manage Jenkins → Nodes → New Node

Step 2 — Enter Node Details

Node Name: WIN-NODE-01

Type: Permanent Agent

Step 3 — Configure Node
Setting	Value
Remote Root Directory	D:\Jenkins\Agent
Labels	windows powershell
Launch Method	Launch agent via Java Web Start / agent.jar
✅ 4. Start the Node Agent on Windows

On Node configuration page, copy the agent.jar command:

Example:

java -jar agent.jar -jnlpUrl http://<JenkinsIP>:8080/computer/WIN-NODE-01/jenkins-agent.jnlp -secret <secret-key>


Run this in CMD or PowerShell on the Windows machine.

If successful → Jenkins shows Connected ✔️

✅ 5. Create a Pipeline Job
Go to:
Jenkins Dashboard → New Item → Pipeline

Name:
windowsPS

✅ 6. Basic Pipeline to Run PowerShell on Windows Node
pipeline {
    agent { label 'windows' }

    stages {
        stage('Check Host Info') {
            steps {
                powershell '''
                    hostname
                    Get-Date
                '''
            }
        }
    }
}

✅ 7. Add Your PowerShell Script to Git
Example script: day1.ps1
Write-Host "This is Jenkins Windows Node Execution"
hostname
Get-Date

✅ 8. Pipeline to Run PS1 from Git Repository
pipeline {
    agent { label 'windows' }

    stages {
        stage('Pull From Git') {
            steps {
                git branch: 'main', url: 'https://github.com/username/windows-automation.git'
            }
        }

        stage('Execute Script') {
            steps {
                powershell '''
                    Write-Host "Running PS1 script..."
                    D:\\Jenkins\\Agent\\workspace\\windowsPS\\day1.ps1
                '''
            }
        }
    }
}

✅ 9. (Optional) Execute Script on a Remote Windows Server

Requires PSRemoting enabled on remote server.

pipeline {
    agent { label 'windows' }

    parameters {
        choice(name: 'SERVER', choices: ['APP01','APP02'])
    }

    stages {
        stage('Run Remote Script') {
            steps {
                powershell """
                Invoke-Command -ComputerName ${params.SERVER} -ScriptBlock {
                    hostname
                    Get-Date
                }
                """
            }
        }
    }
}

📂 Windows Node Workspace Path
D:\Jenkins\Agent\workspace\


Each job will have its own folder.

🎯 Summary
Task	Completed
Setup Windows Node	✔️
Connected Jenkins Agent	✔️
Pipeline job created	✔️
PowerShell executed	✔️
Remote server execution	✔️
📘 Reference

Official Guide: https://www.jenkins.io/doc/book/

PowerShell Remoting: https://learn.microsoft.com/en-us/powershell/