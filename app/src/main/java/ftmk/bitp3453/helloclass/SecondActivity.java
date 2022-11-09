package ftmk.bitp3453.helloclass;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class SecondActivity extends AppCompatActivity {
    TextView movedInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Second Activity");
        movedInformation = findViewById(R.id.copyInfo);
        Serializable user = getIntent().getSerializableExtra(RegistrationActivity.USER_KEY);
        movedInformation.setText("This is your Information \n\n\n" + user);
    }
}