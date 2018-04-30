package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();
    private static final String SAND_NAME = "name";
    private static final String SAND_MAIN_NAME = "mainName";
    private static final String SAND_ALSO = "alsoKnownAs";
    private static final String SAND_PLACE_ORIGIN = "placeOfOrigin";
    private static final String SAND_DESCRIPTION = "description";
    private static final String SAND_IMAGE = "image";
    private static final String SAND_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        JSONObject jsonObject;
        String mainName = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredients = new ArrayList<>();
        List<String> alsoKnownAs = new ArrayList<>();
        try {
            jsonObject = new JSONObject(json);
            JSONObject jsonObject1Name = jsonObject.getJSONObject(SAND_NAME);
            mainName = jsonObject1Name.optString(SAND_MAIN_NAME);
            placeOfOrigin = jsonObject.optString(SAND_PLACE_ORIGIN);
            description = jsonObject.optString(SAND_DESCRIPTION);
            image = jsonObject.optString(SAND_IMAGE);

            alsoKnownAs = jsonArrayList(jsonObject1Name.getJSONArray(SAND_ALSO));
            ingredients = jsonArrayList(jsonObject.getJSONArray(SAND_INGREDIENTS));
        }catch (JSONException e){
            Log.e(LOG_TAG, "Problem with parse", e);
        }
        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }

    private static List<String> jsonArrayList(JSONArray jsonArray){
        List<String> list = new ArrayList<>(0);
        if(jsonArray != null){
            for(int i=0; i<jsonArray.length(); i++){
                try{
                    list.add(jsonArray.getString(i));
                }catch(Exception e){
                    Log.e(LOG_TAG, "Problems with array list", e);
                }
            }
        }
        return list;
    }
}
