package jp.ac.hcs.s3a227.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public UserEntity getUser() {

		// エンティティクラスを生成
		UserEntity userEntity = new UserEntity();
		try {
			userEntity = userRepository.selectAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
			userEntity = null;
		}
		return userEntity;
	}

	public UserData getUser(String user_id) {

		UserData userData = new UserData();
		try {
			userData = userRepository.selectOne(user_id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			userData = null;
		}
		return userData;
	}

	/**
	 * ユーザ情報を１件追加する
	 * 
	 * @param userData 追加するユーザ情報（パスワードは平文）
	 * @return 処理結果（成功：true 失敗: false）
	 */
	public boolean insertOne(UserData userData) {
		int rowNumber;
		try {
			rowNumber = userRepository.insertOne(userData);
		} catch (DataAccessException e) {
			e.printStackTrace();
			rowNumber = 0;
		}
		return rowNumber > 0;
	}
	

	/**
	 * 入力項目をUserDataへ変換する （このメソッドは入力チェクを実施したうえで呼び出すこと）
	 * 
	 * @param form 入力データ
	 * @return UserData
	 */
	UserData refillToData(UserForm form) {
		UserData data = new UserData();
		data.setUser_id(form.getUser_id());
		data.setPassword(form.getPassword());
		data.setUser_name(form.getUser_name());
		data.setDarkmode(form.isDarkmode());
		data.setRole(form.getRole());
		// 初期値は有効とする
		data.setEnabled(true);
		return data;
	}

	public void deleteOne(String user_id) {
		int row = userRepository.deleteOne(user_id);
		System.out.println(row);
	}

}
