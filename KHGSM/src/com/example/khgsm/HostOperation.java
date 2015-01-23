package com.example.khgsm;

import java.util.ArrayList;
import java.util.HashMap;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class HostOperation extends Activity {
	
	TextMessage tMessage = new TextMessage();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hostoperation);
		
		Bundle bundle = this.getIntent().getExtras();
		final String fromMainActivityStr = bundle.getString("Transword"); //Receive string from the previous activity
		final String fromMainActivityStr2 = bundle.getString("Transword2");
		
		ImageButton alarmButton = (ImageButton)findViewById(R.id.imageButtonAlarm);
		ImageButton homeAlarmButton = (ImageButton)findViewById(R.id.imageButtonDial);
		ImageButton disalarmButton = (ImageButton)findViewById(R.id.imageButtonDisaLarm);
		
		alarmButton.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
	
        		tMessage.sendMessages("5556", fromMainActivityStr);

        	}       	
        });
		
		homeAlarmButton.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
	
        		tMessage.sendMessages("5556", fromMainActivityStr2);

        	}       	
        });
		
		disalarmButton.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
	
        		tMessage.sendMessages("5556", "disalarm");

        	}       	
        });
		
		this.myOptionList();
		
		
	}
	
	public void myOptionList()
	{
		ListView list = (ListView) findViewById(R.id.listView1); 
		//���ɶ�̬���飬��������  
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        

        HashMap<String, Object> map1 = new HashMap<String, Object>(); 	        
        map1.put("ItemTitle", " �洢�绰���� " );          
        listItem.add(map1);
        HashMap<String, Object> map2 = new HashMap<String, Object>(); 	        
        map2.put("ItemTitle", " ���ķ�����Ϣ" );          
        listItem.add(map2);
        HashMap<String, Object> map3 = new HashMap<String, Object>(); 	        
        map3.put("ItemTitle", " ������ʱʱ�� " );          
        listItem.add(map3);
        HashMap<String, Object> map4 = new HashMap<String, Object>(); 	        
        map4.put("ItemTitle", " ��������������ʱ��" );          
        listItem.add(map4);
        
      //������������Item�Ͷ�̬�����Ӧ��Ԫ��  
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,R.layout.list_item,new String[] {"ItemTitle"},   new int[] {R.id.ItemTitle});    
         
        //��Ӳ�����ʾ  
 
       list.setAdapter(listItemAdapter);
       
       //��ӵ��  
       
       list.setOnItemClickListener(new OnItemClickListener() {  
       	
           public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {  
               setTitle("�����"+arg2+"����Ŀ"); 
               switch (arg2) {
				case 0:
					Intent intent1 = new Intent(HostOperation.this,SaveNumber.class);
					startActivity(intent1);
					break;
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				default:
					break;
				}
               
           }  
       }); 

	}
}
