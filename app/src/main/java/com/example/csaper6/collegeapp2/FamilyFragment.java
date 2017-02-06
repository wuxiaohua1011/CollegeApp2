package com.example.csaper6.collegeapp2;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.csaper6.collegeapp2.Person.Person;

import java.util.List;


public class FamilyFragment extends ListFragment {
    View rootView;
    FloatingActionButton floatingActionButtonaddNewPerson;
    ListView listView;
    List<Person> family;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_family, container, false);
        wireWidget();
        displayFamilyElements();
        Toast.makeText(getActivity(), "FamilyFragmentStarted", Toast.LENGTH_SHORT).show();
        return rootView;
    }

    private void displayFamilyElements() {
            if (! (family.size()==0)){
                PersonAdapter personAdapter = new PersonAdapter(getActivity(), family);
                setListAdapter(personAdapter);
            }
    }

    private void wireWidget() {
        listView = (ListView)rootView.findViewById(R.id.fragment_family_listView);
        floatingActionButtonaddNewPerson = (FloatingActionButton)rootView.findViewById(R.id.activity_log_in_floatingActionButton_createNewAccount);
    }
}