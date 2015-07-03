The Java CLI API provides a framework for starting, looping and executing a command line interface. It allows for annotations to determine which commands to load, and it handles the discovery and execution of the commands.

The goal of the CLI API is to make it as easy as possible for a developer to create and maintain a command line application that accepts user input and executes commands.

Some features of the API:

  * Tab auto completion of commands (integrated with [JLine](http://jline.sourceforge.net)).
  * Automatic discovery of commands via annotations.
  * Automatic initialization of application via annotations.
  * Automatic injection of command line arguments into Command classes via annotations (integrated with [JCommander](http://jcommander.org/)).
  * API handles looping across user input and executing the correct commands.
  * Application and Commands persist state via the CLIContext.
  * A sample command can be found at [AddUserCommand.java](http://code.google.com/p/java-cli-api/source/browse/trunk/sample-cli-client/src/main/java/net/dharwin/common/tools/cli/sample/client/commands/AddUserCommand.java).

I encourage anyone to look at the [Sample CLI Client](http://code.google.com/p/java-cli-api/source/browse/trunk/sample-cli-client/) to see how this may be used, as well as the [Getting Started](http://code.google.com/p/java-cli-api/wiki/CreatingACLIApplication) page.

The API is available via Maven. The artifacts are synced to the Maven Central repository. The dependency tag is:

```
<dependency>
  <groupId>net.dharwin.common.tools.cli</groupId>
  <artifactId>cli-api</artifactId>
  <version>1.0</version>
</dependency>
```