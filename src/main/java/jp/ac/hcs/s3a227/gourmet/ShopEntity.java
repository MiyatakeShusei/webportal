package jp.ac.hcs.s3a227.gourmet;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 郵便番号情報検索結果の店舗情報
 *各項目のデータ使用については、APIの仕様を参照とする
 *１つの郵便番号に複数の住所が結びつくこともあるため、リスト構造となっている
 *-http://zipcloud.ibsnet.co.jp/doc/api
 */
@Data
public class ShopEntity {

	//店名検索
	private String searchWord;
	
	 /**郵便番号情報のリスト*/
	private List<ShopData> shops = new ArrayList<ShopData>();
}
