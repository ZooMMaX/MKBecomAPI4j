package ru.zoommax.web;

import lombok.Builder;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import ru.zoommax.Init;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Builder
public class Client {
    private static final String testUrl = "https://mpi.mkb.ru:9444";
    private static final String url = "https://mpi.mkb.ru:8444";
    private String urlPath;

    private StringEntity stringEntity;
    private RequestType requestType;

    private final String KEYSTOREPATH = Init.getKEYSTOREPATH();
    private final String KEYSTOREPASS = Init.getKEYSTOREPASS();
    private final String KEYPASS = Init.getKEYPASS();
    private final boolean TEST = Init.isTEST();


    KeyStore readStore() {
        try (InputStream keyStoreStream = Files.newInputStream(Path.of(KEYSTOREPATH))) {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(keyStoreStream, KEYSTOREPASS.toCharArray());
            return keyStore;
        } catch (IOException | CertificateException | KeyStoreException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public String request() {
        String urls = "";
        if (TEST){
            urls = testUrl+urlPath;
        }else {
            urls = url+urlPath;
        }

        SSLContext sslContext = SSLContexts.custom()
                .loadKeyMaterial(readStore(), KEYPASS.toCharArray())
                .build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext,
                new String[] { "TLSv1.2", "TLSv1.3" },
                new String[] {"TLS_RSA_WITH_AES_128_CBC_SHA",
                        "TLS_RSA_WITH_AES_256_CBC_SHA",
                        "TLS_RSA_WITH_AES_128_CBC_SHA256",
                        "TLS_RSA_WITH_AES_256_CBC_SHA256",
                        "TLS_RSA_WITH_AES_128_GCM_SHA256",
                        "TLS_RSA_WITH_AES_256_GCM_SHA384",
                        "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
                        "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
                        "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256",
                        "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
                        "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384"},
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        HttpResponse response = null;
        if (requestType == RequestType.POST){
            HttpPost httpPost = new HttpPost(urls);
            StringEntity entity = stringEntity;
            httpPost.setEntity(entity);
            httpPost.addHeader("Content-Type", "application/json");
            response = httpClient.execute(httpPost);
        } else if (requestType == RequestType.GET) {
            HttpGet httpGet = new HttpGet(urls);
            httpGet.addHeader("Content-Type", "application/json");
            response = httpClient.execute(httpGet);
        }else if (requestType == RequestType.DELETE){
            HttpDelete httpDelete = new HttpDelete(urls);
            response = httpClient.execute(httpDelete);
        }
        assert response != null;
        HttpEntity entity = response.getEntity();
        return new String(entity.getContent().readAllBytes());
    }
}
