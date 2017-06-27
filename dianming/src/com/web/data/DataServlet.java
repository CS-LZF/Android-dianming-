package com.web.data;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import com.web.util.Tool;




@SuppressWarnings("serial")
public class DataServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse response )
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String method = req.getParameter("method");
 
		String dir_path =  req.getRealPath("/") ;
		String fileName = Tool.getFileName()+ ".bak"  ;
		
		if (method.equals("backup")) {

			   backup( dir_path , fileName ) ; 
			   String file =  dir_path + "/bak/" + fileName  ;//即将下载的文件的相对路径    
			   
			   response.setCharacterEncoding("utf-8");    
			   OutputStream os = null;     
		       FileInputStream fis = null; 
			   try {                  
		            if (!(new File(file)).exists()) {    
		                System.out.println("没有文件");    
		                return;    
		             }    
		             System.out.println("文件名为："+file);    
		             os = response.getOutputStream();    
		             response.setHeader("content-disposition", "attachment;filename=" + fileName);  
					 response.setContentType("application/x-download");//设置为下载application/x-download     
		            byte temp[] = new byte[1000];    
		             fis = new FileInputStream(file);    
		            int n = 0;    
		            while ((n = fis.read(temp)) != -1) {    
		                 os.write(temp, 0, n);    
		             }    
		         } catch (Exception e) {    
		             System.out.println("出错");    
		         } finally {    
		            if (os != null)    
		                 os.close();    
		            if (fis != null)    
		                 fis.close();    
		         }  

		} else if (method.equals("restore")) {

			if(ServletFileUpload.isMultipartContent(req)) {  
				 
				   DiskFileItemFactory factory = new DiskFileItemFactory();    

				   factory.setRepository(new File( dir_path + "/tmp"));  
				   //内存最大占用   
		           factory.setSizeThreshold(1024000);   
		           ServletFileUpload sfu = new ServletFileUpload(factory);   
		           sfu.setHeaderEncoding("utf-8");
		           
		           //单个文件最大值byte   
		           sfu.setFileSizeMax(102400000);   
		           //所有上传文件的总和最大值byte   
		           sfu.setSizeMax(204800000);   
		           List items = null;   
		           try {   
		               items = sfu.parseRequest(req);   
		           } catch (SizeLimitExceededException e) {   
		               System.out.println("size limit exception!");   
		           } catch(Exception e) {   
		               e.printStackTrace();   
		           }   
		           
		           Iterator iter = items==null?null:items.iterator();   
		           while(iter != null && iter.hasNext()) {   
		               FileItem item = (FileItem)iter.next();   
		               //简单的表单域   
		               if(item.isFormField()) {     
		                   System.out.println("form field:" + item.getFieldName() + "  ");    
 
		               }    
		               //文件域   
		               else if(!item.isFormField()) {    
	                        System.out.println("上传文件的名称:" + item.getName());
		                   	if(item.getName().lastIndexOf("/") != -1){
		                   		fileName = item.getName().substring(item.getName().lastIndexOf("/")+1,item.getName().length());
		                   	}else if(item.getName().lastIndexOf("\\") != -1){
		                   		fileName = item.getName().substring(item.getName().lastIndexOf("\\")+1,item.getName().length());
		                   	}else{
		                   		fileName = item.getName();
		                   	}
		                   BufferedInputStream in = new BufferedInputStream(item.getInputStream());   
		                   //文件存储在工程的upload目录下,这个目录也得存在   
		                   BufferedOutputStream out = new BufferedOutputStream(   
		                            new FileOutputStream(new File( dir_path + "/upload/" + fileName)));   
		                   Streams.copy(in, out, true);   
		               }   
		           }   
					  
				}		   
			restore(dir_path , fileName) ;
			req.setAttribute("s", "0") ;
			req.getRequestDispatcher("/data_restore.jsp").forward(req, response); 
		}

	}

//	数据备份
	private void backup( String dir_path ,String fileName  ) { 
		
		Process p;
		try {
			p = Runtime.getRuntime().exec( " cmd /c  mysqldump -uroot -p111111 -hlocalhost file >  " + dir_path + "/bak/" + fileName  ) ;
	        p.waitFor();
	        System.out.println(p.exitValue()); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

//	数据备份还原
	private boolean restore( String dir_path ,String fileName  ) { 
		Process p;
		try {
			p = Runtime.getRuntime().exec( " cmd /c  mysql  -uroot -p111111 -hlocalhost file <  " + dir_path + "upload/" + fileName  ) ; 
			return true;  
	         
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false; 
	}
	
	
}
