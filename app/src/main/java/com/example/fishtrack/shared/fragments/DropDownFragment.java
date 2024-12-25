package com.example.fishtrack.shared.fragments;

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
import com.example.fishtrack.databinding.FragmentDropDownBinding;

public class DropDownFragment extends Fragment {

    String[] items = {"Setor1", "Setor2", "Setor3", "Setor4"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;

    FragmentDropDownBinding binding;
    public DropDownFragment(){}
    public DropDownFragment(String[] items) {
        this.items = items;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentDropDownBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        autoCompleteTextView = root.findViewById(R.id.auto_complete_txt);
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
        return root;
    }
}