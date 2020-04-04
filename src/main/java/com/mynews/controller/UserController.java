<<<<<<< HEAD
package com.mynews.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@Api("user")
public class UserController {


	@GetMapping("/login")
	public String loginPage() {
		return "user/login";
	}

//	@ApiOperation("main")
	@GetMapping("/main")
	public String main() {
		return "user/main";
	}
}
=======
package com.mynews.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	
	@GetMapping("/login")
	public String loginPage() {
		return "user/login";
	}
	
	@GetMapping("/main")
	public String main() {
		return "user/main";
	}
}
>>>>>>> ffd47112538e7d0d0faeded5eafd822ac47e4e98
