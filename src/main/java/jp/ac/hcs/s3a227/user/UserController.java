package jp.ac.hcs.s3a227.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/userList")
	public String getUserList(Model model) {
		UserEntity userEntity = userService.getUser();
		model.addAttribute("userEntity", userEntity);
		return "user/userList";
	}

	/**
	 * ユーザ登録画面（管理者用）を表示する
	 * 
	 * @param from  登録時の入力チェック用UserForm
	 * @param model
	 * @return ユーザ登録画面（管理者用）
	 */
	@GetMapping("user/insert")
	public String getUserInsert(UserForm form, Model model) {
		return "user/insert";
	}

	/**
	 * １件分のユーザ情報をデータベースに登録する
	 * 
	 * @param from          登録するユーザ情報（パスワードは平文）
	 * @param bindingresult データバインド実施結果
	 * @param principal     ログイン情報
	 * @param model
	 * @return ユーザ一覧画面
	 */
	@PostMapping("user/insert")
	public String getUserInsert(@ModelAttribute @Validated UserForm form, BindingResult bindingResult,
			Principal principal, Model model) {

		// 入力チェックに引っかかった場合、前の画面に戻る
		if (bindingResult.hasErrors()) {
			return getUserInsert(form, model);
		}
		// 追加処理
		userService.insertOne(userService.refillToData(form));
		return getUserList(model);
	}

	/**
	 * ユーザ詳細情報画面を表示する
	 * @param user_id 検索するユーザID
	 * @param prinicipal ログイン情報
	 * @param model
	 * @return ユーザ詳細情報画面
	 */
	@GetMapping("/user/detail/{id}")
	public String getUserDetail(@PathVariable("id") String user_id,Principal principal, Model model){
		UserData data = userService.getUser(user_id);
		model.addAttribute("userData", data);
		return "user/detail";
	}
	
	@PostMapping("/user/delete")
	public String deleteUser(@RequestParam("user_id") String user_id,Principal principal, Model model){
		System.out.println(user_id);
		//削除処理
		userService.deleteOne(user_id);
		return getUserList(model);
	}
	
	
}
	
