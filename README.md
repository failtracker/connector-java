Connector for Java
==================

Add a dependency to pom.xml:

    <dependency>
        <groupId>com.failtracker</groupId>
        <artifactId>connector-java</artifactId>
        <version>0.1.1</version>
    </dependency>

And the repository where this dependency is available:

    <repository>
        <id>qiiip</id>
        <url>http://qiiip.org/mavenRepo</url>
    </repository>

The same for Grails:

    mavenRepo "http://qiiip.org/mavenRepo/"
    runtime 'com.failtracker:connector-java:0.1.1'

Create a new instance of FT and Connector. Connector accepts token of your project from [failtracker.com](http://failtracker.com).

    FT ft = new FT("[project token]")

In the place where a failure occur, send the failure to the [failtracker.com](http://failtracker.com). This call is synchronous.

    ft.send(new Failure("Failed to load important data", "Detailed information about the failure"))

Or, if it makes sense, send the failure from the catch block:

    try {
        // here the app fails and we must be notified about it
    } catch (Exception e) {
        ft.send(new Failure(e))
    }

For asynchronous calls, create ResponseCallback and pass it into the send method.

    Failure failure = new Failure(causeTitle, throwable)
    ResponseCallback callback = new ResponseCallback() {
        @Override
        void response(Response r) {
            if (r.code == 201) {
                // error happened, notify user it has been also reported
            } else {
                // log an exception (just for the case)
            }
        }
    }
    ft.send(failure, callback)

Use the following line in order to see what is happening during failure reporting.

    ft.setLoggingLevel(Level.ALL)