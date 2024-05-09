package com.example.test.main.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserOpration {

    private DBHelper dbHelper;

    public UserOpration(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertUser(String firstName, String lastName, String email,int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", (id));
        values.put("first_name", firstName);
        values.put("last_name", lastName);
        values.put("email", email);

        db.insert("users", null, values);
        db.close();
    }
    public int getUserCount() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM users", null);
        int count = 0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        db.close();
        return count;
    }
    public Cursor getAllUsers() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query("users", null, null, null, null, null, null);
    }

    public boolean isUserIdExists(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define the selection criteria
        String selection = "id=?";
        String[] selectionArgs = {String.valueOf(id)};

        // Query the database to check if the ID exists
        Cursor cursor = db.query("users", null, selection, selectionArgs, null, null, null);
        boolean exists = cursor.moveToFirst();

        // Close the cursor and database connection
        cursor.close();
        db.close();

        // Return true if the ID exists, false otherwise
        return exists;
    }
    public void deleteUser(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = "id=?";
        String[] selectionArgs = {String.valueOf(id)};
        db.delete("users", selection, selectionArgs);
        db.close();
    }

    public void updateUser(int id, String newFirstName, String newLastName, String newEmail) {
        try{
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("first_name", newFirstName);
            values.put("last_name", newLastName);
            values.put("email", newEmail);
            String selection = "id=?";
            String[] selectionArgs = {String.valueOf(id)};
            db.update("users", values, selection, selectionArgs);
            db.close();
        }catch (Error e){
            Log.e("TAG", "updateUser: ", e);
        }

    }



}
