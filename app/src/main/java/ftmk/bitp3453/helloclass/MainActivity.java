package ftmk.bitp3453.helloclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtVw1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //txtVw1 = findViewById(R.id.textView);
    }

    public void FirstActivity(View view) {

        startActivity(new Intent(this,FirstActivity.class));
    }

    public void ThreadedActivity(View view){
        startActivity(new Intent(this,ThreadedActivity.class));

    }

    public void RegistrationActivity(View view){
        startActivity(new Intent(this,RegistrationActivity.class));

    }
    public void StudentMainActivity(View view){
        startActivity(new Intent(this,StudentMainActivity.class));
    }
}