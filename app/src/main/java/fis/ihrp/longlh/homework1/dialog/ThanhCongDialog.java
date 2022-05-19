package fis.ihrp.longlh.homework1.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import fis.ihrp.longlh.homework1.R;

public class ThanhCongDialog extends DialogFragment {

    //Được dùng khi khởi tạo dialog mục đích nhận giá trị
    public static ThanhCongDialog newInstance(String data) {
        ThanhCongDialog thanhCongDialog = new ThanhCongDialog();
        Bundle args = new Bundle();
        args.putString("data", data);
        thanhCongDialog.setArguments(args);
        return thanhCongDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.layout_thongbao_thanhcong, container);

        View view = inflater.inflate(R.layout.layout_thanhcong, container, false);
        // Set transparent background and no title
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        // Click ben ngoai Dialog khong bi tat
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonOK = view.findViewById(R.id.rutDon_button_ok);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
                ((Activity) getContext()).finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

//        Window window = getDialog().getWindow();
//        if (window == null) return;
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.width = 1370;
//        window.setAttributes(params);

        getDialog().getWindow().setLayout(1200, 760);
    }


}
