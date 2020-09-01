package service;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import config.SearoutesApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class RouteService {

    public void getRoute() throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SearoutesApi.getSeaRouteUrl() + "/50.47119140624999,42.27730877423709;13.809814453125,37.046408899699564?vesselDraft=3.6"))
                .header("x-api-key", SearoutesApi.getKey())
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        FeatureCollection routeFeatureCollection = FeatureCollection.fromJson(response.body());
        for(Feature routeFeature: routeFeatureCollection.features()) {
            System.out.println("distance: " + routeFeature.getProperty("distance"));
            System.out.println("departure: " +routeFeature.getProperty("departure"));
            System.out.println("arrival: " +routeFeature.getProperty("arrival"));
            System.out.println("secaIntersection: " +routeFeature.getProperty("secaIntersection"));
        }
    }
}
