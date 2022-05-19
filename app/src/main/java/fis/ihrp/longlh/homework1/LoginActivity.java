package fis.ihrp.longlh.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import fis.ihrp.longlh.homework1.databinding.ActivityMainBinding;
import fis.ihrp.longlh.homework1.model.LoginRequest;
import fis.ihrp.longlh.homework1.service.RetrofitClient;
import fis.ihrp.longlh.homework1.service.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener {

    // Khai bao bien binding
    private ActivityMainBinding binding;

    // Khai bao bien Service
    private UserService userService;

    // SharedPreferences
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);


        // Goi ham inflate
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // Thay the đối số truyen vao setContentView
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("AppShared", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        setupFloatingLabelErrorTenDangNhap();

        setupFloatingLabelErrorMatKhau();

        xuLyThongTinDangNhap();
    }


    private void setupFloatingLabelErrorTenDangNhap() {
        binding.mainInputLayoutTenDangNhap.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.mainInputLayoutTenDangNhap.setError("Tên đăng nhập không được trống");
                    binding.mainInputLayoutTenDangNhap.setErrorEnabled(true);
                } else {
                    binding.mainInputLayoutTenDangNhap.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setupFloatingLabelErrorMatKhau() {
        binding.mainInputLayoutMatKhau.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.mainInputLayoutMatKhau.setError("Vui lòng nhập mật khẩu");
                    binding.mainInputLayoutMatKhau.setErrorEnabled(true);
                } else {
                    binding.mainInputLayoutMatKhau.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void xuLyThongTinDangNhap() {
        binding.mainButtonDangNhap.setOnClickListener(this);
    }

    // Thực hiện đăng nhập vào hệ thống
    private void xuLyDangNhap(String username, String password) {
        showProcess();

        // Khởi tạo API
        userService = RetrofitClient.getClient();

        // Khởi tạo Request Model
        LoginRequest model = new LoginRequest();
        model.setUsername(username);
        model.setPassword(password);
        model.setOs("1");
        model.setDeviceID("My device ID");
        model.setVersion(1);
        model.setLangID("VN");

        // Thực thi API
        userService.loginUser(model).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("XuLyDangNhap", "onResponse: " + response.body().toString());

                // Lay các trường trong Json tra ve
                String jsonResponse = response.body().toString();
                JSONObject jsonObject = null;
                try {
                    hideProcess();

                    Boolean codeResponse = null;
                    String messageResponse = "";

                    jsonObject = new JSONObject(jsonResponse);
                    codeResponse = jsonObject.getBoolean("code");
                    messageResponse = jsonObject.getString("message");

                    // Xu li chuyen sang man hinh khac
                    if (codeResponse == true) {
                        Intent intent = new Intent(LoginActivity.this, DanhSachNhanVienActivity.class);
                        startActivity(intent);

                        // Lấy message respone tu API tra ve, de show danh sach man hinh moi
                        editor.putString("token", messageResponse);
                        editor.commit();

                    } else {
                        binding.mainTextViewThongBao.setText(messageResponse);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Hide process
                // Thông báo lỗi
                hideProcess();
            }

        });

//        hideProcess();
    }

    private void showProcess() {
        binding.mainProgressBarXuLy.setVisibility(View.VISIBLE);
        binding.mainButtonDangNhap.setVisibility(View.INVISIBLE);
    }

    private void hideProcess() {
        binding.mainProgressBarXuLy.setVisibility(View.GONE);
        binding.mainButtonDangNhap.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View view) {
        int viewID = view.getId();
        switch (viewID){
            case R.id.main_button_dangNhap:{
                String username = binding.mainEditTextTenDangNhap.getText().toString().trim();
                String password = binding.mainEditTextMatKhau.getText().toString().trim();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    xuLyDangNhap(username, password);
                }
                break;
            }

        }

    }
}