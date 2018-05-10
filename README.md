
Prerequisite: Java 8 or later on the classpath

#Run a build including unit tests
./gradlew clean build

#Running
To run the program and provide barren land areas use the gradle run task with the java argument -Dbarren.land.areas.  
The java arg value should be a double quoted string with any internal strings escaped with \. Ideally there would be
a way to pass any remaining arguments after .gradlew run to the Main program, but after a bit of research this does
not appear to be supported.  

#test case 1
./gradlew run -Dbarren.land.areas="{\"0 292 399 307\"}"

#test case 2
./gradlew run -Dbarren.land.areas="{\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}"