###### Settings
Add in .m2\settings.xml
```xml
 <pluginGroups>
     <pluginGroup>io.fabric8</pluginGroup>
  </pluginGroups>
```


###### Build and run
- build docker images
```bash
mvn clean install docker:build
```



command for run:
- go into suitable service's file folder
```bash
cd config-server
```
- run service
```bash
mvn spring-boot:run
```