package demo;

import java.sql.SQLClientInfoException;
import java.util.List;

import com.jfinal.core.Controller;

public class UserController extends Controller {
	public void index() {
		render("/login.html");
	}
	public void login(){
		String name = getPara("name");
		String password = getPara("password");
		String sql = "SELECT * FROM user WHERE name = '" + name + "' AND password = '" + password + "'";
		int n = 0;
		try {
			List<User> users = User.us.find(sql);
			n = users.size();
			if (users.size() > 0) {
				getSession().setAttribute("username", name);
				redirect("/speech");
			}else {
				redirect("/user/index");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(sql);
			System.out.println("n:" + n);
			redirect("/user/index");
		}
		
		
	}
}
