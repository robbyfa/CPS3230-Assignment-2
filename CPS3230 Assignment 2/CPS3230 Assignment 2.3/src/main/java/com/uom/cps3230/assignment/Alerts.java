package com.uom.cps3230.assignment;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Alerts {
    private boolean loggedIn = false, full = false;
    private int noOfAlerts = 0;
    private int eventLogType = 10;
    private int status;

    boolean login() {
        if (!loggedIn) {
            loggedIn = true;
            eventLogType = 5;
            return true;
        } else {
            throw new IllegalStateException();
        }
    }

    boolean invalidLogin(){
        loggedIn = false;
        return true;
    }

    boolean logout() {
        if (loggedIn) {
           loggedIn = false;
           eventLogType = 6;
            return true;
        } else {
            throw new IllegalStateException();
        }
    }

    public List<String> getValuesForGivenKey(String jsonArrayStr, String key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject)jsonArray.get(index)).optString(key))
                .collect(Collectors.toList());
    }


    boolean viewAlerts() throws IOException {
        if(loggedIn) {
       /*     URL url = new URL("https://api.marketalertum.com/EventsLog/ff557502-1ba4-4578-b094-2efdd4375b1d");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(inputStream);

            System.out.println("Test: "+root);

            // Get the array of objects
            for (JsonNode element : root) {
                // Get the value of the "name" field
                eventLogType = element.get("eventLogType").asInt();


                // Print the values
            }

            inputStream.close();
            connection.disconnect();
*/
            eventLogType = 7;
        }
        else{
            throw new IllegalStateException();
        }
        return true;
    }

    boolean createAlert(boolean goodORbad) throws IOException {
        if (noOfAlerts <5 && goodORbad) {

            URL url = new URL("https://api.marketalertum.com/Alert");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            String jsonInputString = "{\n" +
                    "\"alertType\": 1,\n" +
                    "\"heading\": \"Xiaomi Mi E27 LED Essential RGB Smart Bulb \",\n" +
                    "\"description\": \"LIGHT-L26843\",\n" +
                    "\"url\": \"https://www.scanmalta.com/shop/xiaomi-mi-e27-led-essential-rgb-smart-bulb.html\",\n" +
                    "\"imageUrl\" : \"https://www.scanmalta.com/shop/pub/media/catalog/product/cache/b084519189a7c7b3054def1f3dcab96f/p/r/product-600259-main.jpg\",\n" +
                    "\"postedBy\": \"ff557502-1ba4-4578-b094-2efdd4375b1d\",\n" +
                    "\"priceInCents\":1995\n" +
                    "}";
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            status = connection.getResponseCode();

           noOfAlerts++;
           eventLogType = 0;
        } else if (noOfAlerts>=5 && goodORbad) {
            full = true;
            eventLogType = 0;
            noOfAlerts++;

        }
        else if(!goodORbad){
            status = 400;
        }
        else {
            throw new IllegalStateException();
        }
        return true;
    }

    boolean fillAlerts() throws IOException {


            for(int i=0; i<5; i++) {
                URL url = new URL("https://api.marketalertum.com/Alert");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);
                String jsonInputString = "{\n" +
                        "\"alertType\": 1,\n" +
                        "\"heading\": \"Xiaomi Mi E27 LED Essential RGB Smart Bulb \",\n" +
                        "\"description\": \"LIGHT-L26843\",\n" +
                        "\"url\": \"https://www.scanmalta.com/shop/xiaomi-mi-e27-led-essential-rgb-smart-bulb.html\",\n" +
                        "\"imageUrl\" : \"https://www.scanmalta.com/shop/pub/media/catalog/product/cache/b084519189a7c7b3054def1f3dcab96f/p/r/product-600259-main.jpg\",\n" +
                        "\"postedBy\": \"ff557502-1ba4-4578-b094-2efdd4375b1d\",\n" +
                        "\"priceInCents\":1995\n" +
                        "}";
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                status = connection.getResponseCode();
                noOfAlerts++;
            }

            eventLogType = 0;

        return true;
    }


    boolean deleteAlerts() throws IOException {

            full = false;
            noOfAlerts = 0;
            eventLogType = 1;
            URL url = new URL("https://api.marketalertum.com/Alert?userId=ff557502-1ba4-4578-b094-2efdd4375b1d");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            status = connection.getResponseCode();

        return true;
    }

    boolean isLoggedIn() {
        return loggedIn;
    }

    boolean isFull() {
        return full;
    }

    int getNoOfAlerts() {
        return noOfAlerts;
    }

    int getEventLogType() { return eventLogType; }

    int getStatus() { return status; }
    /**
     *
     * @return false if zero or one variable is true. true otherwise
     */

}
