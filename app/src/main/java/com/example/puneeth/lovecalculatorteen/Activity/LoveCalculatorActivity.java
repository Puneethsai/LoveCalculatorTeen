package com.example.puneeth.lovecalculatorteen.Activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.puneeth.lovecalculatorteen.Constants.RestAPI;
import com.example.puneeth.lovecalculatorteen.R;
import com.example.puneeth.lovecalculatorteen.Utils.OkHttpUtil;
import com.example.puneeth.lovecalculatorteen.Utils.ResponseListenerString;
import org.json.JSONException;
import org.json.JSONObject;

public class LoveCalculatorActivity extends AppCompatActivity implements
        ResponseListenerString, View.OnClickListener {

    public TextInputEditText sNameText, fNameText;
    public ImageView imageButton;
    public ResponseListenerString responseListenerString;
    public TextView fnameText, snameText, percentageText, resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_calculator);
        init();
    }

    public void init(){

        sNameText = findViewById(R.id.sname_editText);
        fNameText = findViewById(R.id.fname_editText);
        imageButton = findViewById(R.id.love_img_btn);
        fnameText = findViewById(R.id.fname_txt);
        snameText = findViewById(R.id.sname_txt);
        percentageText = findViewById(R.id.love_percentage);
        resultText = findViewById(R.id.result);
        imageButton.setOnClickListener(this);
        responseListenerString = this;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.love_img_btn){
            getLoveRequest();
        }
    }

    public void getLoveRequest(){

        String fName = fNameText.getText().toString().trim();
        String sName = sNameText.getText().toString().trim();

        String apiURL = RestAPI.loveCaluclatorURL + "fname=" +  fName + "&sname=" + sName;
        OkHttpUtil.postRequest(this, apiURL,responseListenerString);
    }

    @Override
    public void onResponseReceived(String response) {

        if (response != null){

            sNameText.setText(""); fNameText.setText("");

            Log.d("Response", response);

            try {

                JSONObject jsonObject = new JSONObject(response);

                if (jsonObject.length() >0){

//                    JSONObject jsonObject1 = jsonObject.getJSONObject("body");
//                    Log.d("jsonObject1", jsonObject1.toString());


                    if (jsonObject.has("fname") && jsonObject.has("sname") &&
                            jsonObject.has("percentage") && jsonObject.has("result")){

                        String fname = jsonObject.getString("fname");
                        String sname = jsonObject.getString("sname");
                        String percentage = jsonObject.getString("percentage");
                        String result = jsonObject.getString("result");

                        fnameText.setText(fname);
                        snameText.setText(sname);
                        percentageText.setText(percentage + "%");
                        resultText.setText(result);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
