Janrain4j integrates very well with the Spring framework. Just add the bean definitions below to your `applicationContext.xml`:

```
<bean class="com.googlecode.janrain4j.springframework.Janrain4jConfigurer"
        p:apiKey="${janrain.apiKey}"
        p:applicationID="${janrain.applicationID}"
        p:applicationDomain="${janrain.applicationDomain}"
        p:tokenUrl="${janrain.tokenUrl}" />
 
<bean class="com.googlecode.janrain4j.springframework.EngageServiceFactoryBean" />
```

And then autowire the EngageService in e.g. a Spring Controller class like:

```
@Autowired private EngageService engageService;
```

Above Spring configuration example uses a Spring PropertyPlaceholder to retrieve the Janrain4j configuration options. No `janrain4j.properties` file is needed anymore. See the [Janrain4jConfigurer](http://janrain4j.googlecode.com/svn/docs/current/apidocs/com/googlecode/janrain4j/springframework/Janrain4jConfigurer.html) apidocs for full configuration options.

Also have a look at the Spring Framework Basic [sample](http://code.google.com/p/janrain4j/source/browse/#svn/trunk/samples/springframework-basic).