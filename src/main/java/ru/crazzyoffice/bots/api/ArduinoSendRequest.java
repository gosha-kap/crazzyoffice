package ru.crazzyoffice.bots.api;

import javax.swing.plaf.synth.SynthTreeUI;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;

public class ArduinoSendRequest {
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private String url_pologaya;
    private String url_garage;

    public ArduinoSendRequest(String url_pologaya, String url_garage)
    {
        this.url_pologaya = url_pologaya;
        this.url_garage = url_garage;
    }

    public HttpResponse<String> doGet(POSITION position) throws IOException,InterruptedException {
        String url  = null;
        if(position.equals(POSITION.Pologaya)) url = this.url_pologaya;
        else if(position.equals(POSITION.Garage)) url = this.url_garage;

        HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "text")
                .GET()
                .build();
       return  this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    }
}
