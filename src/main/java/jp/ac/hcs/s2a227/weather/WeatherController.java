package jp.ac.hcs.s2a227.weather;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WeatherController {

	@Autowired
	private WeatherService weatherService;
	
	/**
	 * 郵便番号から住所を検索し、結果がmンを表示する。
	 * @param zipcode 検索する郵便番号（ハイフン無し）
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面ー郵便番号
	 */
	
	@RequestMapping("/weather")
	public String postWeather(Principal principal, Model model) {
		String cityCode = "016010";
		WeatherEntity weatherEntity = weatherService.getWeather(cityCode);
		model.addAttribute("weatherEntity" , weatherEntity);
		return "weather/weather";
	}
}
