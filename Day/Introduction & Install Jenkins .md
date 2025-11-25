# 🖥️ Jenkins Windows Node — Setup & PowerShell Pipeline Execution

## 📌 What is a Jenkins Node?

A **Jenkins Node (Agent)** is a machine used by Jenkins to run jobs.

- Jenkins Controller assigns jobs to Nodes.
- Nodes can be **Linux**, **Windows**, or **Mac**.
- A **Windows Node** allows Jenkins to run:
  - PowerShell scripts  
  - Batch commands  
  - Windows builds  
  - File operations on Windows shares  

---

## 📌 Why Use Windows Nodes?

- To run PowerShell scripts  
- To deploy Windows applications  
- To access Windows file shares  
- To perform Windows-specific automation  

---

## ⭐ Overview: What We Will Do

1. Install Java on Windows  
2. Create a Windows Jenkins Node  
3. Connect Node to Jenkins via Agent  
4. Create a Pipeline Job  
5. Execute PowerShell script from Pipeline  
6. (Optional) Execute script on a remote Windows server  

---

# ✅ 1. Install Java on Windows (Required for Agent)

Download **JDK 17**:  
👉 https://adoptium.net/

### Set environment variables:

JAVA_HOME = C:\Program Files\Eclipse Adoptium\jdk-17
PATH = %JAVA_HOME%\bin

perl
Copy code

### Verify installation:

```powershell
java -version
✅ 2. Enable Required Features on Windows Node
Run PowerShell as Administrator:

powershell
Copy code
Enable-PSRemoting -Force
Set-ExecutionPolicy Unrestricted -Force
✅ 3. Create Windows Node in Jenkins
Step 1 — Open:
sql
Copy code
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
From Jenkins Node configuration, copy the agent.jar launch command:

Example:

powershell
Copy code
java -jar agent.jar -jnlpUrl http://<JenkinsIP>:8080/computer/WIN-NODE-01/jenkins-agent.jnlp -secret <secret-key>
Run this on the Windows machine.

If successful → Jenkins shows:

✔️ Connected

✅ 5. Create a Pipeline Job
Navigate:
sql
Copy code
Jenkins Dashboard → New Item → Pipeline
Example job name:
nginx
Copy code
windowsPS
✅ 6. Basic Pipeline to Run PowerShell on Windows Node
groovy
Copy code
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
Example: day1.ps1
powershell
Copy code
Write-Host "This is Jenkins Windows Node Execution"
hostname
Get-Date
✅ 8. Pipeline to Run PS1 Script from Git
groovy
Copy code
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
✅ 9. (Optional) Execute Script on Remote Windows Server
Requires PSRemoting enabled on remote machine.

groovy
Copy code
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
makefile
Copy code
D:\Jenkins\Agent\workspace\
Each pipeline job gets its own folder.

🎯 Summary
Task	Status
Setup Windows Node	✔️
Connected Jenkins Agent	✔️
Pipeline job created	✔️
PowerShell executed	✔️
Remote server execution	✔️

📘 Reference
Jenkins Official Docs: https://www.jenkins.io/doc/book/

PowerShell Remoting: https://learn.microsoft.com/en-us/powershell/
