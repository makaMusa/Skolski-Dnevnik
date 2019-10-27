package com.example.sviostali.sk_dnevnik.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.sviostali.sk_dnevnik.ListViews.SelectedSubjectListView.SelectedSubjectListAdapter;
import com.example.sviostali.sk_dnevnik.R;
import com.orm.SugarContext;

public class SelectedSubjectsActivity extends AppCompatActivity {

    public ListView lvSSubjects;
    public SelectedSubjectListAdapter SSAdapter;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_selected_subjects);

        Bundle s = getIntent().getExtras();
        id = s.getInt("id");
        SSAdapter = new SelectedSubjectListAdapter(getApplicationContext(), id);

        lvSSubjects = findViewById(R.id.lvSelectedSubjects);
        lvSSubjects.setAdapter(SSAdapter);
    }
}
