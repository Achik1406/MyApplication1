package ftmk.bitp3453.helloclass;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import ftmk.bitp3453.helloclass.databinding.ActivityRegistrationBinding;


public class RegistrationActivity extends AppCompatActivity {

    DatePickerDialog datePicker;
    ActivityRegistrationBinding binding;

    final static public String USER_KEY = "objUser";


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.edtFullName.addTextChangedListener(RegTextWatcher);
        binding.edtPwd.addTextChangedListener(RegTextWatcher);
        binding.edtEmail.addTextChangedListener(RegTextWatcher);
        binding.edtBirthdate.addTextChangedListener(RegTextWatcher);
        binding.edtAddress.addTextChangedListener(RegTextWatcher);
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


    }
    private TextWatcher RegTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameInput = binding.edtFullName.getText().toString().trim();
            String pwdInput = binding.edtPwd.getText().toString().trim();
            String emailInput = binding.edtEmail.getText().toString().trim();
            String birthInput = binding.edtBirthdate.getText().toString().trim();
            String addressInput = binding.edtAddress.getText().toString().trim();

            binding.fabAddUser.setEnabled(!nameInput.isEmpty() && !pwdInput.isEmpty() &&
                    !emailInput.isEmpty() && !birthInput.isEmpty() && !addressInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



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
        String strEmail =binding.edtEmail.getText().toString();
        String strBirth = binding.edtBirthdate.getText().toString();
        String strAddress = binding.edtAddress.getText().toString();
        String strGender ="";

        if(binding.rbMale.isChecked())
            strGender = binding.rbMale.getText().toString();
        else if(binding.rbFemale.isChecked())
            strGender = binding.rbFemale.getText().toString();

        String finalStrGender = strGender;

        User user = new User(strFullName,strAddress,strPwd,strBirth,strEmail,strGender);
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(USER_KEY, user.fullname + "\n\n" + user.pwd + "\n\n" + user.email + "\n\n"
                + user.birthdate + "\n\n" + user.address + "\n\n" + user.gender);
        startActivity(intent);
    }
}