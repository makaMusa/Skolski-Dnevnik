package com.example.sviostali.sk_dnevnik.ListViews.MarksListView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.sviostali.sk_dnevnik.Activities.StudentReview;
import com.example.sviostali.sk_dnevnik.R;
import com.example.sviostali.sk_dnevnik.sugarclasses.marks;
import com.example.sviostali.sk_dnevnik.sugarclasses.studentsubject;
import java.util.List;

public class MarksAdapter extends BaseAdapter {

    public List<marks> list;
    public studentsubject stud_sub;
    public marks mark;

    Context context;
    public long id;

    public MarksAdapter(Context context, long id) {
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
        final marks tmpmarks = mark;

        tvDate.setText(mark.getDate());
        tvMark.setText(mark.getMark()+"");

        row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Brisanje ocjene");
                builder.setMessage("Da li ste sigurni da zelite izbrisati ovu ocjenu?  " + tmpmarks.getDate() + "  -  " + tmpmarks.getMark() );
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        tmpmarks.delete();
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Intent intent = new Intent(context, StudentReview.class);
                        intent.putExtra("id",id);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });
        return row;
    }
}
