# Sananmuunnos service (Spring)

## Run tests

    ./gradlew test

## Run service in testing environment

    ./gradlew :bootRun

By default it is con

## Create docker image

For convenient deployment, Spring's Gradle plugin is able to build a docker image, with
name `isokissa/sannanmuunnos-api`:

    ./gradlew bootBuildImage

To run the image:

    docker run -p 8088:8088 -t isokissa/sananmuunnos-api:1.0.0

## Verify that the service is running

For example, if the service is running on localhost:8088:

    curl -X POST -d "CHANGE ME" http://localhost:8088/sananmuunnos


### Reference Documentation

For further reference, please consider the following sections:

- [Official Gradle documentation](https://docs.gradle.org)
- [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/gradle-plugin/reference/html/)
- [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/gradle-plugin/reference/html/#build-image)
- [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#using-boot-devtools)

### Additional Links

These additional references should also help you:

- [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
