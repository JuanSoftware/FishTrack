package com.example.fishtrack.activities.home.fragments.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishtrack.R;
import com.example.fishtrack.databinding.FragmentFoodBinding;


public class FoodFragment extends Fragment {

    private FragmentFoodBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FoodViewModel dashboardViewModel =
                new ViewModelProvider(this).get(FoodViewModel.class);

        binding = FragmentFoodBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        WebView temperWebView = root.findViewById(R.id.web_view_temper);
        WebView phWebView = root.findViewById(R.id.web_view_ph);

        String graphTemper = "<iframe src=\"http://192.168.31.231:5001/embed/query/1/visualization/2?api_key=nWRtSezW08fxLS6tE5Tv4lB7VXLVOhrnaQovHZ0V&\" width=\"720\" height=\"391\"></iframe>";

        String graphPh = "<iframe src=\"http://192.168.31.231:5001/embed/query/1/visualization/3?api_key=nWRtSezW08fxLS6tE5Tv4lB7VXLVOhrnaQovHZ0V&\" width=\"720\" height=\"391\"></iframe>";

        temperWebView.loadData(graphTemper, "text/html","utf-8");

        phWebView.loadData(graphPh, "text/html","utf-8");

        temperWebView.getSettings().setJavaScriptEnabled(true);

        phWebView.getSettings().setJavaScriptEnabled(true);

        temperWebView.setWebChromeClient(new WebChromeClient());

        phWebView.setWebChromeClient(new WebChromeClient());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}