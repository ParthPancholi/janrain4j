Janrain is a great service to bridge the gap between your website and the social networks. Janrain makes it easy for your users to login with an existing identity profile from social networks or identity providers like Facebook, Twitter, Google and Yahoo, to speed up online registration. Users can then interact with friends and publish activity data back through their social networks.

The Janrain API consists of a low-level HTTP API which returns XML or JSON responses. This offers a lot of flexibility as it can be used programming language agnostic this way.

However, when integrating it in a certain programming language, it is not really developer-friendly as you need to construct the HTTP requests and parse the XML or JSON responses manually.

The Janrain4j library provides a high-level and developer-friendly Java API for communicating with the Janrain API.

Simple example assuming you retrieved the token from the request:

```
EngageService engageService = EngageServiceFactory.getEngageService();

UserDataResponse userDataResponse = engageService.authInfo(token);

Profile profile = userDataResponse.getProfile();
String identifier = profile.getIdentifier();

engageService.setStatus(identifier, "Janrain4j is awesome!");
```

Continue with the [getting started](GettingStarted.md) section, or try the [demo application](http://janrain4j.appspot.com/).