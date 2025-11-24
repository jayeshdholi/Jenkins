# Apache Tomcat Installation Guide

This document provides step-by-step instructions to install Apache Tomcat on Linux
(Rocky / CentOS / Ubuntu).

---

## Prerequisites
- Linux server (Rocky / CentOS / Ubuntu)
- Sudo privileges
- Internet connection

---

## Step 1: Install Java

Tomcat requires Java to run.

### Rocky / CentOS:
sudo dnf install java-11-openjdk java-11-openjdk-devel -y
Ubuntu:
sudo apt install openjdk-11-jdk -y
Check Java version:
java -version

Step 2: Create Tomcat User
sudo useradd -m -U -d /opt/tomcat -s /bin/false tomcat

Step 3: Download Tomcat Package
Go to Apache's official site and download the latest version.

Example (Tomcat 10):
cd /tmp
curl -O https://downloads.apache.org/tomcat/tomcat-10/v10.1.13/bin/apache-tomcat-10.1.13.tar.gz

Step 4: Extract Tomcat
sudo mkdir /opt/tomcat
sudo tar -xzvf apache-tomcat-10.1.13.tar.gz -C /opt/tomcat --strip-components=1

Step 5: Set Permissions
sudo chown -R tomcat:tomcat /opt/tomcat
sudo chmod -R u+x /opt/tomcat/bin

Step 6: Create Systemd Service File
sudo nano /etc/systemd/system/tomcat.service

Add this content:

[Unit]
Description=Apache Tomcat Web Application Container
After=network.target

[Service]
Type=forking
User=tomcat
Group=tomcat

Environment="JAVA_HOME=/usr/lib/jvm/jre"
Environment="CATALINA_HOME=/opt/tomcat"
Environment="CATALINA_BASE=/opt/tomcat"
Environment="CATALINA_PID=/opt/tomcat/temp/tomcat.pid"
Environment="JAVA_OPTS=-Djava.security.egd=file:///dev/urandom"

ExecStart=/opt/tomcat/bin/startup.sh
ExecStop=/opt/tomcat/bin/shutdown.sh

Restart=on-failure

[Install]
WantedBy=multi-user.target

Step 7: Reload Daemon & Start Tomcat
sudo systemctl daemon-reload
sudo systemctl start tomcat
sudo systemctl enable tomcat

Check status:
sudo systemctl status tomcat

Step 8: Open Firewall Port (8080)
sudo firewall-cmd --permanent --add-port=8080/tcp
sudo firewall-cmd --reload

Access Tomcat
Open the browser:
http://<server-ip>:8080

Enable Tomcat Manager GUI
sudo nano /opt/tomcat/conf/tomcat-users.xml

Add below code:
<role rolename="manager-gui"/>
<role rolename="admin-gui"/>
<user username="admin" password="admin123" roles="manager-gui,admin-gui"/>

Restart Tomcat:
sudo systemctl restart tomcat

