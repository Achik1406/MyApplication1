package ftmk.bitp3453.helloclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ftmk.bitp3453.helloclass.databinding.ActivityMainBinding;

public class MainActivity extends Drawer_base {

    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding =activityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        allocateActivityTitle("MainActivity");

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
    public void SecondActivityCam(View view){
        startActivity(new Intent(this,SecondActivityCam.class));
    }

    public void SearchStudentActivity(View view){
        startActivity(new Intent(this,SearchStudentActivity.class));
    }
    public void GetRestAPI(View view){
        startActivity(new Intent(this,GetRestAPI.class));
    }

}