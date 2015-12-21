package com.toyo.instantforum.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toyo.instantforum.data.model.User;
import com.toyo.instantforum.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Long save(@RequestBody User user) {
	User newUser = userService.save(user);
	return newUser.getId();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public @ResponseBody User find(@PathVariable("username") String username) {
	User user = userService.findByUsername(username);
	clearStubs(user);
	return user;
    }

    // TODO: melhorar, necessário pois vem com stub e jackson dá erro
    private void clearStubs(User user) {
	if (user != null)
	    user.setCategories(null);
    }

}
