
Prerequisite: Java 8 or later on your path

##Run a build
./gradlew clean build

##Running the application
To run the program using gradle, use ./gradlew run -Dbarren.land.areas="{\"0 292 399 307\"}". The java arg value should be a double quoted string with any internal double quotes escaped with \. Ideally there would be
a way to pass any remaining arguments after "./gradlew run" to the Main program, but after a bit of research this does not appear to be supported.  

##test case 1
./gradlew run -Dbarren.land.areas="{\"0 292 399 307\"}"

##test case 2
./gradlew run -Dbarren.land.areas="{\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}"