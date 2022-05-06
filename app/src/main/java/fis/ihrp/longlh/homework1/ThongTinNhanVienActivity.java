package fis.ihrp.longlh.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fis.ihrp.longlh.homework1.adapter.EmployeeAdapter;
import fis.ihrp.longlh.homework1.adapter.FuncionAdapter;
import fis.ihrp.longlh.homework1.databinding.ActivityDanhSachNhanVienBinding;
import fis.ihrp.longlh.homework1.databinding.ActivityMainBinding;
import fis.ihrp.longlh.homework1.databinding.ActivityThongTinNhanVienBinding;
import fis.ihrp.longlh.homework1.model.Employee;
import fis.ihrp.longlh.homework1.model.FindEmployeeRequest;
import fis.ihrp.longlh.homework1.model.Funcion;
import fis.ihrp.longlh.homework1.model.ListFuncionRequest;
import fis.ihrp.longlh.homework1.service.RetrofitClient;
import fis.ihrp.longlh.homework1.service.UserService;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTinNhanVienActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Funcion funcion;
    private ArrayList<Funcion> listFuncion;
    private FuncionAdapter funcionAdapter;

    // Khai bao bien binding
    private ActivityThongTinNhanVienBinding binding;

    // Khai bao bien Service
    private UserService userService3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_thong_tin_nhan_vien);

        listFuncion = new ArrayList<>();

        // Goi ham inflate
        binding = ActivityThongTinNhanVienBinding.inflate(getLayoutInflater());
        // Thay the đối số truyen vao setContentView
        setContentView(binding.getRoot());

        // Setting thanh toolbar
        Toolbar toolbar = findViewById(R.id.toolbarThongTinNV2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        binding.toolbarThongTinNV2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Lay du lieu nhan vien set lên View
        String item2 = getIntent().getExtras().getString("item2");
        String item1 = getIntent().getExtras().getString("item1");
        String avatar = getIntent().getExtras().getString("avatar");

        Glide.with(this).load(avatar).into(binding.thongTinNhanVienImgViewNhanVien);

        binding.thongTinNhanVienTextViewTenNV.setText(item1);
        binding.thongTinNhanVienTextViewChucVuNV.setText(item2);

        // Call API
        goiAPI_HienThiFuncion(layToken());

        // Set Adapter
        funcionAdapter = new FuncionAdapter(listFuncion, this);
        binding.thongTinNhanVIenRecyclerViewDoiTuong.setAdapter(funcionAdapter);
        binding.thongTinNhanVIenRecyclerViewDoiTuong.setLayoutManager(new GridLayoutManager(this, 3));
    }

    // Ham tra ve Token de goi API
    private String layToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppShared", MODE_PRIVATE);
//        sharedPreferences.getString("token", "");
        String token = sharedPreferences.getString("token", "");
//        Toast.makeText(DanhSachNhanVienActivity.this, token, Toast.LENGTH_SHORT).show();
        return token;
    }

    // Ham Goi API hien thi Funcion
    // Load du lieu Funcion vao View
    private void goiAPI_HienThiFuncion(String token) {
        // Khoi tao API
        userService3 = RetrofitClient.getClient();

        // Set du lieu vao DataHeader
        List<ListFuncionRequest.Param> params = new ArrayList<>();
        ListFuncionRequest.Param param = new ListFuncionRequest.Param();
        param.setF(getIntent().getExtras().getString("empID"));
        params.add(param);

        // Khoi tao Request Model
        ListFuncionRequest model = new ListFuncionRequest();
        model.setAppVersion("V33.PNJ.20200827.2");
        model.setDataHeader(params);
        model.setLangID("vn");
        model.setOs("1");
        model.setStoken(token);

        userService3.listFuncion(model).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // Test ket qua response tra ve du lieu la gi
//                Log.d("TAG", "onResponse: " + bodyToString(call.request().body()));

                try {
                    // Lay các trường trong Json tra ve
                    String jsonResponse = response.body().toString();

                    JSONObject jsonObject = new JSONObject(jsonResponse);

                    JSONArray dataItemResponse = jsonObject.getJSONArray("dataItem");
                    Log.d("TAG", "onResponse: " + dataItemResponse);

                    for (int i = 0; i < dataItemResponse.length(); i++) {
                        JSONObject jsonObject1 = dataItemResponse.getJSONObject(i);

                        funcion = new Funcion();

                        String funcionName = jsonObject1.getString("functionName");
                        String src = jsonObject1.getString("src");

                        funcion.setFunctionName(funcionName);
                        funcion.setSrc(src);

                        listFuncion.add(funcion);
                    }

                    funcionAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    Log.d("TAG",e.getMessage());
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


}