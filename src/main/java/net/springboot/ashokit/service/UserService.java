package net.springboot.ashokit.service;

import java.util.List;

import net.springboot.ashokit.entity.UserAccount;

public interface UserService {

	public String saveOrUpdateUserAcc(UserAccount userAcc);
	
	public List<UserAccount> getAllUsers();
	
	public UserAccount getUserAccount(Integer userId);
	
	public boolean deleteUserAccout(Integer userId);
	
	public boolean updateUserAccountStatus(Integer userId, String status);
	
}	