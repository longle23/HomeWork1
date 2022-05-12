package fis.ihrp.longlh.homework1.dialog;

import android.app.Activity;
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

public class ThatBaiDialog extends DialogFragment {

    public static ThatBaiDialog newInstance(String data) {
        ThatBaiDialog thatBaiDialog = new ThatBaiDialog();
        Bundle args = new Bundle();
        args.putString("data", data);
        thatBaiDialog.setArguments(args);
        return thatBaiDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_thatbai, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonOK = view.findViewById(R.id.thatBai_button_ok);
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
        if (window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = 1370;
        window.setAttributes(params);
    }


}
