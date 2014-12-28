package demo;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Speech extends Model<Speech> {
	public static final Speech me = new Speech();
	
	public Page<Speech> paginate(int pageNumber, int pageSize){
		return paginate(pageNumber, pageSize, "select *", "from speech order by id asc");
	}
}
