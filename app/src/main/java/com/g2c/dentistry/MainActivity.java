package com.g2c.dentistry;

import android.app.ProgressDialog;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private JSONObject jsonObject;
    private ProgressDialog dialog=null;
    TextInputEditText patient_name_field;
    TextView dob,medical_history_description_text,header1,header2,header3,allergic_que,header4;
    RecyclerView optional_question_list,women_list,disease_list,doyouhave_list;
    ArrayList<AddVolunteer> arrayOfUsers = new ArrayList<AddVolunteer>();
    private List<AddVolunteer> movieList = new ArrayList<>();
    private List<WomenModel> womenModels = new ArrayList<>();
    private List<AllergicModel> allergicModels = new ArrayList<>();
    private List<DoYouHaveModel> doYouHaveModels = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    private HeaderText2Adapter headerText2Adapter;
    private HeaderText3Adapter headerText3Adapter;
    private HeaderText4Adapter headerText4Adapter;
    private ProgressBar mInfiniteProgressBar;

    List<AddVolunteer> personUtilsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patient_name_field=findViewById(R.id.patient_name_field);
        dob=findViewById(R.id.dob_text);
        medical_history_description_text=findViewById(R.id.medical_history_description_text);
        header1=findViewById(R.id.header1);
        header2=findViewById(R.id.header2);
        women_list=findViewById(R.id.women_list);
        header3=findViewById(R.id.header3);
        disease_list=findViewById(R.id.disease_list);
        allergic_que=findViewById(R.id.allergic_que);
        doyouhave_list=findViewById(R.id.doyouhave_list);
        header4=findViewById(R.id.header4);
        optional_question_list=findViewById(R.id.optional_question_list);
        mInfiniteProgressBar = findViewById(R.id.loader_progressbar);

        dialog = new ProgressDialog(getApplicationContext());
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        jsonObject = new JSONObject();
        GetData();
    }

    private void GetData(){

        try {

            jsonObject.put("client_id", "1");
            jsonObject.put("location_id", "1");
            jsonObject.put("oc_patient_id", "84");
        } catch (JSONException e) {
            Log.e("JSONObject Here", e.toString());
        }
        final ProgressDialog pd = new ProgressDialog(MainActivity.this);
       // pd.setMessage("loading");
       // pd.show();
        showProgressDialog();

        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, Utils.GetData, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("jsonObject", String.valueOf(jsonObject));
                        ArrayList<AddVolunteer> addVolunteers = new ArrayList<>();
                        final HeaderText1Adapter adapter = new HeaderText1Adapter(getApplicationContext(), arrayOfUsers);
                        ArrayList<WomenModel> womenModels = new ArrayList<>();


                        try{

                            JSONObject innerJson=new JSONObject(String.valueOf(jsonObject));

                            String name= innerJson.getString("name");
                            String date_of_birth= innerJson.getString("date_of_birth");
                            String content1= innerJson.getString("content1");
                            String header_text1= innerJson.getString("header_text1");
                            String header_text2= innerJson.getString("header_text2");
                            String header_text3= innerJson.getString("header_text3");
                            String header_text4= innerJson.getString("header_text4");
                            String allergic_question= innerJson.getString("allergic_question");


                            header1.setText(header_text1);
                            header2.setText(header_text2);
                            header3.setText(header_text3);
                            header4.setText(header_text4);
                            allergic_que.setText(allergic_question);
                            medical_history_description_text.setText(content1);
                            patient_name_field.setText(name);
                            dob.setText(date_of_birth);

                            JSONArray jsonArray=innerJson.getJSONArray("mark_response_data");

                            for(int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                movieList.add(new AddVolunteer(jsonObject1.getString("m_question")));

                                mAdapter = new MoviesAdapter(movieList);
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                optional_question_list.setLayoutManager(mLayoutManager);
                                optional_question_list.setItemAnimator(new DefaultItemAnimator());
                                optional_question_list.setAdapter(mAdapter);


                            }

                            JSONArray jsonArray1=innerJson.getJSONArray("women_data");

                            for(int i=0;i<jsonArray1.length();i++) {
                                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                                womenModels.add(new WomenModel(jsonObject1.getString("w_question")));
                                headerText2Adapter = new HeaderText2Adapter(womenModels);
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                women_list.setLayoutManager(mLayoutManager);
                                women_list.setItemAnimator(new DefaultItemAnimator());
                                women_list.setAdapter(headerText2Adapter);

                            }

                            JSONArray jsonArray2=innerJson.getJSONArray("allergic_data");

                            for(int i=0;i<jsonArray2.length();i++) {
                                JSONObject jsonObject2 = jsonArray2.getJSONObject(i);
                                allergicModels.add(new AllergicModel(jsonObject2.getString("a_question")));
                                headerText3Adapter = new HeaderText3Adapter(allergicModels);
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                disease_list.setLayoutManager(mLayoutManager);
                                disease_list.setItemAnimator(new DefaultItemAnimator());
                                disease_list.setAdapter(headerText3Adapter);

                            }


                            JSONArray jsonArray3=innerJson.getJSONArray("doyouhave_data");

                            for(int i=0;i<jsonArray3.length();i++) {
                                JSONObject jsonObject3 = jsonArray3.getJSONObject(i);
                                doYouHaveModels.add(new DoYouHaveModel(jsonObject3.getString("d_question")));
                                headerText4Adapter = new HeaderText4Adapter(doYouHaveModels);
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                doyouhave_list.setLayoutManager(mLayoutManager);
                                doyouhave_list.setItemAnimator(new DefaultItemAnimator());
                                doyouhave_list.setAdapter(headerText4Adapter);

                            }



                            Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();

                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                       // pd.dismiss();
                        hideProgressDialog();



                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Message from server", volleyError.toString());
                pd.dismiss();
            }
        });
        jsonObjectRequest1.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest1);

    }

    public static void setDynamicHeight(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        //check adapter if null
        if (adapter == null) {
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = height + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(layoutParams);
        listView.requestLayout();
    }

    public void showProgressDialog() {
        if (mInfiniteProgressBar != null) {
            mInfiniteProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressDialog() {
        if (mInfiniteProgressBar != null) {
            mInfiniteProgressBar.setVisibility(View.GONE);
        }
    }
}
