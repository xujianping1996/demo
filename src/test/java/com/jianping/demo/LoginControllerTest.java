package com.jianping.demo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.TreeMap;

import org.apache.jasper.tagplugins.jstl.core.Url;

public class LoginControllerTest {

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://localhost:8088/verification");
		HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
		TreeMap<String, String> map = new TreeMap<>();
		LinkedList<String> list;
	}

}
