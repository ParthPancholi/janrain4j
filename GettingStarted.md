# Installation #

[Download](http://code.google.com/p/janrain4j/downloads/list) and add the janrain4j-x.x.x.jar to your project's classpath (`webapp/WEB-INF/lib`).

Maven users should add the library using the following dependency:

```
<dependency>
    <groupId>com.googlecode.janrain4j</groupId>
    <artifactId>janrain4j</artifactId>
    <version>1.1.0</version>
</dependency>
```

# Configuration #

Create a `janrain4j.properties` file containing the Janrain4j configuration inside the root of your classpath (it should end up in `webapp/WEB-INF/classes`).

The file should contain:

```
janrain4j.api.key = your_api_key
janrain4j.application.id = your_application_id
janrain4j.application.domain = https://your_application_domain.rpxnow.com/
janrain4j.token.url = http://www.your_domain.com/your_token_path
```

You can access the Janrain API Key, Application ID and Application Domain from the Janrain dashboard.

For advanced configuration options available see the [configuration](Configuration.md) section.

# Sign-in Page #

Create a sign-in jsp page containing:

```
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>

<html>
    <body>
        ...
        <janrain:signInEmbedded />
        ...
    </body>
</html>
```

This will include the Janrain embedded sign-in widget on your page.
If you prefer a sign-in link with a pop-up instead of the embedded widget then use:

```
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>

<html>
    <body>
        ...
        <janrain:signInLink>Sign-in</janrain:signInLink>
        ...
        <janrain:signInOverlay />
    </body>
</html>
```

The `<janrain:signInOverlay />` tag should be included just before the closing `</body>` tag.

All jsp tags use the values configured in `janrain4j.properties`. But if needed they can be overridden. See the [tag library](TagLibrary.md) section for the complete Janrain4j tag library reference.

# Authentication Information #

After signing in Janrain will redirect to the token url configured in the `janrain4j.properties`. You need to map this token url using a Servlet or Controller (dependent on what framework you are using).

A typical Servlet implementation will look like:

```
public class TokenServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Retrieve the token sent by Janrain 
        String token = request.getParameter("token");
        
        // Get the EngageService
        EngageService engageService = EngageServiceFactory.getEngageService();

        try {
            // Retrieve the Janrain user data
            UserDataResponse userDataResponse = engageService.authInfo(token);
            Profile profile = userDataResponse.getProfile();
            String identifier = profile.getIdentifier();

            // TODO Retrieve more user data like name, email, friends, ...

            // TODO Do something with the retrieved user data 
        }
        catch (EngageFailureException e) {
            // TODO
        }
        catch (ErrorResponeException e) {
            // TODO
        }
    }
}
```

# Next Steps #

The above code shows how easily you can retrieve the user data from the signed-in user using the `authInfo` method. The example code only retrieves the identifier, but there so much more info available. It's up to you to decide what information is needed and perhaps to store it your local database. You can also map the retrieved identifier to a local primary key or send a status update to the social network of the signed-in user.

See the [EngageService](http://janrain4j.googlecode.com/svn/docs/current/apidocs/com/googlecode/janrain4j/api/engage/EngageService.html) apidocs for a complete overview of all the Engage API methods and the returned data.

Also have a look at the [samples](http://code.google.com/p/janrain4j/source/browse/#svn/trunk/samples).