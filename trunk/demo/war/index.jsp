<%@ page isELIgnored="false" %>
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Hello App Engine</title>
  </head>

  <body>
    <h1>Hello App Engine!</h1>
	
    <janrain:embed languagePreference="nl" embedUrl="http://janrain4j.rpxnow.com/openid/embed" tokenUrl="http://bla.com" />
    
    <table>
      <tr>
        <td colspan="2" style="font-weight:bold;">Available Servlets:</td>        
      </tr>
      <tr>
        <td><a href="janrain4j_demo">Janrain4j_demo</a></td>
      </tr>
    </table>
  </body>
</html>