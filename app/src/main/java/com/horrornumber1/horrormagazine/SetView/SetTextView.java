package com.horrornumber1.horrormagazine.SetView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.horrornumber1.horrormagazine.Activities.Board;
import com.horrornumber1.horrormagazine.R;
import com.horrornumber1.horrormagazine.StaticData.DataHouse;


/**
 * Created by 김태호 on 2017-01-13.
 */

public class SetTextView {

    Context context;
    Activity activity;

    Typeface daum, nanumgothic, yeonsung, hanna, jua;
    // Home 화면 TextView

    private TextView toolbar_title;
    private TextView story, counsil;
    // Board 화면 TextView
    private TextView board_toolbar_title;

    public SetTextView(Context context, Activity activity)
    {
        this.context = context;
        this.activity = activity;
        daum = Typeface.createFromAsset(context.getAssets(), "fonts/daum.ttf");
        nanumgothic = Typeface.createFromAsset(context.getAssets(), "fonts/nanumgothic.ttf");
        yeonsung = Typeface.createFromAsset(context.getAssets(), "fonts/YEONSUNG.ttf");
        jua = Typeface.createFromAsset(context.getAssets(), "fonts/JUA.ttf");

    }

    public void HomeFont()
    {


       // toolbar_title = (TextView) activity.findViewById(R.id.main_toolbar_title);

//        toolbar_title.setTypeface(yeonsung);

        story = (TextView) activity.findViewById(R.id.HOMESTORY);

        story.setTypeface(jua);

        counsil = (TextView) activity.findViewById(R.id.HOMECOUNCIL);

        counsil.setTypeface(jua);

    }
    public void BoardFont()
    {
        board_toolbar_title = (TextView) activity.findViewById(R.id.board_toolbar_title);
        board_toolbar_title.setTypeface(yeonsung);

    }

}
