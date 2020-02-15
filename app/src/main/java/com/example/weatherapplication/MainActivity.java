package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView input;
    private Button searchbtn;
    private ListView listviewmeteo;
    private List<MeteoItem> data=new ArrayList<>();
    private MeteoListModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input=findViewById(R.id.cityvalue);
        searchbtn=findViewById(R.id.button);
        listviewmeteo=findViewById(R.id.listviewmeteo);

        model=new MeteoListModel(this,R.layout.list_items,data);
        listviewmeteo.setAdapter(model);

        searchbtn.setOnClickListener(ev->{
            Log.i("keypressed","true");
            data.clear();
            model.notifyDataSetChanged();

            RequestQueue queue=Volley.newRequestQueue(getApplicationContext());
            String city=input.getText().toString();
            String url="https://samples.openweathermap.org/data/2.5/forecast?q="+city+"&appid=b6907d289e10d714a6e88b30761fae22";
            StringRequest stringRequest=new StringRequest(Request.Method.GET, url, (Response.Listener<String>) response -> {
                try {
                    Log.i("mine",response);
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("list");
                    Log.i("list",jsonArray.toString());
                    for(int i=0;i<jsonArray.length();i++){
                        MeteoItem mt=new MeteoItem();
                        JSONObject d=jsonArray.getJSONObject(i);
                        Log.i("singleobject",d.toString());
                        Date date=new Date(d.getLong("dt")*1000);
                        SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-YYYY");
                        String datestring=sdf.format(date);
                        Log.i("singledate",datestring);
                        JSONObject main=d.getJSONObject("main");
                        Log.i("maincnt",main.toString());
                        //JSONObject weather=d.getJSONObject("weather");
                        int max= (int) (main.getDouble("temp_max")-273.5);
                        int min= (int) (main.getDouble("temp_min")-273.5);
                        int press=(int)main.getDouble("pressure");
                        int humid=(int) main.getDouble("humidity");

                        mt.setDate(datestring);
                        mt.setHumidity(humid);
                        //mt.setImage(weather.getJSONObject());
                        mt.setMaxtemp(max);
                        mt.setMintemp(min);
                        mt.setPression(press);
                        data.add(mt);
                    }
                    model.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            },error -> {

            });
            queue.add(stringRequest);
        });
    }
}
