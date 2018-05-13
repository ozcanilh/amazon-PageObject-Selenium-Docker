# Amazon

End-to-end testing with Selenium, Page Object Pattern, Parallel Test with Multiple Browsers in Docker VM, Java, Maven, TestNG

## For Local Run

## Docker Installation and Running

* Helper Tools for Docker: (Not needed these tools but useful for management Docker)
Install Docker Quickstart Terminal, Kitematic

### Docker Compose File
In docker-compose.yml file
Chrome and Firefox hub link to node .
Session and Instance of hub set to 5
Ports for Browsers: Chrome: 5900 Firefox: 5901 settings are available

### For Docker Run

* BaseTest.java config for running on chrome browser and local. 
* BaseTestDockerConfig.java config for running on MultipleBrowser and Parallel Test in Docker. 

1)Delete code in BaseTest.java 
2)Copy All Code BaseTestDockerConfig.java into BaseTest.java 
3)Or change name BaseTest AS BaseTest1 then change name BaseTestDockerConfig AS BaseTest  
 
* After these settings , before running test with docker:

- Go to project file where docker-compose.yml
- Up Docker with "docker-compose up -d"
- Check State of node and hubs with "docker-compose ps"
- Check docker ps or point directly to your browser and open http://localhost:4444/grid/console or http://192.168.99.100:4444/grid/console .Selenium grid console.
https://prnt.sc/jhgkvr
- Open 2 VNC Viewer for Chrome and Firefox Node
- Type 192.168.99.100.5901 in VNC Server for Mozilla , Password: secret, Connect it
- Type 192.168.99.100.5900 in VNC Server for Chrome , Password: secret, Connect it 
- Run TestNG_Docker.xml as TestNG Suite 
