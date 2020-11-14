### Microservice

* *name* : mic.b2b.search.centralizer
* *type* : api
* *internal port* : 4567
* *external port* : 3005

### Owner dependencies

* lib-generic-util

### Run the container

```
$ docker build -t mic.b2b.search.centralizer .
```

```
$ docker run --name mic.b2b.search.centralizer -p 3005:4567 -d mic.b2b.search.centralizer
```

### Test the functions

* curl /
* curl /v1/search/:partner/:suppliers/:search

### Health check endpoint

* undefined

#### IntelliJ steps to compile

* File > project structure > project language level > 8. Lambda ...
* File > project structure > module > language level > 8. Lambda
* File > settings > compiler > java compiler > 1.8