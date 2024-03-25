import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class AddressPatcher {
    private static final String API_KEY = "9538dd0de7a2e9d25ca752c1500bd238";
    private static final String URL_TEMPLATE = "https://dapi.kakao.com/v2/local/geo/coord2address.json?x=%s&y=%s&input_coord=WGS84";

    public static void main(String[] args) {
        try {
            String urlStr = String.format(URL_TEMPLATE, 126.987786, 37.569764);
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "KakaoAK " + API_KEY);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                String address = jsonResponse.getJSONArray("documents").getJSONObject(0).getJSONObject("address").getString("address_name");
                System.out.println(address);
            } else {
                System.out.println("Error Code:" + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
. 2021_seoul_bus_gps.csv 파일을 읽어서 seoul_bus_gps DataFrame을 생성합니다. 파일 인코딩으로 'euc-kr'을 사용합니다.
2. 카카오 API를 사용하여 주어진 좌표에 대한 주소 정보를 가져옵니다. 이를 위해 urllib.request를 사용하여 HTTP 요청을 보내고, 응답을 받습니다.
3. 모든 정류장에 대해 X좌표와 Y좌표를 사용하여 주소를 조회하고, 조회된 주소를 seoul_bus_gps DataFrame에 추가합니다.
4. MySQL 데이터베이스에 seoul_bus_gps DataFrame을 테이블로 저장합니다.
5. 마지막으로, 수정된 DataFrame을 seoul_bus_gps.csv 파일로 저장합니다. 파일 구분자로 '|'를 사용하고, 인코딩으로 'utf-8'을 사용합니다.
이 과정을 Java로 변환하기 위해서는 다음 단계를 따라야 합니다:
1. CSV 파일 읽기: Apache Commons CSV 라이브러리나 OpenCSV를 사용하여 CSV 파일을 읽습니다.
2. HTTP 요청 보내기: java.net.HttpURLConnection 또는 Apache HttpClient와 같은 라이브러리를 사용하여 HTTP 요청을 보내고 응답을 받습니다.
3. JSON 파싱: org.json 라이브러리를 사용하여 응답으로 받은 JSON 데이터를 파싱합니다.
4. 데이터베이스 연결 및 데이터 삽입: JDBC를 사용하여 MySQL 데이터베이스에 연결하고, 데이터를 삽입합니다.
5. CSV 파일로 저장: Apache Commons CSV 라이브러리를 사용하여 수정된 데이터를 CSV 파일로 저장합니다.
Java로 변환된 코드의 예시는 다음과 같습니다. 이 예시는 HTTP 요청을 보내고 JSON 응답을 파싱하는 부분에 초점을 맞추고 있습니다. 데이터베이스 연결 및 CSV 파일 읽기/쓰기에 필요한 코드는 상황에 맞게 추가해야 합니다.

 */
