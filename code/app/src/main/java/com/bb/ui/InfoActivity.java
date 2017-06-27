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
import com.bb.db.DbControl;
import com.bb.model.Info;
import com.bb.util.Player;
import com.bb.util.Constants;



public class InfoActivity extends Activity {


	private Info info ;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.info_info);
		
//获取上一个activity传过来的参数值
		info = (Info) getIntent().getSerializableExtra("food");

		TextView tv_name = (TextView) this.findViewById(R.id.name);
		tv_name.setText(  info.info_name   ) ;
		 
		TextView biaoqian = (TextView) this.findViewById(R.id.biaoqian);
		biaoqian.setText(  " 描述: " + info.info_description ) ;

		TextView btn_discuss = (TextView) findViewById(R.id.btn_discuss) ;
		btn_discuss.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

//		    	Intent intent = new Intent( InfoActivity.this , AnswerActivity.class ) ;
//				intent.putExtra("id",   info.info_id  );
//				startActivity( intent );

//		    	Intent intent = new Intent( InfoActivity.this , AnswerListActivity.class ) ;
//				intent.putExtra("id",   info.info_id  );
//				startActivity( intent );
			}
		}) ; 
		
	}
	
	
}
