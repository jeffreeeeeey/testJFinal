package com.demo.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LrcParser {
	private LrcInfo lrcInfo = new LrcInfo();
	
	private long currentTime = 0;
	private String currentContent = null;
	private HashMap<Long, String> maps = new HashMap<Long, String>();
	
	private InputStream readLrcFile(String path) throws FileNotFoundException {
		File f = new File(path);
		InputStream ins = new FileInputStream(f);
		return ins;
	}
	
	public LrcInfo parser(String path) throws Exception {
		InputStream in = readLrcFile(path);
		lrcInfo = parser(in);
		return lrcInfo;
	}
	
	public LrcInfo parser(InputStream inputStream) throws IOException {
		InputStreamReader inReader = new InputStreamReader(inputStream);
		BufferedReader reader = new BufferedReader(inReader);
		String line = null;
		while ((line = reader.readLine()) != null) {
			parserLine(line);
		}
		lrcInfo.setInfos(maps);
		return lrcInfo;
	}

	private void parserLine(String line) {
		//get the title of speech
		if (line.startsWith("[ti:")) {
			String title = line.substring(4, line.length() - 1);
			System.out.println("title: " + title);
		}else if (line.startsWith("[ar:")) {
			String artist = line.substring(4, line.length() - 1);
			System.out.println("reader: " + artist);
		}else {
			String reg = "\\[(\\d{2}:\\d{2}\\.\\d{2})\\]";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(line);
			
			while(matcher.find()) {
				String msg = matcher.group();
				int start = matcher.start();
				int end = matcher.end();
				
				int groupCount = matcher.groupCount();
				for (int i = 0; i < groupCount; i++) {
					String timeString = matcher.group(i);
					System.out.println("i:" + i + " timeString:" + timeString);
					if (i == 0) {
						currentTime = strToLong(timeString);
					}
				}
				String[] contentStrings = pattern.split(line);
				for (int i = 0; i < contentStrings.length; i++) {
					if (i == contentStrings.length - 1) {
						currentContent = contentStrings[i];
					}
				}
				maps.put(currentTime, currentContent);
				System.out.println("put currentTime:" + currentTime + " currentContent:" + currentContent);
			}
		}
	}
	private long strToLong(String timeString) {
		
		String[] s = timeString.split(":");
		int min = Integer.parseInt(s[0]);
		String[] ss = s[1].split("\\.");
		int sec = Integer.parseInt(ss[0]);
		int mill = Integer.parseInt(ss[1]);
		return min * 60 * 1000 + sec * 1000 + mill * 10;
	}
}
