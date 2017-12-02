package org.androidtown.materialpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ahsxj on 2017-06-07.
 */

public class CommunicateFragment extends Fragment implements CommunicatePresenter.View {

    Button btnWeb;
    View v;
    TextView TextCamera,TextBlue,TextTether,TextMic,TextWifi;
    SharedPreferences networkFlag;

    private CommunicatePresenter communicatePresenter;

    public static CommunicateFragment newInstance()
    {
        CommunicateFragment fragment = new CommunicateFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if(v == null){
            v = inflater.inflate(R.layout.fragment_communication,container,false);
        }

        TextBlue = (TextView)v.findViewById(R.id.com_text_bluetooth);
        TextCamera = (TextView)v.findViewById(R.id.com_text_camera);
        TextMic = (TextView)v.findViewById(R.id.com_text_mic);
        TextTether = (TextView)v.findViewById(R.id.com_text_tether);
        TextWifi = (TextView)v.findViewById(R.id.com_text_wifi);

        btnWeb = (Button)v.findViewById(R.id.btn_web);
        networkFlag = getActivity().getSharedPreferences("NetworkFlag",0);
        communicatePresenter = new CommunicatePresenterImpl(CommunicateFragment.this);
        communicatePresenter.setView(this);
        setInit();
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Log.i(CommunicateFragment.class.getSimpleName(), "onKey Back listener is working!!!");
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtra("finishstatus", true);
                    startActivity(intent);
                    getActivity().finish();
                    return true;
                } else {
                    return false;
                }
            }
        });

        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = new Fragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frag_communication,fragment);
        fragmentTransaction.addToBackStack("FragC");
        fragmentTransaction.commit();


        btnWeb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.terrier.co19.kr/control"));
                startActivity(intent);
            }
        });


        return v;
    }
    public void setInit() {
        String wifi = networkFlag.getString("WIFI", "null");
        String blue = networkFlag.getString("BLUE", "null");
        String tether = networkFlag.getString("TETHER", "null");
        String camera = networkFlag.getString("CAMERA", "null");
        String record = networkFlag.getString("RECORD", "null");

        if (wifi.equals("true"))
            TextWifi.setText("허용");
        else {
            if (wifi.equals("false")) {
                TextWifi.setText("차단");
            } else
                TextWifi.setText("허용");
        }

        if (blue.equals("true"))
            TextBlue.setText("허용");
        else {
            if (blue.equals("false")) {
                TextBlue.setText("차단");
            } else
                TextBlue.setText("허용");
        }

        if (tether.equals("true"))
            TextBlue.setText("허용");
        else {
            if (tether.equals("false")) {
                TextBlue.setText("차단");
            } else
                TextBlue.setText("허용");
        }

        if (camera.equals("true"))
            TextCamera.setText("허용");
        else {
            if (camera.equals("false")) {
                TextCamera.setText("차단");
            } else
                TextCamera.setText("허용");
        }

        if (record.equals("true"))
            TextMic.setText("허용");
        else {
            if (record.equals("false")) {
                TextMic.setText("차단");
            } else
                TextMic.setText("허용");
        }
    }
}





