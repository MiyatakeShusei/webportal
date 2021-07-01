package jp.ac.hcs.s3a227.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 *
 */

@Transactional
@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;
	
	public TaskEntity getTask(String userName) {
		 TaskEntity taskEntity;
		 try {
			 taskEntity = taskRepository.selectAll(userName);
		 }catch(DataAccessException e){
			 e.printStackTrace();
			 taskEntity = null;
		 }
		
		return taskEntity;
	}

}
