package com.example.sviostali.sk_dnevnik.ListViews.MarksListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.sviostali.sk_dnevnik.R;
import com.example.sviostali.sk_dnevnik.sugarclasses.marks;
import com.example.sviostali.sk_dnevnik.sugarclasses.studentsubject;

import java.util.List;

/**
 * Created by svi ostali on 18.6.2017..
 */

public class MarksAdapter2 extends BaseAdapter{

    public List<marks> list;
    public studentsubject stud_sub;
    public marks mark;

    Context context;
    public long id;

    public MarksAdapter2(Context context, long id) {
        this.context = context;
        this.id = id;
        stud_sub = studentsubject.findById(studentsubject.class,id);
        list = stud_sub.getMarks();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.marks_list, viewGroup, false);

        TextView tvDate = (TextView) row.findViewById(R.id.tvMLdate);
        TextView tvMark = (TextView) row.findViewById(R.id.tvMLmark);
        mark = list.get(position);

        tvDate.setText(mark.getDate());
        tvMark.setText(mark.getMark()+"");

        return row;
    }
}