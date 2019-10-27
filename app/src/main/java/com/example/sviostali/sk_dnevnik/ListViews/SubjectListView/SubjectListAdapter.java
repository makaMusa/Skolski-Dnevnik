package com.example.sviostali.sk_dnevnik.ListViews.SubjectListView;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.example.sviostali.sk_dnevnik.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubjectListAdapter extends BaseAdapter {

    public List<String> list;
    private List<SubjectList> subjectList;
    public Context context;

    public SubjectListAdapter(Context c){
        context = c;
        list = Arrays.asList(context.getResources().getStringArray(R.array.subjects));
        Resources res = context.getResources();
        String[] subjects = res.getStringArray(R.array.subjects);

        subjectList = new ArrayList<>();

        for(int i = 0;i < list.size();i++){
            subjectList.add(new SubjectList(subjects[i]));
        }
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
        View row = layoutInflater.inflate(R.layout.subject_list, viewGroup, false);

        TextView subject = row.findViewById(R.id.tvSubject);
        CheckBox isMarked = row.findViewById(R.id.cbIsMarked);

        final SubjectList temp = subjectList.get(position);

        subject.setText(temp.subject);
        isMarked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (temp.isMarked()){
                    temp.setMarked(false);
                } else {
                    temp.setMarked(true);
                }
            }
        });
        return row;
    }

    public List<SubjectList> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<SubjectList> subjectList) {
        this.subjectList = subjectList;
    }
}
