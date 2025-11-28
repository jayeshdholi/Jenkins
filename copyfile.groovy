pipeline {
    agent { label 'windosnode1' }

    parameters {
        choice(name: 'SERVER', choices: ['akashs1', 'PPG-App02'])
        string(name: 'USERNAME')
        password(name: 'PASSWORD')
    }

    stages {

        stage('Verify Input') {
            steps {
                echo "Running script on: ${params.SERVER}"
                echo "Username: ${params.USERNAME}"
                // Password intentionally NOT printed
            }
        }
        
        stage('Run Remote PowerShell Script') {
            steps {
                script {
                    env.SERVER = params.SERVER
                    env.USERNAME = params.USERNAME
                    env.PASSWORD = params.PASSWORD

                    powershell '''
                        Write-Host "Running script on: $env:SERVER"
                        Write-Host "Using username: $env:USERNAME"

                        $securePass = ConvertTo-SecureString "$env:PASSWORD" -AsPlainText -Force
                        $credential = New-Object System.Management.Automation.PSCredential ("$env:USERNAME", $securePass)

                        Write-Host "Creating remote session..."
                        $session = New-PSSession -ComputerName "$env:SERVER" -Credential $credential

                        $scriptContent = Get-Content "day1.ps1" -Raw

                        Invoke-Command -Session $session -ScriptBlock {
                            Invoke-Expression $using:scriptContent
                        }

                        Remove-PSSession $session
                    '''
                }
            }
        }
    }
}
