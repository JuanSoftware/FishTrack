package com.example.fishtrack;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttHandler {

    private MqttClient client;

    // Conecta ao broker MQTT
    public void connect(String brokerUrl, String clientId) {
        try {
            // Configuração de persistência
            MemoryPersistence persistence = new MemoryPersistence();

            // Inicializa o cliente MQTT
            client = new MqttClient(brokerUrl, clientId, persistence);

            // Opções de conexão
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);

            // Conexão com o broker
            client.connect(connectOptions);
            System.out.println("Connected to MQTT broker at " + brokerUrl);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    // Desconecta do broker MQTT
    public void disconnect() {
        try {
            if (client != null && client.isConnected()) {
                client.disconnect();
                System.out.println("Disconnected from MQTT broker.");
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    // Publica uma mensagem em um tópico
    public void publish(String topic, String message) {
        try {
            if (client != null && client.isConnected()) {
                MqttMessage mqttMessage = new MqttMessage(message.getBytes());
                client.publish(topic, mqttMessage);
                System.out.println("Message published to topic " + topic + ": " + message);
            } else {
                System.err.println("Cannot publish: MQTT client is not connected.");
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    // Assina um tópico e define um callback para mensagens recebidas
    public void subscribe(String topic, IMqttMessageListener messageListener) {
        try {
            if (client != null && client.isConnected()) {
                client.subscribe(topic, messageListener);
                System.out.println("Subscribed to topic: " + topic);
            } else {
                System.err.println("Cannot subscribe: MQTT client is not connected.");
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    // Verifica se o cliente está conectado
    public boolean isConnected() {
        return client != null && client.isConnected();
    }
}
