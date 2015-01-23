package com.example.khgsm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	

	  
	public MySQLiteOpenHelper(Context context){
		super(context,"book.db",null,1);
		
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db)
		throws SQLException{

		db.execSQL("CREATE TABLE books_table(book_id INTEGER PRIMARY KEY,book_name text,STATUE INTEGER,ZONE1 text,ZONE2 text,ZONE3 text,ZONE4 text,PHONENUMBER1 TEXT,PHONENUMBER2 TEXT,PHONENUMBER3 TEXT,PHONENUMBER4 TEXT,PHONENUMBER5 TEXT,DELAY INTEGER,VOLONM INTEGER,TIME INTEGER,ARM INTEGER,LANGUAGE INTEGER,PHONE TEXT,book_author text,password text);");
		/*Build a database(books_table) and sheet
		book_id,book_name,status,zone1,zone2,zone3,zon4,phonenumber1,phonenumber2,phonenumber3
		phomenumber5,delay,volume,time,arm,language,phone,book_author,password
		*/
		}

	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
		
	}
	
	public String ARM(String paramString)
    {
      String str = "";
      Cursor localCursor = getReadableDatabase().rawQuery("select ARM from books_table where book_name='" + paramString + "'", null);
      if ((localCursor != null) && (localCursor.moveToFirst()))
        do
          str = localCursor.getString(localCursor.getColumnIndex("ARM"));
        while (localCursor.moveToNext());
      localCursor.close();
      return str;
    }
	  public String Hostname()
	  {
	    Cursor localCursor = getReadableDatabase().rawQuery("select * from books_table", null);
	    String str = "";
	    if (localCursor != null)
	    {
	      boolean bool = localCursor.moveToFirst();
	      str = null;
	      if (bool)
	        do
	          str = localCursor.getString(localCursor.getColumnIndex("book_name"));
	        while (localCursor.moveToNext());
	    }
	    localCursor.close();
	    return str;
	  }
	  public String Hostname(String paramString)
	  {
		    String str = "";
		    Cursor localCursor = getReadableDatabase().rawQuery("select * from books_table where book_name= '" + paramString + "'", null);
		    if ((localCursor != null) && (localCursor.moveToFirst()))
		      do
		        str = localCursor.getString(localCursor.getColumnIndex("book_name"));
		      while (localCursor.moveToNext());
		    localCursor.close();
		    return str;
	  }
	  
	  public String Hostnumber(String paramString)
	  {
	    String str = "";
	    Cursor localCursor = getReadableDatabase().rawQuery("select * from books_table where book_author= '" + paramString + "'", null);
	    if ((localCursor != null) && (localCursor.moveToFirst()))
	      do
	        str = localCursor.getString(localCursor.getColumnIndex("book_author"));
	      while (localCursor.moveToNext());
	    localCursor.close();
	    return str;
	  }
	  
	  public String HostPassword(String paramString)
	  {
	    String str = "";
	    Cursor localCursor = getReadableDatabase().rawQuery("select * from books_table where book_author= '" + paramString + "'", null);
	    if ((localCursor != null) && (localCursor.moveToFirst()))
	      do
	        str = localCursor.getString(localCursor.getColumnIndex("password"));
	      while (localCursor.moveToNext());
	    localCursor.close();
	    return str;
	  }
	  
	  public String ID(String paramString)
	  {
	    String str = "";
	    
	    Cursor localCursor = getReadableDatabase().rawQuery("select book_id from books_table where book_name='" + paramString + "'", null);

	    if ((localCursor != null) && (localCursor.moveToFirst()))
	      do
	        str = localCursor.getString(localCursor.getColumnIndex("book_id"));
	      while (localCursor.moveToNext());
	    localCursor.close();
	    return str;
	  }	  
	  
	  public String Number(String paramString)
	  {
	    String str = "";
	    Cursor localCursor = getReadableDatabase().rawQuery("select book_author from books_table where book_name='" + paramString + "'", null);
	    if ((localCursor != null) && (localCursor.moveToFirst()))
	      do
	        str = localCursor.getString(localCursor.getColumnIndex("book_author"));
	      while (localCursor.moveToNext());
	    localCursor.close();
	    return str;
	  }
	  
	  public void insert(String paramString1, String paramString2 ,String paramString3)
	  {
	    SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
	    ContentValues localContentValues = new ContentValues();
	    localContentValues.put("book_name", paramString1);
	    localContentValues.put("book_author", paramString2);
	    localContentValues.put("password", paramString3);
	    localSQLiteDatabase.insert("books_table", null, localContentValues);
	  }
	  public void deleteName(String paramString)
	  {
	    getWritableDatabase().delete("books_table", "book_name = ?", new String[] { paramString });
	  }

	  public void deletedID(String paramString)
	  {
	    getWritableDatabase().delete("books_table", "book_id = ?", new String[] { paramString });
	  }

}