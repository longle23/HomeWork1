package fis.ihrp.longlh.homework1.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import fis.ihrp.longlh.homework1.DonNghiPhepActivity;
import fis.ihrp.longlh.homework1.R;
import fis.ihrp.longlh.homework1.ThongTinNhanVienActivity;
import fis.ihrp.longlh.homework1.model.Employee;
import fis.ihrp.longlh.homework1.model.TinhTrangResponse;
import fis.ihrp.longlh.homework1.myinterface.TinhTrangOnclick;

public class TinhTrangAdapter extends RecyclerView.Adapter<TinhTrangAdapter.ViewHolder> {

    private ArrayList<TinhTrangResponse> listTinhTrang;
    private Context context;
    TinhTrangOnclick callback;

    public TinhTrangAdapter(ArrayList<TinhTrangResponse> listTinhTrang, Context context,TinhTrangOnclick callback) {
        this.listTinhTrang = listTinhTrang;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tinhTrangView = inflater.inflate(R.layout.layout_tinhtrang, parent, false);

        ViewHolder viewHolder = new ViewHolder(tinhTrangView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TinhTrangResponse tinhTrangResponse = listTinhTrang.get(position);

        holder.textViewTenTinhTrang.setText(tinhTrangResponse.getItem2());

        // Set màu cho Icon Tinh Trang
        String item1 = tinhTrangResponse.getItem1();
        if (item1.equalsIgnoreCase("")) {
            holder.cardViewTinhTrang.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        } else if (item1.equalsIgnoreCase("1")) {
            holder.cardViewTinhTrang.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
            holder.imageViewTinhTrang.setImageResource(R.drawable.ic_baseline_add_24);
        } else if (item1.equalsIgnoreCase("2")) {
            holder.cardViewTinhTrang.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
            holder.imageViewTinhTrang.setImageResource(R.drawable.ic_baseline_near_me_24);
        } else if (item1.equalsIgnoreCase("3")) {
            holder.cardViewTinhTrang.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            holder.imageViewTinhTrang.setImageResource(R.drawable.ic_baseline_check_244);
        } else if (item1.equalsIgnoreCase("4")) {
            holder.cardViewTinhTrang.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            holder.imageViewTinhTrang.setImageResource(R.drawable.ic_baseline_close_24);
        }

        // Sự kiện Click vào Item Tinh Trang
        holder.tinhTrang_linear_layoutTinhTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.OnItemSelected(tinhTrangResponse.getItem1(),tinhTrangResponse.getItem2());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listTinhTrang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout tinhTrang_linear_layoutTinhTrang;
        CardView cardViewTinhTrang;
        ImageView imageViewTinhTrang;
        TextView textViewTenTinhTrang;

        public ViewHolder(@NonNull View tinhTrangView) {
            super(tinhTrangView);

            tinhTrang_linear_layoutTinhTrang = tinhTrangView.findViewById(R.id.tinhTrang_linear_layoutTinhTrang);
            cardViewTinhTrang = tinhTrangView.findViewById(R.id.tỉnhtrang_cardView_tinhtrang);
            imageViewTinhTrang = tinhTrangView.findViewById(R.id.tinhtrang_imageView_tinhtrang);
            textViewTenTinhTrang = tinhTrangView.findViewById(R.id.tinhtrang_textView_tenTinhTrang);
        }

    }


}