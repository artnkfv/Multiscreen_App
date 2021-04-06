package com.example.android.miwok;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import static com.example.android.miwok.R.layout.bottom_sheet_modal;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(bottom_sheet_modal, container, false);

        TextView share = rootView.findViewById(R.id.share_textView);
        TextView link = rootView.findViewById(R.id.link_textView);
        TextView info =rootView.findViewById(R.id.info_textView);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Click","work");
                Toast.makeText(getActivity(),
                        "Click", Toast.LENGTH_SHORT)
                        .show();
                dismiss();

            }
        });

        link.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }));

        info.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }));
        return rootView;
    }
}
