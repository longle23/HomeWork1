package fis.ihrp.longlh.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fis.ihrp.longlh.homework1.adapter.SwipeDonChoDuyetAdapter;
import fis.ihrp.longlh.homework1.databinding.ActivityPheDuyetDonBinding;
import fis.ihrp.longlh.homework1.dialog.ChuyenDuyetFailDialog;
import fis.ihrp.longlh.homework1.dialog.DuyetDonDialog;
import fis.ihrp.longlh.homework1.dialog.ThanhCongDialog;
import fis.ihrp.longlh.homework1.dialog.TuChoiDuyetDialog;
import fis.ihrp.longlh.homework1.model.DuyetDonRequest;
import fis.ihrp.longlh.homework1.model.TimDonChoDuyetRequest;
import fis.ihrp.longlh.homework1.model.TimDonChoDuyetResponse;
import fis.ihrp.longlh.homework1.model.TuChoiDonRequest;
import fis.ihrp.longlh.homework1.myinterface.DuyetDonOnclick;
import fis.ihrp.longlh.homework1.myinterface.TuChoiDuyetOnclick;
import fis.ihrp.longlh.homework1.service.RetrofitClient;
import fis.ihrp.longlh.homework1.service.UserService;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PheDuyetDonActivity extends AppCompatActivity implements DuyetDonOnclick, TuChoiDuyetOnclick {

    // Khai bao bien binding
    private ActivityPheDuyetDonBinding binding;

    // Khai bao bien Service
    private UserService userService6;

    // Set Adapter Don Cho Duyet
    private ArrayList<TimDonChoDuyetResponse> listDonChoDuyet = new ArrayList<>();
    private SwipeDonChoDuyetAdapter donChoDuyetAdapter;

    private TimDonChoDuyetResponse timDonChoDuyetResponse;

    // Shimmer List Don Cho Duyet
    private ShimmerFrameLayout shimmerFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_phe_duyet_don);


        // Goi ham inflate
        binding = ActivityPheDuyetDonBinding.inflate(getLayoutInflater());
        // Thay the đối số truyen vao setContentView
        setContentView(binding.getRoot());

        // Setting thanh toolbar
        Toolbar toolbar = findViewById(R.id.toolbarDonChoDuyet);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        binding.toolbarDonChoDuyet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Khai bao Shimmer
        shimmerFrameLayout = findViewById(R.id.shimmer_view_duyetDon);

        // Call API Hien Thi Danh Sach Don Cho Duyet
        goiAPI_TimDonChoDuyet(layToken());

        // Setup Adapter Don Cho Duyet
        donChoDuyetAdapter = new SwipeDonChoDuyetAdapter(listDonChoDuyet, this, this, this);
        binding.pheDuyetRecyclerViewListDonChoDuyet.setAdapter(donChoDuyetAdapter);
        binding.pheDuyetRecyclerViewListDonChoDuyet.setLayoutManager(new LinearLayoutManager(this));
    }


    // Ham tra ve Token de goi API
    private String layToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppShared", MODE_PRIVATE);
//        sharedPreferences.getString("token", "");
        String token = sharedPreferences.getString("token", "");
