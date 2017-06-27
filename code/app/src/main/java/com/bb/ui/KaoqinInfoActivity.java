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
import com.bb.model.Zuoye;


public class  KaoqinInfoActivity extends Activity {


	private Zuoye zuoye ;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.zuoye_info);
		 
		zuoye = (Zuoye) getIntent().getSerializableExtra("object");

		TextView tv_mingcheng = (TextView) this.findViewById(R.id.mingcheng);
		tv_mingcheng.setText(  "作业名称 : " + zuoye.mingcheng   ) ;
		TextView tv_miaoshu = (TextView) this.findViewById(R.id.miaoshu);
		tv_miaoshu.setText(  "描述 : " + zuoye.miaoshu   ) ;
		TextView tv_shijian = (TextView) this.findViewById(R.id.shijian);
		tv_shijian.setText(  "时间 : " + zuoye.shijian   ) ;
		TextView tv_kecheng = (TextView) this.findViewById(R.id.kecheng);
		tv_kecheng.setText(  "课程 : " + zuoye.kecheng   ) ;
     
		TextView btn_button1 = (TextView) findViewById(R.id.button1) ;
		btn_button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

		    	Intent intent = new Intent( KaoqinInfoActivity.this , KaoqinListActivity.class ) ;
//				intent.putExtra("id",   info.info_id  );
				startActivity( intent );
				finish();
			}
		}) ; 
		
	}
	
	
}
