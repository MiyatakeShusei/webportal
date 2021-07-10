package jp.ac.hcs.s3a227.gourmet;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GourmetController {

	@Autowired
	private GourmetService gourmetService;
	
	/**
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面ー郵便番号
	 */
	
	@PostMapping("/gourmet")
	public String getGourmet(@RequestParam("shopname") String shopname, Principal principal, Model model) {
		String large_service_area = "SS40";
		ShopEntity shopEntity = gourmetService.getShop(shopname,large_service_area);
		model.addAttribute("shopEntity" , shopEntity);
		System.out.println();
		return "gourmet/gourmet";
	}
	
}
