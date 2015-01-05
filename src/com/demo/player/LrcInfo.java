package com.demo.player;

import java.util.HashMap;
import java.util.Hashtable;

public class LrcInfo {
	private String title;
	private String author;
	private String reader;
	public HashMap<Long, String> infos;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getReader() {
		return reader;
	}
	public void setReader(String reader) {
		this.reader = reader;
	}
	public HashMap<Long, String> getInfos() {
		return infos;
	}
	public void setInfos(HashMap<Long, String> infos) {
		this.infos = infos;
	}
}
