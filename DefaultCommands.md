The Java CLI API comes packaged with a few default commands:

  * Help
    * The help command can be run by simply entering `help`. This will print a list of all known command names, along with their descriptions. Furthermore, the user can type `help -c <commandName>` to get detailed information about that specific command.
    * Alternatively, the help command is also available by doing `<commandName> --help`, such as {{{loglevel --help}}.
  * Log Level
    * The loglevel command can be invoked by doing `loglevel -l <logLevel>`, where log level is set to one of the following: SEVERE, ERROR, WARN, INFO, DEBUG, SUPERFINE. This will force the console to only output messages above the given threshold.
  * Exit
    * Typing the command `exit` will call `shutdown()` on the CommandLineApplication, and will then exit the application.