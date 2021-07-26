package ru.crazzyoffice.bots.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;


public class ArduinoSendRequest {

    @Autowired
    private Environment environment;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private String url;


    public ArduinoSendRequest() {
        this.url = environment.getProperty("arduino.request.pologaya");
   }

    public HttpResponse<String> doGet() throws IOException,InterruptedException {

        HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "text")
                .GET()
                .build();
       return  this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    }
}
