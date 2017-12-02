package org.androidtown.materialpractice;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ahsxj on 2017-11-05.
 */

public class WarningFragment extends Fragment {

    String number;
    SharedPreferences Emergency_Connection_number;
    TextView warning;

    public static WarningFragment newInstance()
    {
        WarningFragment fragment = new WarningFragment();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_warning,container,false);
        Emergency_Connection_number = getActivity().getSharedPreferences("Emergency_number",0);
        warning = (TextView) view.findViewById(R.id.Warning_TextView);
        warning.setText(Emergency_Connection_number.getString("ECN","null"));
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    Log.i(CommunicateFragment.class.getSimpleName(), "onKey Back listener is working!!!");
//                    Intent intent = new Intent(getActivity(), MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    intent.putExtra("finishstatus", true);
//                    startActivity(intent);
                    getActivity().finish();
                    return true;
                } else {
                    return false;
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
