package jp.ac.hcs.s3a227.user;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * フロントーバックでユーザ情報をやり取りする
 *各項目のデータ仕様はUserEntityを参照する
 */
@Data
public class UserForm {
	/**ユーザID（メールアドレス）*/
	@NotBlank(message = "{require_check}")
	@Email(message = "{email_check}")
	private String user_id;
	
	/**パスワード*/
	@NotBlank(message = "{require_check}")
	@Length(min = 4, max = 100, message= "{require_check}")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message= "{require_check}")
	private String password;
	
	/**ユーザ名*/
	@NotBlank(message = "{require_check}")
	private String user_name;
	
	/**ダークモードフラグ*/
	@AssertFalse(message = "{false_check}")
	private boolean darkmode;
	
	/**権限*/
	@NotBlank(message = "{require_check}")
	private String role;
}
