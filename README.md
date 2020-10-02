# Sananmuunnos service (Spring)

## Run tests

    ./gradlew test

## Run service in testing environment

    ./gradlew :bootRun
    
It is configured in `application.properties` to listen to port 8088. Access the service
with url `http://localhost:8088/sananmuunnos`

## Create docker image

For convenient deployment, Spring's Gradle plugin is able to build a docker image, with
name `isokissa/sananmuunnos-api`:

    ./gradlew bootBuildImage

To run the image:

    docker run -p 8088:8088 -t isokissa/sananmuunnos-api:1.0.0

## Verify that the service is running

For example, if the service is running on localhost:8088:

    curl -H "Content-Type: application/json" -X POST -d 'outget "input"' http://localhost:8088/sananmuunnos
