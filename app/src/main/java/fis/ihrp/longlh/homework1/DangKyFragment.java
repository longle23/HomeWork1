package fis.ihrp.longlh.homework1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fis.ihrp.longlh.homework1.databinding.FragmentDangKyBinding;


public class DangKyFragment extends Fragment {

    private FragmentDangKyBinding binding;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDangKyBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.dangkyLinearNghiPhep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DangKyNghiActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }
}