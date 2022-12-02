package ftmk.bitp3453.helloclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import ftmk.bitp3453.helloclass.databinding.ActivityFirstBinding;

public class FirstActivity extends Drawer_base{

    TextView txtvwAge;
    EditText edtName,edtYear;
    Button btnClick;
    int year;

    ActivityFirstBinding activityFirstBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFirstBinding =activityFirstBinding.inflate(getLayoutInflater());
        setContentView(activityFirstBinding.getRoot());
        allocateActivityTitle("FirstActivity");


        txtvwAge = (TextView) findViewById(R.id.txtvwAge);
        edtName = (EditText) findViewById(R.id.edtTxtName);
        edtYear = (EditText) findViewById(R.id.edtYear);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

    }


    public void fnThreadActivity(View Vw)
    {
        Intent intent = new Intent(this , ThreadedActivity.class);
        String strMsg = ((EditText) findViewById(R.id.edtTxtName)).getText().toString();
        intent.putExtra("varStr1", strMsg);
        startActivity(intent);
    }
    public void fnGreet(View vw)
    {
        String strName = edtName.getText().toString();
        String Age = edtYear.getText().toString();
        int i =Integer.parseInt(Age);
        int age = year-i;

        txtvwAge.setText("Helloooo and Welcome " + strName +". You age is : "+ age);
    }

    public void BackToMain(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

}