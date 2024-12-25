package com.example.fishtrack.activities.home.fragments.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishtrack.R;
import com.example.fishtrack.databinding.FragmentFoodBinding;
import com.example.fishtrack.shared.fragments.DropDownFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FoodFragment extends Fragment {
    private DropDownFragment dropDownFragment;
    private String[] itemsDropDown = {"Setor1", "Setor2", "Setor3", "Setor4"};
    private ListView listView;
    private FragmentFoodBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dropDownFragment = new DropDownFragment(this.itemsDropDown);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.drop_down_fragment, dropDownFragment);
        fragmentTransaction.commit();

        FoodViewModel notificationsViewModel =
                new ViewModelProvider(this).get(FoodViewModel.class);

        binding = FragmentFoodBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = root.findViewById(R.id.list_horario);

        List<HashMap<String, String>> listData = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            HashMap<String, String> map = new HashMap<>();
            String horario = String.format("%02d:%02d %s", (int) (Math.random() * 12 + 1), (int) (Math.random() * 60), Math.random() > 0.5 ? "AM" : "PM");
            map.put("title", horario);
            map.put("subtitle", "Refeição " + i);
            listData.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(),
                listData,
                android.R.layout.simple_list_item_2,
                new String[]{"title", "subtitle"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        listView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
