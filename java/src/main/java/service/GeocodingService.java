package service;

import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import config.SearoutesApi;
import org.springframework.security.acls.model.NotFoundException;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeocodingService {

    public Point getPortCoordinates(String unlocode) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SearoutesApi.getGeocodingUrl() + "/" + unlocode))
                .header("x-api-key", SearoutesApi.getKey())
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        FeatureCollection portFeatureCollection;
        if (response != null && response.statusCode() == 200) {
            portFeatureCollection = FeatureCollection.fromJson(response.body());
            return (Point) portFeatureCollection.features().get(0).geometry();
        } else {
            throw new NotFoundException("Port " + unlocode + " not found.");
        }
    }

}
