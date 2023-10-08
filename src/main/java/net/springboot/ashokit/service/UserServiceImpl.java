package net.springboot.ashokit.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.springboot.ashokit.entity.UserAccount;
import net.springboot.ashokit.repository.UserAccountRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserAccountRepository userAccRepo;

	@Override
	public String saveOrUpdateUserAcc(UserAccount userAcc) {
		Integer userId = userAcc.getUserId();
		
		if (userId==null) {
			userAcc.setActiveSw("Y");
		}
		
		userAccRepo.save(userAcc);
		if (userId == null) {
			return "User record saved";
		} else {
			return "User record updated";
		}
	}

	@Override
	public List<UserAccount> getAllUsers() {
		return userAccRepo.findAll();
	}

	@Override
	public boolean deleteUserAccout(Integer userId) {

		boolean existsById = userAccRepo.existsById(userId);

		if (existsById) {
			userAccRepo.deleteById(userId);
			return true;
		}
		return false;
//		try {
//			userAccRepo.deleteById(userId);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
	}

	@Override
	public boolean updateUserAccountStatus(Integer userId, String status) {
		try {
			userAccRepo.updateUserAccountStatus(userId, status);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
//		Optional<UserAccount> findById = userAccRepo.findById(userId);
//		if (findById.isPresent()) {
//			UserAccount userAcc = findById.get();
//			userAcc.setActiveSw(status);
//			userAccRepo.save(userAcc);
//			return true;
//		}
//		return false;
	}
	

	@Override
	public UserAccount getUserAccount(Integer userId) {
		Optional<UserAccount> findById = userAccRepo.findById(userId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

}
