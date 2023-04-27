package com.example.vksearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import Utils.NetworkUtils;


public class MainActivity extends AppCompatActivity {

    private EditText searchField;
    private Button searchButton;
    private TextView result;


    class VKQueryTask extends AsyncTask<URL,Void,String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
                response = NetworkUtils.getResponseFromUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
           return response;
        }
        @Override
        protected void onPostExecute(String response){
            result.setText(response);
                    }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.text_search_field);
        searchButton = findViewById(R.id.button_search_vk);
        result = findViewById(R.id.search_result);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                URL generatedURL = NetworkUtils.generateURL(searchField.getText().toString());

                new VKQueryTask().execute(generatedURL);

            }
        };
        searchButton.setOnClickListener(onClickListener);
    }
}