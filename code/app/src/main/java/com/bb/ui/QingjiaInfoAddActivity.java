package com.bb.ui;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader; 
import java.util.ArrayList; 
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost; 
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle; 
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.self.utils.AppContext;
import com.bb.R;



/**
 * 添加
 *
 */
public class QingjiaInfoAddActivity extends Activity {

	private EditText ed_biaoti; 
	private EditText ed_miaoshu; 
	private EditText ed_shijian; 
	
//	private EditText ed_zhuangtai; 
//	private EditText ed_xusheng;
	
	private TextView tv_kecheng; 
  
	private Button btnOk; 

	private String kecheng ; 
	
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qingjia_info_add);
		
		kecheng = (String) getIntent().getExtras().get("qingjia") ;    
		
		ed_biaoti = (EditText) this.findViewById(R.id.biaoti);
		ed_miaoshu = (EditText) this.findViewById(R.id.miaoshu);
		ed_shijian = (EditText) this.findViewById(R.id.shijian);
		
//		ed_zhuangtai = (EditText) this.findViewById(R.id.zhuangtai);
//		ed_xusheng = (EditText) this.findViewById(R.id.xusheng);
		
		tv_kecheng = (TextView) this.findViewById(R.id.kecheng);
		tv_kecheng.setText("课程：" + kecheng );
		
		
		btnOk = (Button) findViewById(R.id.button1);
		btnOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				showDialog(0);
				new Thread(){
					public void run() {
						try {
							JSONObject jsonObject = new JSONObject();
						    jsonObject.put("biaoti", ed_biaoti.getText().toString());
						    jsonObject.put("miaoshu", ed_miaoshu.getText().toString());
						    jsonObject.put("shijian", ed_shijian.getText().toString());
						    jsonObject.put("zhuangtai", "审核" );
						    jsonObject.put("xusheng", AppContext.userinfo.getUserName() );

						    jsonObject.put("kecheng",  kecheng );
						     
						    jsonObject.put("uid", AppContext.userinfo.getUid());
							
							
							HttpPost post = new HttpPost(AppContext.SERVER + "/qingjia.do?method=saveJson");
							
							List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
							params.add(new BasicNameValuePair("qingjia", jsonObject.toString()));
							
							post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
							post.getParams().setBooleanParameter( CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
							HttpResponse response = (HttpResponse) new DefaultHttpClient().execute(post);
							
							if (response != null) {
								if (200 == response.getStatusLine().getStatusCode()) {
									InputStream is = response.getEntity().getContent();
									Reader reader = new BufferedReader(new InputStreamReader(is));
									StringBuilder builder = new StringBuilder((int) response.getEntity().getContentLength());
									char[] temp = new char[4000];
									int len = 0;
									while ((len = reader.read(temp)) != -1) {
										builder.append(temp, 0, len);
									}
									reader.close();
									is.close();
									final String content = builder.toString();
									response.getEntity().consumeContent();
									runOnUiThread(new Runnable() {
										public void run() {
											removeDialog(0);
											if ( !content.trim().equals("ERROR") ) {

												Toast.makeText(QingjiaInfoAddActivity.this, "添加成功", Toast.LENGTH_LONG).show();
												
												Intent all = new Intent( QingjiaInfoAddActivity.this, MainActivity.class); 
									            startActivity(all);
									            
											} else {
												Toast.makeText(QingjiaInfoAddActivity.this, "添加失败", Toast.LENGTH_LONG).show();
											}
										}
									});
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					};
				}.start();
			}
		});
	}
	
	
}
