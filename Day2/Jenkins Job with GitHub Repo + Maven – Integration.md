<p align="center"> <img src="CoreCard.png" alt="CoreCard" /> </p>

## Pre-Requisites

- Java Installed
- Maven Installed
- Git Client Installed

### Jenkins Installed & Running

**1. Git Installation in Linux VM**

sudo apt install git -y

**2. Maven Installation in Jenkins**

-- Go to Jenkins Dashboard

-- Navigate to Manage Jenkins

-- Open Global Tools Configuration

-- Scroll to Maven

-- Click Add Maven

-- Give a name (example: **Maven-3.9.6**)

-- Select Install automatically OR provide MAVEN_HOME

-- Click Save

**3. Maven Definition (Simple Explanation)**

Maven is a build automation and dependency management tool for Java applications.

## What Maven Does
- Compiles code
- Runs tests
- Downloads dependencies automatically
- Packages your project (jar or war)
- Works perfectly with Jenkins for CI/CD

**Common Maven Commands**

mvn -v               # Check Maven version
mvn clean            # Remove old compiled files
mvn compile          # Compile project
mvn test             # Run tests
mvn package          # Create WAR/JAR
mvn clean package    # Clean + build

**4. GitHub Repository for Practice**

**Git Repo URL:**
https://github.com/akashsarma/maven-web-app.git

**5. Jenkins → Maven → Tomcat Workflow (Diagram)**

**flowchart LR-**

    A[Jenkins Job] --> B[Git Checkout]
    B --> C[Maven Build<br/>clean package]
    C --> D[Generate WAR File]
    D --> E[Deploy to Tomcat<br/>using Deploy Plugin]
    E --> F[Application Running]

**6. Create Jenkins Job (GitHub + Maven) Step-by-step**

Click New Item

Enter job name

Select Freestyle Project → OK

Add description (optional)

Configure Git

Go to Source Code Management

Select Git

Enter repository URL:

https://github.com/akashsarma/maven-web-app.git

Build Step (Maven Build)

Go to Build section

Click Add Build Step

Select Invoke top-level Maven targets

Choose your Maven installation

Enter goals:

clean package

Click Apply → Save

**7. Run the Jenkins Job**

Click Build Now

Click the build number

Open Console Output to view logs

**8. Jenkins Workspace Path**
/var/lib/jenkins/workspace/

Go inside your job → find the target folder → WAR file will be there.
