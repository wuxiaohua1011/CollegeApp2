package com.example.csaper6.collegeapp2;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
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

        listView = (ListView)rootView.findViewById(R.id.fragment_family_listView);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Do you want to edit this person?");
                alertDialogBuilder.setCancelable(true).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

                return false;
            }
        });
        return rootView;
    }

    private void displayFamilyElements() {
        Backendless.Persistence.of("Guardian").find(new AsyncCallback<BackendlessCollection<Map>>() {
            @Override
            public void handleResponse(BackendlessCollection<Map> response) {
                family=new ArrayList<Person>();
                for (int i = 0; i < response.getData().size(); i ++){
                    Map temp = response.getData().get(i);
                    Person person = new Person(temp.get("age").toString(),temp.get("FName").toString(),temp.get("LName").toString(),temp.get("occupation").toString());
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