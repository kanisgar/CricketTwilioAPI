import com.fasterxml.jackson.annotation.JsonProperty;

public class StartupConfig {
    @JsonProperty("ACCOUNT_SID")
    private  String accountSID;
    @JsonProperty("AUTH_TOKEN")
    private  String authToken;
    @JsonProperty("api_key")
    private  String apikey;
    @JsonProperty("from_mobile")
    private  String fromMobile;
    @JsonProperty("to_mobile")
    private  String toMobile;
  /*  @JsonProperty("sendtoWhatsapp")
    private static String SendtoWhatsapp;*/


    public  String getAccountSID() {
        return accountSID;
    }

    public  void setAccountSID(String accountSID) {
        this.accountSID = accountSID;
    }

    public  String getAuthToken() {
        return authToken;
    }

    public  void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public  String getFromMobile() {
        return fromMobile;
    }

    public  void setFromMobile(String fromMobile) {
        this.fromMobile = fromMobile;
    }

    public  String getToMobile() {
        return toMobile;
    }

    public  void setToMobile(String toMobile) {
        this.toMobile = toMobile;
    }

   /* public static String getSendtoWhatsapp() {
        return SendtoWhatsapp;
    }

    public static void setSendtoWhatsapp(String sendtoWhatsapp) {
        SendtoWhatsapp = sendtoWhatsapp;
    }
*/
    public  String getApikey() {
        return apikey;
    }

    public  void setApikey(String apikey) {
        this.apikey = apikey;
    }

    @Override
    public String toString() {
        return "account sid :"+accountSID+"\n"
                +"from_mobile :"+fromMobile+"\n"+
                "api_key :"+apikey;
    }
}
