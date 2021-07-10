package jp.ac.hcs.s3a227.task;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * タスク情報を操作する
 */
@Transactional
@Service
public class TaskService {

 @Autowired
 TaskRepository taskRepository;

 /**
  * 指定したユーザIDのタスク情報を全件取得する.
  * 
  * @param userId ユーザID
  * @return TaskEntity
  */
 public TaskEntity getTask(String userId) {

  // エンティティクラスを生成
  TaskEntity taskEntity = new TaskEntity();
  try {
   taskEntity = taskRepository.selectAll(userId);
  } catch (DataAccessException e) {
   e.printStackTrace();
   taskEntity = null;
  }
  return taskEntity;
 }

 public boolean insertTask(String userId, String comment, String date) {

  // エンティティクラスを生成
  TaskData taskData = new TaskData();
  int count;
  
  try {
   SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
         Date limitday = sdFormat.parse(date);
   taskData.setUser_id(userId);
   taskData.setComment(comment);
   taskData.setLimitday(limitday);
   count = taskRepository.insertOne(taskData);
  } catch (DataAccessException | ParseException e) {
   e.printStackTrace();
   count = 0;
  }
  return count > 0;
 }
 
 public boolean deleteTask(int taskId) {

  // エンティティクラスを生成
  int count;
  try {
   count = taskRepository.deleteOne(taskId);
  } catch (DataAccessException e) {
   e.printStackTrace();
   count = 0;
  }
  return count > 0;
 }
 
 /**
  * タスク情報をCSVファイルとしてサーバに保存する.
  * @param user_id ユーザID
  * @throws DataAccessException
  */
 public void taskListCsvOut(String user_id) throws DataAccessException {
  taskRepository.tasklistCsvOut(user_id);
 }

 /**
  * サーバーに保存されているファイルを取得して、byte配列に変換する.
  * @param fileName ファイル名
  * @return ファイルのbyte配列
  * @throws IOException ファイル取得エラー
  */
 public byte[] getFile(String fileName) throws IOException {
  FileSystem fs = FileSystems.getDefault();
  Path p = fs.getPath(fileName);
  byte[] bytes = Files.readAllBytes(p);
  return bytes;
 }
}