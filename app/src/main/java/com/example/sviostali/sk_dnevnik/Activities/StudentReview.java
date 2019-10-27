package com.example.sviostali.sk_dnevnik.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.sviostali.sk_dnevnik.ListViews.MarksListView.MarksAdapter;
import com.example.sviostali.sk_dnevnik.R;
import com.example.sviostali.sk_dnevnik.sugarclasses.marks;
import com.example.sviostali.sk_dnevnik.sugarclasses.studentsubject;
import com.orm.SugarContext;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class StudentReview extends AppCompatActivity {

    public TextView tvName, tvAverage, tvFinalMark, tvBirthDate, tvAbsence;
    public Button btnSetMark, btnSetFinalMark, btnAutoSetFinalMark, btnRmvAbsence,btnAddAbsence;
    public ImageView ivAvatar;
    public long id;
    public float avg, sum=0;
    public studentsubject stud_sub;
    public marks mark;
    public int setMark;
    public ListView lvMarks;
    public MarksAdapter MAdapter;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_student_review);

        tvName = findViewById(R.id.tvURname);
        tvAverage = findViewById(R.id.tvURaverage);
        tvFinalMark = findViewById(R.id.tvURFinalMark);
        tvBirthDate = findViewById(R.id.tvURbirthdate);
        tvAbsence = findViewById(R.id.tvSRabsence);

        btnSetMark = findViewById(R.id.btnURsetMark);
        btnSetFinalMark = findViewById(R.id.btnURsetFinalMark);
        btnAutoSetFinalMark = findViewById(R.id.btnURautosetFinalMark);
        btnRmvAbsence = findViewById(R.id.btnSRremoveAbsence);
        btnAddAbsence = findViewById(R.id.btnSRaddAbsence);

        ivAvatar = findViewById(R.id.ivSRavatar);

        Bundle s = getIntent().getExtras();

        id = s.getLong("id");
        stud_sub = studentsubject.findById(studentsubject.class, id);

        tvAbsence.setText(stud_sub.getAbsence()+"");

        MAdapter = new MarksAdapter(StudentReview.this, id);
        lvMarks =  findViewById(R.id.lvSRmarks);
        lvMarks.setAdapter(MAdapter);

        tvName.setText(stud_sub.getUser().getLogin());
        tvBirthDate.setText(stud_sub.getUser().getBirthdate());

        Glide.with(this)
                .load(stud_sub.getUser().getAvatar())
                .into(ivAvatar);

        if(stud_sub.getFinalmark()>0){
            tvFinalMark.setText("Zaključna ocjena je: "+stud_sub.getFinalmark()+"");
        }
        getAverage();

        //unos ocijene
        btnSetMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(StudentReview.this);
                builder.setTitle("Ocjena:");
                final EditText input = new EditText(StudentReview.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setMark = Integer.parseInt(input.getText().toString());
                        if(setMark<1||setMark>5){
                            Toast.makeText(StudentReview.this, "Unesite ocjenu u rasponu 1-5", Toast.LENGTH_SHORT).show();
                        }else{
                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            String strDate = sdf.format(c.getTime());
                            Toast.makeText(StudentReview.this, "Ocjenili ste s ocjenom: "+setMark, Toast.LENGTH_SHORT).show();
                            mark = new marks(stud_sub, setMark, strDate, 0);
                            mark.save();
                            getAverage();
                            MAdapter = new MarksAdapter(StudentReview.this, id);
                            lvMarks = findViewById(R.id.lvSRmarks);
                            lvMarks.setAdapter(MAdapter);
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        //zakljucivanje
        btnSetFinalMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(StudentReview.this);
                builder2.setTitle("Zakljucna ocjena:");
                final EditText input2 = new EditText(StudentReview.this);
                input2.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder2.setView(input2);
                builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setMark = Integer.parseInt(input2.getText().toString());
                        if(setMark<0||setMark>5){
                            Toast.makeText(StudentReview.this, "Unesite ocjenu u rasponu 1-5", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(StudentReview.this, "Zakljucili ste ocjenu: "+setMark, Toast.LENGTH_SHORT).show();
                            stud_sub.setFinalmark(setMark);
                            stud_sub.save();
                            tvFinalMark.setText("Zaključna ocjena je: "+setMark);
                        }
                        dialog.dismiss();
                    }
                });
                builder2.setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });
                builder2.show();
            }
        });

        btnAutoSetFinalMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = Math.round(avg);
                Toast.makeText(StudentReview.this, "Automatski zaključena ocjena je: "+tmp, Toast.LENGTH_SHORT).show();
                stud_sub.setFinalmark(tmp);
                stud_sub.save();
                tvFinalMark.setText("Zaključna ocjena je: "+tmp);
            }
        });

        btnAddAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = stud_sub.getAbsence();
                tmp = tmp+1;
                stud_sub.setAbsence(tmp);
                stud_sub.save();
                tvAbsence.setText(tmp+"");
            }
        });

        btnRmvAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = stud_sub.getAbsence();
                tmp = tmp-1;
                if(tmp>=0) {
                    stud_sub.setAbsence(tmp);
                    stud_sub.save();
                    tvAbsence.setText(tmp + "");
                }
            }
        });
    }

    private void getAverage() {
        List<marks> list = stud_sub.getMarks();
        sum=0;
        if(list.isEmpty()){
            tvAverage.setText("Trebate više ocjena za računanje prosjeka.");
        }else{
            for(int i = 0;i<list.size();i++){
                sum+=list.get(i).getMark();
            }
            avg = sum/list.size();
            tvAverage.setText("Vaš prosjek je: " + avg);
        }
    }
}
