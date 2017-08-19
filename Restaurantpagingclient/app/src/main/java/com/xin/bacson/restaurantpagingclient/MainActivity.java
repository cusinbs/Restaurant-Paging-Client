package com.xin.bacson.restaurantpagingclient;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String urlGetData= "http://192.168.1.75/restaurantpaging/getdata.php";
    EditText edtName;
    Button btnSearch;
    ArrayList<Paging> pagingArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = (EditText)findViewById(R.id.editTextName);
        btnSearch = (Button)findViewById(R.id.buttonSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(urlGetData);
            }
        });
    }

    private void getData(String urlGetData){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlGetData, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //pagingArrayList.clear();
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        //Log.d("Xin",response.toString());
                        pagingArrayList = new ArrayList<Paging>();
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);

                                //Log.d("Xin",object.getString("Name"));
                                //Log.d("Xin1",pagingArrayList.size()+"");
                                pagingArrayList.add(new Paging(
                                        object.getInt("ID"),
                                        object.getString("NAME"),
                                        object.getInt("NUM"),
                                        object.getInt("READY")
                                ));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //Log.d("Xin1",pagingArrayList.size()+"");
                        //Log.d("Xin2",pagingArrayList.get(2).getName()+"");
                        //adapter.notifyDataSetChanged();
                        Search(pagingArrayList);
                        //Log.d("Xin3",pagingArrayList.get(2).getName()+"");
                    }
                }, new Response.ErrorListener() {
            @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                    }
                    }
        );
        //Log.d("Xin3",pagingArrayList.get(2).getName()+"");
        requestQueue.add(jsonArrayRequest);
        //Log.d("Xin4",pagingArrayList.get(2).getReady()+"");
        //Log.d("Xin",pagingArrayList.size()+"");
    }

    private void Search(ArrayList<Paging> pagings){
        String nameSearch = edtName.getText().toString().trim();
        //Log.d("Xin4",pagingArrayList.get(2).getName()+"");
        //Log.d("Xin5",pagings.get(2).getName()+"");
        //Log.d("Xin2",pagings.size()+"");

        for(int i = 0; i < pagings.size(); i++){
            //Log.d("Xin6",pagings.get(i).getName().trim().toString());
            //Log.d("Xin1",nameSearch);
            if(nameSearch.equals(pagings.get(i).getName().trim())){
                checkStatus(pagings.get(i).getReady());
            }
        }
    }

    private void checkStatus(int ready) {
        Log.d("Xin0",ready+"");
        Vibrator v = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        //v.vibrate(5000);
        if(ready != 0){
            // Vibrate for 500 milliseconds
            v.vibrate(500);
            Log.d("Xin Vibrate","Yes");
        }
    }
}
