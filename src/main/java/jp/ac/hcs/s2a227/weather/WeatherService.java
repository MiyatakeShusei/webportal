package jp.ac.hcs.s2a227.weather;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@Service
public class WeatherService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/**天気予報検索API リクエストURL*/
	private static final String URL = "https://weather.tsukumijima.net/api/forecast?city={cityCode}";
	
	/**
	 * 都市情報に紐づく天気予報情報を取得する
	 * @param cityCode 都市コード
	 * @return Entity
	 */
	
public WeatherEntity getWeather(String cityCode) {
		
		//APIへアクセスして、結果を取得
		String json = restTemplate.getForObject(URL, String.class, cityCode);
		
		//エンティティクラスを生成
		WeatherEntity weatherEntity = new WeatherEntity();
		
		//jsonクラスへの変換失敗のため、例外処理 
		try {
			//変換クラスを生成し、文字列からjsonクラスへ変換する（例外の可能性あり）
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			
			//forecastをForEachで配列分繰り返す（配列分取得する）
			for(JsonNode forcast : node.get("forcasts")) {
				//データクラスの生成（result１件分）
				WeatherData weatherData = new WeatherData();
				
				weatherData.setDateLabel(forcast.get("cityName").asText());
				weatherData.setTelop(forcast.get("cityName").asText());
				
				//可変長配列の末尾に追加
				weatherEntity.getForecasts().add(weatherData);
			}
		}catch(IOException e) {
			//例外発生時は、エラーメッセージの詳細を標準エラー出力
			e.printStackTrace();
		}
		return weatherEntity;
	}
}
