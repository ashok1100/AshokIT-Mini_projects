package net.springboot.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.springboot.ashokit.entity.UserAccount;
import net.springboot.ashokit.service.UserService;

@Controller
public class UserControllerModel {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("users", new UserAccount());
		return "index";
	}

	@PostMapping("/save-user")
	public String handleSubmitButton(@ModelAttribute("users") UserAccount user, Model model) {
		//user.setActiveSw("Y");
		System.out.println(user);
		String msg = userService.saveOrUpdateUserAcc(user);
		model.addAttribute("msg", msg);
		model.addAttribute("users", new UserAccount());
		return "index"; 
	}

	@GetMapping("/users")
	public String findAllUsers(Model model) {
		List<UserAccount> usersList = userService.getAllUsers();
		model.addAttribute("users", usersList);
		return "view-users";
	}

	@PostMapping("/update")
	public String updateUser(@ModelAttribute("users") UserAccount user, Model model) {
		String msg = userService.saveOrUpdateUserAcc(user);
		model.addAttribute("users", msg);
		return "edit-user";
	}

	@GetMapping("/edit")
	public String editUser(@RequestParam("id") Integer id, Model model) {
		UserAccount userAccount = userService.getUserAccount(id);
		model.addAttribute("users", userAccount);
		return "index";
	}

	@GetMapping("/delete")
	public String deleteUser(@RequestParam("id") Integer id, Model model) {
		userService.deleteUserAccout(id);
		model.addAttribute("msg", "User record deleted");
		return "forward:/users";
	}

	@GetMapping("/update-status")
	public String updateStatus(@RequestParam("id") Integer id, @RequestParam("status") String status, Model model) {

		userService.updateUserAccountStatus(id, status);
		if ("Y".equals(status)) {
			model.addAttribute("msg", "User Account activated");
		} else {
			model.addAttribute("msg", "User Account De-activated");
		}

		return "forward:/users";
	}

}
