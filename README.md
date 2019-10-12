# api-automation-hf

* To run a specific Test case, 
1. In pom.xml, for maven-surefire-plugin <artifact>, add <groups> to configuration & put name of testsuite to be run
eg. if you want to run "TestGetBookings" test case, put <groups>com.hellofresh.Tests.TestGetBookings</groups> in configuration
2. then, in command line, execute mvn clean test
