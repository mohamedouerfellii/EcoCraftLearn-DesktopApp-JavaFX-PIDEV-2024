package tn.SIRIUS.api;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SMS {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static  String ACCOUNT_SID = "ACd3443809359cb57f1af6d42fe97e5ef5";
    public static  String AUTH_TOKEN = "ad6f55c5405874b91ea4847008b89db5";
      public static void setAccountSID(String accountSid) {
          ACCOUNT_SID = accountSid;
      }
      public static void setAuthToken(String authToken) {
          AUTH_TOKEN = authToken;
      }
    public static void main(String[] args) {

    }

    public static void sendSMS(String recipientPhoneNumber, String senderPhoneNumber, String sendingMessage) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(recipientPhoneNumber),
                        new com.twilio.type.PhoneNumber(senderPhoneNumber),
                        sendingMessage)
                .create();

        System.out.println(message.getSid());
    }
}