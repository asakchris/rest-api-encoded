# Enabling compression
- Compression is a very simple and effective way to save bandwidth and improve the response time.
- It reduces the response time by compressing the response body and then sending it over to the clients.
- It is disabled by default in Spring Boot, refer `server.compression.enabled` property in [this](server/src/main/resources/application.yml) file to enable it.
- Compression has a small overhead, so it can be enabled only if the response body is more than specific size, refer `server.compression.min-response-size` property.
- Client needs to send `Accept-Encoding` header with value as `gzip`, then only server compress the response.
- If server compress the response, then it adds `Content-Encoding` header with value as `gzip`. Clients have to check this header and uncompress the response.

### Server
- It is a Spring Boot REST API application which compress the response if response body is more than 2 KB.
- Run this application using [this](.run/Server.run.xml) file.
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
#### Open Feign
- Spring Cloud OpenFeign is designed to allow developers to use a declarative way to build HTTP clients by means of creating annotated interfaces without writing any boilerplate code.
- Refer [UserClient](client-feign/src/main/java/org/example/client/feign/client/UserClient.java) for the user endpoint exposed by Server module.