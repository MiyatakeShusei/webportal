package jp.ac.hcs.s3a227.task;

import java.util.Date;

import lombok.Data;

/**
 * １件分のタスク情報
 */

@Data
public class TaskData {
 
 /**
  * タスクID
  * 主キー、SQLにて自動採番
  */
 private int id;
 
 /**
  * ユーザID
  * Userテーブルの主キーと紐づく、ログイン情報から取得
  */
 private String user_id;
 
 /**
  * コメント
  * 必須入力
  */
 private String comment;
 
 /**
  * 期限日
  * 必須入力
  */
 private Date limitday;
}