//        Toast.makeText(DanhSachNhanVienActivity.this, token, Toast.LENGTH_SHORT).show();
        return token;
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

    // Ham Goi API lay danh sach Don Cho Duyet
    private void goiAPI_TimDonChoDuyet(String token) {
        // Set Shimmer
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmerAnimation();

        // Khoi tao API
        userService6 = RetrofitClient.getClient();

        // Set du lieu vao DataHeader
        List<TimDonChoDuyetRequest.Param> params = new ArrayList<>();
        TimDonChoDuyetRequest.Param param = new TimDonChoDuyetRequest.Param();
        param.setFromDate("");
        param.setLeaveRecordID("");
        param.setLeaveTypeID("");
        param.setStatus("");
        param.setToDate("");
        param.setTop("");

        params.add(param);

        // Khoi tao Request Model
        TimDonChoDuyetRequest model = new TimDonChoDuyetRequest();
        model.setAppVersion("V33.PNJ.20200827.2");
        model.setDataHeader(params);
        model.setLangID("vn");
        model.setStoken(token);

        userService6.timDonChoDuyet(model).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // Test giá call API la gi
                Log.d("TAG", "DonChoDuyet Request: " + bodyToString(call.request().body()));

                try {
                    // Lay các trường trong Json tra ve
                    String jsonResponse = response.body().toString();

                    JSONObject jsonObject = new JSONObject(jsonResponse);

                    JSONArray dataItemResponse = jsonObject.getJSONArray("dataItem");
                    Log.d("TAG", "DonChoDuyet Response: " + dataItemResponse);

                    for (int i = 0; i < dataItemResponse.length(); i++) {
                        JSONObject jsonObject1 = dataItemResponse.getJSONObject(i);

                        timDonChoDuyetResponse = new TimDonChoDuyetResponse();

                        String empID = jsonObject1.getString("empID");
                        String empName = jsonObject1.getString("empName");
                        String gender = jsonObject1.getString("gender");
                        String leaveRecordID = jsonObject1.getString("leaveRecordID");
                        String leaveName = jsonObject1.getString("leaveName");
                        String fromDate = jsonObject1.getString("fromDate");
                        String toDate = jsonObject1.getString("toDate");
                        String duration = jsonObject1.getString("duration");
                        String taken = jsonObject1.getString("taken");
                        String actionDate = jsonObject1.getString("actionDate");
                        String reason = jsonObject1.getString("reason");
                        String replacePerson = jsonObject1.getString("replacePerson");

                        timDonChoDuyetResponse.setEmpID(empID);
                        timDonChoDuyetResponse.setEmpName(empName);
                        timDonChoDuyetResponse.setLeaveRecordID(leaveRecordID);
                        timDonChoDuyetResponse.setLeaveName(leaveName);
                        timDonChoDuyetResponse.setFromDate(fromDate);
                        timDonChoDuyetResponse.setToDate(toDate);
                        timDonChoDuyetResponse.setDuration(duration);
                        timDonChoDuyetResponse.setTaken(taken);
                        timDonChoDuyetResponse.setReason(reason);

                        listDonChoDuyet.add(timDonChoDuyetResponse);
                    }
                    donChoDuyetAdapter.notifyDataSetChanged();

                    // Nho them "" de set Text kieu String, khong la loi~
                    binding.pheDuyetTvSoDonDuyet.setText(listDonChoDuyet.size() + "");

                    // Set Time Shimmer Ket Thuc
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 1s
                            shimmerFrameLayout.stopShimmerAnimation();
                            shimmerFrameLayout.setVisibility(View.GONE);

                            // Set Text ket qua So don dang CHO DUYET
                            binding.pheDuyetTvKetQua.setVisibility(View.VISIBLE);
                            binding.pheDuyetTvSoDonDuyet.setVisibility(View.VISIBLE);
                        }
                    }, 1000);

                } catch (Exception e) {
                    Log.d("TAG Message", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    // Ham Goi API Duyet Don
    private void goiAPI_DuyetDon(String token) {
        // Khoi tao API
        userService6 = RetrofitClient.getClient();

        // Set du lieu vao DataHeader
        List<DuyetDonRequest.Param> params = new ArrayList<>();
        DuyetDonRequest.Param param = new DuyetDonRequest.Param();
        param.setApprove("1");
        param.setComment("");
        param.setID(timDonChoDuyetResponse.getLeaveRecordID());

        params.add(param);

        // Khoi tao Request Model
        DuyetDonRequest model = new DuyetDonRequest();
        model.setAppVersion("V33.PNJ.20200827.2");
        model.setDataHeader(params);
        model.setLangID("vn");
        model.setStoken(token);

        userService6.duyetDon(model).enqueue(new Callback<JsonObject>() {
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
                        DuyetDonDialog editNameDialogFragment = DuyetDonDialog.newInstance("Some Title");
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

    // Ham Goi API Tu Choi Don
    private void goiAPI_TuChoiDon(String token) {
        // Khoi tao API
        userService6 = RetrofitClient.getClient();

        // Set du lieu vao DataHeader
        List<TuChoiDonRequest.Param> params = new ArrayList<>();
        TuChoiDonRequest.Param param = new TuChoiDonRequest.Param();
        param.setApprove("0");
        param.setComment("");
        param.setID("");

        params.add(param);

        // Khoi tao Request Model
        TuChoiDonRequest model = new TuChoiDonRequest();
        model.setAppVersion("V33.PNJ.20200827.2");
        model.setDataHeader(params);
        model.setLangID("vn");
        model.setStoken(token);

        userService6.tuChoiDon(model).enqueue(new Callback<JsonObject>() {
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
                        DuyetDonDialog editNameDialogFragment = DuyetDonDialog.newInstance("Some Title");
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
    public void duyetDon(String leaveRecordID) {
        // Call API Duyet Don
        goiAPI_DuyetDon(layToken());
    }

    @Override
    public void OnItemSelected1() {
        FragmentManager fm = getSupportFragmentManager();
        TuChoiDuyetDialog editNameDialogFragment = TuChoiDuyetDialog.newInstance("Some Title");
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

    @Override
    public void OnItemSelected2(String leaveRecordID) {
        // Call API Duyet Don
        goiAPI_TuChoiDon(layToken());
    }


}