package ftmk.bitp3453.helloclass;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.Calendar;
import ftmk.bitp3453.helloclass.databinding.ActivityRegistrationBinding;


public class RegistrationActivity extends AppCompatActivity {

    DatePickerDialog datePicker;
    ActivityRegistrationBinding binding;
    EditText edtFullName,edtPwd,edtEmail,edtBirthdate,edtAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edtFullName = (EditText)findViewById(R.id.edtFullName);
        edtPwd = (EditText)findViewById(R.id.edtPwd);
        edtEmail =(EditText)findViewById(R.id.edtEmail);

        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.edtBirthdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                fnInvokeDatePicker();
            }
        });
        binding.edtBirthdate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                fnInvokeDatePicker();
            }
        });
        binding.fabAddUser.setOnClickListener(this::fnAddUser);

        edtAddress = (EditText)findViewById(R.id.edtAddress);

    }
    private void fnInvokeDatePicker()
    {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        datePicker = new DatePickerDialog(RegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                binding.edtBirthdate.setText(dayOfMonth + "/" + (month+1) + "/" + year);
            }
        },year,month,day);
        datePicker.show();
    }

    private void fnAddUser(View view){
        String strFullName = binding.edtFullName.getText().toString();
        String strPwd = binding.edtPwd.getText().toString();
        String strEmail = binding.edtEmail.getText().toString();
        String strBirth = binding.edtBirthdate.getText().toString();
        String strAddress = binding.edtAddress.getText().toString();
        String strGender ="";

        if(binding.rbMale.isChecked())
            strGender = binding.rbMale.getText().toString();
        else if(binding.rbFemale.isChecked())
            strGender = binding.rbFemale.getText().toString();

        User user = new User(strFullName,strAddress,strPwd,strBirth,strGender,strEmail);
        Intent intent = new Intent(this,ThreadedActivity.class);
        intent.putExtra("objUser",user);
    }

    public void HelloNopen(View view) {

        startActivity(new Intent(this,RegistrationActivity.class));
    }
}