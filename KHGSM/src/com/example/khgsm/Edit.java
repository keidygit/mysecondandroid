package com.example.khgsm;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Edit extends Activity {
	
	private MySQLiteOpenHelper mBooksdb;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);

		mBooksdb = new MySQLiteOpenHelper(this);
		
		ImageButton iButtonReturn = (ImageButton)findViewById(R.id.editimageButton1);
		ImageButton iButtonEdit = (ImageButton)findViewById(R.id.editimageButton2);
		
		iButtonReturn.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		finish();	//return to MainActivity
        	}       	
        });
		
		iButtonEdit.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		EditText etHostName = (EditText)findViewById(R.id.editHostName);
        		EditText etHostNumber = (EditText)findViewById(R.id.editHostNum);
        		EditText etHostPassword = (EditText)findViewById(R.id.editHostPass);
        		
				String strHostName = etHostName.getText().toString();
				String strHostNumber = etHostNumber.getText().toString();
				String strHostPassword = etHostPassword.getText().toString(); 
				
				
				if("".equals(strHostName) || "".equals(strHostNumber) || "".equals(strHostPassword))
				{
					Toast.makeText(Edit.this, "主机名，主机号码，主机密码不能为空", Toast.LENGTH_LONG).show();
					return;
				}
			
				String strExistHostName = mBooksdb.Hostname(strHostName);
				String strExistHostNumber = mBooksdb.Hostnumber(strHostNumber);
				
        		
				if(strExistHostName.equals(strHostName))
				{
					Toast.makeText(Edit.this, "主机名已经存在", Toast.LENGTH_LONG).show();
					etHostName.setText("");
					etHostNumber.setText("");
					etHostPassword.setText("");
					return;
				}
				else if(strExistHostNumber.equals(strHostNumber))
				{
					Toast.makeText(Edit.this, "主机号码已经存在" , Toast.LENGTH_LONG).show();
					etHostName.setText("");
					etHostNumber.setText("");
					etHostPassword.setText("");
					return;
				}
				else if(!strExistHostName.equals(strHostName) || !strExistHostNumber.equals(strHostNumber))
				{
					mBooksdb.insert(strHostName, strHostNumber, strHostPassword);
				}
				
				Intent _intent = new Intent(Edit.this,MainActivity.class);
        		startActivity(_intent);
				finish();	//Return to MainActivity
        	}       	
        });
	}
	
	@Override  
	protected void onDestroy() {  
	    super.onDestroy();  
	    if (mBooksdb  != null) {  
	        mBooksdb.close();  //Close database
	    }  
	} 

}
