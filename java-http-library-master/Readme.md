### Library

* *name* : lib.generic.http
* *type* : Generic functions and utilities for actions over http like REST and SOAP apis.

### Owner dependencies

* none

### Versions

* *1.0.0* : https://code.personal.com/confluence/display/MIC/lib.generic.http+-+1.0.0

### Components

* SOAP API integrations
* REST API integrations

### Build and upload the library

* Create a *settings.xml* file from *settings.xml.dist*
* Replace the *USERNAME* by your artifactory.personal.com username
* Replace the *PASSWORD* by your artifactory.personal.com password
* Run the following command to compile and publish in the artifact server

```
$ cd lib.generic.http/
$ docker-compose up
```

#### IntelliJ tip to compile

* File > project structure > project language level > 8. Lambda ...
* File > project structure > module > language level > 8. Lambda
* File > settings > compiler > java compiler > 1.8