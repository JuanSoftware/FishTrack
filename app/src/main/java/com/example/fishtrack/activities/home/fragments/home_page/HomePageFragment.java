package com.example.fishtrack.activities.home.fragments.home_page;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishtrack.R;
import com.example.fishtrack.databinding.FragmentHomePageBinding;
import com.example.fishtrack.shared.fragments.DropDownFragment;
import com.example.fishtrack.shared.utils.StyleUtils;
import com.example.fishtrack.MqttHandler;

public class HomePageFragment extends Fragment {

    private static final String BROKER_URL = "tcp://146.190.131.25:1883";
    private static final String CLIENT_ID = "fishtrack-client";
    private static final String TOPIC_TEMPERATURE = "sensor/temperature/tanqueum";




    private FragmentHomePageBinding binding;
    private DropDownFragment dropDownFragment;
    private String[] itemsDropDown = {"Setor1", "Setor2", "Setor3", "Setor4"};
    private MqttHandler mqttHandler;
    private final Handler uiHandler = new Handler(Looper.getMainLooper());

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        HomePageViewModel homeViewModel = new ViewModelProvider(this).get(HomePageViewModel.class);

        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        StyleUtils styleUtils = new StyleUtils();
        styleUtils.setBackground("#01417e", 20f, 2, "#000000");

        // Initialize MQTT handler
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL, CLIENT_ID);

        // Initialize TextView for temperature value
        TextView temperatureValueTextView = binding.valueTemperature;

        // Subscribe to MQTT topic
        subscribeToTemperatureTopic(TOPIC_TEMPERATURE, temperatureValueTextView);

        // DropDown Fragment setup
        dropDownFragment = new DropDownFragment(itemsDropDown);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dropdown_menu, dropDownFragment);
        fragmentTransaction.commit();

        root.findViewById(R.id.tanque_img).setOnClickListener(v -> {
            root.findViewById(R.id.tanque_img).setVisibility(View.GONE);
            root.findViewById(R.id.info_data).setVisibility(View.VISIBLE);
        });

        root.findViewById(R.id.info_data).setOnClickListener(v -> {
            root.findViewById(R.id.tanque_img).setVisibility(View.VISIBLE);
            root.findViewById(R.id.info_data).setVisibility(View.GONE);
        });

        return root;
    }

    private void subscribeToTemperatureTopic(String topic, TextView temperatureValueTextView) {
        mqttHandler.subscribe(topic, (topic1, message) -> {
            String receivedTemperature = new String(message.getPayload());
            uiHandler.post(() -> temperatureValueTextView.setText(receivedTemperature + "°C"));
        });
    }

    public GradientDrawable circleShape(int color) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setColor(color);
        shape.setStroke(2, Color.parseColor("#000000"));
        return shape;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mqttHandler.disconnect();
        binding = null;
    }
}
