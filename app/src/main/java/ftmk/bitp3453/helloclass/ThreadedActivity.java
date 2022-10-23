package ftmk.bitp3453.helloclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ThreadedActivity extends AppCompatActivity {


    ImageView iv;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaded);


        //get variabel from previous activity
        iv = (ImageView)findViewById(R.id.imgVwProfile);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setText("Hello There!!");
    }

    public void fnTakePic(View Vw)
    {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent (android.provider.MediaStore.ACTION_IMAGE_CAPTURE);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText(tv1.getText().toString() + ".... \nThis Is Your Picture Hehehehe");
                    }
                });
            }
        };
        Thread thr = new Thread(run);
        thr.start();
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bp);
    }
}