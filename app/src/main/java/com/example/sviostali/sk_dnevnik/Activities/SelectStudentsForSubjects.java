package com.example.sviostali.sk_dnevnik.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.sviostali.sk_dnevnik.ListViews.UsersListAdapter.UserListAdapter;
import com.example.sviostali.sk_dnevnik.R;
import com.example.sviostali.sk_dnevnik.sugarclasses.studentsubject;
import com.example.sviostali.sk_dnevnik.sugarclasses.subjects;
import com.example.sviostali.sk_dnevnik.sugarclasses.usersugar;
import com.orm.SugarContext;
import java.util.List;

public class SelectStudentsForSubjects extends AppCompatActivity {

    public Button bAccept;
    public ListView lvStudents;
    public UserListAdapter userListAdapter;
    long id;
    subjects sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_subject_list);

        userListAdapter = new UserListAdapter(getApplicationContext());

        bAccept = findViewById(R.id.bAccept);
        lvStudents = findViewById(R.id.lvSubjects);
        lvStudents.setAdapter(userListAdapter);

        Bundle s = getIntent().getExtras();
        id = s.getLong("id");
        sub = subjects.findById(subjects.class,id);

        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usersugar user = usersugar.findById(usersugar.class, id);

                studentsubject stsub;
                for(int i=0;i<userListAdapter.getUserList().size();i++){

                    if(userListAdapter.getUserList().get(i).isMarked()){
                        List<studentsubject> allStudentSubject = studentsubject.listAll(studentsubject.class);

                        int check=0;
                        for(int j=0; j<allStudentSubject.size(); j++){
                            String a = allStudentSubject.get(j).getSubject().getName();
                            long b = allStudentSubject.get(j).getUser().getId();
                            if(userListAdapter.getSUser(i).getId()==b && a.equals(sub.getName())){
                                check=1;
                            }
                        }
                        if(check==0){
                            stsub = new studentsubject(userListAdapter.getSUser(i), sub, 0, 0);
                            stsub.save();
                        }
                    }
                }
            finish();
            }
        });
    }
}