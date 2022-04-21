package fis.ihrp.longlh.homework1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import fis.ihrp.longlh.homework1.R;
import fis.ihrp.longlh.homework1.ThongTinNhanVienActivity;
import fis.ihrp.longlh.homework1.model.Employee;
import fis.ihrp.longlh.homework1.model.Funcion;

public class FuncionAdapter extends RecyclerView.Adapter<FuncionAdapter.ViewHolder> {

    private ArrayList<Funcion> listFuncion;
    private Context context;

    public FuncionAdapter(ArrayList<Funcion> listFuncion, Context context) {
        this.listFuncion = listFuncion;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View funcionView = inflater.inflate(R.layout.layout_nhan_vien_he_thong, parent, false);

        ViewHolder viewHolder = new ViewHolder(funcionView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Funcion funcion = listFuncion.get(position);

        holder.nhanVienHeThong_textView_doiTuong.setText(funcion.getFunctionName());
        // Lay ten Image
        String src = funcion.getSrc();

        //Lay ID cua Image dua vao ten Image
        int imgID = context.getResources().getIdentifier(src,"drawable",context.getPackageName());
        holder.nhanVienHeThong_imgView_doiTuong.setImageResource(imgID);
    }

    @Override
    public int getItemCount() {
        return listFuncion.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView nhanVienHeThong_imgView_doiTuong;
        TextView nhanVienHeThong_textView_doiTuong;
        LinearLayout thongTinNhanVien_linear_layoutFuncion;

        public ViewHolder(@NonNull View funcionView) {
            super(funcionView);
            thongTinNhanVien_linear_layoutFuncion = funcionView.findViewById(R.id.thongTinNhanVien_linear_layoutFuncion);
            nhanVienHeThong_imgView_doiTuong = funcionView.findViewById(R.id.nhanVienHeThong_imgView_doiTuong);
            nhanVienHeThong_textView_doiTuong = funcionView.findViewById(R.id.nhanVienHeThong_textView_doiTuong);
        }
    }

}
