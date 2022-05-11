package fis.ihrp.longlh.homework1.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import fis.ihrp.longlh.homework1.DanhSachDonNghiActivity;
import fis.ihrp.longlh.homework1.R;

public class RutDonNghiDialog extends DialogFragment {

    //Được dùng khi khởi tạo dialog mục đích nhận giá trị
    public static RutDonNghiDialog newInstance(String data) {
        RutDonNghiDialog rutDonNghiDialog = new RutDonNghiDialog();
        Bundle args = new Bundle();
        args.putString("data", data);
        rutDonNghiDialog.setArguments(args);
        return rutDonNghiDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_thongbao_rutdonnghi, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonOK = view.findViewById(R.id.rutDon_button_ok);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
                Intent intent = new Intent(getContext(), DanhSachDonNghiActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if (window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = 1370;
        window.setAttributes(params);
    }


}
