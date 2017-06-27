package com.bb.ui;


import java.util.List;

import com.bb.R;
import com.bb.api.HttpApiAccessor;
import com.bb.model.Info;
import com.bb.model.Type;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class NewsListActivity extends ListActivity {


    private Type type = null ;
	private List<Info>  list;
	private NewsAdapter adapter;

	private String queryByName = null  ;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.info_list);  
    	
        if(  getIntent().getSerializableExtra("newsType") != null ) {
    		type  = (Type)getIntent().getSerializableExtra("newsType") ;   
    	}
    	
//        TextView tv_back = (TextView)findViewById(R.id.back) ;
//        tv_back.setOnClickListener( new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(NewsListActivity.this, HomeActivity.class); 
//				startActivity(intent);
//			}
//		});
        
//        TextView tv_refresh = (TextView)findViewById(R.id.refresh) ;
//        tv_refresh.setOnClickListener( new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				String  news_type  = null  ;
//				if( type != null) 
//					news_type = type.getName()  ;
//				
//				try {
//					list =  HttpApiAccessor.getFollowedByType( 0 , 500 , news_type   ) ;
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				new LoadTask().execute(); 
//			}
//		});
        
		new LoadTask().execute();
    }
    
    
	public class NewsAdapter extends BaseAdapter {

		public int getCount() {
			return list.size();
		}

		
		public Object getItem(int arg0) {
			return list.get(arg0);
		}

		
		public long getItemId(int position) {
			return position;
		}

		
		public View getView(int position, View convertView, ViewGroup parent) {
			String  name = null;
			convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.news_item, null);

			name = list.get(position).info_name  ;
			
			TextView tv = (TextView) convertView.findViewById(R.id.newsName);
			tv.setText( name ); 
			convertView.setTag(name);
			return convertView;
		}
	}
	
	
	/**
	 * 异步加载所有资源
	 *
	 */
	public class LoadTask extends AsyncTask<Void, Void, Void>{
		
		
		protected Void doInBackground(Void... arg0) { 

			String  news_type  = null  ;
			if( type != null) 
				news_type = type.getName()  ;

			try {
				list =  HttpApiAccessor.getFollowedByType( -1 , -1 , news_type   ) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
			return null;
		}

		
		protected void onPostExecute(Void result) {
			adapter = new NewsAdapter();
			setListAdapter(adapter);
			removeDialog(0);
			super.onPostExecute(result);
		}

		
		protected void onPreExecute() {
			showDialog(0);
			super.onPreExecute();
		}
	}

	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
//		Intent intent = new Intent(NewsListActivity.this, NewsInfoActivity.class);
//		intent.putExtra( "news", list.get(position) );
//		startActivity(intent);
		Intent intent = new Intent( NewsListActivity.this, InfoActivity.class);
		intent.putExtra("food", list.get(position));
		startActivity(intent);
	}
	
	
	    
	    
}
