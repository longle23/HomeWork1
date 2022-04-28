package fis.ihrp.longlh.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import fis.ihrp.longlh.homework1.databinding.ActivityDangKyNghiBinding;
import fis.ihrp.longlh.homework1.databinding.ActivityMainBinding;
import fis.ihrp.longlh.homework1.model.Funcion;
import fis.ihrp.longlh.homework1.model.ListFuncionRequest;
import fis.ihrp.longlh.homework1.model.LoaiNghiRequest;
import fis.ihrp.longlh.homework1.model.LoaiNghiResponse;
import fis.ihrp.longlh.homework1.model.NguoiKiemDuyetRequest;
import fis.ihrp.longlh.homework1.model.NguoiKiemDuyetResponse;
import fis.ihrp.longlh.homework1.service.RetrofitClient;
import fis.ihrp.longlh.homework1.service.UserService;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyNghiActivity extends AppCompatActivity {

    private LoaiNghiResponse loaiNghiResponse;
    private NguoiKiemDuyetResponse nguoiKiemDuyetResponse;

    // Khai bao bien binding
    private ActivityDangKyNghiBinding binding;

    // Khai bao bien Service
    private UserService userService4;

    // ArrayList trong AutoCompleteTextView
    private List<String> listLoaiNghi = new ArrayList<>();
    private ArrayAdapter<String> adapterLoaiNghi;

    // Khai báo Calendar
    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dang_ky_nghi);


        // Goi ham inflate
        binding = ActivityDangKyNghiBinding.inflate(getLayoutInflater());
        // Thay the đối số truyen vao setContentView
        setContentView(binding.getRoot());

        // Setting thanh toolbar
        Toolbar toolbar = findViewById(R.id.toolbarDKNghi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Call API Loai Nghi
        goiAPI_GetLoaiNghi(layToken());

        // Set Adapter Loai Nghi
        adapterLoaiNghi = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listLoaiNghi);
        binding.dangKyNghiAutoCompleteLoaiNghi.setAdapter(adapterLoaiNghi);

        // Setup Calendar Tu Ngay
        DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar.set(Calendar.YEAR, i);
                myCalendar.set(Calendar.MONTH, i1);
                myCalendar.set(Calendar.DAY_OF_MONTH, i2);
                updateLabelTuNgay();
            }
        };

        binding.dangKyNghiEditTextTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DangKyNghiActivity.this, date1, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Setup Calendar Den Ngay
        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar.set(Calendar.YEAR, i);
                myCalendar.set(Calendar.MONTH, i1);
                myCalendar.set(Calendar.DAY_OF_MONTH, i2);
                updateLabelDenNgay();
            }
        };

        binding.dangKyNghiEditTextDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DangKyNghiActivity.this, date2, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Call API Nguoi Kiem Duyet
        goiAPI_NguoiKiemDuyet(layToken());

        // Set su kien Click Tinh Phep
        binding.dangKyNghiButtonTinhPhep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    // Ham tra ve Token de goi API
    private String layToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppShared", MODE_PRIVATE);
//        sharedPreferences.getString("token", "");
        String token = sharedPreferences.getString("token", "");
//        Toast.makeText(DanhSachNhanVienActivity.this, token, Toast.LENGTH_SHORT).show();
        return token;
    }

    // Ham Goi API lay Loai Nghi
    // Load du lieu Loai nghi vao View
    private void goiAPI_GetLoaiNghi(String token) {
        // Khoi tao API
        userService4 = RetrofitClient.getClient();

        // Set du lieu vao DataHeader
        List<ListFuncionRequest.Param> params = new ArrayList<>();
        ListFuncionRequest.Param param = new ListFuncionRequest.Param();
        param.setF("1");
        params.add(param);

        // Khoi tao Request Model
        LoaiNghiRequest model = new LoaiNghiRequest();
        model.setAppVersion("V33.PNJ.20200827.2");
        model.setDataHeader(params);
        model.setLangID("vn");
        model.setStoken(token);

        userService4.loaiNghiFuncion(model).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // Test ket qua response tra ve du lieu la gi
//                Log.d("TAG", "onResponse: " + bodyToString(call.request().body()));

                try {
                    // Lay các trường trong Json tra ve
                    String jsonResponse = response.body().toString();

                    JSONObject jsonObject = new JSONObject(jsonResponse);

                    JSONArray dataItemResponse = jsonObject.getJSONArray("dataItem");
                    Log.d("TAG LoaiNghi", "onResponse: " + dataItemResponse);

                    for (int i = 0; i < dataItemResponse.length(); i++) {
                        JSONObject jsonObject1 = dataItemResponse.getJSONObject(i);

                        loaiNghiResponse = new LoaiNghiResponse();

                        String id = jsonObject1.getString("id");
                        String nameEN = jsonObject1.getString("nameEN");

                        listLoaiNghi.add(nameEN);
                    }
                    adapterLoaiNghi.notifyDataSetChanged();

                } catch (Exception e) {
                    Log.d("TAG Message", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    // Lay request tra ve
//    private String bodyToString(final RequestBody request) {
//        try {
//            final RequestBody copy = request;
//            final Buffer buffer = new Buffer();
//            if (copy != null)
//                copy.writeTo(buffer);
//            else
//                return "";
//            return buffer.readUtf8();
//        } catch (final IOException e) {
//            return "did not work";
//        }
//    }

    private void updateLabelTuNgay() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        binding.dangKyNghiEditTextTuNgay.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void updateLabelDenNgay() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        binding.dangKyNghiEditTextDenNgay.setText(dateFormat.format(myCalendar.getTime()));
    }

    // Ham Goi API lay nguoi Kiem Duyet
    // Load du lieu Nguoi Kiem Duyet vao View
    private void goiAPI_NguoiKiemDuyet(String token) {
        // Khoi tao API
        userService4 = RetrofitClient.getClient();

        // Set du lieu vao DataHeader
        List<ListFuncionRequest.Param> params = new ArrayList<>();
        ListFuncionRequest.Param param = new ListFuncionRequest.Param();
        param.setF("1");
        params.add(param);

        // Khoi tao Request Model
        NguoiKiemDuyetRequest model2 = new NguoiKiemDuyetRequest();
        model2.setAppVersion("V33.PNJ.20200827.2");
        model2.setDataHeader(params);
        model2.setLangID("vn");
        model2.setStoken(token);

        userService4.loadNguoiKiemDuyet(model2).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // Test ket qua response tra ve du lieu la gi
//                Log.d("TAG", "onResponse: " + bodyToString(call.request().body()));

                try {
                    // Lay các trường trong Json tra ve
                    String jsonResponse = response.body().toString();

                    JSONObject jsonObject = new JSONObject(jsonResponse);

                    JSONArray dataItemResponse = jsonObject.getJSONArray("dataItem");
//                    Log.d("TAG KiemDuyet", "onResponse: " + dataItemResponse);

                    for (int i = 0; i < dataItemResponse.length(); i++) {
                        JSONObject jsonObject1 = dataItemResponse.getJSONObject(i);

                        nguoiKiemDuyetResponse = new NguoiKiemDuyetResponse();

                        String approverID = jsonObject1.getString("approverID");
                        String approverName = jsonObject1.getString("approverName");

                        binding.dangKyNghiEditTextNguoiDuyet.setText(approverName);
                    }
                } catch (Exception e) {
                    Log.d("TAG Message", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }


}