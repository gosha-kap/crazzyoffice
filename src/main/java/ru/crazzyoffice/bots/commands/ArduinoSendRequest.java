package ru.crazzyoffice.bots.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ArduinoSendRequest implements SendRequest {

    private static final Logger logger =
            LoggerFactory.getLogger(ArduinoSendRequest.class);


    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private String url;

    public ArduinoSendRequest(String url) {
        this.url = url;
    }

    private HttpResponse<String> doGet() throws IOException, InterruptedException {

        HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "text")
                .GET()
                .build();
        return this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Override
    public ResponseMessage request() {
        try {
            doGet();
        } catch (IOException e) {
            logger.error("Error   :  {}", e.getMessage());
            return ResponseMessage.ERROR_TIMEOUT;

        } catch (InterruptedException e) {
            logger.error("Error   :  {}", e.getMessage());
            return ResponseMessage.ERROR_UNEXCPTED;
        }
        return ResponseMessage.ACCESS_GRANTED;
    }
}
