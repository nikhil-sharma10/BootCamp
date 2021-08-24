package com.bootcampProject.BootcampProject.convertor;

import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JSONObjectConverter implements AttributeConverter<JSONObject, String> {


    @Override
    public String convertToDatabaseColumn(JSONObject jsonObject) {
        String json;
        try{
            json = jsonObject.toString();
        }
        catch (NullPointerException ex){
            ex.getMessage();
            json = "";
        }
        return json;
    }

    @Override
    public JSONObject convertToEntityAttribute(String s) {
        JSONObject jsonObject;
        try{
            jsonObject = new JSONObject(s);
        }
        catch (JSONException ex){
            jsonObject = null;
            ex.getMessage();
        }
        return jsonObject;
    }
}
