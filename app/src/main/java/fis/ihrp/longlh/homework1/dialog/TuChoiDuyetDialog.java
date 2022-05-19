package fis.ihrp.longlh.homework1.dialog;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import fis.ihrp.longlh.homework1.R;
import fis.ihrp.longlh.homework1.myinterface.TuChoiDuyetOnclick;

public class TuChoiDuyetDialog extends DialogFragment {

    public interface TuChoiDuyetDialogListener {
        void sendInput(String inputText);
    }

    public TuChoiDuyetDialogListener tuChoiDuyetDialogListener;

    //Được dùng khi khởi tạo dialog mục đích nhận giá trị
    public static TuChoiDuyetDialog newInstance(String data) {
        TuChoiDuyetDialog tuChoiDuyetDialog = new TuChoiDuyetDialog();
        Bundle args = new Bundle();
        args.putString("data", data);
        tuChoiDuyetDialog.setArguments(args);
        return tuChoiDuyetDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.layout_thongbao_thanhcong, container);

        View view = inflater.inflate(R.layout.layout_tuchoiduyet_dialog, container, false);
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
    public void onAttach(@NonNull Context context) {
        {
            super.onAttach(context);
            try {
                tuChoiDuyetDialogListener = (TuChoiDuyetDialogListener) getActivity();
            } catch (ClassCastException e) {
                Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Kiem tra Nhap vao Ly Do Tu Choi
        TextInputLayout textInput_lyDo = view.findViewById(R.id.tuChoiDuyet_textInput_lyDo);
        textInput_lyDo.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    textInput_lyDo.setError("Bắt buộc nhập lý do từ chối");
                    textInput_lyDo.setErrorEnabled(true);
                } else {
                    textInput_lyDo.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Su kien nut Huy
        Button btHuy = view.findViewById(R.id.tuChoiDuyet_bt_huy);
        btHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        // Su kien nut OK
        EditText tuChoiDuyet_ed_lyDo = view.findViewById(R.id.tuChoiDuyet_ed_lyDo);
        Button tuChoiDuyet_bt_ok = view.findViewById(R.id.tuChoiDuyet_bt_ok);
        tuChoiDuyet_bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = tuChoiDuyet_ed_lyDo.getText().toString();
                tuChoiDuyetDialogListener.sendInput(input);
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
