package fis.ihrp.longlh.homework1.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fis.ihrp.longlh.homework1.R;
import fis.ihrp.longlh.homework1.model.LoaiNghiResponse;
import fis.ihrp.longlh.homework1.model.TinhTrangResponse;
import fis.ihrp.longlh.homework1.myinterface.TinhTrangOnclick;

public class LoaiNghiAdapter extends RecyclerView.Adapter<LoaiNghiAdapter.ViewHolder> {

    private ArrayList<LoaiNghiResponse> listLoaiNghi;
    private Context context;
    private TinhTrangOnclick callback;

    public LoaiNghiAdapter(ArrayList<LoaiNghiResponse> listLoaiNghi, Context context, TinhTrangOnclick callback) {
        this.listLoaiNghi = listLoaiNghi;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View loaiNghiView = inflater.inflate(R.layout.layout_loainghi, parent, false);

        ViewHolder viewHolder = new ViewHolder(loaiNghiView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoaiNghiResponse loaiNghiResponse = listLoaiNghi.get(position);

        holder.textViewLoaiNghi.setText(loaiNghiResponse.getNameEN());

        // Sự kiện Click vào Item Loai Nghi
        holder.loaiNghi_linear_layoutLoaiNghi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.OnItemSelected(loaiNghiResponse.getId(), loaiNghiResponse.getNameEN(), "100");
            }
        });

    }

    @Override
    public int getItemCount() {
        return listLoaiNghi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout loaiNghi_linear_layoutLoaiNghi;
        TextView textViewLoaiNghi;

        public ViewHolder(@NonNull View loaiNghiView) {
            super(loaiNghiView);

            loaiNghi_linear_layoutLoaiNghi = loaiNghiView.findViewById(R.id.loaiNghi_linear_layoutLoaiNghi);
            textViewLoaiNghi = loaiNghiView.findViewById(R.id.loaiNghi_textView_loaiNghi);
        }

    }

}

