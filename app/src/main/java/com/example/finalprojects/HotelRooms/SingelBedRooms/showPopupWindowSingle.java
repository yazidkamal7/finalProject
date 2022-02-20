package com.android.hotelbooking.HotelRooms.SingelBedRooms;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hotelbooking.R;

public class showPopupWindowSingle extends AppCompatActivity {

    public void showPopupWindow(final View v) {

        LayoutInflater inflat = (LayoutInflater) v.getContext().getSystemService(v.getContext().LAYOUT_INFLATER_SERVICE);
        View popV = inflat.inflate(R.layout.popup, null);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean flag = true;
        final PopupWindow pop_win = new PopupWindow(popV, width, height, flag);

        pop_win.showAtLocation(v, Gravity.CENTER, 0, 0);

        TextView text = popV.findViewById(R.id.titleText);
        text.setText("Are You SURE To Book This Room ?");

        Button editBTN = popV.findViewById(R.id.messageButtonYesReserve);
        Button editNObtn = popV.findViewById(R.id.messageButtonNo);
        editNObtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop_win.dismiss();
            }
        });
        popV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pop_win.dismiss();
                return true;
            }
        });
    }

}