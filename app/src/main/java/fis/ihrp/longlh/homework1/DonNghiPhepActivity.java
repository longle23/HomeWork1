package fis.ihrp.longlh.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import fis.ihrp.longlh.homework1.adapter.EmployeeAdapter;
import fis.ihrp.longlh.homework1.adapter.LoaiNghiAdapter;
import fis.ihrp.longlh.homework1.adapter.TinhTrangAdapter;
import fis.ihrp.longlh.homework1.databinding.ActivityDangKyNghiBinding;
import fis.ihrp.longlh.homework1.databinding.ActivityDonNghiPhepBinding;
import fis.ihrp.longlh.homework1.model.Employee;
import fis.ihrp.longlh.homework1.model.LoaiNghiRequest;
import fis.ihrp.longlh.homework1.model.LoaiNghiResponse;
import fis.ihrp.longlh.homework1.model.TinhTrangRequest;
import fis.ihrp.longlh.homework1.model.TinhTrangResponse;
import fis.ihrp.longlh.homework1.myinterface.TinhTrangOnclick;
import fis.ihrp.longlh.homework1.service.RetrofitClient;
import fis.ihrp.longlh.homework1.service.UserService;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonNghiPhepActivity extends AppCompatActivity implements TinhTrangOnclick {

    // Khai bao bien binding
    private ActivityDonNghiPhepBinding binding;

    // Khai bao bien Service
    private UserService userService5;

    // Set Adapter Loai Nghi
    private LoaiNghiResponse loaiNghiResponse;
    private ArrayList<LoaiNghiResponse> listLoaiNghi = new ArrayList<>();
    private LoaiNghiAdapter loaiNghiAdapter;

    // Khai báo Calendar
    final Calendar myCalendar = Calendar.getInstance();

    // Khai bao bien idLoaiNghi
    String idLoaiNghi;

    // Set Adapter Tinh Trang
    private TinhTrangResponse tinhTrangResponse;
    private ArrayList<TinhTrangResponse> listTinhTrang = new ArrayList<>();
    private TinhTrangAdapter tinhTrangAdapter;

    // BottomSheetDialog
    private BottomSheetDialog bottomSheetDialogLoaiNghi;
    private BottomSheetDialog bottomSheetDialogTinhTrang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_don_nghi_phep);

        // Goi ham inflate
        binding = ActivityDonNghiPhepBinding.inflate(getLayoutInflater());
        // Thay the đối số truyen vao setContentView
        setContentView(binding.getRoot());

        // Setting thanh toolbar
        Toolbar toolbar = findViewById(R.id.toolbarDonNghiPhep);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        binding.toolbarDonNghiPhep.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Call API Loai Nghi
        goiAPI_GetLoaiNghi(layToken());
        // Set up Adapter Loai Nghi
        initBottomSheetDialog1(listLoaiNghi);
        // Show Bottom Dialog Loai Nghi
        showBottomSheetDialog1();

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

        binding.donNghiPhepEditTextTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DonNghiPhepActivity.this, date1, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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

        binding.donNghiPhepEditTextDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DonNghiPhepActivity.this, date2, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Call API Tinh Trang
        goiAPI_GetTinhTrang(layToken());
        // Set up Adapter Tinh Trang
        initBottomSheetDialog2(listTinhTrang);
        // Show Bottom Dialog Tinh Trang
        showBottomSheetDialog2();

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
        userService5 = RetrofitClient.getClient();

        // Set du lieu vao DataHeader
        List<LoaiNghiRequest.Param> params = new ArrayList<>();
        LoaiNghiRequest.Param param = new LoaiNghiRequest.Param();
        param.setF("1");
        params.add(param);

        // Khoi tao Request Model
        LoaiNghiRequest model = new LoaiNghiRequest();
        model.setAppVersion("V33.PNJ.20200827.2");
        model.setDataHeader(params);
        model.setLangID("vn");
        model.setStoken(token);

        userService5.loaiNghiFuncion(model).enqueue(new Callback<JsonObject>() {
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

                        loaiNghiResponse.setId(id);
                        loaiNghiResponse.setNameEN(nameEN);
                        listLoaiNghi.add(loaiNghiResponse);
                    }
                    initBottomSheetDialog1(listLoaiNghi);

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

    private void updateLabelTuNgay() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        binding.donNghiPhepEditTextTuNgay.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void updateLabelDenNgay() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        binding.donNghiPhepEditTextDenNgay.setText(dateFormat.format(myCalendar.getTime()));
    }


    // Ham Goi API lay Tinh Trang
    // Load du lieu Tinh Trang vao View
    private void goiAPI_GetTinhTrang(String token) {
        // Khoi tao API
        userService5 = RetrofitClient.getClient();

        // Set du lieu vao DataHeader
        List<TinhTrangRequest.Param> params = new ArrayList<>();
        TinhTrangRequest.Param param = new TinhTrangRequest.Param();
        param.setF("2");
        params.add(param);

        // Khoi tao Request Model
        TinhTrangRequest model = new TinhTrangRequest();
        model.setAppVersion("V33.PNJ.20200827.2");
        model.setDataHeader(params);
        model.setLangID("vn");
        model.setStoken(token);

        userService5.tinhTrang(model).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // Test ket qua response tra ve du lieu la gi
//                Log.d("TAG", "onResponse: " + bodyToString(call.request().body()));

                try {
                    // Lay các trường trong Json tra ve
                    String jsonResponse = response.body().toString();

                    JSONObject jsonObject = new JSONObject(jsonResponse);

                    JSONArray dataItemResponse = jsonObject.getJSONArray("dataItem");
                    Log.d("TAG TinhTrang", "onResponse: " + dataItemResponse);

                    for (int i = 0; i < dataItemResponse.length(); i++) {
                        JSONObject jsonObject1 = dataItemResponse.getJSONObject(i);

                        tinhTrangResponse = new TinhTrangResponse();

                        String item1 = jsonObject1.getString("item1");
                        String item2 = jsonObject1.getString("item2");

                        tinhTrangResponse.setItem1(item1);
                        tinhTrangResponse.setItem2(item2);
                        listTinhTrang.add(tinhTrangResponse);
                    }
                    initBottomSheetDialog2(listTinhTrang);

                } catch (Exception e) {
                    Log.d("TAG Message", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    // Setup Adapter Loai Nghi
    private void initBottomSheetDialog1(ArrayList<LoaiNghiResponse> listLoaiNghi) {
        bottomSheetDialogLoaiNghi = new BottomSheetDialog(DonNghiPhepActivity.this);
        bottomSheetDialogLoaiNghi.setContentView(R.layout.bottom_sheet_dialog_loainghi);

        RecyclerView bottom_recyclerView_loaiNghi = bottomSheetDialogLoaiNghi.findViewById(R.id.bottom_recyclerView_loaiNghi);
        loaiNghiAdapter = new LoaiNghiAdapter(listLoaiNghi, DonNghiPhepActivity.this, this);
        bottom_recyclerView_loaiNghi.setAdapter(loaiNghiAdapter);
        bottom_recyclerView_loaiNghi.setLayoutManager(new LinearLayoutManager(DonNghiPhepActivity.this));
    }

    // Setup Adapter Tinh Trang
    private void initBottomSheetDialog2(ArrayList<TinhTrangResponse> listTinhTrang) {
        bottomSheetDialogTinhTrang = new BottomSheetDialog(DonNghiPhepActivity.this);
        bottomSheetDialogTinhTrang.setContentView(R.layout.bottom_sheet_dialog_tinhtrang);

        RecyclerView bottom_recyclerView_tinhTrang = bottomSheetDialogTinhTrang.findViewById(R.id.bottom_recyclerView_tinhTrang);
        tinhTrangAdapter = new TinhTrangAdapter(listTinhTrang, DonNghiPhepActivity.this, this);
        bottom_recyclerView_tinhTrang.setAdapter(tinhTrangAdapter);
        bottom_recyclerView_tinhTrang.setLayoutManager(new LinearLayoutManager(DonNghiPhepActivity.this));
    }

    // Show Bottom Sheet Dialog Loai Nghi
    private void showBottomSheetDialog1() {
        binding.donNghiPhepEditTextLoaiNghi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialogLoaiNghi.show();
            }
        });
    }

    // Show Bottom Sheet Dialog Tinh Trang
    private void showBottomSheetDialog2() {
        binding.donNghiPhepEditTextTinhTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialogTinhTrang.show();
            }
        });
    }


    @Override
    public void OnItemSelected(String id, String name, String codeId) {
        switch (codeId){
            case "100":{
//                Toast.makeText(this, id + name, Toast.LENGTH_SHORT).show();
                binding.donNghiPhepEditTextLoaiNghi.setText(name);

                String idLoaiNghi = id;

                bottomSheetDialogLoaiNghi.dismiss();
                break;
            }

            case "101":{
//                Toast.makeText(this, id + " - " + name, Toast.LENGTH_SHORT).show();

                //set EditText tình trạng
                //lưu id vào 1 biến selectedTinhTrang
                //hide bottomSheetDialog
                binding.donNghiPhepEditTextTinhTrang.setText(name);

                String idTinhtrang = id;

                bottomSheetDialogTinhTrang.dismiss();
                break;
            }

        }
    }

}