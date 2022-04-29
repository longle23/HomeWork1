package fis.ihrp.longlh.homework1.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_thanhcong, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonOK = view.findViewById(R.id.thanhCong_button_ok);
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
        Window window = getDialog().getWindow();
        if(window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = 1370;
        window.setAttributes(params);
    }


}
