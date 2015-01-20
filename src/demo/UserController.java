package demo;

import com.jfinal.core.Controller;

public class UserController extends Controller {
	public void index() {
		render("/login.html");
	}
}
