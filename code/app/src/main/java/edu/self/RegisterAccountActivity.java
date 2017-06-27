package edu.self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
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
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bb.R;
import com.bb.util.Constants;
import com.bb.util.HttpUtil;


/**
 * 注册界面
 * @author mzba
 *
 */
public class RegisterAccountActivity extends Activity {
	
	private EditText etuserId;
	private EditText etpassword;
	private EditText etpassword2;
	private EditText etuserName;
	private EditText etphone;
	private EditText etaddress; 
	
	private ProgressDialog dialog; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registeraccount);
		
		etuserId = (EditText) findViewById(R.id.register_edit_account);
		etpassword = (EditText) findViewById(R.id.register_edit_pwd);
		etpassword2 = (EditText) findViewById(R.id.register_edit_pwd2);
		etuserName = (EditText) findViewById(R.id.register_edit_userName);
		etphone = (EditText) findViewById(R.id.register_edit_phone);
		etaddress = (EditText) findViewById(R.id.register_edit_address);
		 
	  
		((ImageButton) findViewById(R.id.register_btn)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				dialog = new ProgressDialog(RegisterAccountActivity.this);
				dialog.setMessage("正在初始化...请稍候！");
				dialog.show();
				
				String userId = etuserId.getText().toString().trim();
				String password = etpassword.getText().toString().trim();
				String password2 = etpassword2.getText().toString().trim();
				String userName = etuserName.getText().toString().trim();
				String phone = etphone.getText().toString().trim();
				String address = etaddress.getText().toString().trim();
			 
				if (userId.equals("")) {
					makeToast("用户名不能为空！");
					return;
				} else if (password.equals("")) {
					makeToast("密码不能为空！");
					return;
				} else if (!password.equals(password2)) {
					makeToast("两次输入密码不一样！");
					return;
				} else if (userName.equals("")) {
					makeToast("姓名不能为空！");
					return;
				} else if (phone.equals("")) {
					makeToast("电话不能为空！");
					return;
				} else if (address.equals("")) {
					makeToast("地址不能为空！");
					return;
 				}
				JSONObject userObject = new JSONObject();
				try {
					userObject.put("userId", userId);
					userObject.put("userName", userName);
					userObject.put("password", password);
					userObject.put("phone", phone);
					userObject.put("address", address); 
					if (HttpUtil.isConnectInternet(RegisterAccountActivity.this)) {
						HttpPost post = new HttpPost(Constants.SERVER + Constants.SERVER_REGISTER);
						List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
						params.add(new BasicNameValuePair("register", userObject.toString()));
						Log.i(RegisterAccountActivity.class.getCanonicalName(), userObject.toString());
						post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
						post.getParams().setBooleanParameter(
								CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
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
								String content = builder.toString();
								response.getEntity().consumeContent();
								Log.i(RegisterAccountActivity.class.getCanonicalName(), content.trim());
								if (content.trim().equals("ERROR")) {
									dialog.dismiss();
									Toast.makeText(RegisterAccountActivity.this, "注册失败，请稍后再试！", Toast.LENGTH_LONG).show();
								} else if (content.trim().equals("EXISTSUSER")) {
									runOnUiThread(new Runnable() {
										public void run() {
											dialog.dismiss();
											Toast.makeText(RegisterAccountActivity.this, "该帐号已存在！", Toast.LENGTH_LONG).show();
										}
									});
								} else {
									runOnUiThread(new Runnable() {
										public void run() {
											dialog.dismiss();
											Toast.makeText(RegisterAccountActivity.this, "注册成功！", Toast.LENGTH_LONG).show();
											Intent intent = new Intent();
											intent.setClass(RegisterAccountActivity.this, LoginActivity.class);
											startActivity(intent);
											RegisterAccountActivity.this.finish();
										}
									});
								}
							}
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					runOnUiThread(new Runnable() {
						public void run() {
							if (dialog.isShowing()) {
								dialog.dismiss();
							}
						}
					});
				}
			}
		});
	}
	 
	
	public void makeToast(String msg) {
		Toast.makeText(RegisterAccountActivity.this, msg, Toast.LENGTH_LONG).show();
	}
	
}
