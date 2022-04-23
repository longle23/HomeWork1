package fis.ihrp.longlh.homework1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import fis.ihrp.longlh.homework1.DanhSachNhanVienActivity;
import fis.ihrp.longlh.homework1.DanhSachNhanVienFragment;
import fis.ihrp.longlh.homework1.R;
import fis.ihrp.longlh.homework1.ThongTinNhanVienActivity;
import fis.ihrp.longlh.homework1.model.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private ArrayList<Employee> listEmployee;
    private Context context;

    public EmployeeAdapter(ArrayList<Employee> listEmployee, Context context) {
        this.listEmployee = listEmployee;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View employeeView = inflater.inflate(R.layout.layoutnhanvienlist, parent, false);

        ViewHolder viewHolder = new ViewHolder(employeeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = listEmployee.get(position);

        holder.textViewTenNV.setText(employee.getItem1());
        holder.textViewChucVuNV.setText(employee.getItem2());
        holder.textViewPhongBanNV.setText(employee.getItem4());

        // Cách lấy ảnh dạng Bitmap
//        Bitmap bitmap = parseImg(employee.getAvatar());
//        holder.circleImageViewNV.setImageBitmap(bitmap);

        // Sử dụng Glide để load Img (String url -> Img)
        Glide.with(context).load(employee.getAvatar()).into(holder.circleImageViewNV);

        // Sự kiện Click chuyển sang màn hình khác
        holder.linearLayoutNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThongTinNhanVienActivity.class);

                intent.putExtra("empID", employee.getEmpID());
                intent.putExtra("item2", employee.getItem2());
                intent.putExtra("item1", employee.getItem1());
                intent.putExtra("avatar", employee.getAvatar());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listEmployee.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageViewNV;
        TextView textViewTenNV, textViewChucVuNV, textViewPhongBanNV;
        LinearLayout linearLayoutNV;
        public ViewHolder(@NonNull View employeeView) {
            super(employeeView);
            linearLayoutNV = employeeView.findViewById(R.id.danhSachNhanVien_linear_layoutNV);
            circleImageViewNV = employeeView.findViewById(R.id.danhSachNhanVien_circleImgageView_imgNV);
            textViewTenNV = employeeView.findViewById(R.id.danhSachNhanVien_textView_tenNV);
            textViewChucVuNV = employeeView.findViewById(R.id.danhSachNhanVien_textView_chucVuNV);
            textViewPhongBanNV = employeeView.findViewById(R.id.danhSachNhanVien_textView_phongBanNV);
        }
    }


    // Cách chuyển Img từ chuỗi url sang kieu Bitmap
//    private Bitmap parseImg(String avatar){
//        // Truy cập lấy ảnh
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        URL url = null;
//        try {
//            url = new URL(avatar);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        Bitmap bmp = null;
//        try {
//            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return bmp;
//    }

}
