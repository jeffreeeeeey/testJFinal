package demo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import com.demo.player.LrcInfo;
import com.demo.player.LrcParser;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;

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
		//Handle the upload file
		UploadFile uf = getFile();
		File file = uf.getFile();
		String pathString = file.getPath();
		String fileName = file.getName();
		System.out.println(pathString);
		System.out.println(fileName);
		
		//String uploadDir = File.separator + "upload" + File.separator;
		//String path_tmp = PathKit.getWebRootPath() + uploadDir;
		//System.out.println(path_tmp);
		//UploadFile uploadFile = getFile("lrc", path_tmp);
		
		Speech speech = getModel(Speech.class);
		speech.save();
		
		Integer id = speech.getInt("id");
		Speech.me.findById(id).set("lrc", fileName).update();
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
		Speech speech = Speech.me.findById(getParaToInt());
		String fileNameString = speech.getStr("lrc");
		if (fileNameString == null) {
			fileNameString = "gettysburg-address-jd.lrc";
		}
		String filePath = "WebRoot\\upload\\" + fileNameString;
		LrcParser lp = new LrcParser();
		LrcInfo lrcInfo = new LrcInfo();
		lrcInfo = lp.parser(filePath);
		HashMap<Long, String> map = lrcInfo.getInfos();
		Iterator<Long> iterator = map.keySet().iterator();
		ArrayList<Long> keyArrayList = new ArrayList<Long>();
		
		while (iterator.hasNext()) {
			Long key = iterator.next() ;
			keyArrayList.add(key);
			String value = map.get(key);
			//System.out.println("key:" + key + "--> value:" + value);
		}
		Collections.sort(keyArrayList);
		setAttr("lrc", lrcInfo);
		setAttr("infoMap", map);
		setAttr("keyArrayList", keyArrayList);
		setAttr("speech", Speech.me.findById(getParaToInt()));
		
		render("/speechDetail.html");
		
	}
}
