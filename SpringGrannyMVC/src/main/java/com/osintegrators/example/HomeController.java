package com.osintegrators.example;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	AddressService service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.debug("in home method");
		
		addModelElements(model);
		return "home";
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public String get(Model model, @RequestParam String oldName, @RequestParam String selectedIndex) {
		logger.debug("in get method");

		Address address = service.getAddressByName(oldName);
		
		model.addAttribute("selectedIndex",selectedIndex);
		model.addAttribute("address", address);
		addModelElements(model);
		return "home";
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, @RequestParam String oldName, @RequestParam String name, @RequestParam String address, @RequestParam String phone, @RequestParam String email) {
		logger.debug("in save method");
		
		Address old = service.getAddressByName(oldName);
		
		if (old != null) {
			old.setName(name);
			old.setAddress(address);
			old.setPhone(phone);
			old.setEmail(email);
		}
		
		logger.debug(old == null ? "old -= null" : "old != null");
		
		Address add = old != null ? old : new Address(name, address, phone, email);
		service.createAddress(add);
		
		addModelElements(model);
		model.addAttribute("status","created");
		
		return "home";
	}
	

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Model model, @RequestParam String name) {
		logger.debug("in delete method");
		
		Address add = service.getAddressByName(name);
		service.deleteAddress(add);

		addModelElements(model);
		
		model.addAttribute("status","deleted");
		
		return "home";
	}	
	
	private void addModelElements(Model model) {
		List<Address> addresses = service.getAllAddresses();
		model.addAttribute("addresses", addresses);
	}
	
	
}
