package com.example.fishtrack.activities.home.fragments.records;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishtrack.databinding.FragmentRecordsBinding;

public class RecordsFragment extends Fragment {

    private FragmentRecordsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecordsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(RecordsViewModel.class);

        binding = FragmentRecordsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
<<<<<<< Updated upstream
=======
        WebView temperWebView = root.findViewById(R.id.web_view_temper);
        WebView phWebView = root.findViewById(R.id.web_view_ph);

        String graphTemper = "<iframe src=\"http://146.190.131.25:5001/embed/query/1/visualization/2?api_key=eOBWwEN5y6VAtBs0U96VieZ1ehXWQ7L0CQv4Qcgm&\" width=\"720\" height=\"391\"></iframe>";

        String graphPh = "<iframe src=\"http://146.190.131.25:5001/embed/query/2/visualization/4?api_key=dkSfknCOe1uUy5ia77U7nwDe71HlNWyt5j7htvVp&\" width=\"720\" height=\"391\"></iframe>";

        String encodedHTMLPH = Base64.encodeToString(graphPh.getBytes(), Base64.NO_PADDING);
        String encodedHTMLTemper= Base64.encodeToString(graphTemper.getBytes(), Base64.NO_PADDING);

        temperWebView.getSettings().setDomStorageEnabled(true);
        temperWebView.getSettings().setAllowContentAccess(true);
        temperWebView.getSettings().setMixedContentMode(temperWebView.getSettings().MIXED_CONTENT_ALWAYS_ALLOW);
        temperWebView.getSettings().setLoadsImagesAutomatically(true);
        temperWebView.getSettings().setJavaScriptEnabled(true);

        phWebView.getSettings().setJavaScriptEnabled(true);
        phWebView.getSettings().setDomStorageEnabled(true);
        phWebView.getSettings().setAllowContentAccess(true);
        phWebView.getSettings().setMixedContentMode(temperWebView.getSettings().MIXED_CONTENT_ALWAYS_ALLOW);
        phWebView.getSettings().setLoadsImagesAutomatically(true);

        temperWebView.loadData(encodedHTMLTemper, "text/html","base64");

        phWebView.loadData(encodedHTMLPH, "text/html","base64");

        temperWebView.setWebChromeClient(new WebChromeClient());

        phWebView.setWebChromeClient(new WebChromeClient());


>>>>>>> Stashed changes

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}