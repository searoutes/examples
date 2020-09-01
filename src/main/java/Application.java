import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import service.GeocodingService;
import service.RouteService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) throws Exception {

        GeocodingService geocodingService = new GeocodingService();
        RouteService routeService = new RouteService();

        List<String> locodes = Arrays.asList("DEHAM", "USNYC", "USMIA");
        List<Point> points = locodes.stream().map(geocodingService::getPortCoordinates).collect(Collectors.toList());

        FeatureCollection routeFeatureCollection = routeService.getRoute(points);
        for(Feature routeFeature: routeFeatureCollection.features()) {
            System.out.println(routeFeature.properties());
            //System.out.println(routeFeature.geometry());
        }
    }
}