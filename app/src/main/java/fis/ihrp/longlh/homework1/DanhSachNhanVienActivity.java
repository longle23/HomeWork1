package fis.ihrp.longlh.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
//import android.support.widget.SearchView;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fis.ihrp.longlh.homework1.adapter.EmployeeAdapter;
import fis.ihrp.longlh.homework1.databinding.ActivityDanhSachNhanVienBinding;
import fis.ihrp.longlh.homework1.model.DataHeader;
import fis.ihrp.longlh.homework1.model.Employee;
import fis.ihrp.longlh.homework1.model.FindEmployeeRequest;
import fis.ihrp.longlh.homework1.service.RetrofitClient;
import fis.ihrp.longlh.homework1.service.UserService;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DanhSachNhanVienActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Employee employee;
    private ArrayList<Employee> listEmployee;
    private EmployeeAdapter employeeAdapter;

    // Khai bao bien binding
    private ActivityDanhSachNhanVienBinding binding;

    // Khai bao bien Service
    private UserService userService2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_danh_sach_nhan_vien);

        // Goi ham inflate
        binding = ActivityDanhSachNhanVienBinding.inflate(getLayoutInflater());
        // Thay the đối số truyen vao setContentView
        setContentView(binding.getRoot());

        listEmployee = new ArrayList<>();

//        Employee employee1 = new Employee();
//        employee1.setEmpID("1");
//        employee1.setGender("1");
//        employee1.setItem1("Hoang Long");
//        employee1.setItem2("Intern");
//        employee1.setItem4("HH");
//        employee1.setAvatar("");
//        listEmployee.add(employee1);
//        listEmployee.add(employee1);
//        listEmployee.add(employee1);
//        listEmployee.add(employee1);

        goiAPI_HienThiNhanVien(layToken());

        employeeAdapter = new EmployeeAdapter(listEmployee, this);
        binding.danhSachNhanVienRecyclerViewListNV.setAdapter(employeeAdapter);
        binding.danhSachNhanVienRecyclerViewListNV.setLayoutManager(new LinearLayoutManager(this));

        employeeAdapter.notifyDataSetChanged();

        binding.mainEditTextTimKiemNV.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;

                    goiAPI_HienThiNhanVien(layToken());

                    // Click vao tim kiem để ẩn ban phim
                    binding.mainEditTextTimKiemNV.clearFocus();
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(binding.mainEditTextTimKiemNV.getWindowToken(), 0);
                }
                return handled;
            }
        });

    }

    // Ham tra ve Token de goi API
    private String layToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppShared", MODE_PRIVATE);
//        sharedPreferences.getString("token", "");
        String token = sharedPreferences.getString("token", "");
//        Toast.makeText(DanhSachNhanVienActivity.this, temp, Toast.LENGTH_SHORT).show();
        return token;
    }

    // Ham Goi API Tim Kiem Nhan Vien
    // Load du lieu nhan vien vao View
    private void goiAPI_HienThiNhanVien(String token) {
        // Khoi tao API
        userService2 = RetrofitClient.getClient();

        // Set du lieu vao DataHeader
        List<FindEmployeeRequest.Param> params = new ArrayList<>();
        FindEmployeeRequest.Param param = new FindEmployeeRequest.Param();
//        param.setF("Dương");
        param.setF(binding.mainEditTextTimKiemNV.getText().toString());
        params.add(param);

        // Khoi tao Request Model
        FindEmployeeRequest model = new FindEmployeeRequest();
        model.setAppVersion("V33.PNJ.20200827.2");
        model.setDataHeader(params);
        model.setLangID("vn");
        model.setOs("1");
        model.setStoken(token);

        userService2.findEmployee(model).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // Test ket qua response tra ve du lieu la gi
//                Log.d("TAG", "onResponse: "+ bodyToString(call.request().body()));

                // Lay các trường trong Json tra ve
                String jsonResponse = response.body().toString();

                try {
                    JSONObject jsonObject = new JSONObject(jsonResponse);

//                    Object dataItemResponse = jsonObject.getJSONObject("dataItem");

                    JSONArray dataItemResponse = jsonObject.getJSONArray("dataItem");
                    Log.d("TAG", "onResponse: " + dataItemResponse);

                    if (dataItemResponse.length() == 0) {
                        thongBaoKhongCoDuLieu();
                    } else {
                        // Refresh lại cái list
                        listEmployee.clear();
                        Log.d("TAG", "Response: " + listEmployee.size());

                        hienDSKhiCoDuLieu();

                        for (int i = 0; i < dataItemResponse.length(); i++) {
                            JSONObject jsonObject1 = dataItemResponse.getJSONObject(i);

                            employee = new Employee();

                            String item1 = jsonObject1.getString("item1");
                            String item2 = jsonObject1.getString("item2");
                            String item4 = jsonObject1.getString("item4");
                            String avatar = jsonObject1.getString("avatar");
                            employee.setItem1(item1);
                            employee.setItem2(item2);
                            employee.setItem4(item4);
                            employee.setAvatar(avatar);
                            listEmployee.add(employee);
                        }

                    }
                    employeeAdapter.notifyDataSetChanged();
                    Log.d("TAG", "onResponse: " + listEmployee.size());

                    binding.danhSachNhanVienTextViewKetQua.setText(listEmployee.size() + " kết quả");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    // Lay request tra ve
    private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    private void thongBaoKhongCoDuLieu() {
        binding.danhSachNhanVienTextViewKetQua.setVisibility(View.INVISIBLE);
        binding.danhSachNhanVienRecyclerViewListNV.setVisibility(View.GONE);
        binding.danhSachNhanVienImageViewNotify.setVisibility(View.VISIBLE);
        binding.danhSachNhanVienEditTextNotify.setVisibility(View.VISIBLE);
    }

    private void hienDSKhiCoDuLieu(){
        binding.danhSachNhanVienTextViewKetQua.setVisibility(View.VISIBLE);
        binding.danhSachNhanVienRecyclerViewListNV.setVisibility(View.VISIBLE);
        binding.danhSachNhanVienImageViewNotify.setVisibility(View.GONE);
        binding.danhSachNhanVienEditTextNotify.setVisibility(View.GONE);
    }

}