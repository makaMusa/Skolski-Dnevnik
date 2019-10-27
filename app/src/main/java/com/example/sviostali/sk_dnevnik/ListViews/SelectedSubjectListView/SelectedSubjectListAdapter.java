package com.example.sviostali.sk_dnevnik.ListViews.SelectedSubjectListView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.sviostali.sk_dnevnik.Activities.SelectedSubject;
import com.example.sviostali.sk_dnevnik.R;
import com.example.sviostali.sk_dnevnik.sugarclasses.subjects;
import com.example.sviostali.sk_dnevnik.sugarclasses.usersugar;
import java.util.List;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SelectedSubjectListAdapter extends BaseAdapter {

    public subjects sub;
    public List<subjects> list;
    public Context context;

    public usersugar us;

    public int id;

    public SelectedSubjectListAdapter(Context c, int id) {
        this.context = c;
        us = usersugar.findById(usersugar.class,id);
        list = us.getSubjects();
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
    public View getView(int position, final View convertView, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.selected_subject_list, viewGroup, false);

        TextView subject = row.findViewById(R.id.tvSelectedSubject);

        sub = list.get(position);
        subject.setText(sub.getName());
        final long id = sub.getId();
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , SelectedSubject.class);
                i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("id", id);
                context.startActivity(i);
            }
        });
        return row;
    }
}
