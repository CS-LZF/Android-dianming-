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
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;

import com.bb.R;
import com.bb.ui.InfoListActivity;
import com.bb.ui.MainActivity;
import com.bb.util.Constants;
import com.bb.util.HttpUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * 登录页面
 * @author mzba
 *
 */
public class LoginActivity extends Activity {
	
	private EditText et_id;
	private EditText et_password;
	private ProgressDialog dialog;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loginpage);
        
        SharedPreferences preferences = getSharedPreferences(
    			Constants.Preferences_user,
    			Activity.MODE_APPEND);
        
        et_id = (EditText) findViewById(R.id.login_edit_account);
        et_id.setText(preferences.getString(Constants.Preferences_userid, ""));
		et_password = (EditText) findViewById(R.id.login_edit_pwd);
		
		final CheckBox cbSavePw = (CheckBox) findViewById(R.id.save_password);
		final CheckBox cbAutoLogin = (CheckBox) findViewById(R.id.login_automatic);
		
		if (preferences.getBoolean(Constants.Preferences_savepassword, false)) {
			Log.i(LoginActivity.class.getCanonicalName(), preferences.getString(Constants.Preferences_password, ""));
			et_password.setText(preferences.getString(Constants.Preferences_password, ""));
			cbSavePw.setSelected(true);
		}
		if (preferences.getBoolean(Constants.Preferences_autologin, false)) {
			cbAutoLogin.setSelected(true);
			login();
		}
		
		((ImageButton) findViewById(R.id.login_btn))
		.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences preferences = getSharedPreferences(
		    			Constants.Preferences_user,
		    			Activity.MODE_APPEND);
				Editor editor = preferences.edit();
				if (cbAutoLogin.isChecked()) {
					editor.putBoolean(Constants.Preferences_autologin, true);
					editor.putBoolean(Constants.Preferences_savepassword, true);
					cbSavePw.setSelected(true);
					editor.commit();
				} else {
					editor.putBoolean(Constants.Preferences_autologin, false);
					editor.commit();
				}
				if (cbSavePw.isChecked()) {
					editor.putBoolean(Constants.Preferences_savepassword, true);
					editor.putString(Constants.Preferences_password, et_password.getText().toString());
					editor.commit();
				} else {
					editor.putBoolean(Constants.Preferences_savepassword, false);
					editor.putString(Constants.Preferences_password, "");
					editor.commit();
				}
				String uin = et_id.getText().toString().trim();
				String pwd = et_password.getText().toString().trim();
				if ((uin.length() == 0) || (pwd.length() == 0)) {
					Toast.makeText(LoginActivity.this, "用户名或者密码不能为空",
							Toast.LENGTH_LONG).show();
					return;
				}
				editor.putString(Constants.Preferences_userid, et_id.getText().toString());
				editor.commit();
				login();
			}
		});
		
		((ImageButton) findViewById(R.id.login_option)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openOptionsMenu();
			}
		});
    }
    
    public void login() {
    	dialog = new ProgressDialog(this);
		dialog.setMessage("登录中...请稍候");
		dialog.show();
    	new Thread(){
			public void run() {
				if (HttpUtil.isConnectInternet(LoginActivity.this)) {
					HttpPost post = new HttpPost(Constants.SERVER + Constants.SERVER_LOGIN);
					List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
					params.add(new BasicNameValuePair("userId", et_id.getText().toString()));
					params.add(new BasicNameValuePair("password", et_password.getText().toString()));
					try {
						post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
						post.getParams().setBooleanParameter(
								CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
						HttpResponse response = (HttpResponse) new DefaultHttpClient().execute(post);;
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
								if (content.equals("true")) {
									runOnUiThread(new Runnable() {
										public void run() {
											Constants.userId =  et_id.getText().toString() ;
											System.out.println( " Constants.userId :::::" + Constants.userId ); 

											Intent intent = new Intent();
											intent.setClass(LoginActivity.this, MainActivity.class);
											intent.putExtra("userId", et_id.getText().toString());
											
											startActivity(intent); 
											
											
											dialog.dismiss();
											LoginActivity.this.finish();
										}
									});
								} else if (content.equals("false")) {
									runOnUiThread(new Runnable() {
										public void run() {
											dialog.dismiss();
											Toast.makeText(LoginActivity.this, "帐号不存在或用户名密码错误！", Toast.LENGTH_LONG).show();
										}
									});
								} else {
									runOnUiThread(new Runnable() {
										public void run() {
											dialog.dismiss();
											Toast.makeText(LoginActivity.this, "登录失败，请稍后再试！", Toast.LENGTH_LONG).show();
										}
									});
								}
							}
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.clear();
		getMenuInflater().inflate(R.menu.login_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.register_account:
			startActivity(new Intent(this, RegisterAccountActivity.class));
			finish();
			break;
		case R.id.net_setting:
			startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
			break;
		case R.id.update:
			
			break;
		case R.id.exit:
			finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		SharedPreferences preferences = getSharedPreferences(
    			Constants.Preferences_user,
    			Activity.MODE_APPEND);
		Editor editor = preferences.edit();
		editor.putString(Constants.Preferences_userid, et_id.getText().toString());
		editor.commit();
	}
}