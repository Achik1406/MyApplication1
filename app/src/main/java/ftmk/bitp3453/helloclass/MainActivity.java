package ftmk.bitp3453.helloclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void NavigationView(View view){
        startActivity(new Intent(this,MainActivity2.class));
    }
}