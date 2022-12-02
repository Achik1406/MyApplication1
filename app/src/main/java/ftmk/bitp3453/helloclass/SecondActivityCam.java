package ftmk.bitp3453.helloclass;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ftmk.bitp3453.helloclass.databinding.ActivitySecondCamBinding;

public class SecondActivityCam extends AppCompatActivity {

    Executor executor;
    Handler handler;
    Bitmap bitmap = null;
    ImageView imageView;

    Button buttonClick;
    ActivitySecondCamBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondCamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        executor= Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
        buttonClick = (Button) findViewById(R.id.btnAsyncTask);
        imageView = (ImageView) findViewById(R.id.imgVwSelfie);

        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if(networkInfo != null  && networkInfo.isConnected())
        {
            // the background task executor and handler is done within this checking
            //….
            //….
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Network!! Please add data plan or connect to wifi network!", Toast.LENGTH_SHORT).show();
        }

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL ImageURL = new URL("https://ftmk.utem.edu.my/web/wp-content/uploads/2020/02/cropped-Logo-FTMK.png");
                            HttpURLConnection connection = (HttpURLConnection) ImageURL.openConnection();
                            connection.setDoInput(true);
                            connection.connect();
                            InputStream inputStream = connection.getInputStream();
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inPreferredConfig = Bitmap.Config.RGB_565;
                            bitmap = BitmapFactory.decodeStream(inputStream,null,options);


                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {  // this is to update main thread -- UI
                            @Override
                            public void run() {
                                binding.imgVwSelfie.setImageBitmap(bitmap);
                            }
                        });
                    }
                });

            }
        });


    }

}