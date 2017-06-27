package com.bb.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bb.R;
import com.bb.api.HttpApiAccessor;
import com.bb.model.Info;
import com.bb.util.AsyncImageLoader;

import java.util.ArrayList;


/**
 * 列表activity
 * @author Administrator
 *
 */
public class InfoListActivity  extends  ListActivity {


    
    public static final int COMPOSE_UPDATE_REQUEST_CODE = 1339;

    private FoodsAdapter adapter = null;
    
    private ArrayList<Info> infoList;

    private String kaoqin;
    
    private String type;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        if( getIntent().getExtras().get("kaoqin") != null ){
        	 kaoqin = (String) getIntent().getExtras().get("kaoqin") ;        
        }
        
        setContentView(R.layout.info_list);  
        
//        加载数据
        new LoadTask().execute();     
    }
    
//    继承自android的AsyncTask异步类
    public class LoadTask extends AsyncTask<Void, Void, Void>{
		
		/**
		 * 后台运行，加载数据
		 */
		protected Void doInBackground(Void... arg0) {
			try {
				infoList =  HttpApiAccessor.getFollowed(-1, -1,type) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * 在执行时调用，将适配器类传入
		 */
		protected void onPostExecute(Void result) {
			adapter = new FoodsAdapter() ;
			setListAdapter(adapter);
			removeDialog(0);
			super.onPostExecute(result);
		}
		
		protected void onPreExecute() {
			showDialog(0);
			super.onPreExecute();
		}
	}

    
    /**
     *  点击每一行时跳转到
     */
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.i( " onListItemClick "  , kaoqin + "=============  list ================"  );
//		Intent intent = new Intent( InfoListActivity.this, InfoActivity.class);
//		intent.putExtra("food", infoList.get(position));
//		startActivity(intent);
		
		if( kaoqin != null && kaoqin.equals("kaoqin") ){

			Intent intent = new Intent( InfoListActivity.this, KaoqinActivity.class);
			intent.putExtra("kecheng", infoList.get(position) );
			startActivity(intent);
		}else if( kaoqin != null && kaoqin.equals("zuoye") ){

			Intent intent = new Intent( InfoListActivity.this, ZuoyeListActivity.class);
			intent.putExtra("zuoye", infoList.get(position).getInfo_name() );
			startActivity(intent);
		}else if( kaoqin != null && kaoqin.equals("qingjia") ){

			Intent intent = new Intent( InfoListActivity.this, QingjiaInfoAddActivity.class);
			intent.putExtra("qingjia", infoList.get(position).getInfo_name() );
			startActivity(intent);
		}

		
	}
	
    
/**
 * 适配器类
 * @author Administrator
 *
 */ 
	public class FoodsAdapter extends BaseAdapter {

		private AsyncImageLoader asyncImageLoader;
		
		public int getCount() {
			return infoList.size();
		}

		
		public Object getItem(int arg0) {
			return infoList.get(arg0);
		}

		
		public long getItemId(int position) {
			return position;
		}

/**
 * 		将每一行设置为foods_list_row中定义的格式，并且赋值
 */
		public View getView(int position, View convertView, ViewGroup parent) {
		
			asyncImageLoader = new AsyncImageLoader();
			 
			convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.info_list_row, null);
 
		    TextView name, todayPrice , description , price ;
		    
	        Info u = infoList.get(position); 
	        name = (TextView) convertView.findViewById(R.id.name) ; 
	        name.setText(  u.info_name    );
	        
			return convertView;
		}
	}


	protected void onStop(){
		finish();

	}
    
}
