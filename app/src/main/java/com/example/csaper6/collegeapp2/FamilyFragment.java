package com.example.csaper6.collegeapp2;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.example.csaper6.collegeapp2.Person.Person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;


public class FamilyFragment extends ListFragment {
    View rootView;
    FloatingActionButton floatingActionButtonaddNewPerson;
    ListView listView;
    List<Person> family;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        wireWidget();
        displayFamilyElements();
        MainActivity.fragmentArrayList.add(FamilyFragment.this);
        return rootView;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Person person = family.get(position);
        String fname = person.getfName();
                // ((TextView) v.findViewById(R.id.person_adapter_textview_fname)).getText().toString();
        String lname =  person.getlName();
                //((TextView) v.findViewById(R.id.person_adapter_textview_lastname)).getText().toString();
        String age = person.getage();
                //((TextView) v.findViewById(R.id.person_adapter_dateofbirth)).getText().toString();
        String job = person.getJob();
                //((TextView) v.findViewById(R.id.person_adapter_textview_job)).getText().toString();
        String objectId = person.getObjectId();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main,new CreateNewPersonFragment(fname,lname,age,job,objectId)).commit();

    }

    private void displayFamilyElements() {
        Backendless.Persistence.of("Guardian").find(new AsyncCallback<BackendlessCollection<Map>>() {
            @Override
            public void handleResponse(BackendlessCollection<Map> response) {
                family=new ArrayList<Person>();
                for (int i = 0; i < response.getData().size(); i ++){
                    Map temp = response.getData().get(i);
                    Person person = new Person(temp.get("age").toString(),temp.get("FName").toString(),temp.get("LName").toString(),temp.get("occupation").toString(),temp.get("objectId").toString());
                    family.add(i, person);
                }
                PersonAdapter personAdapter = new PersonAdapter(getActivity(), family);
                setListAdapter(personAdapter);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getActivity(), fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void wireWidget() {
        listView = (ListView)rootView.findViewById(R.id.fragment_family_listView);
        floatingActionButtonaddNewPerson=MainActivity.floatingActionButtonAdd;
        floatingActionButtonaddNewPerson.show();
        floatingActionButtonaddNewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CreateNewPersonFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main,fragment).commit();
            }
        });
    }


}