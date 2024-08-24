package com.example.sqlite;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
    EditText Rollno, Name, Marks;
    Button Insert, Delete, Update, View, ViewAll;
    SQLiteDatabase db;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rollno = findViewById(R.id.Rollno);
        Name = findViewById(R.id.Name);
        Marks = findViewById(R.id.Marks);
        Insert = findViewById(R.id.Insert);
        Delete = findViewById(R.id.Delete);
        Update = findViewById(R.id.Update);
        View = findViewById(R.id.View);
        ViewAll = findViewById(R.id.ViewAll);

        Insert.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Update.setOnClickListener(this);
        View.setOnClickListener(this);
        ViewAll.setOnClickListener(this);

        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR, name VARCHAR, marks VARCHAR);");
    }

    public void onClick(View view) {
        if (view == Insert) {
            if (Rollno.getText().toString().trim().isEmpty() ||
                    Name.getText().toString().trim().isEmpty() ||
                    Marks.getText().toString().trim().isEmpty()) {
                showMessage("Error", "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO student VALUES('" + Rollno.getText() + "','" + Name.getText() +
                    "','" + Marks.getText() + "');");
            showMessage("Success", "Record added");
            clearText();
        }

        if (view == Delete) {
            if (Rollno.getText().toString().trim().isEmpty()) {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + Rollno.getText() + "'", null);
            if (c.moveToFirst()) {
                db.execSQL("DELETE FROM student WHERE rollno='" + Rollno.getText() + "'");
                showMessage("Success", "Record Deleted");
            } else {
                showMessage("Error", "Invalid Rollno");
            }
            c.close();
            clearText();
        }

        // Updating a record in the Student table
        if (view == Update) {
            // Checking for empty roll number
            if (Rollno.getText().toString().trim().isEmpty()) {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + Rollno.getText() + "'", null);
            if (c.moveToFirst()) {
                db.execSQL("UPDATE student SET name='" + Name.getText() + "', marks='" + Marks.getText() +
                        "' WHERE rollno='" + Rollno.getText() + "'");
                showMessage("Success", "Record Modified");
            } else {
                showMessage("Error", "Invalid Rollno");
            }
            c.close();  // Close the cursor to release resources
            clearText();
        }

        // Display a record from the Student table
        if (view == View) {
            // Checking for empty roll number
            if (Rollno.getText().toString().trim().isEmpty()) {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + Rollno.getText() + "'", null);
            if (c.moveToFirst()) {
                Name.setText(c.getString(1));
                Marks.setText(c.getString(2));
            } else {
                showMessage("Error", "Invalid Rollno");
                clearText();
            }
            c.close();  // Close the cursor to release resources
        }

        // Displaying all the records
        if (view == ViewAll) {
            Intent intent = new Intent(this, StudentListActivity.class);
            startActivity(intent);
        }
    }

    public void showMessage(String title, String message) {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText() {
        Rollno.setText("");
        Name.setText("");
        Marks.setText("");
        Rollno.requestFocus();
    }
}
