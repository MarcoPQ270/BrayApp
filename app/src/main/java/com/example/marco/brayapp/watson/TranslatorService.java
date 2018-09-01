package com.example.marco.brayapp.watson;
import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslationResult;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import org.json.JSONException;
import org.json.JSONObject;


public class TranslatorService {
    private  String translatedText(String text, String language){
        IamOptions options = new IamOptions.Builder()
                .apiKey("{iam_api_key}")
            .build();

    LanguageTranslator languageTranslator = new LanguageTranslator(
            "2018-05-01",
            options);

    TranslateOptions translateOptions = new TranslateOptions.Builder()
            .addText(text)
            .modelId(language)
            .build();

    TranslationResult result = languageTranslator.translate(translateOptions)
            .execute();
    return result.toString();
    }
    public static String getTextTranslated(String JsonObject){
        String finalText="Error al traducir";
        try {
            JSONObject json= new JSONObject(JsonObject);
            JSONObject data=json.getJSONObject("translations");
            finalText=data.getString("translation");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalText;
    }
}
