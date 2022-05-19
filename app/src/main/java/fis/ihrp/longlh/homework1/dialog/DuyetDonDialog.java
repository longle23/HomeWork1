package fis.ihrp.longlh.homework1.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import fis.ihrp.longlh.homework1.R;

public class DuyetDonDialog extends DialogFragment {

    //Được dùng khi khởi tạo dialog mục đích nhận giá trị
    public static DuyetDonDialog newInstance(String data) {
        DuyetDonDialog duyetDonDialog = new DuyetDonDialog();
        Bundle args = new Bundle();
        args.putString("data", data);
        duyetDonDialog.setArguments(args);
        return duyetDonDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.layout_duyetsuccess, container);

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

        getDialog().getWindow().setLayout(1200, 740);
    }


}
