package com.bb.ui;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.bb.R;
import com.bb.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import edu.self.UserInfo;
import edu.self.component.AppException;
import edu.self.component.Connect;
import edu.self.utils.AppContext;
import table.Activity.KcbActivity;


/**
 * 系统启动类，显示操作
 * @author Administrator
 *
 */
public class MainActivity extends Activity {

  
    private LinearLayout four_row ;
    
	private LinearLayout  kaoqin_row , exitRow  , two_row , three_row  ;
    

	public String userId; 
	private ProgressDialog dialog ;  
	private List<UserInfo> list; 
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);   
        
        initLayout();
    }

    
    public void initLayout(){
    	 
	   		dialog = new ProgressDialog(this);
			dialog.setMessage("正在加载用户信息...请稍候");
			dialog.show();
			
			new Thread(){
				public void run() {
					loadAllUserInfo();
					runOnUiThread(new Runnable() {
						public void run() {  
							for (UserInfo userinfo: list) {
								if (userinfo.getUserId().equals(Constants.userId )) {
									
									System.out.println(" userinfo.getUserId() ===" + userinfo.getUserId() );
									AppContext.userinfo = userinfo; 
	 
									load();
								}  
							} 
							dialog.dismiss();
						}
					});
				};
			}.start();  
	}
	
    
    private void load(){
	    
    	   
	     kaoqin_row = (LinearLayout)findViewById(R.id.kaoqin_row);
	     kaoqin_row.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View v) {
	    		 
	             Intent all = new Intent( MainActivity.this, InfoListActivity.class); 
	             all.putExtra("kaoqin", "kaoqin" );
	             startActivity(all);
	         }
	     });
	     

	     two_row = (LinearLayout)findViewById(R.id.two_row);
	     two_row.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View v) {
				 Intent intent1 = new Intent(MainActivity.this, KcbActivity.class);
				 startActivity(intent1);

	         }
	     });

	     three_row = (LinearLayout)findViewById(R.id.three_row);
	     three_row.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View v) {
	        	 
	             Intent all = new Intent( MainActivity.this, KaoqinListActivity.class); 
	             all.putExtra("type", Constants.FLAG_ALL);
	             startActivity(all);
	         }
	     });
	     

	     four_row = (LinearLayout)findViewById(R.id.four_row);
	     four_row.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View v) {
	             Intent all = new Intent( MainActivity.this, InfoListActivity.class); 
	             all.putExtra("kaoqin", "qingjia" );
	             startActivity(all);
	         }
	     });
	     
	     
	     
	//     退出
	     exitRow = (LinearLayout)findViewById(R.id.exit_row);
	     exitRow.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View v) {
	             finish();
	         }
	     }); 
    }
	
	/**
	 * 加载所有用户的信息
	 */
	private void loadAllUserInfo() {
		list = new ArrayList<UserInfo>();
		Connect connect = new Connect(AppContext.SERVER_USERS, AppContext.HTTP_POST);
		try {
			byte[] data = connect.queryServer(null);
			JSONObject object = new JSONObject(new String(data, "gb2312"));
			JSONArray userArray = object.getJSONArray("users");
			for (int i = 0;i < userArray.length();i++) {
				JSONObject userObject = userArray.getJSONObject(i);
				UserInfo userinfo = new UserInfo();
				userinfo.setUid(userObject.getInt("uid"));
				userinfo.setPassword(userObject.getString("password"));
				userinfo.setUserId(userObject.getString("userId"));
				userinfo.setUserName(userObject.getString("userName"));
				userinfo.setAddress(userObject.getString("address"));
				userinfo.setPhone(userObject.getString("phone"));
				userinfo.setGrade( userObject.getInt("grade")); 
				list.add(userinfo);
			}
		} catch (AppException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}


}
