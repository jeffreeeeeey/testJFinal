package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.demo.player.LrcInfo;
import com.demo.player.LrcParser;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class SpeechController extends Controller {
	public void index(){
		setAttr("speechPage", Speech.me.paginate(getParaToInt(0, 1), 10));
		render("/speechList.html");
	}
	public void addSpeech(){
		setAttr("action", "add");
		render("/addSpeech.html");
	}
	@Before(SpeechValidator.class)
	public void save() {
		Speech speech = getModel(Speech.class);
		speech.save();
		Integer id = speech.getInt("id");
		redirect("/speech/speechDetail/" + id);
	}
	public void edit() {
		setAttr("speech", Speech.me.findById(getParaToInt()));
		setAttr("action", "edit");
		render("/addSpeech.html");
	}
	public void delete() {
		Speech.me.deleteById(getParaToInt());
		redirect("/speech");
	}
	
	//@Before(SpeechValidator.class)
	public void update(){
		getModel(Speech.class).update();
		Integer id = getModel(Speech.class).getInt("id");
		redirect("/speech/speechDetail/" + id);
	}
	public void speechDetail() throws Exception{
		
		LrcParser lp = new LrcParser();
		LrcInfo lrcInfo = new LrcInfo();
		lrcInfo = lp.parser("e:\\javaEE\\WorkSpace\\testJFinal\\WebRoot\\video\\gettysburg-address-jd.lrc");
		HashMap<Long, String> map = lrcInfo.getInfos();
		Iterator<Long> iterator = map.keySet().iterator();
		ArrayList<Long> keyArrayList = new ArrayList<Long>();
		
		while (iterator.hasNext()) {
			Long key = iterator.next() ;
			keyArrayList.add(key);
			String value = map.get(key);
			System.out.println("key:" + key + "--> value:" + value);
		}
		
		setAttr("lrc", lrcInfo);
		setAttr("infoMap", map);
		setAttr("speech", Speech.me.findById(getParaToInt()));
		
		render("/speechDetail.html");
		
	}
}
