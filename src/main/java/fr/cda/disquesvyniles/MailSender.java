package fr.cda.disquesvyniles;

import sendinblue.ApiClient;
import sendinblue.ApiException;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.AccountApi;
import sibModel.GetAccount;

public class MailSender {




        public static void main(String[] args) {
            ApiClient defaultClient = Configuration.getDefaultApiClient();

            // Configure API key authorization: api-key
            ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
            apiKey.setApiKey("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");
            // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
            //apiKey.setApiKeyPrefix("Token");


            AccountApi apiInstance = new AccountApi();
            try {
                GetAccount result = apiInstance.getAccount();
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling AccountApi#getAccount");
                e.printStackTrace();
            }
        }
}
