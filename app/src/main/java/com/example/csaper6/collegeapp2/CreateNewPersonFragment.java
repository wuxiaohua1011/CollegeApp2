package com.example.csaper6.collegeapp2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.HashMap;
import java.util.Map;


public class CreateNewPersonFragment extends Fragment {
    FloatingActionButton floatingActionButton;
    EditText editTextfName,editTextlname,editTextage,editTextjob;
    Button buttonSubmit;
    Spinner spinnerRelationship;
    View rootView;

    String stringeditTextfName,  stringeditTextlname,stringeditTextage, stringeditTextjob;

    public CreateNewPersonFragment() {
    }

    @SuppressLint("ValidFragment")
    public CreateNewPersonFragment(String editTextfName, String editTextlname, String editTextage, String editTextjob) {
        this.stringeditTextfName=editTextfName;
        this.stringeditTextlname= editTextlname;
        this.stringeditTextage = editTextage;
        this.stringeditTextjob = editTextjob;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_new_person, container, false);
        MainActivity.fragmentArrayList.add(CreateNewPersonFragment.this);
        wireWidget();
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (checkEmpty()){
                  HashMap hashMap = new HashMap();
                  hashMap.put("age",editTextage.getText().toString());
                  hashMap.put("FName",editTextfName.getText().toString());
                  hashMap.put("LName",editTextlname.getText().toString());
                  hashMap.put("occupation",editTextjob.getText().toString());

                  Backendless.Persistence.of("Guardian").save(hashMap, new AsyncCallback<Map>() {
                      @Override
                      public void handleResponse(Map response) {
                          Toast.makeText(getActivity(), "Data Uploaded", Toast.LENGTH_SHORT).show();
                          Fragment fragment = new FamilyFragment();
                          FragmentManager fragmentManager = getFragmentManager();
                          fragmentManager.beginTransaction().replace(R.id.content_main,fragment).commit();
                      }

                      @Override
                      public void handleFault(BackendlessFault fault) {
                          Toast.makeText(getActivity(), fault.getMessage(), Toast.LENGTH_SHORT).show();
                      }
                  });
              }
                else{
                  Toast.makeText(getActivity(), "Please Make Sure You filled All spaces", Toast.LENGTH_SHORT).show();
              }
            }
        });
        return rootView;
    }

    private boolean checkEmpty() {
        boolean temp1 = editTextfName.getText().toString().equals("");
        boolean temp2 = editTextlname.getText().toString().equals("");
        boolean temp3 = editTextage.getText().toString().equals("");
        boolean temp4 = editTextjob.getText().toString().equals("");

        return !(temp1 && temp2 && temp3 && temp4);
    }

    private void wireWidget() {
        floatingActionButton = MainActivity.floatingActionButtonAdd;
        floatingActionButton.hide();

        editTextage = (EditText)rootView.findViewById(R.id.fragment_create_new_person_edittext_age);
        editTextfName=(EditText)rootView.findViewById(R.id.fragment_create_new_person_edittext_personFirstname);
        editTextlname = (EditText)rootView.findViewById(R.id.fragment_create_new_person_edittext_lastname);
        editTextjob = (EditText)rootView.findViewById(R.id.fragment_create_new_person_edittext_job);

        buttonSubmit = (Button)rootView.findViewById(R.id.fragment_create_new_person_button_submit);

        spinnerRelationship = (Spinner)rootView.findViewById(R.id.fragment_create_new_person_spinner_relationship);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.relationship, android.R.layout.simple_spinner_item); // Creating adapter for spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //specify drop-down style
        spinnerRelationship.setAdapter(adapter);

        editTextage.setText(stringeditTextage);
        editTextfName.setText(stringeditTextfName);
        editTextlname.setText(stringeditTextlname );
        editTextjob.setText(stringeditTextjob);


    }

    public EditText getEditTextfName() {
        return editTextfName;
    }

    public EditText getEditTextlname() {
        return editTextlname;
    }

    public EditText getEditTextage() {
        return editTextage;
    }

    public EditText getEditTextjob() {
        return editTextjob;
    }
}
