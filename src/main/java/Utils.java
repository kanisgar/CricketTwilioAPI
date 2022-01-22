import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

public class Utils {
    public static String output = "";
    private static  StringBuffer result= new StringBuffer();
    public static StringBuffer getUrl(String apikey) {

        try {
            result.setLength(0);
            URL url = new URL(apikey);
            //  URL url = new URL("https://randomuser.me/api/0.8/?results=5");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            System.out.println("The response code is " + conn.getResponseCode());
           /* if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }*/

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {

                System.out.println(output);
                result.append(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return result;
    }

    public static ObjectMapper getDefaultObjectMapper()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }
    public static JsonNode parse(ObjectMapper objectMapper,String jsonStr) throws IOException {
        return objectMapper.readTree(jsonStr);
    }
    public static void parse(String jsonStr,ResultPOJO resultPOJO)
    {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonStr);
        JsonArray jsonArray= (JsonArray) jsonObject.get("result");
        Iterator<JsonElement> iterator = jsonArray.iterator();
        while(iterator.hasNext()) {
            jsonObject=(JsonObject)   jsonParser.parse(iterator.next().toString());
            String team=jsonObject.get("event_home_team").getAsString().trim();
            String team2=jsonObject.get("event_away_team").getAsString().trim();
            if (team.contains("England") || team2.contains("England")) {

                resultPOJO.setAway_team(team2);
                resultPOJO.setHome_team(team);
                resultPOJO.setEvent_status(jsonObject.get("event_status").getAsString().trim());
                resultPOJO.setLeague_name(jsonObject.get("league_name").getAsString().trim());
                resultPOJO.setEvent_status_info(jsonObject.get("event_status_info").getAsString().trim());
            }
        }
        if(resultPOJO!=null)
        {
            System.out.println(resultPOJO);
        }
    }
        public static void sendSms(ResultPOJO resultPOJO,boolean image,String imageurl,StartupConfig startupConfig)
        {

            String ACCOUNT_SID=startupConfig.getAccountSID();
            String AUTH_TOKEN=startupConfig.getAuthToken();
            System.out.println(ACCOUNT_SID+"  "+AUTH_TOKEN);
            String from_mobile=startupConfig.getFromMobile();
            String to_mobile=startupConfig.getToMobile();
            Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
            //System.out.println("inside send sms and resultPojos is \n"+resultPOJO);

            if(!image)
            {
                Message message = Message.creator(
                        new PhoneNumber("whatsapp:"+to_mobile), new PhoneNumber("whatsapp:"+from_mobile),
                        resultPOJO.toString()).create();
                System.out.println(message.getSid());
                System.out.println(message.toString());
            }
            else
            {
                Message message = Message.creator(
                        new PhoneNumber("whatsapp:"+to_mobile), new PhoneNumber("whatsapp:"+from_mobile),
                        resultPOJO.toString()).setMediaUrl((Arrays.asList(URI.create(imageurl)))).create();
                System.out.println(message.getSid());
                System.out.println(message.toString());
            }


        }
    }

