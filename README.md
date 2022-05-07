# HttpLogFormatterDemo

In order to replicate the behavior mentioned in [the issue](https://github.com/zalando/logbook/issues/1321) please run the application and the send one of the following requests

cURL
```curl
curl --location --request POST 'http://localhost:8080/demo' \
--form 'text="Hello World"'
```

or HTTP (e.g. through IntelliJ's http client)
```
POST http://localhost:8080/demo
Content-Type: multipart/form-data; boundary=abc

--abc
Content-Disposition: form-data; name="text"
Content-Type: text/plain

Hello World
--abc--
```

The application should throw a `MissingServletRequestPartException: Required request part 'text' is not present` even though the part is included in the request.
