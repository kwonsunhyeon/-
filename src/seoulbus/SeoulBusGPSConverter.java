package seoulbus;

import java.util.ArrayList;
import java.util.List;

public class SeoulBusGPSConverter {
    private static final String CSV_FILE_PATH = "c:/data/2021_seoul_bus_gps.csv";
    private static final String NEW_CSV_FILE_PATH = "c:/data/seoul_bus_gps_updated.csv";
    private static final String API_KEY = "9538dd0de7a2e9d25ca752c1500bd238";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/yscom?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) {
        List<BusStop> busStops = new ArrayList<>();
//   1. CSV 파일 읽기
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
