pipeline {
    agent { label 'WIN-NODE-01' }

    parameters {
        choice(
            name: 'SERVER',
            choices: ['JAYESHD', 'PPG-App02'],
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
