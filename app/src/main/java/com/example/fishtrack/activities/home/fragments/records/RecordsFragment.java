package com.example.fishtrack.activities.home.fragments.records;

import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishtrack.R;
import com.example.fishtrack.databinding.FragmentFoodBinding;
import com.example.fishtrack.databinding.FragmentRecordsBinding;


public class RecordsFragment extends Fragment {

    private FragmentRecordsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecordsViewModel dashboardViewModel =
                new ViewModelProvider(this).get(RecordsViewModel.class);

        binding = FragmentRecordsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        WebView temperWebView = root.findViewById(R.id.web_view_temper);
        WebView phWebView = root.findViewById(R.id.web_view_ph);

        String graphTemper = "<iframe src=\"http://64.23.231.54:5001/embed/query/1/visualization/2?api_key=6uqA3HzQOGDX5Sk7PpfpNvNYlvaNCRvxGyz9H2wb&\" width=\"720\" height=\"391\"></iframe>";

        String graphPh = "<iframe src=\"http://64.23.231.54:5001/embed/query/1/visualization/2?api_key=6uqA3HzQOGDX5Sk7PpfpNvNYlvaNCRvxGyz9H2wb&\" width=\"720\" height=\"391\"></iframe>";

        String encodedHTMLPH = Base64.encodeToString(graphPh.getBytes(), Base64.NO_PADDING);
        String encodedHTMLTemper= Base64.encodeToString(graphTemper.getBytes(), Base64.NO_PADDING);

        temperWebView.getSettings().setDomStorageEnabled(true); // Habilitar Armazenamento DOM
        temperWebView.getSettings().setAllowContentAccess(true); // Permitir acesso ao conteúdo
        temperWebView.getSettings().setMixedContentMode(temperWebView.getSettings().MIXED_CONTENT_ALWAYS_ALLOW);
        temperWebView.getSettings().setLoadsImagesAutomatically(true);
        temperWebView.getSettings().setJavaScriptEnabled(true);

        phWebView.getSettings().setJavaScriptEnabled(true);
        phWebView.getSettings().setDomStorageEnabled(true); // Habilitar Armazenamento DOM
        phWebView.getSettings().setAllowContentAccess(true); // Permitir acesso ao conteúdo
        phWebView.getSettings().setMixedContentMode(temperWebView.getSettings().MIXED_CONTENT_ALWAYS_ALLOW);
        phWebView.getSettings().setLoadsImagesAutomatically(true);

        temperWebView.loadData(encodedHTMLTemper, "text/html","base64");

        phWebView.loadData(encodedHTMLPH, "text/html","base64");

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