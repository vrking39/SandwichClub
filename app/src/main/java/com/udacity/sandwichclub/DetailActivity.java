package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView mAnotherName = findViewById(R.id.also_known_tv);
        TextView mIngredients = findViewById(R.id.ingredients_tv);
        TextView mDescription = findViewById(R.id.description_tv);
        TextView mPlaceOfOrigin = findViewById(R.id.origin_tv);
        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        if (sandwich.getDescription().isEmpty()){
            mDescription.setText("N/A");
        }else{
            mDescription.setText(sandwich.getDescription());
        }

        if (sandwich.getPlaceOfOrigin().isEmpty()){
            mPlaceOfOrigin.setText("N/A");
        }else {
            mPlaceOfOrigin.setText(sandwich.getPlaceOfOrigin());
        }

        if (sandwich.getAlsoKnownAs().isEmpty()){
            mAnotherName.setText("N/A");
        }else {
            mAnotherName.setText(TextUtils.join("\n", sandwich.getAlsoKnownAs()));
        }

        if (sandwich.getIngredients().isEmpty()){
            mIngredients.setText("N/A");
        }else {
            mIngredients.setText(TextUtils.join("\n", sandwich.getIngredients()));
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
