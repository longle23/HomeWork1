package fis.ihrp.longlh.homework1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class DanhSachNhanVienActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_nhan_vien);


        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.navigation_timKiem:
                        fragment = new DanhSachNhanVienFragment();
                        loadFragment(fragment);
                        return true;

                    case R.id.navigation_dangKy:
                        fragment = new DangKyFragment();
                        loadFragment(fragment);
                        return true;

                    case R.id.navigation_donNghi:
                        fragment = new DangKyFragment();
                        loadFragment(fragment);
                        return true;

                }
                return false;
            }
        });

        // Set fragment mac dinh
        loadFragment(new DanhSachNhanVienFragment());

    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}