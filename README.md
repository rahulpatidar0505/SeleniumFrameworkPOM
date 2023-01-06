# Selenium Test
This project is used for functional testing of test application.

# Github repository link:
https://github.com/rahulpatidar0505/SeleniumFrameworkPOM

# Local machine setup:
1) Install following software on local(macOS):
    1) Intellij Aqua : https://www.jetbrains.com/aqua/download/#section=mac
2) Git clone master branch from Git repository:
   git clone https://github.com/rahulpatidar0505/SeleniumFrameworkPOM.git
3) Open project in Intellij Aqua(All dependency will be downloaded automatically).
4) Precondition: Assuming user is new and Cart is empty here https://magento.softwaretestingboard.com/.
5) Trigger option: 
   1) Run test directly from Test file.
   2) Run testng.xml tile
   3) Open terminal and run "mvn clean install"
   4) There is TestRunner.java file created inside utility package, we can trigger this file as well when we have multiple testng.xml available.

# Jenkin Setup in my Mac: 
1) Ref : https://www.jenkins.io/download/weekly/macos/
2) Install : brew install jenkins
3) Start : brew services start jenkins
4) GoTo browser and open : http://localhost:8080/ and install necessory plugin
5) Create New Item, select project type and configure system and Global configuration settings(Add JDK and Maven OR directly write commands in Execute Shell Script) and select project.
6) Trigger Build Now
