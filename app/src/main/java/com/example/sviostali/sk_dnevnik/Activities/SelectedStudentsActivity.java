package com.example.sviostali.sk_dnevnik.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.sviostali.sk_dnevnik.ListViews.SelectedStudentsListView.SelectedStudentsAdapter;
import com.example.sviostali.sk_dnevnik.R;
import com.orm.SugarContext;

public class SelectedStudentsActivity extends AppCompatActivity {

    public ListView lvSelectedStudents;
    public SelectedStudentsAdapter SStudent;
    public long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_selected_students);
        Bundle s = getIntent().getExtras();
        id = s.getLong("id");
        SStudent = new SelectedStudentsAdapter(this, id);

        lvSelectedStudents = (ListView) findViewById(R.id.lvSelectedStudents);
        lvSelectedStudents.setAdapter(SStudent);

    }
}
