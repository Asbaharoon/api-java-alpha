package br.com.alpha.bean;

import java.io.Serializable;

public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3049927401445239743L;
	private int id;
	private String url;
	
	public Client(int id, String url) {
		super();
		this.id = id;
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public String getUrl() {
		return url;
	}
	
}
