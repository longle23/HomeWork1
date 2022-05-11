package fis.ihrp.longlh.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

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

import fis.ihrp.longlh.homework1.adapter.DonNghiDaTaoAdapter;
import fis.ihrp.longlh.homework1.adapter.LoaiNghiAdapter;
import fis.ihrp.longlh.homework1.adapter.TinhTrangAdapter;
import fis.ihrp.longlh.homework1.databinding.ActivityDonNghiPhepBinding;
import fis.ihrp.longlh.homework1.model.ChiTietDonNghiRequest;
import fis.ihrp.longlh.homework1.model.ChiTietDonNghiResponse;
import fis.ihrp.longlh.homework1.model.LoaiNghiRequest;
import fis.ihrp.longlh.homework1.model.LoaiNghiResponse;
import fis.ihrp.longlh.homework1.model.TimDonNghiRequest;
import fis.ihrp.longlh.homework1.model.TimDonNghiResponse;
import fis.ihrp.longlh.homework1.model.TinhTrangRequest;
import fis.ihrp.longlh.homework1.model.TinhTrangResponse;
import fis.ihrp.longlh.homework1.myinterface.ChiTietDonOnclick;
import fis.ihrp.longlh.homework1.myinterface.TinhTrangOnclick;
import fis.ihrp.longlh.homework1.service.RetrofitClient;
import fis.ihrp.longlh.homework1.service.UserService;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachDonNghiActivity extends AppCompatActivity implements TinhTrangOnclick, ChiTietDonOnclick {

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

    // Khai bao bien idTinhTrang
    String idTinhTrang;

    // Khai bao bien ID Don Nghi Da Tao
    String leaveRecordID;
    // khai bao bien ID trang thai Don Nghi
    String statusID;

    // Set Adapter Tinh Trang
    private TinhTrangResponse tinhTrangResponse;
    private ArrayList<TinhTrangResponse> listTinhTrang = new ArrayList<>();
    private TinhTrangAdapter tinhTrangAdapter;

    // BottomSheetDialog
    private BottomSheetDialog bottomSheetDialogLoaiNghi;
    private BottomSheetDialog bottomSheetDialogTinhTrang;

    // Set Adapter Don Nghi Da Tao
    private ArrayList<TimDonNghiResponse> listDonNghi = new ArrayList<>();
    private DonNghiDaTaoAdapter donNghiDaTaoAdapter;

    private TimDonNghiResponse timDonNghiResponse;


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
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

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
                new DatePickerDialog(DanhSachDonNghiActivity.this, date1, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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
                new DatePickerDialog(DanhSachDonNghiActivity.this, date2, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Call API Tinh Trang
        goiAPI_GetTinhTrang(layToken());
        // Set up Adapter Tinh Trang
        initBottomSheetDialog2(listTinhTrang);
        // Show Bottom Dialog Tinh Trang
        showBottomSheetDialog2();

        // Call API Tim Kiem Don
        goiAPI_TimKiemDon(layToken());

        // Set up Adapter Don Nghi Da Tao
        donNghiDaTaoAdapter = new DonNghiDaTaoAdapter(listDonNghi, DanhSachDonNghiActivity.this, this);
        binding.donNghiPhepRecyclerViewListDonNghi.setAdapter(donNghiDaTaoAdapter);
        binding.donNghiPhepRecyclerViewListDonNghi.setLayoutManager(new LinearLayoutManager(this));

        donNghiDaTaoAdapter.notifyDataSetChanged();
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
        bottomSheetDialogLoaiNghi = new BottomSheetDialog(DanhSachDonNghiActivity.this);
        bottomSheetDialogLoaiNghi.setContentView(R.layout.bottom_sheet_dialog_loainghi);

        RecyclerView bottom_recyclerView_loaiNghi = bottomSheetDialogLoaiNghi.findViewById(R.id.bottom_recyclerView_loaiNghi);
        loaiNghiAdapter = new LoaiNghiAdapter(listLoaiNghi, DanhSachDonNghiActivity.this, this);
        bottom_recyclerView_loaiNghi.setAdapter(loaiNghiAdapter);
        bottom_recyclerView_loaiNghi.setLayoutManager(new LinearLayoutManager(DanhSachDonNghiActivity.this));
    }

    // Setup Adapter Tinh Trang
    private void initBottomSheetDialog2(ArrayList<TinhTrangResponse> listTinhTrang) {
        bottomSheetDialogTinhTrang = new BottomSheetDialog(DanhSachDonNghiActivity.this);
        bottomSheetDialogTinhTrang.setContentView(R.layout.bottom_sheet_dialog_tinhtrang);

        RecyclerView bottom_recyclerView_tinhTrang = bottomSheetDialogTinhTrang.findViewById(R.id.bottom_recyclerView_tinhTrang);
        tinhTrangAdapter = new TinhTrangAdapter(listTinhTrang, DanhSachDonNghiActivity.this, this);
        bottom_recyclerView_tinhTrang.setAdapter(tinhTrangAdapter);
        bottom_recyclerView_tinhTrang.setLayoutManager(new LinearLayoutManager(DanhSachDonNghiActivity.this));
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

    // Ham Goi API Tim Kiem Don Nghi
    // Load du lieu Don Nghi Da Tao vao View
    private void goiAPI_TimKiemDon(String token) {
        binding.donNghiPhepButtonTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Lay du lieu de Call API
                String tuNgay = binding.donNghiPhepEditTextTuNgay.getText().toString();
                String denNgay = binding.donNghiPhepEditTextDenNgay.getText().toString();

                //Khoi tao API
                userService5 = RetrofitClient.getClient();

                // Set du lieu vao Adapter
                List<TimDonNghiRequest.Param> params = new ArrayList<>();
                TimDonNghiRequest.Param param = new TimDonNghiRequest.Param();
                param.setFromDate(tuNgay);
                param.setLeaveTypeID(idLoaiNghi);
                param.setStatus(idTinhTrang);
                param.setToDate(denNgay);
                param.setTop("");
                params.add(param);

                // Khoi tao Request Model
                TimDonNghiRequest model = new TimDonNghiRequest();
                model.setAppVersion("V33.PNJ.20200827.2");
                model.setDataHeader(params);
                model.setLangID("vn");
                model.setStoken(token);

                userService5.timKiemDon(model).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        try {
                            // Kiem tra du lieu khi call
                            Log.d("TAG", "onResponse: " + bodyToString(call.request().body()));

                            // Lay các trường trong Json tra ve
                            String jsonResponse = response.body().toString();

                            JSONObject jsonObject = new JSONObject(jsonResponse);

                            JSONArray dataItemResponse = jsonObject.getJSONArray("dataItem");

                            Log.d("TAG", "Don Da Tao: " + dataItemResponse);

                            listDonNghi.clear();

                            for (int i = 0; i < dataItemResponse.length(); i++) {
                                JSONObject jsonObject1 = dataItemResponse.getJSONObject(i);

                                timDonNghiResponse = new TimDonNghiResponse();

                                statusID = jsonObject1.getString("statusID");
                                String status = jsonObject1.getString("status");
                                String leaveName = jsonObject1.getString("leaveName");
                                String duration = jsonObject1.getString("duration");
                                String taken = jsonObject1.getString("taken");
                                String nguoiPheDuyet = jsonObject1.getString("nguoiPheDuyet");
                                leaveRecordID = jsonObject1.getString("leaveRecordID");

                                timDonNghiResponse.setStatusID(statusID);
                                timDonNghiResponse.setStatus(status);
                                timDonNghiResponse.setLeaveName(leaveName);
                                timDonNghiResponse.setDuration(duration);
                                timDonNghiResponse.setTaken(taken);
                                timDonNghiResponse.setNguoiPheDuyet(nguoiPheDuyet);
                                timDonNghiResponse.setLeaveRecordID(leaveRecordID);
                                listDonNghi.add(timDonNghiResponse);
                            }
                            donNghiDaTaoAdapter.notifyDataSetChanged();

                            binding.donNghiPhepTextViewKetQua.setVisibility(View.VISIBLE);
                            binding.donNghiPhepTextViewKetQua.setText(listDonNghi.size() + " Kết quả");

                        } catch (Exception e) {
                            Log.d("TAG Message", e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });

            }
        });

    }


    // Su kien Click Item trong LoaiNghi, TinhTrang
    @Override
    public void OnItemSelected(String id, String name, String codeId) {
        switch (codeId) {
            case "100": {
//                Toast.makeText(this, id + name, Toast.LENGTH_SHORT).show();
                binding.donNghiPhepEditTextLoaiNghi.setText(name);

                idLoaiNghi = id;

                bottomSheetDialogLoaiNghi.dismiss();
                break;
            }

            case "101": {
//                Toast.makeText(this, id + " - " + name, Toast.LENGTH_SHORT).show();

                //set EditText tình trạng
                //lưu id vào 1 biến selectedTinhTrang
                //hide bottomSheetDialog
                binding.donNghiPhepEditTextTinhTrang.setText(name);

                idTinhTrang = id;

                bottomSheetDialogTinhTrang.dismiss();
                break;
            }

        }
    }

    @Override
    public void DonNghiSelected(String leaveRecordID, String statusID) {

        Intent intent = new Intent(DanhSachDonNghiActivity.this, DangKyNghiActivity.class);

        intent.putExtra("idDonNghi", leaveRecordID);
        intent.putExtra("statusID", statusID);
        intent.putExtra("mode2", new String("ChiTietDon"));

        startActivity(intent);
    }


}