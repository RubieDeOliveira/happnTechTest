#Happn Tech Test

## Requirements

To run the app you must have :
- jdk11
- gradle 

## Run the application

To launch the application :
- build it
    - you can use the gradle windows on the right side of Intellij
    - click on build
- type `java -jar build/libs/happnTechTest-1.0.0.jar` in a terminal

The program will ask you the pathfile of your file. By default it treats the file you gaves us for this test.
Then you just have to enter the command you want between :
- --nbpoi '{"min_lat": [DOUBLE],"min_lon": [DOUBLE]}'
- --densest '{"n": [INT]}'