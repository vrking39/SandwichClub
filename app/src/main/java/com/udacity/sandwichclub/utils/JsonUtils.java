package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.*;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
            if (json == null || json.isEmpty()){
                    return null;
            }

            try {
                    JSONObject sandwichDetails = new JSONObject(json);
                    JSONObject name = sandwichDetails.getJSONObject("name");

                    String mainName = name.getString("mainName");

                    JSONArray arrayKnownAs = name.getJSONArray("alsoKnownAs");
                    List<String> alsoKnownAs = new ArrayList<>();
                    for (int i = 0; i < arrayKnownAs.length(); i++) {
                            alsoKnownAs.add(arrayKnownAs.getString(i));
                    }

                    String placeOfOrigin = sandwichDetails.getString("placeOfOrigin");

                    String description = sandwichDetails.getString("description");

                    String image = sandwichDetails.getString("image");

                    JSONArray arrayIngredients = sandwichDetails.getJSONArray("ingredients");
                    List<String> ingredients = new ArrayList<>();
                    for (int i = 0; i < arrayIngredients.length(); i++) {
                            ingredients.add(arrayIngredients.getString(i));
                    }

                    return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            }catch (JSONException e){
                    e.printStackTrace();
                    return null;
            }
    }
}
