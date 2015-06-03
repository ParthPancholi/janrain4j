Under construction.

# Simple #

The simplest configuration possible is to create a `janrain4j.properties` file containing the Janrain4j configuration inside the root of your classpath (it should end up in `webapp/WEB-INF/classes`).

The file should contain at least:

```
janrain4j.api.key = your_api_key
janrain4j.application.id = your_application_id
janrain4j.application.domain = https://your_application_domain.rpxnow.com/
janrain4j.token.url = http://www.your_domain.com/your_token_path
```

Other (optional) configuration options are:

```
janrain4j.language.preference = your_language_preference
janrain4j.proxy.host = your_proxy_host
janrain4j.proxy.port = your_proxy_port
janrain4j.proxy.username = your_proxy_username
janrain4j.proxy.password = your_proxy_password
janrain4j.connect.timeout = your_http_connection_timeout
janrain4j.read.timeout = your_http_read_timeout
```

# Advanced #

By default Janrain4j will look for a `janrain4j.properties` file inside the root of your classpath. If you want to use a different file name you can override it by using the [Janrain4jConfigListener](http://janrain4j.googlecode.com/svn/docs/current/apidocs/com/googlecode/janrain4j/conf/Janrain4jConfigListener.html) servlet listener and context parameter in your `web.xml`:

```
<context-param>
    <param-name>janrain4jConfigLocation</param-name>
    <param-value>janrain4j-Development.properties</param-value>
</context-param>
    
<listener>
    <listener-class>com.googlecode.janrain4j.conf.Janrain4jConfigListener</listener-class>
</listener>
```

If you are using the Spring framework you should also have a look at the [SpringFramework](SpringFramework.md) integration section.

# Programmatic #

You can also programmatically configure Janrain4j. This might be useful for unit testing but can also be used in production if you prefer. Just make sure you create - and store - the Config object before you interact with the EngageService.

```
import static com.googlecode.janrain4j.conf.Config.Builder.*;

import com.googlecode.janrain4j.conf.ConfigHolder;

Config config = build()
        .apiKey("your_api_key")
        .applicationID("your_application_id")
        .applicationDomain("https://your_application_domain.rpxnow.com/")
        .tokenUrl("http://www.your_domain.com/your_token_path");

ConfigHolder.setConfig(config);
```

See the [Config](http://janrain4j.googlecode.com/svn/docs/current/apidocs/com/googlecode/janrain4j/conf/Config.html) apidocs for more information.