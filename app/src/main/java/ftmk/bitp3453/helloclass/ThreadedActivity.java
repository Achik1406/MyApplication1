package ftmk.bitp3453.helloclass;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ftmk.bitp3453.helloclass.databinding.ActivityThreadedBinding;

public class ThreadedActivity extends Drawer_base {

    ActivityThreadedBinding activityThreadedBinding;
    ImageView iv;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityThreadedBinding =activityThreadedBinding.inflate(getLayoutInflater());
        setContentView(activityThreadedBinding.getRoot());
        allocateActivityTitle("ActivityThread");


        //get variabel from previous activity
        iv = (ImageView)findViewById(R.id.imgVwProfile);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setText("Hello There!!");
    }


    public void fnTakePic(View vw)
    {
        Runnable run =new Runnable() {
            @Override
            public void run() {
                // 1000 auto-generated method stub

                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //1000 auto-generated method stub
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

    public void BackToMain(View view)
    {
        startActivity(new Intent(this,MainActivity.class));
    }
}