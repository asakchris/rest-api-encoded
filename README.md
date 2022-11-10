# Enabling compression
- Compression is a very simple and effective way to save bandwidth and improve the response time.
- It reduces the response time by compressing the response body and then sending it over to the clients.
- It is disabled by default in Spring Boot, refer `server.compression.enabled` property in [this](server/src/main/resources/application.yml) file to enable it.
- Compression has a small overhead, so it can be enabled only if the response body is more than specific size, refer `server.compression.min-response-size` property.
- Client needs to send `Accept-Encoding` header with value as `gzip`, then only server compress the response.
- If server compress the response, then it adds `Content-Encoding` header with value as `gzip`. Clients have to check this header and uncompress the response.

### Server
- It is a Spring Boot REST API application which compress the response if response body is more than 2 KB.
- Run this application using [Server.run.xml](.run/Server.run.xml) file.
- Refer below for details about how to access the endpoints:
```shell
# Get uncompressed response
curl http://localhost:18000/api/v1/server/users

# Get compressed response and write it into users.zip file in current directory
curl http://localhost:18000/api/v1/server/users -H "Accept-Encoding: gzip" -o users.zip

# Get compressed response but print only header
curl -I http://localhost:18000/api/v1/server/users -H "Accept-Encoding: gzip"
```

### Client
Refer below sample clients using Open Feign & Rest Template:

#### Open Feign
- Spring Cloud OpenFeign is designed to allow developers to use a declarative way to build HTTP clients by means of creating annotated interfaces without writing any boilerplate code.
- Refer [UserClient](client-feign/src/main/java/org/example/client/feign/client/UserClient.java) for the user endpoint exposed by Server module.
- Enabled response code compression using `feign.compression.response.enabled` property in [application.yml](client-feign/src/main/resources/application.yml).
- Run this client using [Client-Feign.run.xml](.run/Client-Feign.run.xml) file.

#### Rest Template
- Replaced default request factory with Apache HttpClient `HttpComponentsClientHttpRequestFactory`, which uncompress the response on the fly.
  Refer [Config](client-rest-template/src/main/java/org/example/client/rest/config/Config.java).
- This request factory from Apache HttpClient sends `Accept-Encoding` header and uncompress the response if it contains `Content-Encoding` header with value as `gzip`.
- Run this client using [Client-Rest-Template.run.xml](.run/Client-Rest-Template.run.xml) file.
