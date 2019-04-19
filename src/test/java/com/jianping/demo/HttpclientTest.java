package com.jianping.demo;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
public class HttpclientTest {
	public static void main(String[] args) {
		System.setProperty("http.proxyHost", "localhost"); 
		System.setProperty("http.proxyPort", "8888"); 
		System.setProperty("https.proxyHost", "localhost");
		System.setProperty("https.proxyPort", "8888");
		String url = "http://10.20.202.169:9090/sms/sendbysms";
		 HttpPost post = null;
		    try {
		        HttpClient httpClient = new DefaultHttpClient();

		        // 设置超时时间
		        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
		        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
		            
		        post = new HttpPost(url);
		        // 构造消息头
		        post.setHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
//		        post.setHeader("Connection", "Close");C
//		        String sessionId = getSessionId();
//		        post.setHeader("SessionId", sessionId);
//		        post.setHeader("appid", appid);
		        post.setHeader("username", "cls");
		        post.setHeader("sign", "U908M3u08ntxlb0QkIIlBRDp6GT/W4WlPRkXzge38cinyQBcwhxlCOxCGMQyVKG1NOtoJGvFs7IS E2kM4VWtER/sSsavxTS704RfEqhKKgCf1dsqpyXXqfgeB0uaE4cOuwhhh1UAma0BmO81Qpoya7gU PGgD/gFI0WYM09V6SXA=");
		        // 构建消息实体
		        Map<String ,String > param = new HashMap<String ,String >();
				Map<String ,String > data = new HashMap<String,String >();
				List<Map<String ,String >> datap = new ArrayList<>();
				param.put("channo", "01");//短信渠道—固定值01
				param.put("type", "01");//通知类:01 业务受理:02 验证类:03
				param.put("template", "999999");//固定值999999
				data.put("target", "17643173220");
				data.put("plantime", "");
				data.put("content", "测试短信");
				datap.add(data);
				param.put("data", JSONArray.toJSONString(datap));
//				conn.connect();
				String jsonObject = JSONObject.toJSONString(param);
		        StringEntity entity = new StringEntity(jsonObject, Charset.forName("UTF-8"));
		        entity.setContentEncoding("UTF-8");
		        // 发送Json格式的数据请求
		        entity.setContentType("application/json");
		        post.setEntity(entity);
		            
		        HttpResponse response = httpClient.execute(post);
		            System.out.println(response.getEntity().toString());
		        // 检验返回码
		        int statusCode = response.getStatusLine().getStatusCode();
		        if(statusCode != HttpStatus.SC_OK){
//		            LogUtil.info("请求出错: "+statusCode);
//		            isSuccess = false;
		        }else{
//		            int retCode = 0;
//		            String sessendId = "";
//		            // 返回码中包含retCode及会话Id
//		            for(Header header : response.getAllHeaders()){
//		                if(header.getName().equals("retcode")){
//		                    retCode = Integer.parseInt(header.getValue());
//		                }
//		                if(header.getName().equals("SessionId")){
//		                    sessendId = header.getValue();
//		                }
//		            }
//		            
//		            if(ErrorCodeHelper.IAS_SUCCESS != retCode ){
//		                // 日志打印
//		                LogUtil.info("error return code,  sessionId: "sessendId"\t"+"retCode: "+retCode);
//		                isSuccess = false;
//		            }else{
//		                isSuccess = true;
//		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
//		        isSuccess = false;
		    }finally{
		        if(post != null){
		            try {
		                post.releaseConnection();
		                Thread.sleep(500);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		    }
	}
	public static String getSessionId(){
	    UUID uuid = UUID.randomUUID();
	    String str = uuid.toString();
	    return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
	}
}
