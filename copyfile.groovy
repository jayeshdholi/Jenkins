pipeline {
    agent { label 'LAB-windows-EV' }

    parameters {
        choice(
            name: 'SERVER',
            choices: ['PPG-App01', 'PPG-App02'],
            description: 'Choose server to run script'
        )
        string(name: 'USERNAME', description: 'Enter username')
        password(name: 'PASSWORD', description: 'Enter password')
    }

    stages {

        stage('Run Remote PowerShell Script') {
            steps {
                powershell """
                    Write-Host "Running script on: ${params.SERVER}"
                    Write-Host "Using username: ${params.USERNAME}"

                    # Convert password to secure string
                    \$securePass = ConvertTo-SecureString "${params.PASSWORD}" -AsPlainText -Force
                    \$credential = New-Object System.Management.Automation.PSCredential ("${params.USERNAME}", \$securePass)

                    # Create remote session
                    Write-Host "Creating remote session..."
                    \$session = New-PSSession -ComputerName "${params.SERVER}" -Credential \$credential

                    # Load script from workspace
                    \$scriptContent = Get-Content "day1.ps1" -Raw

                    # Execute using session
                    Invoke-Command -Session \$session -ScriptBlock {
                        Invoke-Expression \$using:scriptContent
                    }

                    # Close session
                    Remove-PSSession \$session
                """
            }
        }
    }
}
