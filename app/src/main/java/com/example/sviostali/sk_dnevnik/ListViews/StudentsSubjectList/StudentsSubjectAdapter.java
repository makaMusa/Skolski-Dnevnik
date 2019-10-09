package com.example.sviostali.sk_dnevnik.ListViews.StudentsSubjectList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.sviostali.sk_dnevnik.Activities.StudentMarks;
import com.example.sviostali.sk_dnevnik.R;
import com.example.sviostali.sk_dnevnik.sugarclasses.subjects;
import com.example.sviostali.sk_dnevnik.sugarclasses.studentsubject;
import com.example.sviostali.sk_dnevnik.sugarclasses.usersugar;
import java.util.List;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class StudentsSubjectAdapter extends BaseAdapter {

    public Context context;
    public long id;
    public List<studentsubject> list;
    public subjects sub;

    public StudentsSubjectAdapter(Context context, long id) {
        this.context = context;
        this.id = id;
        this.list = usersugar.findById(usersugar.class,id).getStudSub();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.selected_subject_list, viewGroup, false);

        TextView tvSubject = (TextView) row.findViewById(R.id.tvSelectedSubject);

        sub = list.get(position).getSubject();

        tvSubject.setText(sub.getName());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , StudentMarks.class);
                i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("id", list.get(position).getId());
                context.startActivity(i);
            }
        });
        return row;
    }
}
