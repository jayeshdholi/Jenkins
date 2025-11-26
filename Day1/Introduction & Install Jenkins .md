  <p align="center">
    <img src="CoreCard.png" alt="CoreCard" />
  </p>

# What is Jenkins?

- **Jenkins** is an open-source automation tool used for **Continuous Integration (CI)** and **Continuous Delivery (CD)**.
- Jenkins is developed using **Java**.
- Jenkins was originally part of the **Hudson Project**.
- Initially, it was called **Hudson**, and later it was renamed to **Jenkins**.

  ---

# About CI CD-

- **CI and CD** are two most frequently used terms in modern development practises and DevOps practises.
- CI stands for Continuous Integration. It is fundamental DevOps best practise where developers frequently merge code changes to central repository where automated builds and tests runs.
- CD means Continuous Delivery or Continous Deployment.
- Jenkins is a self-contained, open-source automation server which can be used to automate all sorts of tasks related to building, testing, and delivering or deploying software.

  ---

# Build & Deployment Process-

1) Take latest source code from repository

2) Compile source code

3) Execute Unit tests (Junits)

4) Perform Code Review

5) Package code as war file

6) Deploy the war file into server

Note: All the above build and deployment tasks can be automated using Jenkins tool.

---

# Jenkins Installation-

step-by-step instructions to install **Jenkins** on Linux (Rocky/Ubuntu) or Windows systems for Continuous Integration (CI) and Continuous Delivery (CD) setup.

---

# 1. Prerequisites

Before starting the installation, ensure you have:

* **Java (JDK 11 or 17)** installed and configured
* **Internet connection** (to download Jenkins packages and plugins)
* **Administrator/root privileges**

---

# 2. Check Java Installation, updated package & disable firewall

Update package:

```bash
sudo dnf update
sudp dnf upgrade
```

Disable firewall:

```bash
sudo systemctl stop firewalld
sudo systemctl disable firewalld
```

Verify Java version:

```bash
java -version
```

If Java is not installed, install it using one of the following commands:

### For Linux (Rocky/Ubuntu)

```bash
sudo dnf install java-17-openjdk -y     # For Rocky/CentOS
sudo apt install openjdk-17-jdk -y      # For Ubuntu/Debian
```

### For Windows

1. Download **JDK 17** from [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or [Adoptium](https://adoptium.net/).
2. Install and configure environment variables:

   ```
   JAVA_HOME = C:\Program Files\Java\jdk-17
   PATH = %JAVA_HOME%\bin
   ```

---

# 3. Install Jenkins

### For Linux (Rocky/Red Hat)

1. **Add Jenkins Repository**

   ```bash
   sudo wget -O /etc/yum.repos.d/jenkins.repo \
     https://pkg.jenkins.io/redhat-stable/jenkins.repo
   sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io-2023.key
   ```

2. **Install Jenkins**

   ```bash
   sudo dnf install jenkins -y
   ```

3. **Start and Enable Jenkins**

   ```bash
   sudo systemctl enable jenkins
   sudo systemctl start jenkins
   sudo systemctl status jenkins
   ```

---

### For Ubuntu/Debian

```bash
wget -q -O - https://pkg.jenkins.io/debian/jenkins.io.key | sudo apt-key add -
sudo sh -c 'echo deb https://pkg.jenkins.io/debian binary/ > /etc/apt/sources.list.d/jenkins.list'
sudo apt update
sudo apt install jenkins -y
sudo systemctl enable jenkins
sudo systemctl start jenkins
```

---

### For Windows

1. Download the latest **Jenkins Windows Installer (.msi)** from
   👉 [https://www.jenkins.io/download](https://www.jenkins.io/download)
2. Run the installer as **Administrator**.
3. During setup:

   * Select **Run Jenkins as a Service**
   * Jenkins will run on default port **8080**
4. Access Jenkins after installation:

   ```
   http://localhost:8080
   ```

---

# 4. Unlock Jenkins

After installation, Jenkins asks for an **Administrator password**.

* **Linux**

  ```bash
  sudo cat /var/lib/jenkins/secrets/initialAdminPassword
  ```

* **Windows**

  ```
  C:\Program Files\Jenkins\secrets\initialAdminPassword
  ```

Copy and paste the password into the web UI to proceed.

<img width="1054" height="606" alt="image" src="https://github.com/user-attachments/assets/4058b88f-0bd8-4bba-a902-20ae374723b0" />

---

# 5. Customize Jenkins Setup

1. Choose **“Install suggested plugins”** during setup.
2. Create your **first admin user**.
3. Set Jenkins URL (default: `http://<your-server-ip>:8080`).


---

# 6. Verify Installation

* Access Jenkins Dashboard:

  ```
  http://<your-ip>:8080
  ```

---

# 7. Post-Installation Configuration (Optional)

* **Change Jenkins port:**
  Edit `/etc/sysconfig/jenkins` →

  ```
  JENKINS_PORT="9090"
  ```
* **Secure Jenkins** using HTTPS via Nginx or Apache reverse proxy.
* **Install Common Plugins:**

  * Git
  * Pipeline
  * Blue Ocean
  * Credentials Binding
  * Email Extension

---

### Create Admin account

## Creating First Job in Jenkins 

1) Goto Jenkins Dashboard

2) Click on New Item

		-> Enter Item Name (Job Name)
		-> Select Free Style Project & Click OK
		-> Enter some description
		-> Click on 'Build' tab
		-> Click on 'Add Build Step' and select 'Execute Shell'

3) Enter below shellscript

		-> echo "Hello Guys,"
		-> touch test.txt
		-> echo "Hello Guys, Welcome to Jenkins Classes" >> test.txt
		-> echo "Done..!!"

4) Apply and Save

Note: With above steps we have created JENKINS Job

5) Click on 'Build Now' to start Job execution

6) Click on 'Build Number' and then click on 'Console Ouput' to see job execution details.


=> Jenkins Home Directory in EC2 : /var/lib/jenkins/workspace/

		$ cd /var/lib/jenkins/workspace/

7) Go to Jenkins home directory and check for the job name --> check the file created inside the job

---


📘 **Reference:** [Official Jenkins Installation Guide](https://www.jenkins.io/doc/book/installing/)
