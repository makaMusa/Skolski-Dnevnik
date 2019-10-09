package com.example.sviostali.sk_dnevnik.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.sviostali.sk_dnevnik.ListViews.StudentsSubjectList.StudentsSubjectAdapter;
import com.example.sviostali.sk_dnevnik.R;

public class StudentsSubjects extends AppCompatActivity {

    public ListView lvStudentsSubjets;
    public int id;
    public StudentsSubjectAdapter SSadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_subjects);

        Bundle s = getIntent().getExtras();
        id = s.getInt("id");
        lvStudentsSubjets = (ListView) findViewById(R.id.lvStudentsSubjects);
        SSadapter = new StudentsSubjectAdapter(this,id);
        lvStudentsSubjets.setAdapter(SSadapter);
    }
}
