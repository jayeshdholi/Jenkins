# Jenkins Windows Node — Setup & PowerShell Pipeline Execution

## What is a Jenkins Node?

A **Jenkins Node (Agent)** is a machine used by Jenkins to run jobs.

- Jenkins Controller assigns jobs to Nodes.
- Nodes can be **Linux**, **Windows**, or **Mac**.
- A **Windows Node** allows Jenkins to run:
  - PowerShell scripts  
  - Batch commands  
  - Windows builds  
  - File operations on Windows shares  

---

## Why Use Windows Nodes?

- To run PowerShell scripts  
- To deploy Windows applications  
- To access Windows file shares  
- To perform Windows-specific automation  

---

## Overview: What We Will Do

1. Install Java on Windows  
2. Create a Windows Jenkins Node  
3. Connect Node to Jenkins via Agent  
4. Create a Pipeline Job  
5. Execute PowerShell script from Pipeline  
6. (Optional) Execute script on a remote Windows server  

---

## Jenkins Freestyle vs Pipeline — Key Differences

| Feature                  | **Freestyle Job**                 | **Pipeline Job**                               |
| ------------------------ | --------------------------------- | ---------------------------------------------- |
| **Job Definition**       | GUI-based configuration           | Code-based (Jenkinsfile)                       |
| **Complexity Handling**  | Suitable for simple tasks         | Designed for complex CI/CD workflows           |
| **Scriptability**        | Limited                           | Full scripting with Groovy                     |
| **Source Control**       | Cannot store job config in Git    | Jenkinsfile stored in Git (version control)    |
| **Stages/Steps**         | Basic build steps                 | Advanced stages, parallel, agents, steps       |
| **Build Restart/Resume** | Cannot resume if Jenkins restarts | Pipelines can resume after restart             |
| **Parameter Support**    | Basic parameters                  | Advanced parameters + logic                    |
| **Pipeline as Code**     | ❌ No                              | ✔ Yes (best practice)                          |
| **Parallel Execution**   | ❌ No                              | ✔ Yes (parallel stages)                        |
| **Error Handling**       | Very basic                        | try/catch, post blocks, notifications          |
| **Environment Handling** | Limited                           | Full control over env vars, credentials, tools |
| **Reusability**          | Hard to reuse                     | Shared libraries, reusable code                |
| **Credentials Usage**    | GUI-only                          | Secure with `withCredentials`                  |
| **Approval/Governance**  | Hard to track changes             | Every change in Jenkinsfile is audited in Git  |

---

# 1. Install Java on Windows (Required for Agent)

Download **JDK 17**:  
https://adoptium.net/

### Set environment variables:

JAVA_HOME = C:\Program Files\Eclipse Adoptium\jdk-17
PATH = %JAVA_HOME%\bin

### Verify installation:

```powershell
java -version
2. Enable Required Features on Windows Node

Run PowerShell as Administrator:
Enable-PSRemoting -Force
Set-ExecutionPolicy Unrestricted -Force

3. Create Windows Node in Jenkins
Step 1 — Open:
Jenkins Dashboard → Manage Jenkins → Nodes → New Node

Step 2 — Enter Node Details
Node Name: WIN-NODE-01

Type: Permanent Agent

Step 3 — Configure Node
Setting	Value
Remote Root Directory	D:\Jenkins\Agent
Labels	WIN-NODE-01
Launch Method	Launch agent via Java Web Start / agent.jar

4. Start the Node Agent on Windows
From Jenkins Node configuration, copy the agent.jar launch command:

Example:
java -jar agent.jar -jnlpUrl http://<JenkinsIP>:8080/computer/WIN-NODE-01/jenkins-agent.jnlp -secret <secret-key>
Run this on the Windows machine.

If successful → Jenkins shows:
Connected

5. Create a Pipeline Job
Jenkins Dashboard → New Item → Pipeline
Example job name:
nginx
Copy code
windowsPS

6. Basic Pipeline to Run PowerShell on Windows Node
pipeline {
    agent { label 'WIN-NODE-01' }

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

7. Add Your PowerShell Script to Git
Write-Host "This is Jenkins Windows Node Execution"
hostname
Get-Date

8. Pipeline to Run PS1 Script from Git

pipeline {
    agent { label 'LAB-windows-EV' }

    parameters {
        choice(
            name: 'SERVER',
            choices: ['PPG-App01', 'PPG-App02'],
            description: 'Choose server to run script'
        )
    }

    stages {

        stage('Run Remote PowerShell Script') {
            steps {
                powershell """
                Write-Host "Running script on: ${params.SERVER}"

                # Read PS1 file from workspace
                \$scriptContent = Get-Content "day1.ps1" -Raw

                # Execute it remotely
                Invoke-Command -ComputerName ${params.SERVER} -ScriptBlock {
                    Invoke-Expression \$using:scriptContent
                }
                """
            }
        }
    }
}

**Windows Node Workspace Path**
makefile
D:\Jenkins\Agent\workspace\
Each pipeline job gets its own folder.

---


📘 Reference
Jenkins Official Docs: https://www.jenkins.io/doc/book/
PowerShell Remoting: https://learn.microsoft.com/en-us/powershell/

