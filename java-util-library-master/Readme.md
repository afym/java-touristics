### Library

* *name* : lib.generic.util
* *type* : Generic functions and utilities for all the projects

### Owner dependencies

* none

### Versions

* 1.0.0

### Components

* date operations and format
* cryptografy functions
* system utils for integrations with docker

### Build and upload the library

* Create a *settings.xml* file from *settings.xml.dist*
* Replace the *USERNAME* by your artifactory.personaln.com username
* Replace the *PASSWORD* by your artifactory.personaln.com password
* Run the following command to compile and publish in the artifact server
 
```
$ cd lib.generic.util/
$ docker-compose up
```

#### IntelliJ tip to compile

* File > project structure > project language level > 8. Lambda ...
* File > project structure > module > language level > 8. Lambda
* File > settings > compiler > java compiler > 1.8