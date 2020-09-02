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
import java.util.List;


public class RouteService {

    private String getCoordsFromPoints(List<Point> points) {
        StringBuilder sb = new StringBuilder();
        for(Point point : points) {
            sb.append(point.coordinates().get(0).toString()).append(",").append(point.coordinates().get(1)).append(";");
        }
        return sb.substring(0,sb.length()-1);
    }

    public FeatureCollection getRoute(List<Point> points){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SearoutesApi.getSeaRouteUrl() + "/" + getCoordsFromPoints(points)))
                .header("x-api-key", SearoutesApi.getKey())
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if (response != null && response.statusCode() == 200) {
            return FeatureCollection.fromJson(response.body());
        } else {
            throw new NotFoundException("Route not found.");
        }
    }
}
