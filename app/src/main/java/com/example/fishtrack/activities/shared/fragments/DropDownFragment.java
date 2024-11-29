package com.example.fishtrack.activities.shared.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.fishtrack.R;

public class DropDownFragment extends Fragment {

    String[] items = {"setor1","setor2","setor3","setor4","setor5"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;


    public DropDownFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View viewFragment = inflater.inflate(R.layout.fragment_drop_down, container, false);

        autoCompleteTextView = viewFragment.findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.list_item, items);
        autoCompleteTextView.setInputType(InputType.TYPE_NULL);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
            }
        });
        return viewFragment;
    }
}