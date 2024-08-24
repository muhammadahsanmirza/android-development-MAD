package com.example.adapters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

class CustomListAdapter extends ArrayAdapter<String> {
    private final String[] studentName;
    private final String[] aridNmbr;
    private final Context context;
    private final int[] imageId;

    public CustomListAdapter(Context context, String[] studentName, String[] aridNmbr, int[] imageId) {
        super(context, android.R.layout.simple_list_item_2, studentName);
        this.context = context;
        this.studentName = studentName;
        this.aridNmbr = aridNmbr;
        this.imageId = imageId;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            listItemView = inflater.inflate(R.layout.studentinfo, parent, false);
        }

        ImageView image = listItemView.findViewById(R.id.student_image);
        TextView name = listItemView.findViewById(R.id.name);
        TextView aridNumberTextView = listItemView.findViewById(R.id.aridNumber);

        image.setImageResource(imageId[position]);
        name.setText(studentName[position]);
        aridNumberTextView.setText(aridNmbr[position]);

        return listItemView;
    }
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] studentImage = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six};
        String[] studentName = {"Ahsan", "Hassan", "Haseeb", "Subhan", "Muneeb", "Huzaifa"};
        String[] aridNumber = {"21-arid-737", "21-arid-717", "21-arid-715", "21-arid-690", "21-arid-764", "21-arid-719"};

        CustomListAdapter adapter = new CustomListAdapter(this, studentName, aridNumber, studentImage);

        ListView listView = findViewById(R.id.listview1);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedStudent = studentName[position];
                Toast.makeText(getApplicationContext(), selectedStudent, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
