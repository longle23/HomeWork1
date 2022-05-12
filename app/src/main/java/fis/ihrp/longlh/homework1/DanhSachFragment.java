package fis.ihrp.longlh.homework1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fis.ihrp.longlh.homework1.databinding.FragmentDanhSachBinding;


public class DanhSachFragment extends Fragment {

    private FragmentDanhSachBinding binding;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDanhSachBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.danhSachLinearNghiPhep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DanhSachDonNghiActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}