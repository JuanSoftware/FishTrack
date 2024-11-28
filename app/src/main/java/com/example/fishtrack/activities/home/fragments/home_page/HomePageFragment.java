package com.example.fishtrack.activities.home.fragments.home_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishtrack.R;
import com.example.fishtrack.activities.shared.frames.DropDownFragment;
import com.example.fishtrack.databinding.FragmentHomePageBinding;

public class HomePageFragment extends Fragment {

    private FragmentHomePageBinding binding;
    private DropDownFragment dropDownFragment;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomePageViewModel homeViewModel =
                new ViewModelProvider(this).get(HomePageViewModel.class);

        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        dropDownFragment = new DropDownFragment();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dropdown_menu,dropDownFragment);
        fragmentTransaction.commit();
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}