package com.example.sviostali.sk_dnevnik.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.example.sviostali.sk_dnevnik.ListViews.MarksListView.MarksAdapter2;
import com.example.sviostali.sk_dnevnik.R;
import com.example.sviostali.sk_dnevnik.sugarclasses.studentsubject;

public class StudentMarks extends AppCompatActivity {

    public TextView tvFinalMark, tvAbsence;
    public ListView lvMarks;
    public studentsubject stud_sub;
    public long id;
    public MarksAdapter2 Madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_marks);

        Bundle s = getIntent().getExtras();

        id = s.getLong("id");

        stud_sub = studentsubject.findById(studentsubject.class, id);

        tvFinalMark = findViewById(R.id.tvSMfinalmark);
        tvAbsence = findViewById(R.id.tvSMabsence);
        lvMarks = findViewById(R.id.lvSMmarks);
        Madapter = new MarksAdapter2(this, id);
        lvMarks.setAdapter(Madapter);

        if(stud_sub.getFinalmark()!=0) {
            tvFinalMark.setText("Zaključna ocjena: "+ stud_sub.getFinalmark());
        }else{
            tvFinalMark.setText("Nije zaljučeno");
        }
        tvAbsence.setText("Broj izostanaka: "+ stud_sub.getAbsence());
    }
}
