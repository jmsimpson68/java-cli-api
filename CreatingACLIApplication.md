# Introduction #

This Wiki covers how to use the CLI API to create a custom CLI application.


# Steps #

  * Add the cli-api as a dependency to your project.
  * Make sure the main class of your final jar specifies _net.dharwin.common.tools.cli.api.EntryPoint_.
  * Create your implementation of the CommandLineApplication class. This should be a class that extends CommandLineApplication, and the class must be annotated with @CLIEntry.
  * Optionally create your own CLIContext implementation. This can be handy if you want to make it easy to store known objects (see the sample client's use of the SampleCLIContext).
    * If you are using a custom context, your @CLIEntry class MUST override _createContext()_ to return it! Also, your commands MUST specify the same context type (or a superclass of it). This is explained below.
  * Create your commands. Ideally, this all go in the same package (or at least its own hierarchy of packages). Each command should extend Command, and the generics on Command MUST match the context type (or a superclass of) that you specified in your _createContext()_.
