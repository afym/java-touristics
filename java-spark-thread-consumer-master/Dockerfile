FROM maven:3.5.0-jdk-8-alpine

WORKDIR /usr/mic-b2b-search-centralizer

ADD pom.xml /usr/mic-b2b-search-centralizer/pom.xml
ADD settings.xml /usr/share/maven/conf/settings.xml

RUN ["mvn", "dependency:purge-local-repository"]
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

ADD src /usr/mic-b2b-search-centralizer/src
RUN ["mvn", "package"]

RUN rm -rf /usr/mic.providerB.webservice/src/*
RUN rm -rf /tmp/log/*

EXPOSE 4567

CMD ["/usr/bin/java", "-jar", "target/microservice-jar-with-dependencies.jar"]