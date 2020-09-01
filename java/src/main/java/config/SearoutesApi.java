package config;

import lombok.Getter;

public class SearoutesApi {
    @Getter
    private final static String rootUrl = "https://api.searoutes.com";
    @Getter
    private final static String seaRouteUrl = rootUrl + "/route/v2/sea";
    @Getter
    private final static String geocodingUrl = rootUrl + "/geocoding/v2/port";
    @Getter
    private final static String key = System.getenv("SEAROUTES_API_KEY");
}
