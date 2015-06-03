# signInEmbedded #

Embeds the sign-in widget.

_Examples_

```
<janrain:signInEmbedded />

<janrain:signInEmbedded languagePreference="en" />

<janrain:signInEmbedded width="600" height="360" />
```

_Attributes_

  * `applicationDomain` (optional) - The Janrain application domain. Defaults to value from ConfigHolder. E.g. `https://mydomain.rpxnow.com/`
  * `tokenUrl` (optional) - The token url. Defaults to value from ConfigHolder. E.g. `http://mydomain.com/token`
  * `defaultProvider` (optional) - The default sign-in provider. E.g. `facebook`
  * `flags` (optional) - The flags to be set. E.g. `show_provider_list`
  * `languagePreference` (optional) - The language preference. Defaults to value from ConfigHolder. E.g. `en` or `nl`
  * `width` (optional) - The width of the sign-in widget in pixels. Defaults to `400`
  * `height` (optional) - The height of the sign-in widget in pixels. Defaults to `240`


# signInLink #

Renders the sign-in link.

_Examples_

```
<janrain:signInLink>Sign In</janrain:signInLink>

<janrain:signInLink styleClass="my-style">Sign In</janrain:signInLink>
```

_Attributes_

  * `applicationDomain` (optional) - The Janrain application domain. Defaults to value from ConfigHolder. E.g. `https://mydomain.rpxnow.com/`
  * `tokenUrl` (optional) - The token url. Defaults to value from ConfigHolder. E.g. `http://mydomain.com/token`
  * `style` (optional) - The CSS style to be added to the link. E.g. `color: black; font-size: 16px;`
  * `styleClass` (optional) - The CSS style class to be added to the link. E.g. `my-class`


# signInOverlay #

Includes the sign-in overlay.

_Examples_

```
<janrain:signInOverlay />

<janrain:signInOverlay languagePreference="en" />
```

_Attributes_

  * `defaultProvider` (optional) - The default sign-in provider. E.g. `facebook`
  * `flags` (optional) - The flags to be set. E.g. `show_provider_list`
  * `languagePreference` (optional) - The language preference. Defaults to value from ConfigHolder. E.g. `en` or `nl`