package gijin.weather_app.api;

public class WeatherApi {
    public static class WeatherEndpoint {
        public static final String BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0";
    }

    public static class WeatherQueryParam {
        public static String SERVICE_KEY = "serviceKey=";
        public static String NUM_OF_ROWS = "numOfRows=";
        public static String PAGE_NO = "pageNo=";

        public static String DATA_TYPE = "dataType=";
        public static String BASE_DATE = "base_date=";
        public static String BASE_TIME = "base_time=";
        public static String NX = "nx=";
        public static String NY = "ny=";
    }
}
