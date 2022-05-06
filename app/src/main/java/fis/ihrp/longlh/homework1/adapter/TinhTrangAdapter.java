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
import fis.ihrp.longlh.homework1.model.TinhTrangResponse;
import fis.ihrp.longlh.homework1.myinterface.TinhTrangOnclick;

public class TinhTrangAdapter extends RecyclerView.Adapter<TinhTrangAdapter.ViewHolder> {

    private ArrayList<TinhTrangResponse> listTinhTrang;
    private Context context;
    private TinhTrangOnclick callback;

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
//            holder.cardViewTinhTrang.setBackgroundResource(R.color.tinhTrang_xam);
//            holder.cardViewTinhTrang.setCardBackgroundColor(Color.parseColor("#E3E3E3"));
            holder.cardViewTinhTrang.setCardBackgroundColor(ContextCompat.getColor(context,R.color.tinhTrang_xam));
        } else if (item1.equalsIgnoreCase("1")) {
            holder.cardViewTinhTrang.setCardBackgroundColor(ContextCompat.getColor(context,R.color.tinhTrang_xanhDuong));
            holder.imageViewTinhTrang.setImageResource(R.drawable.ic_baseline_add_24);
        } else if (item1.equalsIgnoreCase("2")) {
            holder.cardViewTinhTrang.setCardBackgroundColor(ContextCompat.getColor(context,R.color.tinhTrang_vang));
            holder.imageViewTinhTrang.setImageResource(R.drawable.ic_baseline_near_me_244);
        } else if (item1.equalsIgnoreCase("3")) {
            holder.cardViewTinhTrang.setCardBackgroundColor(ContextCompat.getColor(context,R.color.tinhTrang_xanhLa));
            holder.imageViewTinhTrang.setImageResource(R.drawable.ic_baseline_check_244);
        } else if (item1.equalsIgnoreCase("4")) {
            holder.cardViewTinhTrang.setCardBackgroundColor(ContextCompat.getColor(context,R.color.tinhTrang_do));
            holder.imageViewTinhTrang.setImageResource(R.drawable.ic_baseline_close_24);
        }

        // Sự kiện Click vào Item Tinh Trang
        holder.tinhTrang_linear_layoutTinhTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.OnItemSelected(tinhTrangResponse.getItem1(),tinhTrangResponse.getItem2(), "101");
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