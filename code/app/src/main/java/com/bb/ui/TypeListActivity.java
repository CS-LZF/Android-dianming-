package com.bb.ui;



import java.util.List;

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

import com.bb.R;
import com.bb.api.HttpApiAccessor;
import com.bb.model.Type;

import edu.self.LoginActivity;



public class TypeListActivity extends ListActivity {

 
	private List<Type>  list;
	private NewsAdapter adapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.news_type_list);

        TextView tv_refresh = (TextView)findViewById(R.id.refresh) ;
        tv_refresh.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				list =  HttpApiAccessor.getAllNewsType(   ) ;
				new LoadTask().execute();
			}
		});

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
			name = list.get(position).getName() ;
			
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

			list =  HttpApiAccessor.getAllNewsType(   ) ;
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
		Intent intent = new Intent(TypeListActivity.this, NewsListActivity.class);
		intent.putExtra( "newsType", list.get(position) );
		startActivity(intent);
	}
	    
	    
}
