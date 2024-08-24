package com.example.sqlite;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentListActivity extends Activity {

    ListView listView;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        listView = findViewById(R.id.listView);
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);

        ArrayList<String> studentList = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM student", null);
        if (c.getCount() == 0) {
            studentList.add("No records found");
        } else {
            while (c.moveToNext()) {
                String record = "Rollno: " + c.getString(0) + "\n" +
                        "Name: " + c.getString(1) + "\n" +
                        "Marks: " + c.getString(2);
                studentList.add(record);
            }
        }
        c.close();  // Close the cursor to release resources

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        listView.setAdapter(adapter);
    }
}
