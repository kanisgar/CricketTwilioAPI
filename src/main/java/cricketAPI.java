import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class cricketAPI {


    public static void main(String[] args) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
       objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File f = new File(args[0]);
        if (f.exists())
            System.out.println("Config File Exists");
        else {
            System.out.println("Config file not exisits so exiting the job");
            System.exit(2);
        }
        StartupConfig startupConfig = objectMapper.readValue(f,StartupConfig.class);
        System.out.println(startupConfig);
        int counter = 5;
        while (true) {

            //String imageUrl="https://cdn.insidesport.in/wp-content/uploads/2022/01/21142514/WhatsApp-Image-2022-01-21-at-1.48.52-PM.jpeg";
            String imageUrl="https://static.toiimg.com/thumb/msid-88853138,width-400,resizemode-4/88853138.jpg";
            boolean sendImage=false;
            ResultPOJO resultPOJO = new ResultPOJO();
            String jsonStr = Utils.getUrl(startupConfig.getApikey()).toString();
            Utils.parse(jsonStr, resultPOJO);
            if (counter %5==0)
            {
                sendImage=true;
            }
            else
            {
                sendImage=false;
            }
            if (resultPOJO != null) {
                Utils.sendSms(resultPOJO,sendImage,imageUrl,startupConfig);
            }
            TimeUnit.SECONDS.sleep(60);
            counter++;
        }
    }
}
