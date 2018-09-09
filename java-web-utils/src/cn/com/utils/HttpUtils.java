package cn.com.utils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;
public class HttpUtils {

	public static String post(String urlPath,Map param,Set<Map> attrib)throws Exception{
		URL url = new URL(urlPath);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);  //�����������������������POST��ʽ���ֽڣ���д����֮ǰӦ�Ƚ��������ֽڳ������õ�����"Content-Length"<�ֽڳ���>��  
		connection.setDoInput(true);//�����룬�ӷ�������ȡ��������

		connection.setRequestMethod("POST"); //���õ�¼ģʽΪPOST���ַ�����д��  
		connection.setInstanceFollowRedirects(false);   
		connection.connect();  
		
		OutputStreamWriter out = new OutputStreamWriter(connection    
			      .getOutputStream(), "utf-8");    
		
		//���е�loginName��loginPasswordҲ���Ķ�html�����֪�ģ���Ϊ���ж�Ӧ�Ĳ�������    
		out.write("loginName=admin&loginPassword=*****&validateCode=autoupdate"); // post�Ĺؼ����ڣ�    
		//remember to clean up    
		out.flush();    
		out.close();  
	
		String cookieVal = connection.getHeaderField("Set-Cookie");  //��ʽ:JSESSIONID=541884418E77E7F07363CCEE91D4FF7E; Path=/  
		connection.disconnect();  
		  
		//��½�ɹ��󣬼��ɷ�������URL�ˡ�  
		String s = "http://192.168.5.40:8090/ylysystem/cms/systemsetting/u_cmsInput.sc?" +  
		        "buildType=date&maxResults=10&firstResult=0&buildContent=index" +  
		        "&beginDate=2013-03-13&endDate=2013-03-20";    
		//���´�һ������    
		url = new URL(s);    
		HttpURLConnection resumeConnection = (HttpURLConnection) url  
		      .openConnection();    
		if (cookieVal != null) {    
		     //����cookie��Ϣ��ȥ���Ա����Լ�����ݣ�����ᱻ��Ϊû��Ȩ��    
		  resumeConnection.setRequestProperty("Cookie", cookieVal);//���õ�½����   
		}  
		resumeConnection.connect();    
		InputStream urlStream = resumeConnection.getInputStream();    
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream));    
		String ss = null;    
		String total = "";    
		while ((ss = bufferedReader.readLine()) != null) {    
		    System.err.println(ss);  
		    total += ss;    
		}    
		//F.write(total, new FileOutputStream("d:/index.html"));    
		bufferedReader.close();   
		return "";
	}
}
