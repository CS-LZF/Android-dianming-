package com.bb.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button; 
import android.widget.SeekBar;
import android.widget.TextView;
import com.bb.R; 
import com.bb.model.Info; 
import com.bb.util.Constants;
import com.bb.model.Qingjia;


public class  QingjiaInfoActivity extends Activity {


	private Qingjia qingjia ;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.qingjia_info);
		 
		qingjia = (Qingjia) getIntent().getSerializableExtra("object");

		TextView tv_biaoti = (TextView) this.findViewById(R.id.biaoti);
		tv_biaoti.setText(  "标题 : " + qingjia.biaoti   ) ;
		TextView tv_miaoshu = (TextView) this.findViewById(R.id.miaoshu);
		tv_miaoshu.setText(  "描述 : " + qingjia.miaoshu   ) ;
		TextView tv_shijian = (TextView) this.findViewById(R.id.shijian);
		tv_shijian.setText(  "请假时间 : " + qingjia.shijian   ) ;
		TextView tv_zhuangtai = (TextView) this.findViewById(R.id.zhuangtai);
		tv_zhuangtai.setText(  "请假状态 : " + qingjia.zhuangtai   ) ;
		TextView tv_xusheng = (TextView) this.findViewById(R.id.xusheng);
		tv_xusheng.setText(  "学生 : " + qingjia.xusheng   ) ;
		TextView tv_kecheng = (TextView) this.findViewById(R.id.kecheng);
		tv_kecheng.setText(  "课程 : " + qingjia.kecheng   ) ;
     
		TextView btn_button1 = (TextView) findViewById(R.id.button1) ;
		btn_button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

		    	Intent intent = new Intent( QingjiaInfoActivity.this , QingjiaListActivity.class ) ;
//				intent.putExtra("id",   info.info_id  );
				startActivity( intent );
				finish();
			}
		}) ; 
		
	}
	
	
}
