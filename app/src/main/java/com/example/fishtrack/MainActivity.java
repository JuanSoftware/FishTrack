package com.example.fishtrack;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String BROKER_URL = "tcp://10.0.2.2:1883";
    private static final String CLIENT_ID = "fishtrack-client";
    private static final String TOPIC_TEMPERATURE = "sensor/temperature";

    private MqttHandler mqttHandler;
    private TextView receivedMessageTextView;
    private final Handler uiHandler = new Handler(Looper.getMainLooper());

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa o handler MQTT
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL, CLIENT_ID);

        // Inicializa a TextView para exibir mensagens
        receivedMessageTextView = findViewById(R.id.receivedMessageTextView);

        // Configura o botão para se inscrever no tópico
        Button subscribeButton = findViewById(R.id.subscribeButton);
        subscribeButton.setOnClickListener(view -> {
            subscribeToTopic(TOPIC_TEMPERATURE);
            Toast.makeText(this, "Subscribed to topic: " + TOPIC_TEMPERATURE, Toast.LENGTH_SHORT).show();
        });

        // Configura o botão para publicar no tópico
        Button publishButton = findViewById(R.id.publishButton);
        publishButton.setOnClickListener(view -> {
            String temperatureMessage = "25.5"; // Exemplo de temperatura
            publishMessage(TOPIC_TEMPERATURE, temperatureMessage);
        });
    }

    @Override
    protected void onDestroy() {
        mqttHandler.disconnect();
        super.onDestroy();
    }

    private void publishMessage(String topic, String message) {
        mqttHandler.publish(topic, message);
        Toast.makeText(this, "Published message: " + message, Toast.LENGTH_SHORT).show();
    }

    private void subscribeToTopic(String topic) {
        mqttHandler.subscribe(topic, (topic1, message) -> {
            String receivedMessage = new String(message.getPayload());
            // Atualiza a TextView na thread principal
            uiHandler.post(() -> receivedMessageTextView.setText("Received: " + receivedMessage));
        });
    }
}
