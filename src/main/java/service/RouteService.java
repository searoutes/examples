package service;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import config.SearoutesApi;
import org.springframework.beans.factory.annotation.Autowired;

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
            sb.append(point.coordinates().get(0).toString() + "," + point.coordinates().get(1) + ";");
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return FeatureCollection.fromJson(response.body());
    }
}
