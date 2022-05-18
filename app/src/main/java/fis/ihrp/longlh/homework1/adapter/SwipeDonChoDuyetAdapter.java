package fis.ihrp.longlh.homework1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;

import fis.ihrp.longlh.homework1.DangKyNghiActivity;
import fis.ihrp.longlh.homework1.PheDuyetDonActivity;
import fis.ihrp.longlh.homework1.R;
import fis.ihrp.longlh.homework1.dialog.ThanhCongDialog;
import fis.ihrp.longlh.homework1.model.TimDonChoDuyetResponse;
import fis.ihrp.longlh.homework1.myinterface.DuyetDonOnclick;
import fis.ihrp.longlh.homework1.myinterface.TuChoiDuyetOnclick;

public class SwipeDonChoDuyetAdapter extends RecyclerSwipeAdapter<SwipeDonChoDuyetAdapter.ViewHolder> {

    private ArrayList<TimDonChoDuyetResponse> listDonChoDuyet;
    private Context context;
    private DuyetDonOnclick callback1;
    private TuChoiDuyetOnclick callback2;

    public SwipeDonChoDuyetAdapter(ArrayList<TimDonChoDuyetResponse> listDonChoDuyet, Context context, DuyetDonOnclick callback, TuChoiDuyetOnclick callback2) {
        this.listDonChoDuyet = listDonChoDuyet;
        this.context = context;
        this.callback1 = callback;
        this.callback2 = callback2;
    }


    @NonNull
    @Override
    public SwipeDonChoDuyetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View donChoDuyetView = inflater.inflate(R.layout.layout_donchoduyet, parent, false);

        SwipeDonChoDuyetAdapter.ViewHolder viewHolder = new SwipeDonChoDuyetAdapter.ViewHolder(donChoDuyetView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SwipeDonChoDuyetAdapter.ViewHolder holder, int position) {
        TimDonChoDuyetResponse timDonChoDuyetResponse = listDonChoDuyet.get(position);

        holder.donChoDuyet_tv_tenNguoi.setText(timDonChoDuyetResponse.getEmpName());
        holder.donChoDuyet_tv_loaiNghi.setText(timDonChoDuyetResponse.getLeaveName());
        holder.donChoDuyet_tv_thoiGianNghi.setText(timDonChoDuyetResponse.getDuration());
        holder.donChoDuyet_tv_soNgayNghi.setText(timDonChoDuyetResponse.getTaken());

        // Swipe Layout
        holder.swipe_donChoDuyet.setShowMode(SwipeLayout.ShowMode.PullOut);
        // Drag From Right
        holder.swipe_donChoDuyet.addDrag(SwipeLayout.DragEdge.Right, holder.swipe_donChoDuyet.findViewById(R.id.swipe_bottom_right));
        // Handling different events when swiping
        holder.swipe_donChoDuyet.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });

        // Xu li button Duyet
        holder.donChoDuyet_bt_duyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback1.nutDuyetSwipe(timDonChoDuyetResponse.getLeaveRecordID());
            }
        });

        // Xu li button Tu Choi
        holder.donChoDuyet_bt_tuChoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback2.nutTuChoiSwipe(timDonChoDuyetResponse.getLeaveRecordID());
            }
        });

        // Xu li Khi an vao Don Dang Cho Duyet
        holder.donChoDuyet_linear_layoutDonChoDuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DangKyNghiActivity.class);

                intent.putExtra("empName",timDonChoDuyetResponse.getEmpName());
                intent.putExtra("leaveRecordID",timDonChoDuyetResponse.getLeaveRecordID());
                intent.putExtra("leaveName",timDonChoDuyetResponse.getLeaveName());
                intent.putExtra("fromDate",timDonChoDuyetResponse.getFromDate());
                intent.putExtra("toDate",timDonChoDuyetResponse.getToDate());
                intent.putExtra("taken",timDonChoDuyetResponse.getTaken());
                intent.putExtra("reason",timDonChoDuyetResponse.getReason());

                intent.putExtra("mode3", new String("chiTietDuyetDon"));

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDonChoDuyet.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_donChoDuyet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout donChoDuyet_linear_layoutDonChoDuyet;
        ImageView donChoDuyet_imgView_anh;
        TextView donChoDuyet_tv_tenNguoi, donChoDuyet_tv_loaiNghi,donChoDuyet_tv_thoiGianNghi,donChoDuyet_tv_soNgayNghi;

        SwipeLayout swipe_donChoDuyet;
        Button donChoDuyet_bt_duyet, donChoDuyet_bt_tuChoi;

        public ViewHolder(@NonNull View donChoDuyetView) {
            super(donChoDuyetView);

            donChoDuyet_linear_layoutDonChoDuyet = donChoDuyetView.findViewById(R.id.donChoDuyet_linear_layoutDonChoDuyet);
            donChoDuyet_imgView_anh = donChoDuyetView.findViewById(R.id.donChoDuyet_imgView_anh);

            donChoDuyet_tv_tenNguoi = donChoDuyetView.findViewById(R.id.donChoDuyet_tv_tenNguoi);
            donChoDuyet_tv_loaiNghi = donChoDuyetView.findViewById(R.id.donChoDuyet_tv_loaiNghi);
            donChoDuyet_tv_thoiGianNghi = donChoDuyetView.findViewById(R.id.donChoDuyet_tv_thoiGianNghi);
            donChoDuyet_tv_soNgayNghi = donChoDuyetView.findViewById(R.id.donChoDuyet_tv_soNgayNghi);

            swipe_donChoDuyet = donChoDuyetView.findViewById(R.id.swipe_donChoDuyet);
            donChoDuyet_bt_duyet = donChoDuyetView.findViewById(R.id.donChoDuyet_bt_duyet);
            donChoDuyet_bt_tuChoi = donChoDuyetView.findViewById(R.id.donChoDuyet_bt_tuChoi);
        }

    }


}