package com.jerryboot.springbootdemo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jerryboot.springbootdemo.model.EditLog;

@Repository
public interface EditLogDao extends JpaRepository<EditLog, Integer>{

	@Query("from EditLog")
	public List<EditLog> listAllLog(EditLog log);
	
	
	@Query(value="insert into edit_log values("
			+ ":edit_time,:employee_action,:employee_name,:tablename,:updated_table_id"
			+ ")",nativeQuery = true)
	@Modifying
	public void saveEditLog(@Param("employee_name")String username,@Param("employee_action")String action,@Param("tablename")String tableName,@Param("updated_table_id")Integer updatedTableId,@Param("edit_time")Date editTime);
	
	
	
}
