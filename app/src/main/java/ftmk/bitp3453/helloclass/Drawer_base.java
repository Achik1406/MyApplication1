package ftmk.bitp3453.helloclass;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Drawer_base extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base,null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle  = new ActionBarDrawerToggle(this, drawerLayout,R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId())
        {
            case R.id.nav_main_activity:
                startActivity(new Intent(this,FirstActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_camera_activity:
                startActivity(new Intent(this,ThreadedActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_settings:
                Toast.makeText(getApplicationContext(),"You navigated to settings screen",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_logout:
                Toast.makeText(getApplicationContext(),"You are Logged out! see ya!",Toast.LENGTH_SHORT).show();
                return true;
        }

        return false;
    }

    protected void allocateActivityTitle(String StringTitle){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(StringTitle);
        }
    }
}