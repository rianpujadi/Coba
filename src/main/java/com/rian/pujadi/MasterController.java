package com.rian.pujadi;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class MasterController {
	
	
	@Autowired
	private UserService userService;
	

	@RequestMapping("/")
	public String usermain(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);
		return "user";
	}
	
	@RequestMapping("/user")
	public String user(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);
		return "user";
	}
	

	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") int id) {
	    userService.delete(id);
	    return "redirect:/";       
		}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String showEditUser(@PathVariable(name = "id") int id, Model model) {
		User user = userService.get(id);
		model.addAttribute("user", user);
        return "edituser";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String SaveUser(@Valid User tbl_user, BindingResult result, 
			ModelMap model, RedirectAttributes redirectattributes) {
		if (result.hasErrors()) {
			return "newuser";
		}
		userService.save(tbl_user);
		return "user";
	}

	
	@RequestMapping(value = "newuser", method = RequestMethod.GET)
	public String showNewUser(ModelMap model) {
		User tbl_user = new User();
		model.addAttribute("tbl_user", tbl_user);
			return "newuser";
		}

	
	@RequestMapping(value = "update/update", method = RequestMethod.POST)
	public String EditUser(@Valid User tbl_user, BindingResult result, 
			ModelMap model, RedirectAttributes redirectattributes) {
		if (result.hasErrors()) {
			return "edituser";
		}
		userService.save(tbl_user);
		return "user";
	}

	
	
//
//	@ModelAttribute("brands")
//	public List<String> initializeSections() {
//
//		List<String> brands = new ArrayList<String>();
//		brands.add("PANASONIC");
//		brands.add("SAMSUNG");
//		brands.add("SANYO");
//		return brands;
//}
}

