package com.example.demo.config;

import io.split.client.SplitClient;
import io.split.client.SplitFactory;
import io.split.client.SplitFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileInputStream;
import java.security.KeyStore;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.SSLContext;

@Configuration
public class SplitConfig {

    @Bean
    public SplitClient splitClient() throws Exception {
        // Load the SSL certificate
        String certificatePath = "C:/Program Files/Java/jdk1.8.0_144/jre/lib/security/cacerts";
        String certificatePassword = "changeit";

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        try (FileInputStream fis = new FileInputStream(certificatePath)) {
            keyStore.load(fis, certificatePassword.toCharArray());
        }

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        // Build the Split client
        SplitFactory splitFactory = SplitFactoryBuilder.build("kq9hdp1bloqn10p5idvjucdoei2tg45jj5md");
        return splitFactory.client();
    }
}