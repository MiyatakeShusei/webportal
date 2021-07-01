package jp.ac.hcs.s3a227.task;

import java.security.Principal;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskservice;
	
	/**
	 * タスク管理機能の業務ロジック
	 * @param commet 入力されたタスク内容
	 * @param limitdate 入力された期日
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面
	 * @throws ParseException 
	 */
	
	@RequestMapping("/task")
	public String postTask(Principal principal, Model model) {
		TaskEntity taskEntity = taskservice.getTask(principal.getName());
		model.addAttribute("taskEntity" , taskEntity);
		return "task/task";
	}
}
