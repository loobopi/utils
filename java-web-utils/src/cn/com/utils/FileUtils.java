package cn.com.utils;
import java.io.File;
import java.io.FileWriter;
public class FileUtils {
	
	public static void writer(String filepath,byte[] bytes,boolean append) throws Exception{
		
	}
	/**
	 * @Description ÎÄ¼þÐ´Èë
	 * @param filepath
	 * @param content
	 * @param append
	 * @throws Exception
	 */
	public static void writer(String filepath,String content,boolean append) throws Exception{
		File file = new File(filepath);
		FileWriter writer = new FileWriter(file,append);
		writer.write(content);
		writer.close();
	}
}
