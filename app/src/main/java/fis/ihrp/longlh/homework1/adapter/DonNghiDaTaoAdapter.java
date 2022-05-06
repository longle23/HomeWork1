package fis.ihrp.longlh.homework1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fis.ihrp.longlh.homework1.R;
import fis.ihrp.longlh.homework1.model.TimDonNghiResponse;
import fis.ihrp.longlh.homework1.myinterface.ChiTietDonOnclick;

public class DonNghiDaTaoAdapter extends RecyclerView.Adapter<DonNghiDaTaoAdapter.ViewHolder> {

    private ArrayList<TimDonNghiResponse> listDonNghi;
    private Context context;
//    private TimKiemDonOnclick callback;
    private ChiTietDonOnclick callback;

    public DonNghiDaTaoAdapter(ArrayList<TimDonNghiResponse> listDonNghi, Context context, ChiTietDonOnclick callback) {
        this.listDonNghi = listDonNghi;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public DonNghiDaTaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View donNghiDaTaoView = inflater.inflate(R.layout.layout_donnghidatao, parent, false);

        DonNghiDaTaoAdapter.ViewHolder viewHolder = new DonNghiDaTaoAdapter.ViewHolder(donNghiDaTaoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DonNghiDaTaoAdapter.ViewHolder holder, int position) {
        TimDonNghiResponse timDonNghiResponse = listDonNghi.get(position);

        // Set màu cho Icon Status (Tinh Trang)
        String statusID = timDonNghiResponse.getStatusID();
        if (statusID.equalsIgnoreCase("")) {
//            holder.donNghiTao_cardView_tinhTrang.setBackgroundResource(R.color.tinhTrang_xam);
            holder.donNghiTao_cardView_tinhTrang.setCardBackgroundColor(ContextCompat.getColor(context,R.color.tinhTrang_xam));
            holder.donNghiTao_tv_tinhTrang.setTextColor(ContextCompat.getColor(context,R.color.tinhTrang_xam));
        } else if (statusID.equalsIgnoreCase("1")) {
            holder.donNghiTao_cardView_tinhTrang.setCardBackgroundColor(ContextCompat.getColor(context,R.color.tinhTrang_xanhDuong));
            holder.donNghiTao_imgView_tinhTrang.setImageResource(R.drawable.ic_baseline_add_24);
            holder.donNghiTao_tv_tinhTrang.setTextColor(ContextCompat.getColor(context,R.color.tinhTrang_xanhDuong));
        } else if (statusID.equalsIgnoreCase("2")) {
            holder.donNghiTao_cardView_tinhTrang.setCardBackgroundColor(ContextCompat.getColor(context,R.color.tinhTrang_vang));
            holder.donNghiTao_imgView_tinhTrang.setImageResource(R.drawable.ic_baseline_near_me_244);
            holder.donNghiTao_tv_tinhTrang.setTextColor(ContextCompat.getColor(context,R.color.tinhTrang_vang));
        } else if (statusID.equalsIgnoreCase("3")) {
            holder.donNghiTao_cardView_tinhTrang.setCardBackgroundColor(ContextCompat.getColor(context,R.color.tinhTrang_xanhLa));
            holder.donNghiTao_imgView_tinhTrang.setImageResource(R.drawable.ic_baseline_check_244);
            holder.donNghiTao_tv_tinhTrang.setTextColor(ContextCompat.getColor(context,R.color.tinhTrang_xanhLa));
        } else if (statusID.equalsIgnoreCase("4")) {
            holder.donNghiTao_cardView_tinhTrang.setCardBackgroundColor(ContextCompat.getColor(context,R.color.tinhTrang_do));
            holder.donNghiTao_imgView_tinhTrang.setImageResource(R.drawable.ic_baseline_close_24);
            holder.donNghiTao_tv_tinhTrang.setTextColor(ContextCompat.getColor(context,R.color.tinhTrang_do));
        }

        holder.donNghiTao_tv_tinhTrang.setText(timDonNghiResponse.getStatus());
        holder.donNghiTao_tv_loaiNghi.setText(timDonNghiResponse.getLeaveName());
        holder.donNghiTao_tv_thoiGianNghi.setText(timDonNghiResponse.getDuration());
        holder.donNghiTao_tv_soNgayNghi.setText(timDonNghiResponse.getTaken());
        holder.donNghiTao_tv_nguoiDuyet.setText(timDonNghiResponse.getNguoiPheDuyet());

        // Sự kiện Click vào Item Don Nghi Da Tao
        holder.donNghiTao_linear_layoutDonDaTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.DonNghiSelected(timDonNghiResponse.getLeaveRecordID(), timDonNghiResponse.getStatusID());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDonNghi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout donNghiTao_linear_layoutDonDaTao;
        CardView donNghiTao_cardView_tinhTrang;
        ImageView donNghiTao_imgView_tinhTrang;
        TextView donNghiTao_tv_tinhTrang, donNghiTao_tv_loaiNghi, donNghiTao_tv_thoiGianNghi, donNghiTao_tv_soNgayNghi, donNghiTao_tv_nguoiDuyet;

        public ViewHolder(@NonNull View donNghiDaTaoView) {
            super(donNghiDaTaoView);

            donNghiTao_linear_layoutDonDaTao = donNghiDaTaoView.findViewById(R.id.donNghiTao_linear_layoutDonDaTao);
            donNghiTao_cardView_tinhTrang = donNghiDaTaoView.findViewById(R.id.donNghiTao_cardView_tinhTrang);
            donNghiTao_imgView_tinhTrang = donNghiDaTaoView.findViewById(R.id.donNghiTao_imgView_tinhTrang);
            donNghiTao_tv_tinhTrang = donNghiDaTaoView.findViewById(R.id.donNghiTao_tv_tinhTrang);
            donNghiTao_tv_loaiNghi = donNghiDaTaoView.findViewById(R.id.donNghiTao_tv_loaiNghi);
            donNghiTao_tv_thoiGianNghi = donNghiDaTaoView.findViewById(R.id.donNghiTao_tv_thoiGianNghi);
            donNghiTao_tv_soNgayNghi = donNghiDaTaoView.findViewById(R.id.donNghiTao_tv_soNgayNghi);
            donNghiTao_tv_nguoiDuyet = donNghiDaTaoView.findViewById(R.id.donNghiTao_tv_nguoiDuyet);


        }
    }


}
