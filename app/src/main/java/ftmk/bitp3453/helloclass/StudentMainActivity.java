package ftmk.bitp3453.helloclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Vector;

import ftmk.bitp3453.helloclass.databinding.ActivityStudentMainBinding;

public class StudentMainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
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

        binding.fabAdd.setOnClickListener(this:: fnAdd );

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