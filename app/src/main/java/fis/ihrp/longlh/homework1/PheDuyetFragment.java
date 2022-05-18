package fis.ihrp.longlh.homework1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fis.ihrp.longlh.homework1.databinding.FragmentDanhSachBinding;
import fis.ihrp.longlh.homework1.databinding.FragmentPheDuyetBinding;


public class PheDuyetFragment extends Fragment {

    private FragmentPheDuyetBinding binding;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_phe_duyet, container, false);

        binding = FragmentPheDuyetBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.pheDuyetLinearDonNghi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PheDuyetDonActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}