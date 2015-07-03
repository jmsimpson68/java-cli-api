For some applications, properties may want to be loaded on startup. Some of these properties might be internally set, while some might want to sit outside of the JAR file to be configured by the user.

An example use case for this may be an application that interacts with a web service. The property file may want to specify a `host` property that determines which web host to contact. This way, the user does not have to specify the host every time the application is run, but the user may be able to run a `changehost` command that replaces the property in the context.

To specify the property, you should override the `getExternalPropertiesFile` function of the `CLIContext`.

For example, in SampleCLIContext.java, we have:
```
/**
         * Specify the property file used by this context to load
         * initial properties from. This property file should sit outside of
         * the final jar file.
         */
        @Override
        protected String getExternalPropertiesFile() {
                return new File("sample_client.properties");
        }
```

This means that `sample_client.properties` may sit next to the JAR file, and its properties will be loaded into the context on startup.

The function `getInternalPropertiesFilename()` can specify a resource name to load properties from. This allows the application developer to specify a location of properties that should be loaded on startup, but could be located inside the built JAR and not be exposed to the user.

The Sample CLI Client demonstrates the use of context initialization by doing the following:

  * Two properties files are defined. The internal properties file is located under `/src/main/resources` and is called `embedded_sample_client.properties`. The external properties file is located at the project root and is called `sample_client.properties`.
  * The SampleCLIContext overriddes `getInternalPropertiesFilename()` to return `"/embedded_sample_client.properties"`, and `getExternalPropertiesFile()` is overridden to return `new File("sample_client.properties")`.
  * The Maven pom.xml copies the `sample_client.properties` file to the target directory after the build completes. This makes it easy to have the properties file next to the JAR.

Note that by specifying `new File("sample_client.properties")`, we are essentially saying the `sample_client.properties` file has to be in the working directory, not next to the JAR file. You may want to specify a fully-qualified file path, such as `new File("/etc/myapplication/sample_client.properties")`.

To see the sample client load the properties, you can execute the command _getproperty -p embedded\_property\_one_ or _getproperty -p external\_property\_one_. This will invoke the `GetPropertyCommand`, which simply outputs the value of a context key (and these keys were defined in the sample property files).