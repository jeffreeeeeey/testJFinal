package demo;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class User extends Model<User> {
	public static final User us = new User();
	public Page<User> paginate(int pageNumber, int pageSize){
		return paginate(pageNumber, pageSize, "select *", "from user order by id asc");
	}
}
