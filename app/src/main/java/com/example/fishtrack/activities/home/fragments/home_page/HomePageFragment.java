package com.example.fishtrack.activities.home.fragments.home_page;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishtrack.R;
import com.example.fishtrack.shared.fragments.DropDownFragment;
import com.example.fishtrack.databinding.FragmentHomePageBinding;
import com.example.fishtrack.shared.utils.ObjUtils;
import com.example.fishtrack.shared.utils.StyleUtils;

public class HomePageFragment extends Fragment {

    private Boolean showImage = true;
    private Button button;
    private Button button2;
    private FragmentHomePageBinding binding;
    private DropDownFragment dropDownFragment;
    private String[] itemsDropDown = {"Setor1","Setor2","Setor3","Setor4"};
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        HomePageViewModel homeViewModel = new ViewModelProvider(this).get(HomePageViewModel.class);

        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        button = new Button(getActivity());
        button2 = new Button(getActivity());
        View root = binding.getRoot();

        StyleUtils styleUtils = new StyleUtils();

        styleUtils.setBackground("#94a3b8",20f,2,"#000000");

        styleUtils.setStyleInElement(root.findViewById(R.id.panel_img));

        GradientDrawable circle1 = circleShape(Color.parseColor("#afaeb5"));
        GradientDrawable circle2 = circleShape(Color.parseColor("#e7e6ed"));

        button.setId(View.generateViewId());
        button.setBackground(circle1);


        button2.setId(View.generateViewId());
        button2.setBackground(circle2);


        ConstraintLayout parentConstraint = root.findViewById(R.id.test);

        parentConstraint.addView(button);
        parentConstraint.addView(button2);

        ConstraintSet constraintSet1 = new ConstraintSet();
        constraintSet1.connect(button.getId(), ConstraintSet.TOP, parentConstraint.getId(), ConstraintSet.TOP);
        constraintSet1.connect(button.getId(), ConstraintSet.START, parentConstraint.getId(), ConstraintSet.START);
        constraintSet1.connect(button.getId(), ConstraintSet.END, button2.getId(), ConstraintSet.START,10);
        constraintSet1.connect(button.getId(), ConstraintSet.BOTTOM, parentConstraint.getId(), ConstraintSet.BOTTOM);
        constraintSet1.constrainWidth(button.getId(), 20);
        constraintSet1.constrainHeight(button.getId(), 20);
        constraintSet1.createHorizontalChain(
            parentConstraint.getId(), ConstraintSet.LEFT,
            parentConstraint.getId(), ConstraintSet.RIGHT,
            new int[]{button.getId(), button2.getId()},
            null,
            ConstraintSet.CHAIN_PACKED
        );
        constraintSet1.applyTo(parentConstraint);

        ConstraintSet constraintSet2 = new ConstraintSet();
        constraintSet2.connect(button2.getId(), ConstraintSet.TOP, parentConstraint.getId(), ConstraintSet.TOP);
        constraintSet2.connect(button2.getId(), ConstraintSet.START, button.getId(), ConstraintSet.END,10);
        constraintSet2.connect(button2.getId(), ConstraintSet.END, parentConstraint.getId(), ConstraintSet.END);
        constraintSet2.connect(button2.getId(), ConstraintSet.BOTTOM, parentConstraint.getId(), ConstraintSet.BOTTOM);
        constraintSet2.constrainWidth(button2.getId(), 20);
        constraintSet2.constrainHeight(button2.getId(), 20);
        constraintSet2.applyTo(parentConstraint);



        dropDownFragment = new DropDownFragment(itemsDropDown);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dropdown_menu,dropDownFragment);
        fragmentTransaction.commit();

        root.findViewById(R.id.tanque_img).setOnClickListener(v -> {
            button.setBackground(circle2);
            button2.setBackground(circle1);
            root.findViewById(R.id.tanque_img).setVisibility(View.GONE);
            root.findViewById(R.id.info_data).setVisibility(View.VISIBLE);
        });

        root.findViewById(R.id.info_data).setOnClickListener(v -> {
            button.setBackground(circle1);
            button2.setBackground(circle2);
            root.findViewById(R.id.tanque_img).setVisibility(View.VISIBLE);
            root.findViewById(R.id.info_data).setVisibility(View.GONE);
        });

        return root;
    }

    public GradientDrawable circleShape(int color){
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setColor(color);
        shape.setStroke(2,Color.parseColor("#000000"));
        return shape;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}