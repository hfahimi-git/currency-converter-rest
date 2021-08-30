# currency-converter-rest
after running the application by `./mvnw spring-boot:run` or `java -jar currency-converterImpl-rest-0.0.1-SNAPSHOT.jar`, open the browser and go to http://localhost:8080/convert with following params:

?source=&lt;source currnecy symbol&gt;

&target=&lt;target currency symbol&gt;

&amount=&lt;amount of money&gt;

# Example
localhost:8080/convert?source=aud&target=usd&amount=1450000

# response

```json
{
  "success": true,
  "convertCredential": {
    "source": "aud",
    "target": "usd",
    "amount": 1453200.0
  },
  "date": "Thu, 26 Aug 2021 15:53:24 GMT",
  "calculatedRate": 0.7250299,
  "result": 1053613.4,
  "_links": {
    "self": {
      "href": "http://localhost:8080/convert?source=aud&target=usd&amount=1453200.0"
    }
  }
}
```
