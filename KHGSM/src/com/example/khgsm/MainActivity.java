package com.example.khgsm;



import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;


import android.os.Bundle;
import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private MySQLiteOpenHelper referdb;
	String  referdbStr;
	boolean editHost = false;
	String acquireItemHostNameByLongClick;
	String acquireItemHostNameByOneClick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		           
		ImageButton iButton1 = (ImageButton)findViewById(R.id.mainimageButton1);
		ImageButton iButton2 = (ImageButton)findViewById(R.id.mainimageButton2);
		
		iButton1.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		editHost = !editHost;
        		
        	}       	
        });
		iButton2.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Intent _intent = new Intent(MainActivity.this,Edit.class);
        		startActivity(_intent);
        		finish();
        	}       	
        });
		
		this.myList();
		
      
    }  
	public void myList()
	{
		referdb = new MySQLiteOpenHelper(this);
		
		ListView list = (ListView) findViewById(R.id.ListView01); 
		//生成动态数组，加入数据  
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>(); 
  
        Cursor localCursor = referdb.getReadableDatabase().rawQuery("select * from books_table", null);
	    if (localCursor != null)
	    {
	      boolean bool = localCursor.moveToFirst();
	      referdbStr = null;
	      if (bool)
	        do
	        {
	          referdbStr = localCursor.getString(localCursor.getColumnIndex("book_name"));
	          
	          HashMap<String, Object> map = new HashMap<String, Object>(); 	        
	          map.put("ItemTitle", referdbStr );          
	          listItem.add(map);
	        }
	        while (localCursor.moveToNext());
	    }
	    localCursor.close();
   
        
        //生成适配器的Item和动态数组对应的元素  
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,R.layout.list_item,new String[] {"ItemTitle"},   new int[] {R.id.ItemTitle});    
         
        //添加并且显示  
 
       list.setAdapter(listItemAdapter);  
          
        //添加点击  
        
        list.setOnItemClickListener(new OnItemClickListener() {  
        	
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {  
                setTitle("点击第"+arg2+"个项目"); 
                if(!editHost)
                {
                	//enter the new activity
                	TextView acquireHNByOne = (TextView)arg1.findViewById(R.id.ItemTitle);	//Acquire the item value(HostName)
                	acquireItemHostNameByOneClick = acquireHNByOne.getText().toString();
                	Intent _intent = new Intent(MainActivity.this,HostOperation.class);
                	Bundle bundle = new Bundle();
                	bundle.putString("Transword", acquireItemHostNameByOneClick);	//Transport the string to next activity use Bundle
                	bundle.putString("Transword2", "999");
                	_intent.putExtras(bundle);
                	startActivity(_intent);
                }
                
            }  
        }); 
          
      //添加长按点击  

        list.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  
              
            public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
            	if(editHost)
            	{
	                menu.setHeaderTitle("选项");     
	                menu.add(0, 0, 0, "删除此项");   
            	}
            } 

        });
        list.setOnItemLongClickListener(new OnItemLongClickListener() {
        
        	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {  
                TextView acquireHNLong = (TextView)arg1.findViewById(R.id.ItemTitle);	//Acquire the item value(HostName)
                acquireItemHostNameByLongClick = acquireHNLong.getText().toString();
				return false; 
                
            } 
		});



	}
	
      
    //长按菜单响应函数  
    @Override  
    public boolean onContextItemSelected(MenuItem item) {  
        setTitle("点击了长按菜单里面的第"+item.getItemId()+"个项目");
        if(item.getItemId() == 0);
        {
        	referdb.deleteName(acquireItemHostNameByLongClick);
        	this.myList();	//Refresh the item
        }
        return super.onContextItemSelected(item);  
    } 
    
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        if (referdb  != null) {  
            referdb.close();  //Close database
        }  
    } 
    
	
    
   /* 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	*/

}
