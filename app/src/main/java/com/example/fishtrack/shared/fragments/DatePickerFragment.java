//package com.example.fishtrack.shared.fragments;
//
//
//import android.os.Bundle;
//import android.text.InputType;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Toast;
//
//import androidx.fragment.app.Fragment;
//
//import com.example.fishtrack.R;
//import com.example.fishtrack.databinding.FragmentDatePickerBinding;
//import com.example.fishtrack.databinding.FragmentDropDownBinding;
//import com.google.android.material.datepicker.MaterialDatePicker;
//
//
//
//
//public class DatePickerFragment extends Fragment {
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        FragmentDatePickerBinding binding;
//        binding = FragmentDatePickerBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder
//                .datePicker()
//                .setTitleText("Selecione uma data")
//                .setTheme(com.google.android.material.R.style.)
//                .build();
//
//        // Mostrar o DatePicker
//        root.findViewById(R.id.button_open_date_picker).setOnClickListener(view -> datePicker.show(getActivity().getSupportFragmentManager(), "DATE_PICKER"));
//
//        // Lidar com a seleção de data
//        datePicker.addOnPositiveButtonClickListener(selection -> {
//            // `selection` é o timestamp da data selecionada (em milissegundos)
//            Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
//        });
//        return root;
//    }
//
//}
