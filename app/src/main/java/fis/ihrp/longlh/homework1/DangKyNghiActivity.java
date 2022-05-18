package fis.ihrp.longlh.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

import fis.ihrp.longlh.homework1.adapter.LoaiNghiAdapter;
import fis.ihrp.longlh.homework1.databinding.ActivityDangKyNghiBinding;
import fis.ihrp.longlh.homework1.dialog.DuyetDonDialog;
import fis.ihrp.longlh.homework1.dialog.ThanhCongDialog;
import fis.ihrp.longlh.homework1.dialog.ChuyenDuyetSuccessDialog;
import fis.ihrp.longlh.homework1.dialog.ChuyenDuyetFailDialog;
import fis.ihrp.longlh.homework1.dialog.TuChoiDuyetDialog;
import fis.ihrp.longlh.homework1.model.CapNhatDonRequest;
import fis.ihrp.longlh.homework1.model.ChiTietDonNghiRequest;
import fis.ihrp.longlh.homework1.model.ChiTietDonNghiResponse;
import fis.ihrp.longlh.homework1.model.ChuyenDuyetRequest;
import fis.ihrp.longlh.homework1.model.DuyetDonRequest;
import fis.ihrp.longlh.homework1.model.LoaiNghiRequest;
import fis.ihrp.longlh.homework1.model.LoaiNghiResponse;
import fis.ihrp.longlh.homework1.model.NguoiKiemDuyetRequest;
import fis.ihrp.longlh.homework1.model.NguoiKiemDuyetResponse;
import fis.ihrp.longlh.homework1.model.RutDonNghiRequest;
import fis.ihrp.longlh.homework1.model.TinhPhepRequest;
import fis.ihrp.longlh.homework1.model.TinhPhepResponse;
import fis.ihrp.longlh.homework1.model.TuChoiDonRequest;
import fis.ihrp.longlh.homework1.model.XoaDonRequest;
import fis.ihrp.longlh.homework1.myinterface.TinhTrangOnclick;
import fis.ihrp.longlh.homework1.service.RetrofitClient;
import fis.ihrp.longlh.homework1.service.UserService;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyNghiActivity extends AppCompatActivity implements TinhTrangOnclick, TuChoiDuyetDialog.TuChoiDuyetDialogListener {

    private LoaiNghiResponse loaiNghiResponse;
    private NguoiKiemDuyetResponse nguoiKiemDuyetResponse;
    private TinhPhepResponse tinhPhepResponse;

    // Khai bao bien binding
    private ActivityDangKyNghiBinding binding;

    // Khai bao bien Service
    private UserService userService4;

    // Set Adapter Loai Nghi
    private ArrayList<LoaiNghiResponse> listLoaiNghi = new ArrayList<>();
    private LoaiNghiAdapter loaiNghiAdapter;

    // Khai báo Calendar
    final Calendar myCalendar = Calendar.getInstance();

    // Khai bao bien idLoaiNghi
    String idLoaiNghi;

    // Khai bao bien idNguoiDuyet
    String approverID;

    // Lay id tu Intent DanhSachDonNghiActivity
    String idDonNghi;
    String statusID;

    ChiTietDonNghiResponse chiTietDonNghiResponse;

    // BottomSheetDialog
    private BottomSheetDialog bottomSheetDialogLoaiNghi;

    String leaveRecordID;

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
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        binding.toolbarDKNghi.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

        // Lay du lieu Intent gui tu DanhSachDonNghiActivity
        idDonNghi = getIntent().getExtras().getString("idDonNghi");
        statusID = getIntent().getExtras().getString("statusID");
        String mode1 = getIntent().getExtras().getString("mode1");
        String mode2 = getIntent().getExtras().getString("mode2");
        String mode3 = getIntent().getExtras().getString("mode3");

        /////
        // Lay mode de show View hien thi
        // Dùng so sanh equalsIgnoreCase phai xét "!= null"
        if (mode1 != null && mode1.equalsIgnoreCase("taoMoi")) {
            // Call API Loai Nghi
            goiAPI_GetLoaiNghi(layToken());

            // Call API Nguoi Kiem Duyet
            goiAPI_NguoiKiemDuyet(layToken());

            // Set su kien Click Tinh Phep
            xuLyTinhPhep();

            // Set su kien Click Chuyen Duyet
            xuLyChuyenDuyet();
        }
        // Dùng so sanh equalsIgnoreCase phai xét "!= null"
        else if (mode2 != null && mode2.equalsIgnoreCase("chiTietDon")) {
            binding.dangKyNghiButtonChuyenDuyet.setVisibility(View.GONE);
            binding.dangKyNghiTextInputTinhTrang.setVisibility(View.VISIBLE);

            binding.dangKyNghiTvTenToolbar.setText("Chi tiết đơn nghỉ");

            // Call API Chi Tiet Don Nghi
            goiAPI_ChiTietDonNghi(layToken());

            if (statusID.equalsIgnoreCase("1")) {
                binding.dangKyNghiButtonChuyenDuyet2.setVisibility(View.VISIBLE);
                binding.dangKyNghiButtonXoaDon.setVisibility(View.VISIBLE);

                // Call API Loai Nghi
                goiAPI_GetLoaiNghi(layToken());

                // Set su kien Click Tinh Phep
                xuLyTinhPhep2();

                // Call API Cap Nhat Don
                goiAPI_CapNhatDon(layToken());

                // Call API Cap Nhat Don
                goiAPI_XoaDon(layToken());

            } else if (statusID.equalsIgnoreCase("2")) {
                binding.dangKyNghiButtonTinhPhep.setVisibility(View.GONE);
                binding.dangKyNghiButtonRutLaiDon.setVisibility(View.VISIBLE);

                binding.dangKyNghiTextInputLoaiNghi.setEnabled(false);
                binding.dangKyNghiEditTextTuNgay.setEnabled(false);
                binding.dangKyNghiEditTextDenNgay.setEnabled(false);
                binding.dangKyNghiEditTextLyDo.setEnabled(false);

                // Call API Rut Lai Don
                goiAPI_RutDonNghi(layToken());

            } else if (statusID.equalsIgnoreCase("3")) {
                binding.dangKyNghiButtonTinhPhep.setVisibility(View.GONE);

                binding.dangKyNghiTextInputLoaiNghi.setEnabled(false);
                binding.dangKyNghiEditTextTuNgay.setEnabled(false);
                binding.dangKyNghiEditTextDenNgay.setEnabled(false);
                binding.dangKyNghiTextInputLyDo.setEnabled(false);

            } else if (statusID.equalsIgnoreCase("4")) {

            }

        } else if (mode3 != null && mode3.equalsIgnoreCase("chiTietDuyetDon")) {
            binding.dangKyNghiButtonTinhPhep.setVisibility(View.GONE);
            binding.dangKyNghiButtonChuyenDuyet.setVisibility(View.GONE);

            binding.dangKyNghiImgViewAnh.setVisibility(View.VISIBLE);
            binding.dangKyNghiTvTenNguoiGui.setVisibility(View.VISIBLE);
            binding.dangKyNghiButtonDuyet.setVisibility(View.VISIBLE);
            binding.dangKyNghiButtonTuChoi.setVisibility(View.VISIBLE);

            binding.dangKyNghiTvTenToolbar.setText("Chi tiết đơn nghỉ");

            binding.dangKyNghiTextInputLoaiNghi.setEnabled(false);
            binding.dangKyNghiEditTextTuNgay.setEnabled(false);
            binding.dangKyNghiEditTextDenNgay.setEnabled(false);
            binding.dangKyNghiEditTextLyDo.setEnabled(false);

            // Call API Nguoi Kiem Duyet
            goiAPI_NguoiKiemDuyet(layToken());

            // Lay du lieu Don Cho Duyet set lên View
            String empName = getIntent().getExtras().getString("empName");
            leaveRecordID = getIntent().getExtras().getString("leaveRecordID");
            String leaveName = getIntent().getExtras().getString("leaveName");
            String fromDate = getIntent().getExtras().getString("fromDate");
            String toDate = getIntent().getExtras().getString("toDate");
            String taken = getIntent().getExtras().getString("taken");
            String reason = getIntent().getExtras().getString("reason");

            binding.dangKyNghiTvTenNguoiGui.setText(empName);
            binding.dangKyNghiEditTextLoaiNghi.setText(leaveName);
            binding.dangKyNghiEditTextTuNgay.setText(fromDate);
            binding.dangKyNghiEditTextDenNgay.setText(toDate);
            binding.dangKyNghiEditTextNgayNghi.setText(taken);
            binding.dangKyNghiEditTextLyDo.setText(reason);

            // Hien Dialog khi nhap vao button Tu Choi
            binding.dangKyNghiButtonTuChoi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fm = getSupportFragmentManager();
                    TuChoiDuyetDialog editNameDialogFragment = TuChoiDuyetDialog.newInstance("Some Title");
                    editNameDialogFragment.show(fm, "fragment_edit_name");
                }
            });

            // Call API Duyet Don
            goiAPI_DuyetDon(layToken());
        }

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
//                    Log.d("TAG LoaiNghi", "onResponse: " + dataItemResponse);

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

    // Setup Adapter Loai Nghi
    private void initBottomSheetDialog1(ArrayList<LoaiNghiResponse> listLoaiNghi) {
        bottomSheetDialogLoaiNghi = new BottomSheetDialog(DangKyNghiActivity.this);
        bottomSheetDialogLoaiNghi.setContentView(R.layout.bottom_sheet_dialog_loainghi);

        RecyclerView bottom_recyclerView_loaiNghi = bottomSheetDialogLoaiNghi.findViewById(R.id.bottom_recyclerView_loaiNghi);
        loaiNghiAdapter = new LoaiNghiAdapter(listLoaiNghi, DangKyNghiActivity.this, this);
        bottom_recyclerView_loaiNghi.setAdapter(loaiNghiAdapter);
        bottom_recyclerView_loaiNghi.setLayoutManager(new LinearLayoutManager(DangKyNghiActivity.this));
    }

    // Show Bottom Sheet Dialog Loai Nghi
    private void showBottomSheetDialog1() {
        binding.dangKyNghiEditTextLoaiNghi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialogLoaiNghi.show();
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
        List<NguoiKiemDuyetRequest.Param> params = new ArrayList<>();
        NguoiKiemDuyetRequest.Param param = new NguoiKiemDuyetRequest.Param();
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
                Log.d("TAG", "KiemDuyet Request: " + bodyToString(call.request().body()));

                try {
                    // Lay các trường trong Json tra ve
                    String jsonResponse = response.body().toString();

                    JSONObject jsonObject = new JSONObject(jsonResponse);

                    JSONArray dataItemResponse = jsonObject.getJSONArray("dataItem");
                    Log.d("TAG KiemDuyet", "onResponse: " + dataItemResponse);

                    for (int i = 0; i < dataItemResponse.length(); i++) {
                        JSONObject jsonObject1 = dataItemResponse.getJSONObject(i);

                        nguoiKiemDuyetResponse = new NguoiKiemDuyetResponse();

                        approverID = jsonObject1.getString("approverID");
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

    // Call API Tinh phep khi Tao Moi Don Nghi
    private void xuLyTinhPhep() {
        binding.dangKyNghiButtonTinhPhep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lay du lieu de Call API
                String token = layToken();
                String tuNgay = binding.dangKyNghiEditTextTuNgay.getText().toString();
                String denNgay = binding.dangKyNghiEditTextDenNgay.getText().toString();

                // Khoi tao API
                userService4 = RetrofitClient.getClient();

                // Set du lieu vao DataHeader
                List<TinhPhepRequest.Param> params = new ArrayList<>();
                TinhPhepRequest.Param param = new TinhPhepRequest.Param();
                param.setAP1("0");
                param.setAP2("0");
                param.setFromDate(tuNgay);
                param.setIsFromTom("");
                param.setIsToTom("");
                param.setLeaveTypeID(idLoaiNghi);
                param.setToDate(denNgay);
                params.add(param);

                // Khoi tao Request Model
                TinhPhepRequest model3 = new TinhPhepRequest();
                model3.setAppVersion("V33.PNJ.20200827.2");
                model3.setDataHeader(params);
                model3.setLangID("vn");
                model3.setStoken(token);

                userService4.tinhPhep(model3).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        try {
                            // Kiem tra du lieu khi call
                            Log.d("TAG", "onResponse: " + bodyToString(call.request().body()));

                            // Lay các trường trong Json tra ve
                            String jsonResponse = response.body().toString();

                            JSONObject jsonObject = new JSONObject(jsonResponse);

                            JSONArray dataItemResponse = jsonObject.getJSONArray("dataItem");

                            for (int i = 0; i < dataItemResponse.length(); i++) {
                                JSONObject jsonObject1 = dataItemResponse.getJSONObject(i);

                                tinhPhepResponse = new TinhPhepResponse();

                                String soNgayNghi = jsonObject1.getString("soNgayNghi");

                                binding.dangKyNghiEditTextNgayNghi.setText(soNgayNghi);
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
        });
    }

    // Call API Tinh phep khi Xem Chi Tiêt Don Nghi
    private void xuLyTinhPhep2() {
        binding.dangKyNghiButtonTinhPhep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lay du lieu de Call API
                String token = layToken();
                String tuNgay = binding.dangKyNghiEditTextTuNgay.getText().toString();
                String denNgay = binding.dangKyNghiEditTextDenNgay.getText().toString();

                // Khoi tao API
                userService4 = RetrofitClient.getClient();

                // Set du lieu vao DataHeader
                List<TinhPhepRequest.Param> params = new ArrayList<>();
                TinhPhepRequest.Param param = new TinhPhepRequest.Param();
                param.setAP1("0");
                param.setAP2("0");
                param.setFromDate(tuNgay);
                param.setIsFromTom("");
                param.setIsToTom("");
                param.setLeaveTypeID(chiTietDonNghiResponse.getLeaveTypeID());
                param.setToDate(denNgay);
                params.add(param);

                // Khoi tao Request Model
                TinhPhepRequest model3 = new TinhPhepRequest();
                model3.setAppVersion("V33.PNJ.20200827.2");
                model3.setDataHeader(params);
                model3.setLangID("vn");
                model3.setStoken(token);

                userService4.tinhPhep(model3).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        try {
                            // Kiem tra du lieu khi call
                            Log.d("TAG", "onResponse: " + bodyToString(call.request().body()));

                            // Lay các trường trong Json tra ve
                            String jsonResponse = response.body().toString();

                            JSONObject jsonObject = new JSONObject(jsonResponse);

                            JSONArray dataItemResponse = jsonObject.getJSONArray("dataItem");

                            for (int i = 0; i < dataItemResponse.length(); i++) {
                                JSONObject jsonObject1 = dataItemResponse.getJSONObject(i);

                                tinhPhepResponse = new TinhPhepResponse();

                                String soNgayNghi = jsonObject1.getString("soNgayNghi");

                                binding.dangKyNghiEditTextNgayNghi.setText(soNgayNghi);
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
        });
    }

    // Xu li Chuyen Duyet
    private void xuLyChuyenDuyet() {
        binding.dangKyNghiButtonChuyenDuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lay du lieu de Call API
                String token = layToken();
                String tuNgay = binding.dangKyNghiEditTextTuNgay.getText().toString();
                String denNgay = binding.dangKyNghiEditTextDenNgay.getText().toString();
                String lyDoNghi = binding.dangKyNghiEditTextLyDo.getText().toString();

                // Khoi tao API
                userService4 = RetrofitClient.getClient();

                // Set du lieu vao DataHeader
                List<ChuyenDuyetRequest.Param> params = new ArrayList<>();
                ChuyenDuyetRequest.Param param = new ChuyenDuyetRequest.Param();
                param.setLeaveTypeID(idLoaiNghi);
                param.setFromDate(tuNgay);
                param.setToDate(denNgay);
                param.setIsFromTom("0");
                param.setIsToTom("0");
                param.setApprover(approverID);
                param.setReplacePerson("");
                param.setReason(lyDoNghi);
                param.setStatus("1");

                params.add(param);

                // Khoi tao Request Model
                ChuyenDuyetRequest model4 = new ChuyenDuyetRequest();
                model4.setAppVersion("V33.PNJ.20200827.2");
                model4.setDataHeader(params);
                model4.setLangID("vn");
                model4.setStoken(token);

                userService4.chuyenDuyet(model4).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        try {
                            // Kiem tra du lieu khi call
                            Log.d("TAG", "onResponse: " + bodyToString(call.request().body()));

                            // Lay các trường trong Json tra ve
                            String jsonResponse = response.body().toString();

                            JSONObject jsonObject = new JSONObject(jsonResponse);

                            String code = jsonObject.getString("code");

                            // Xu li xuat hien man hinh thong bao
                            if (code.equalsIgnoreCase("0")) {
                                FragmentManager fm = getSupportFragmentManager();
                                ChuyenDuyetSuccessDialog editNameDialogFragment = ChuyenDuyetSuccessDialog.newInstance("Some Title");
                                editNameDialogFragment.show(fm, "fragment_edit_name");
                            } else if (code.equalsIgnoreCase("2")) {
                                FragmentManager fm = getSupportFragmentManager();
                                ChuyenDuyetFailDialog editNameDialogFragment = ChuyenDuyetFailDialog.newInstance("Some Title");
                                editNameDialogFragment.show(fm, "fragment_edit_name");
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
        });
    }

    // Ham Goi API Chi Tiet Don Nghi
    private void goiAPI_ChiTietDonNghi(String token) {
        // Khoi tao API
        userService4 = RetrofitClient.getClient();

        // Set du lieu vao DataHeader
        List<ChiTietDonNghiRequest.Param> params = new ArrayList<>();
        ChiTietDonNghiRequest.Param param = new ChiTietDonNghiRequest.Param();
        param.setID(idDonNghi);
        params.add(param);

        // Khoi tao Request Model
        ChiTietDonNghiRequest model = new ChiTietDonNghiRequest();
        model.setAppVersion("V31.POS.20190603.5");
        model.setDataHeader(params);
        model.setLangID("vn");
        model.setStoken(token);

        userService4.chiTietDonNghi(model).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // Test ket qua response tra ve du lieu la gi
                Log.d("TAG", "ChiTietDon Request: " + bodyToString(call.request().body()));

                try {
                    // Lay các trường trong Json tra ve
                    String jsonResponse = response.body().toString();

                    JSONObject jsonObject = new JSONObject(jsonResponse);

                    JSONArray dataItemResponse = jsonObject.getJSONArray("dataItem");
                    Log.d("TAG ChiTietDonNghi", "onResponse: " + dataItemResponse);

                    for (int i = 0; i < dataItemResponse.length(); i++) {
                        JSONObject jsonObject1 = dataItemResponse.getJSONObject(i);

                        chiTietDonNghiResponse = new ChiTietDonNghiResponse();

                        String leaveRecordID = jsonObject1.getString("leaveRecordID");
                        String leaveTypeID = jsonObject1.getString("leaveTypeID");
                        String leaveName = jsonObject1.getString("leaveName");
                        String fromDate = jsonObject1.getString("fromDate");
                        String toDate = jsonObject1.getString("toDate");
                        String approver = jsonObject1.getString("approver");
                        String approverName = jsonObject1.getString("approverName");
                        String taken = jsonObject1.getString("taken");
                        String reason = jsonObject1.getString("reason");
                        String statusID = jsonObject1.getString("statusID");
                        String statusName = jsonObject1.getString("statusName");

                        chiTietDonNghiResponse.setLeaveTypeID(leaveTypeID);
                        chiTietDonNghiResponse.setApprover(approver);

                        // Set text len View
                        binding.dangKyNghiEditTextLoaiNghi.setText(leaveName);
                        binding.dangKyNghiEditTextTuNgay.setText(fromDate);
                        binding.dangKyNghiEditTextDenNgay.setText(toDate);
                        binding.dangKyNghiEditTextNguoiDuyet.setText(approverName);
                        binding.dangKyNghiEditTextNgayNghi.setText(taken);
                        binding.dangKyNghiEditTextLyDo.setText(reason);
                        binding.dangKyNghiEditTextTinhTrang.setText(statusName);

                        // Set màu editText Tinh Trang
                        if (statusID.equalsIgnoreCase("")) {
                            binding.dangKyNghiEditTextTinhTrang.setTextColor(ContextCompat.getColor(DangKyNghiActivity.this, R.color.tinhTrang_xam));
                            Drawable img1 = binding.dangKyNghiEditTextTinhTrang.getContext().getResources().getDrawable(R.drawable.ic_xam);
                            binding.dangKyNghiEditTextTinhTrang.setCompoundDrawablesWithIntrinsicBounds(img1, null, null, null);
                        } else if (statusID.equalsIgnoreCase("1")) {
                            binding.dangKyNghiEditTextTinhTrang.setTextColor(ContextCompat.getColor(DangKyNghiActivity.this, R.color.tinhTrang_xanhDuong));
                            Drawable img2 = binding.dangKyNghiEditTextTinhTrang.getContext().getResources().getDrawable(R.drawable.ic_xanhduong);
                            binding.dangKyNghiEditTextTinhTrang.setCompoundDrawablesWithIntrinsicBounds(img2, null, null, null);
                        } else if (statusID.equalsIgnoreCase("2")) {
                            binding.dangKyNghiEditTextTinhTrang.setTextColor(ContextCompat.getColor(DangKyNghiActivity.this, R.color.tinhTrang_vang));
                            Drawable img3 = binding.dangKyNghiEditTextTinhTrang.getContext().getResources().getDrawable(R.drawable.send);
                            binding.dangKyNghiEditTextTinhTrang.setCompoundDrawablesWithIntrinsicBounds(img3, null, null, null);
                        } else if (statusID.equalsIgnoreCase("3")) {
                            binding.dangKyNghiEditTextTinhTrang.setTextColor(ContextCompat.getColor(DangKyNghiActivity.this, R.color.tinhTrang_xanhLa));
                            Drawable img4 = binding.dangKyNghiEditTextTinhTrang.getContext().getResources().getDrawable(R.drawable.ic_xanhla);
                            binding.dangKyNghiEditTextTinhTrang.setCompoundDrawablesWithIntrinsicBounds(img4, null, null, null);
                        } else if (statusID.equalsIgnoreCase("4")) {
                            binding.dangKyNghiEditTextTinhTrang.setTextColor(ContextCompat.getColor(DangKyNghiActivity.this, R.color.tinhTrang_do));
                            Drawable img5 = binding.dangKyNghiEditTextTinhTrang.getContext().getResources().getDrawable(R.drawable.remove);
                            binding.dangKyNghiEditTextTinhTrang.setCompoundDrawablesWithIntrinsicBounds(img5, null, null, null);
                        }

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

    // Ham Goi API Chi Tiet Don Nghi
    private void goiAPI_RutDonNghi(String token) {
        binding.dangKyNghiButtonRutLaiDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Lay du lieu de Call API
                String tuNgay = binding.dangKyNghiEditTextTuNgay.getText().toString();
                String denNgay = binding.dangKyNghiEditTextDenNgay.getText().toString();
                String lyDoNghi = binding.dangKyNghiEditTextLyDo.getText().toString();

                String leaveTypeID = chiTietDonNghiResponse.getLeaveTypeID();
                String approver = chiTietDonNghiResponse.getApprover();

                // Khoi tao API
                userService4 = RetrofitClient.getClient();

                // Set du lieu vao DataHeader
                List<RutDonNghiRequest.Param> params = new ArrayList<>();
                RutDonNghiRequest.Param param = new RutDonNghiRequest.Param();
                param.setLeaveRecordID(idDonNghi);
                param.setLeaveTypeID(leaveTypeID);
                param.setFromDate(tuNgay);
                param.setToDate(denNgay);
                param.setAP1("0");
                param.setAP2("0");
                param.setApprover(approver);
                param.setReplacePerson("");
                param.setReason(lyDoNghi);
                param.setStatus("3");

                params.add(param);

                // Khoi tao Request Model
                RutDonNghiRequest model = new RutDonNghiRequest();
                model.setAppVersion("V31.POS.20190603.5");
                model.setDataHeader(params);
                model.setLangID("vn");
                model.setOs("1");
                model.setStoken(token);

                userService4.rutDonNghi(model).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        // Test ket qua response tra ve du lieu la gi
                        Log.d("TAG", "RutDonNghi Request: " + bodyToString(call.request().body()));

                        try {
                            // Lay các trường trong Json tra ve
                            String jsonResponse = response.body().toString();

                            JSONObject jsonObject = new JSONObject(jsonResponse);
                            Log.d("TAG", "RutDonNghi Response: " + jsonObject);

                            String code = jsonObject.getString("code");

                            // Xu li xuat hien man hinh thong bao
                            if (code.equalsIgnoreCase("0")) {
                                FragmentManager fm = getSupportFragmentManager();
                                ThanhCongDialog editNameDialogFragment = ThanhCongDialog.newInstance("Some Title");
                                editNameDialogFragment.show(fm, "fragment_edit_name");
                            } else if (code.equalsIgnoreCase("2")) {
                                FragmentManager fm = getSupportFragmentManager();
                                ChuyenDuyetFailDialog editNameDialogFragment = ChuyenDuyetFailDialog.newInstance("Some Title");
                                editNameDialogFragment.show(fm, "fragment_edit_name");
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
        });

    }

    // Ham Goi API Chi Tiet Don Nghi
    private void goiAPI_CapNhatDon(String token) {
        binding.dangKyNghiButtonChuyenDuyet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Lay du lieu de Call API
                String tuNgay = binding.dangKyNghiEditTextTuNgay.getText().toString();
                String denNgay = binding.dangKyNghiEditTextDenNgay.getText().toString();
                String lyDoNghi = binding.dangKyNghiEditTextLyDo.getText().toString();

//                String leaveTypeID = chiTietDonNghiResponse.getLeaveTypeID();
                String approver = chiTietDonNghiResponse.getApprover();

                // Khoi tao API
                userService4 = RetrofitClient.getClient();

                // Set du lieu vao DataHeader
                List<CapNhatDonRequest.Param> params = new ArrayList<>();
                CapNhatDonRequest.Param param = new CapNhatDonRequest.Param();
                param.setLeaveRecordID(idDonNghi);
                param.setLeaveTypeID(chiTietDonNghiResponse.getLeaveTypeID());
                param.setFromDate(tuNgay);
                param.setToDate(denNgay);
                param.setAP1("0");
                param.setAP2("0");
                param.setApprover(approver);
                param.setReplacePerson("");
                param.setReason(lyDoNghi);
                param.setStatus("2");

                params.add(param);

                // Khoi tao Request Model
                CapNhatDonRequest model = new CapNhatDonRequest();
                model.setAppVersion("V31.POS.20190603.5");
                model.setDataHeader(params);
                model.setLangID("vn");
                model.setStoken(token);

                userService4.capNhatDon(model).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        // Test ket qua response tra ve du lieu la gi
                        Log.d("TAG", "CapNhatDon Request: " + bodyToString(call.request().body()));

                        try {
                            // Lay các trường trong Json tra ve
                            String jsonResponse = response.body().toString();

                            JSONObject jsonObject = new JSONObject(jsonResponse);
                            Log.d("TAG", "CapNhatDon Response: " + jsonObject);

                            String code = jsonObject.getString("code");

                            // Xu li xuat hien man hinh thong bao
                            if (code.equalsIgnoreCase("0")) {
                                FragmentManager fm = getSupportFragmentManager();
                                ThanhCongDialog editNameDialogFragment = ThanhCongDialog.newInstance("Đơn đăng ký đã chuyển phê duyệt");
                                editNameDialogFragment.show(fm, "fragment_edit_name");
                            } else if (code.equalsIgnoreCase("2")) {
                                FragmentManager fm = getSupportFragmentManager();
                                ChuyenDuyetFailDialog editNameDialogFragment = ChuyenDuyetFailDialog.newInstance("Some Title");
                                editNameDialogFragment.show(fm, "fragment_edit_name");
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
        });

    }

    // Ham Goi API Xoa Don
    private void goiAPI_XoaDon(String token) {
        binding.dangKyNghiButtonXoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Khoi tao API
                userService4 = RetrofitClient.getClient();

                // Set du lieu vao DataHeader
                List<XoaDonRequest.Param> params = new ArrayList<>();
                XoaDonRequest.Param param = new XoaDonRequest.Param();
                param.setID(idDonNghi);
                params.add(param);

                // Khoi tao Request Model
                XoaDonRequest model = new XoaDonRequest();
                model.setAppVersion("V31.POS.20190603.5");
                model.setDataItem(params);
                model.setLangID("vn");
                model.setStoken(token);

                userService4.xoaDon(model).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        // Test ket qua response tra ve du lieu la gi
                        Log.d("TAG", "XoaDon Request: " + bodyToString(call.request().body()));

                        try {
                            // Lay các trường trong Json tra ve
                            String jsonResponse = response.body().toString();

                            JSONObject jsonObject = new JSONObject(jsonResponse);
                            Log.d("TAG", "XoaDon Response: " + jsonObject);

                            String code = jsonObject.getString("code");

                            // Xu li xuat hien man hinh thong bao
                            if (code.equalsIgnoreCase("0")) {
                                FragmentManager fm = getSupportFragmentManager();
                                ThanhCongDialog editNameDialogFragment = ThanhCongDialog.newInstance("Some Title");
                                TextView tvContent = editNameDialogFragment.getDialog().findViewById(R.id.thongBao_thanhCong_content);
                                tvContent.setText("Xử lý thành công");
                                editNameDialogFragment.show(fm, "fragment_edit_name");
                            } else if (code.equalsIgnoreCase("2")) {
                                FragmentManager fm = getSupportFragmentManager();
                                ChuyenDuyetFailDialog editNameDialogFragment = ChuyenDuyetFailDialog.newInstance("Some Title");
                                editNameDialogFragment.show(fm, "fragment_edit_name");
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
        });

    }

    // Ham Goi API Duyet Don
    private void goiAPI_DuyetDon(String token) {
        binding.dangKyNghiButtonDuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Khoi tao API
                userService4 = RetrofitClient.getClient();

                // Set du lieu vao DataHeader
                List<DuyetDonRequest.Param> params = new ArrayList<>();
                DuyetDonRequest.Param param = new DuyetDonRequest.Param();
                param.setApprove("1");
                param.setComment("");
                param.setID(leaveRecordID);

                params.add(param);

                // Khoi tao Request Model
                DuyetDonRequest model = new DuyetDonRequest();
                model.setAppVersion("V33.PNJ.20200827.2");
                model.setDataItem(params);
                model.setLangID("vn");
                model.setStoken(token);

                userService4.duyetDon(model).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        // Test giá call API la gi
                        Log.d("TAG", "DuyetDon Request: " + bodyToString(call.request().body()));

                        try {
                            // Lay các trường trong Json tra ve
                            String jsonResponse = response.body().toString();

                            JSONObject jsonObject = new JSONObject(jsonResponse);
                            Log.d("TAG", "DuyetDon Response: " + jsonObject);

                            String code = jsonObject.getString("code");

                            // Xu li xuat hien man hinh thong bao
                            if (code.equalsIgnoreCase("0")) {
                                FragmentManager fm = getSupportFragmentManager();
                                ThanhCongDialog editNameDialogFragment = ThanhCongDialog.newInstance("Some Title");
                                editNameDialogFragment.show(fm, "fragment_edit_name");
                            } else if (code.equalsIgnoreCase("2")) {
                                FragmentManager fm = getSupportFragmentManager();
                                ChuyenDuyetFailDialog editNameDialogFragment = ChuyenDuyetFailDialog.newInstance("Some Title");
                                editNameDialogFragment.show(fm, "fragment_edit_name");
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
        });

    }

    // Ham Goi API Tu Choi Don
    private void goiAPI_TuChoiDon(String token) {
        // Khoi tao API
        userService4 = RetrofitClient.getClient();

        // Set du lieu vao DataHeader
        List<TuChoiDonRequest.Param> params = new ArrayList<>();
        TuChoiDonRequest.Param param = new TuChoiDonRequest.Param();
        param.setApprove("0");
        param.setComment(minput);
        param.setID(leaveRecordID);

        params.add(param);

        // Khoi tao Request Model
        TuChoiDonRequest model = new TuChoiDonRequest();
        model.setAppVersion("V33.PNJ.20200827.2");
        model.setDataItem(params);
        model.setLangID("vn");
        model.setStoken(token);

        userService4.tuChoiDon(model).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // Test giá call API la gi
                Log.d("TAG", "TuChoiDon Request: " + bodyToString(call.request().body()));

                try {
                    // Lay các trường trong Json tra ve
                    String jsonResponse = response.body().toString();

                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    Log.d("TAG", "TuChoiDon Response: " + jsonObject);

                    String code = jsonObject.getString("code");

                    // Xu li xuat hien man hinh thong bao
                    if (code.equalsIgnoreCase("0")) {
                        FragmentManager fm = getSupportFragmentManager();
                        ThanhCongDialog editNameDialogFragment = ThanhCongDialog.newInstance("Some Title");
                        editNameDialogFragment.show(fm, "fragment_edit_name");
                    } else if (code.equalsIgnoreCase("2")) {
                        FragmentManager fm = getSupportFragmentManager();
                        ChuyenDuyetFailDialog editNameDialogFragment = ChuyenDuyetFailDialog.newInstance("Some Title");
                        editNameDialogFragment.show(fm, "fragment_edit_name");
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

    @Override
    public void OnItemSelected(String id, String name, String codeId) {
        switch (codeId) {
            case "100": {
//                Toast.makeText(this, id + name, Toast.LENGTH_SHORT).show();
                binding.dangKyNghiEditTextLoaiNghi.setText(name);

                idLoaiNghi = id;

                bottomSheetDialogLoaiNghi.dismiss();
                break;
            }
        }
    }

    // Tao bien lay Ly Do nhap vao tu Dialog
    private String minput;

    // Lay Input Ly Do tu Dialog Tu Choi, roi Call API TuChoiDon
    @Override
    public void sendInput(String inputText) {
        Log.d("TAG", "SendInput: got the input: " + inputText);

        minput = inputText;

        // Call API Tu Choi Don
        goiAPI_TuChoiDon(layToken());
    }


}