package com.jerryboot.springbootdemo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jerryboot.springbootdemo.model.Administrator;

@Repository
public interface AdministratorDao extends JpaRepository<Administrator, Integer>{

	
	@Query("from Administrator")
	public List<Administrator> listAllAdmin();
	
	@Query("from Administrator where administrator_id=:id")
	public List<Administrator> findAdministratorById(@Param("id") Integer id);
	
	@Query("from Administrator where administrator_account=:account and administrator_password=:password")
	public List<Administrator> findByAccountAndPassword(@Param("account")String account,@Param("password")String password);
	
	@Query("from Administrator where administrator_account=:account")
	public Optional<Administrator> findByAccount(@Param("account")String account);
	
//	@Query("delete from Administrator where administrator_id=:id")
//	@Transactional
//	@Modifying
//	public void deleteAdminById(Integer id);
	
	
	
}
