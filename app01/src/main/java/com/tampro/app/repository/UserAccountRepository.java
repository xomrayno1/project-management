package com.tampro.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tampro.app.entity.UserAccount;

@Repository
public interface UserAccountRepository  extends PagingAndSortingRepository<UserAccount, Long>{
	@Query(value = "SELECT * FROM USER_ACCOUNT UA WHERE  UA.USERNAME LIKE  ?1   ",nativeQuery = true)
	public	Page<UserAccount> findByUsername(Pageable pageable , String username);
	 
	@Query(value = "SELECT ua.id,ua.create_date,ua.update_date,ua.password,ua.email,ua.username,ua.first_name,ua.last_name FROM  account_role ar  " + 
			" inner join user_account ua on ua.id  = ar.account_id  " + 
			" where ar.role_id = ?1  ",nativeQuery = true)
	public List<UserAccount> findByRole(long roleId);
	// find by project and account admin  
	//select ua.id,ua.create_date,ua.update_date,ua.password,ua.email,ua.username,ua.first_name,ua.last_name 
	//from project_manager pm 
	//inner join project p on p.id = pm.project_id
	//inner join user_account ua on ua.id = pm.account_id
	//inner join account_role ar on ar.account_id = ua.id
	//where pm.project_id = 2 and  ar.role_id = 2
	@Query(value = " select ua.id,ua.create_date,ua.update_date,ua.password,ua.email,ua.username,ua.first_name,ua.last_name "
			+ "	from project_manager pm "
			+ " inner join project p on p.id = pm.project_id  "
			+ " inner join user_account ua on ua.id = pm.account_id  "
			+ " where pm.project_id = ?1   ",nativeQuery = true)
	public List<UserAccount> findByProject(long projectId);
	
	public	Optional<UserAccount> findByUsername(String username); 
	
	public	Optional<UserAccount> findByEmail(String email); 
	
	public Page<UserAccount> findAll();
}
