package ftmk.bitp3453.helloclass;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import ftmk.bitp3453.helloclass.databinding.ActivityStudentMainBinding;

public class StudentMainActivity extends AppCompatActivity {
    private ActivityStudentMainBinding binding;
    private Student student;
    LinearLayout linearLayout;

    private Vector<Student> students;
    private StudentAdapter adapter;

    private DatePickerDialog datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayout = findViewById(R.id.linear_layout);

        binding = ActivityStudentMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        //binding.fabAdd.setOnClickListener(this:: fnAdd );
        binding.fabAdd.setOnClickListener(this:: fnAddToREST);

        binding.edtBirthdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b)
            {
                if(b) {
                    fnInvokeDatePicker();
                }
                if(!b)
                {
                    fnFormValidaton();
                }

            }

        });
        binding.edtBirthdate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                fnInvokeDatePicker();
            }
        });

        students = new Vector<>();
        adapter = new StudentAdapter(getLayoutInflater(),students);


        binding.rcvStud.setAdapter(adapter);
        binding.rcvStud.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(binding.rcvStud);

    }

    private void fnAddToREST(View view) {
        String strURL = "http://192.168.0.4/RESTAPI/rest_api.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("respond");

                    if(success.equals("Information Saved!")){

                        Toast.makeText(getApplicationContext(), "Respond from server: " +
                                jsonObject.getString("respond"), Toast.LENGTH_SHORT).show();

                    }



                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String fullname = binding.edtFullName.getText().toString();
                String studNo = binding.edtStudNum.getText().toString();
                String email = binding.edtEmail.getText().toString();
                String birth = binding.edtBirthdate.getText().toString();
                String gender = "";
                String state = binding.spnState.getSelectedItem().toString();

                if(binding.rbMale.isChecked())
                    gender = binding.rbMale.getText().toString();
                else if(binding.rbFemale.isChecked())
                    gender = binding.rbFemale.getText().toString();

                student = new Student(fullname,studNo,email,gender,birth, state);

                students.add(student);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        adapter.notifyItemInserted(students.size());
                    }
                });


                Map<String, String> params = new HashMap< >();
                params.put("selectFn", "fnSaveData");
                params.put("studName", fullname);
                params.put("studGender", gender);
                params.put("studDob", birth);
                params.put("studNo", studNo);
                params.put("studState", state);
                return params;


            }

        };
        requestQueue.add(stringRequest);
    }




    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            students.remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
        }
    };

    private void fnFormValidaton() {
    }

    private void fnInvokeDatePicker()

    {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog

        datePicker = new DatePickerDialog(StudentMainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                binding.edtBirthdate.setText(dayOfMonth + "/" + (month+1) + "/" + year);
            }
        },year,month,day);
        datePicker.show();
    }

    private void fnAdd(View view)
    {
        String fullname = binding.edtFullName.getText().toString().trim();
        String studNo = binding.edtStudNum.getText().toString().trim();
        String email = binding.edtEmail.getText().toString().trim();
        String birth = binding.edtBirthdate.getText().toString().trim();
        String gender = "";
        String state = binding.spnState.getSelectedItem().toString().trim();

        if(binding.rbMale.isChecked())
            gender = binding.rbMale.getText().toString();
        else if(binding.rbFemale.isChecked())
            gender = binding.rbFemale.getText().toString();

        student = new Student(fullname,studNo,email,gender,birth, state);

        students.add(student);
        adapter.notifyItemInserted(students.size());
    }


}