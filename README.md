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

    curl -H "Content-Type: application/json" -X POST -d '   mama tata    ' http://localhost:8088/sananmuunnos

## Implementation details

### "Tokens"

Input text is firstly split into "tokens", that have form `<non-spaces><spaces>`. For first
token, the `<non-spaces>` can be empty, when there are leading spaces in the beginning of 
input string.  

### Finite State machine to split words

The state transition table is used to split the word token into its "beginning" 
and the "rest" (B - add to "beginning", R - add to "rest"):

| State \ Input | **vowel** | **non-vowel** |
| -------------|:-------------:| :--------:|
| **START**         | VOWEL_START, B | START, B |
| **VOWEL_START**   | VOWEL_START, B | REST, R |
| **REST**         | REST, R | REST, R |
