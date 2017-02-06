package com.example.csaper6.collegeapp2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.csaper6.collegeapp2.Person.Person;

import org.w3c.dom.Text;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person>{

    public PersonAdapter(Context context, List<Person> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_person_adapter,null);
        }
        Person person = getItem(position);

        TextView fNametextView = (TextView)convertView.findViewById(R.id.person_adapter_textview_fname);
        TextView lNametextView = (TextView)convertView.findViewById(R.id.person_adapter_textview_lastname);
        TextView jobtextView = (TextView)convertView.findViewById(R.id.person_adapter_textview_job);
        TextView dateTextView = (TextView)convertView.findViewById(R.id.person_adapter_dateofbirth);

        fNametextView.setText(person.getfName());
        lNametextView.setText(person.getlName());
        jobtextView.setText(person.getJob());
        dateTextView.setText(person.getDateOfBirth().toString());

        return convertView;
    }
}
