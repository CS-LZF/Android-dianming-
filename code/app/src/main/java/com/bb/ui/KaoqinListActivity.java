package com.bb.ui;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bb.R;
import com.bb.api.KaoqinHttpAdapter;
import com.bb.model.Discuss2;
import com.bb.util.AsyncImageLoader;

import java.util.ArrayList;

import edu.self.utils.AppContext;



public class KaoqinListActivity  extends  ListActivity {


    private ZuoyeAdapter adapter = null;
    
    private ArrayList<Discuss2> list;

    private String type;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        type = (String) getIntent().getExtras().get("type");        
        
        type = String.valueOf(  AppContext.userinfo.getUserId()   ) ;
        
        setContentView(R.layout.zuoye_list);  
        
        new LoadTask().execute();     
    }
    
	/**
	 * 异步加载所有资源
	 *
	 */
	public class LoadTask extends AsyncTask<Void, Void, Void>{
	 
		protected Void doInBackground(Void... arg0) {
			try {
				list =  KaoqinHttpAdapter.getAllKaoqinList(-1, -1,type) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
 
		protected void onPostExecute(Void result) {
			adapter = new ZuoyeAdapter() ;
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
//		Intent intent = new Intent( KaoqinListActivity.this, KaoqinInfoActivity.class);
//		intent.putExtra("object", list.get(position));
//		startActivity(intent);
	}
	

	public class ZuoyeAdapter extends BaseAdapter {

		private AsyncImageLoader asyncImageLoader;
		
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
		
			asyncImageLoader = new AsyncImageLoader();
			 
			convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.zuoye_list_row, null);
 
		    TextView name =  (TextView) convertView.findViewById(R.id.name); ;
		    
		    Discuss2 u = list.get(position); 
	        name.setText(  u.getTbl_date() + "--------" + u.content     );
	        
			return convertView;
		}
	}
	
	
 
    }
