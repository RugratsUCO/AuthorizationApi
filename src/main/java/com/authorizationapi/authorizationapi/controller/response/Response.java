package com.authorizationapi.authorizationapi.controller.response;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
	private List<T> data;
	private List<String> messages;
	
	public Response(List<T> data, List<String> messages) {
		super();
		setData(data);
		setMessages(messages);
	}
	public Response() {
		super();
		setData(new ArrayList<>());
		setMessages(new ArrayList<>());
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = UtilObject.getDefault(data, new ArrayList<>());
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = UtilObject.getDefault(messages, new ArrayList<>());
	}
	
	
	
}