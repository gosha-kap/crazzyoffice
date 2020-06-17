package ru.crazzyoffice.bots.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;

public class ArduinoSendRequest {
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private String url;

    public ArduinoSendRequest(String url) {
        this.url = url;
    }

    public HttpResponse<String> doGet() throws IOException,InterruptedException {
        HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(this.url))
                .header("Content-Type", "text")
                .GET()
                .build();
       return  this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    }
}
