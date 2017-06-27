package table.Activity.temp;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;

import table.Activity.KcbActivity;
public class MyAdapter {

	private Context context;
	private KcbActivity main;
	private Cursor[] cursor=new Cursor[7];
	private SimpleCursorAdapter[] adapter;
	
	private SharedPreferences preferences;
	
	public MyAdapter(Context context){
		this.context=context;
		main=(KcbActivity) context;
	}
	public void test(){
	
	
			
	}
	
}
