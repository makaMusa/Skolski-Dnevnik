package com.example.sviostali.sk_dnevnik.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.sviostali.sk_dnevnik.R;
import com.example.sviostali.sk_dnevnik.ListViews.SubjectListView.SubjectList;
import com.example.sviostali.sk_dnevnik.ListViews.SubjectListView.SubjectListAdapter;
import com.orm.SugarContext;
import com.example.sviostali.sk_dnevnik.sugarclasses.subjects;
import com.example.sviostali.sk_dnevnik.sugarclasses.usersugar;
import java.util.List;

public class SubjectListActivity extends AppCompatActivity {

    public ListView lvSubjects;
    public Button bAccept;
    public SubjectListAdapter subjectListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_subject_list);

        subjectListAdapter = new SubjectListAdapter(getApplicationContext());

        bAccept = (Button) findViewById(R.id.bAccept);
        lvSubjects = (ListView) findViewById(R.id.lvSubjects);
        lvSubjects.setAdapter(subjectListAdapter);

        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle s = getIntent().getExtras();

                int id = s.getInt("id");
                subjects sub;
                usersugar user = usersugar.findById(usersugar.class, id);
                for (SubjectList sl : subjectListAdapter.getSubjectList()){
                    if(sl.isMarked()){
                        List<subjects> allSubjects = subjects.listAll(subjects.class);

                        //Provjerava da li postoji jos ijedan predmet-profesor par u bazi podataka
                        //Ako postoji, da taj profesor ne moze predavat isti predmet 2 puta
                        int check=0;
                        for(int i=0; i<allSubjects.size(); i++){
                            String a = allSubjects.get(i).getName();
                            long b = allSubjects.get(i).getUser().getId();
                            if(sl.getSubject().equals(a) && id == b){
                                check=1;
                            }
                        }
                        if(check==0){
                            sub = new subjects(sl.getSubject(), user);
                            sub.save();
                        }
                    }
                }
                finish();
            }
        });
    }
}
