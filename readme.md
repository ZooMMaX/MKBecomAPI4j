# MKBecomAPI4j

## Wrapper for API of Moscow Credit Bank (MCB)

---

### Wrapper initialization
To initialize the wrapper, you need to set the parameters:

* KEYSTOREPATH -
  path to the certificate
* KEYSTOREPASS - сertificate key
* KEYPASS - alias key
* TEST - `true` if the certificate is a test one, `false` otherwise

Example

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

Example

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