package demo;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.*;
import com.jfinal.plugin.activerecord.*;
import com.jfinal.plugin.c3p0.*;

public class DemoConfig extends JFinalConfig {
	public void configConstant(Constants me){
		me.setDevMode(true);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/hello", HelloController.class);
		
	}

	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://localhost/db_name",
				"root", "123456");
		me.add(cp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		me.add(arp);
		arp.addMapping("speech", Speech.class);
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		
	}

	@Override
	public void configHandler(Handlers me) {
		
	}
	
	
}
