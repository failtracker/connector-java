Connector for Java
==================

Create a new instance of FT and Connector. Connector accepts token of your project from [failtracker.com](http://failtracker.com)

    FT ft = new FT(new Connector("tDFhnPiYmRrmgu79Z"))

In the place where a failure occur, send the failure to the [failtracker.com](http://failtracker.com).

    ft.report(new Failure(ex))

Or, if it makes sense, send the failure from the catch block:

    try {
        // here the app fails and we must be notified about it
    } catch (Exception e) {
        ft.report(new Failure(e))
    }