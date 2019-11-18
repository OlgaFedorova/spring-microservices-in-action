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



###### Notes
In your configuration:
- ribbon.connectionTimeout is 5000
- ribbon.readTimeout is 15000
- ribbon.maxAutoRetries is 0 (default)
- ribbon.maxAutoRetriesNextServer is 1 (default)
So the hystrixTimeout should be: (5000 + 15000) * (1 + 0) * (1 + 1) // -> 4000