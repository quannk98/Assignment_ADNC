package quannkph29999.fpoly.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import quannkph29999.fpoly.assignment.Adapter.ViewPager2Adapter;
import quannkph29999.fpoly.assignment.Fragment.Music_Fragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private NavigationBarView bottomNavi;
    private ViewPager2Adapter viewPager2Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavi = findViewById(R.id.bottom_Navi);
        viewPager2 = findViewById(R.id.viewpager2);
        Bundle check = getIntent().getExtras();
        boolean checkdn = check.getBoolean("khach");
        if(checkdn == true){
            bottomNavi.getMenu().clear();
            bottomNavi.inflateMenu(R.menu.menu_khach);
        }
        else if(checkdn == false) {
            bottomNavi.getMenu().clear();
            bottomNavi.inflateMenu(R.menu.menu_bottom_navi);
        }
        setUpViewPager2();
        bottomNavi.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_music:

                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.action_newspaper:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.action_person:
                        viewPager2.setCurrentItem(2);
                        break;
                }


                return true;
            }
        });



    }
    private void setUpViewPager2(){
           viewPager2Adapter = new ViewPager2Adapter(MainActivity.this);
           viewPager2.setAdapter(viewPager2Adapter);
           viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
               @Override
               public void onPageSelected(int position) {
                   super.onPageSelected(position);
                   switch (position){
                       case 0:
                           setTitle("QUÂN-MP3");

                           bottomNavi.getMenu().findItem(R.id.action_music).setChecked(true);
                           break;
                       case 1:
                           setTitle("QUÂN-NEWS");
                           bottomNavi.getMenu().findItem(R.id.action_newspaper).setChecked(true);
                           break;
                       case 2:
                           setTitle("Thông Tin Cá Nhân");
                           bottomNavi.getMenu().findItem(R.id.action_person).setChecked(true);
                           break;
                   }
               }
           });
    }
}