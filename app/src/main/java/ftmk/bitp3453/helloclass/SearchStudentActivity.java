package ftmk.bitp3453.helloclass;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ftmk.bitp3453.helloclass.databinding.ActivitySearchStudentBinding;

public class SearchStudentActivity extends AppCompatActivity {

    private ActivitySearchStudentBinding binding;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fnSearch();
            }
        });
    }

    private void fnSearch() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String strURL = "http://192.168.0.4/RESTAPI/rest_api.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Toast.makeText(getApplicationContext(), "Getting some respond here", Toast.LENGTH_SHORT).show();
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                Toast.makeText(getApplicationContext(), "Waiting...", Toast.LENGTH_SHORT).show();
                                JSONObject obj = jsonArray.getJSONObject(i);

                                binding.txtVwStudName2.setText(obj.getString("stud_name"));
                                binding.txtVwStudGender.setText(obj.getString("stud_gender"));
                                binding.txtVwStudNo.setText(obj.getString("stud_no"));
                                binding.txtVwStudState.setText(obj.getString("stud_state"));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Unable to fetch student info", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String strStudNo = binding.edtStudID.getText().toString();

                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnSearchStud");
                params.put("stud_no", strStudNo);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}