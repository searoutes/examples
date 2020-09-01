import service.RouteService;

public class Application {
    public static void main(String[] args) throws Exception {
        RouteService routeService = new RouteService();
        routeService.getRoute();
    }
}