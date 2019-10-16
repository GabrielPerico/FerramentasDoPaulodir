package com.edu.senac.ferramentashardware;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static android.app.ProgressDialog.show;

public class QrcodeFragment extends Fragment {
    Button btn;
    TextView textView;
    //Activity activity= this.getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_qrcode, container, false);

        btn = view.findViewById(R.id.button);
        textView = view.findViewById(R.id.textview);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setPrompt("");
                // intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCameraId(0);
                //intentIntegrator.setxCameraId(0);
                IntentIntegrator.forSupportFragment(QrcodeFragment.this).initiateScan();
            }
        });
        return view;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.e("sla",result.getContents());
                Toast.makeText(getActivity(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).
                show();
                textView.setText(result.getContents()+"");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}