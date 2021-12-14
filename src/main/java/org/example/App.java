package org.example;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class App 
{
    //chat id for user who needs to recieve message
    private static final String CHAT_ID = "";

    //token for accessing and sending message through bot
    private static final String TOKEN = "";

    public static void main(String[] args) throws IOException, InterruptedException {
        //String message = "Hello World from Java 11";

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .version(HttpClient.Version.HTTP_2)
                .build();

        while(true) {

            Trailer trailer = new Trailer();
            String str = trailer.recent();
            trailer.closeDriver();
            UriBuilder builder = UriBuilder
                    .fromUri("https://api.telegram.org")
                    .path("/{token}/sendMessage")
                    .queryParam("chat_id", CHAT_ID)
                    .queryParam("text", str);

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(builder.build("bot" + TOKEN))
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            //System.out.println(response.statusCode());
            //System.out.println(response.body());
            Thread.sleep(600000);
        }
    }
}
