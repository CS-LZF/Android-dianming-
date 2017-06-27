package com.bb.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bb.R;
import com.bb.api.HttpApiAccessor;
import com.bb.model.Info;

import java.util.HashMap;

import edu.self.MyApplication;
import edu.self.utils.AppContext;



public class KaoqinActivity extends Activity {


	private Info info ;

	private LocationClient mLocClient;
	
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kaoqin_info);
		mLocClient = ((MyApplication)getApplication()).mLocationClient;
		setLocationOption();
		mLocClient.start();
//获取上一个activity传过来的参数值
		info = (Info) getIntent().getSerializableExtra("kecheng");
		TextView tv_name = (TextView) this.findViewById(R.id.name);
		tv_name.setText(   " 课程: " + info.info_name   ) ;
		Button btn_discuss = (Button) findViewById(R.id.qiandao) ;
		btn_discuss.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				HashMap  map  = new HashMap() ;
				map.put("method", "save" ) ;
				map.put("news_id", AppContext.userinfo.getUserId()  ) ;
				map.put("content", info.info_name ) ;
//				   精度，维度
				map.put("type",  "1" ) ;
				map.put("answer" ,	String.valueOf(MyApplication.now_longitude)  ) ;
				map.put("uid",  String.valueOf(MyApplication.now_latitude)  ) ;
				map.put("username",  AppContext.userinfo.getUserName() ) ;
				String result =  HttpApiAccessor.sendDiscuss2( map ) ;
				if( result != null && result.equals("1") ){
					Toast.makeText( KaoqinActivity.this, "考勤签到成功" , Toast.LENGTH_SHORT ).show() ;
				}
				Intent intent = new Intent( KaoqinActivity.this , MainActivity.class ) ;
				startActivity( intent );
			}
		}) ;

	}





	//设置相关参数
	private void setLocationOption(){
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps( false );				//打开gps
		option.setCoorType( "bd09ll");		//设置坐标类型
		option.setServiceName("com.baidu.location.service_v2.9");
		option.setPoiExtraInfo( true );	
		option.setAddrType("all");
		
//		option.setScanSpan(1000);
		option.setScanSpan(3000);
		
		option.setPriority(LocationClientOption.NetWorkFirst);      //设置网络优先
//		if(mPriorityCheck.isChecked())
//		{
//			option.setPriority(LocationClientOption.NetWorkFirst);      //设置网络优先
//		}
//		else
//		{
//			option.setPriority(LocationClientOption.GpsFirst);        //不设置，默认是gps优先
//		}

		option.setPoiNumber(10);
		option.disableCache(true);		
		mLocClient.setLocOption(option);
	}
	
	
	
}
