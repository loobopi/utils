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
		connection.setDoOutput(true);  //打开输出，向服务器输出参数（POST方式、字节）（写参数之前应先将参数的字节长度设置到配置"Content-Length"<字节长度>）  
		connection.setDoInput(true);//打开输入，从服务器读取返回数据

		connection.setRequestMethod("POST"); //设置登录模式为POST（字符串大写）  
		connection.setInstanceFollowRedirects(false);   
		connection.connect();  
		
		OutputStreamWriter out = new OutputStreamWriter(connection    
			      .getOutputStream(), "utf-8");    
		
		//其中的loginName和loginPassword也是阅读html代码得知的，即为表单中对应的参数名称    
		out.write("loginName=admin&loginPassword=*****&validateCode=autoupdate"); // post的关键所在！    
		//remember to clean up    
		out.flush();    
		out.close();  
	
		String cookieVal = connection.getHeaderField("Set-Cookie");  //格式:JSESSIONID=541884418E77E7F07363CCEE91D4FF7E; Path=/  
		connection.disconnect();  
		  
		//登陆成功后，即可访问其他URL了。  
		String s = "http://192.168.5.40:8090/ylysystem/cms/systemsetting/u_cmsInput.sc?" +  
		        "buildType=date&maxResults=10&firstResult=0&buildContent=index" +  
		        "&beginDate=2013-03-13&endDate=2013-03-20";    
		//重新打开一个连接    
		url = new URL(s);    
		HttpURLConnection resumeConnection = (HttpURLConnection) url  
		      .openConnection();    
		if (cookieVal != null) {    
		     //发送cookie信息上去，以表明自己的身份，否则会被认为没有权限    
		  resumeConnection.setRequestProperty("Cookie", cookieVal);//设置登陆配置   
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
