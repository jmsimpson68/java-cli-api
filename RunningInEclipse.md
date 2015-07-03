Since JLine does not like running in Eclipse, you can specify a JVM property "jlineDisable=true" to prevent JLine from running. In this case, the CLI API will simply read from System.in using the Scanner class. This disables auto-complete functionality.

To specify this JVM argument in Eclipse, modify your Run Configuration. Under the 'Arguments' tab, find the 'VM Arguments' box and add the following:

`-DjlineDisable=true`

When running with this run configuration, JLine will be disabled.