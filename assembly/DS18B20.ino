// Include the libraries we need
#include <OneWire.h>
#include <DallasTemperature.h>
#include <WiFi.h>
#include <PubSubClient.h>

// Data wire is plugged into port 2 on the Arduino
#define ONE_WIRE_BUS 2

// Setup a oneWire instance to communicate with any OneWire devices (not just Maxim/Dallas temperature ICs)
OneWire oneWire(ONE_WIRE_BUS);

// Pass our oneWire reference to Dallas Temperature.
DallasTemperature sensors(&oneWire);

// WiFi credentials
const char* ssid = "definir_ssid";
const char* password = "definir_senha";

// MQTT broker settings
const char* mqttServer = "broker.hivemq.com";
const int mqttPort = 1883;
const char* mqttTopic = "sensor/temperature/tanqueum";

// WiFi and MQTT client
WiFiClient espClient;
PubSubClient client(espClient);

// Function to connect to WiFi
void setupWiFi() {
  Serial.print("Connecting to WiFi...");
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("\nWiFi connected!");
}

// Function to reconnect to MQTT
void reconnectMQTT() {
  while (!client.connected()) {
    Serial.println("Connecting to MQTT...");
    if (client.connect("ESP32Client")) {
      Serial.println("Connected to MQTT broker!");
    } else {
      Serial.print("Failed to connect. State: ");
      Serial.println(client.state());
      delay(2000);
    }
  }
}

/*
 * The setup function. We only start the sensors and initialize WiFi and MQTT here
 */
void setup(void) {
  // Start serial port
  Serial.begin(9600);
  Serial.println("Dallas Temperature IC Control Library Demo");

  // Start up the library
  sensors.begin();

  // Connect to WiFi
  setupWiFi();

  // Set up MQTT server
  client.setServer(mqttServer, mqttPort);
}

/*
 * Main function, get and show the temperature, then send it via MQTT
 */
void loop(void) {
  // Maintain MQTT connection
  if (!client.connected()) {
    reconnectMQTT();
  }
  client.loop();

  // Request temperatures
  Serial.print("Requesting temperatures...");
  sensors.requestTemperatures(); // Send the command to get temperatures
  Serial.println("DONE");

  // Get temperature from the first sensor
  float tempC = sensors.getTempCByIndex(0);

  // Check if reading was successful
  if (tempC != DEVICE_DISCONNECTED_C) {
    Serial.print("Temperature for the device 1 (index 0) is: ");
    Serial.println(tempC);

    // Convert temperature to string and publish to MQTT
    String payload = String(tempC, 2); // Limit to 2 decimal places
    if (client.publish(mqttTopic, payload.c_str())) {
      Serial.println("Temperature sent to MQTT broker successfully!");
    } else {
      Serial.println("Failed to send temperature to MQTT broker.");
    }
  } else {
    Serial.println("Error: Could not read temperature data");
  }

  // Wait 5 seconds before the next reading
  delay(5000);
}
