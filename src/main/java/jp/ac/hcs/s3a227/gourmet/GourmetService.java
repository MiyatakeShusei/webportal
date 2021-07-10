package jp.ac.hcs.s3a227.gourmet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 店舗情報を検索する -http://webservice.recruit.co.jp/hotpepper/gourmet
 */

@Transactional
@Service
public class GourmetService {

	@Autowired
	RestTemplate restTemplate;

	/** 郵便番号検索API リクエストURL */
	private static final String URL = "http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key={API_KEY}&name={shopname}&large_service_area={large_service_area}&format=json";
	private static final String API_KEY = "762f07abbc0b7d7f";

	/***
	 * 
	 * 指定した店舗名と大サービスエリアコードに紐づく店舗情報を取得する
	 * 
	 * @param shopname           店舗名
	 * @param large_service_area 大サービスエリアコード
	 * @return ShopEntity
	 */

	public ShopEntity getShop(String shopname, String large_service_area) {

		// APIへアクセスして、結果を取得
		String json = restTemplate.getForObject(URL, String.class, API_KEY,shopname,large_service_area);

		// エンティティクラスを生成
		ShopEntity shopEntity = new ShopEntity();
		shopEntity.setSearchWord(shopname);

		// jsonクラスへの変換失敗のため、例外処理
		try {
			// 変換クラスを生成し、文字列からjsonクラスへ変換する（例外の可能性あり）
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);

			// shop（配列）をForEachで配列分繰り返す
			for (JsonNode shop : node.get("results").get("shop")) {
				// データクラスの生成（result１件分）
				ShopData shopData = new ShopData();

				shopData.setId(shop.get("id").asText());
				shopData.setName(shop.get("name").asText());
				shopData.setLogo_image(shop.get("logo_image").asText());
				shopData.setName_kana(shop.get("name_kana").asText());
				shopData.setAddress(shop.get("address").asText());
				shopData.setAccess(shop.get("access").asText());
				shopData.setUrl(shop.get("urls").get("pc").asText());
				shopData.setImage(shop.get("photo").get("mobile").asText());
			

				// 可変長配列の末尾に追加
				shopEntity.getShops().add(shopData);
			}
		} catch (IOException e) {
			// 例外発生時は、エラーメッセージの詳細を標準エラー出力
			e.printStackTrace();
		}
		return shopEntity;
	}

}
