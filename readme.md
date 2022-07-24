# MKBecomAPI4j

## Wrapper for API of Moscow Credit Bank (MCB)
![Maven Central](https://img.shields.io/maven-central/v/ru.zoommax/MKBecomAPI4j?color=bridthgreen&style=plastic)
![api v](https://img.shields.io/badge/MCB_API-v1.11-bridthgreen?style=plastic)
![GitHub](https://img.shields.io/github/license/ZooMMaX/MKBecomAPI4j?color=b&style=plastic)
---

### Wrapper initialization
To initialize the wrapper, you need to set the parameters:

* KEYSTOREPATH -
  path to the certificate
* KEYSTOREPASS - сertificate key
* KEYPASS - alias key
* TEST - `true` if the certificate is a test one, `false` otherwise

![example initalize](https://img.shields.io/badge/EXAMPLE-initialize-yellow?style=plastic)

```
...

Init.setTEST(true);
Init.setKEYSTOREPATH("cert.p12");
Init.setKEYSTOREPASS("passwd");
Init.setKEYPASS("passwd"); 

...
```

---

### Sending a request and receiving a response

To create a request, the library provides `PayloadBuilder.class`.

`ResponseParser.class` is provided in the library for parsing the response and getting fields.

![example srpr](https://img.shields.io/badge/EXAMPLE-send_request_&_parse_response-yellow?style=plastic)

```java
public class Main {
    public static void main(String[] args){
        Init.setTEST(true);
        Init.setKEYSTOREPATH("cert.p12");
        Init.setKEYSTOREPASS("passwd");
        Init.setKEYPASS("passwd");
        PayloadBuilder getData = PayloadBuilder.builder()
                .mid("600000000002121")
                .oid("test")
                .pan("4432730000000168")
                .exp_date("2106")
                .cvv("986")
                .amount("000000000100")
                .currency("643")
                .aid("443222")
                .client_mail("test@mkb.ru")
                .build();
        ResposeParser dataParser = getData.request(MkbEndPoints.pareq3dsACS, RequestType.POST);
        System.out.println(dataParser.getAcs_url()+"\n"+dataParser.getPareq());
        
    }
}
```

`MkbEndPoints` - enumeration of available url paths in the API.

`RequestType` - enumeration of available types of http requests in the API.

---

![pdf.ico](https://github.com/ZooMMaX/MKBecomAPI4j/blob/master/pdf.ico)
[OPEN MCB API DOCS](https://github.com/ZooMMaX/MKBecomAPI4j/blob/master/MCB-API-DOCS.pdf)

---

![dependency maven](https://img.shields.io/badge/DEPENDENCY-Maven-C71A36?style=plastic&logo=apachemaven)

```xml
<dependency>
  <groupId>ru.zoommax</groupId>
  <artifactId>MKBecomAPI4j</artifactId>
  <version>1.0</version>
</dependency>
```

![dependency gradle](https://img.shields.io/badge/DEPENDENCY-Gradle-02303A?style=plastic&logo=gradle)

```groovy
implementation 'ru.zoommax:MKBecomAPI4j:1.0'
```
