package com.horrornumber1.horrormagazine.Fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.horrornumber1.horrormagazine.Activities.Content;
import com.horrornumber1.horrormagazine.Adapters.TabListViewAdapter;
import com.horrornumber1.horrormagazine.DataModel.Model;
import com.horrornumber1.horrormagazine.DataModel.MyData;
import com.horrornumber1.horrormagazine.R;
import com.horrornumber1.horrormagazine.StaticData.DataHouse;

import java.util.List;
import java.util.Random;

/**
 * Created by 김태호 on 2017-01-18.
 */

public class BoardTextFragment extends Fragment {

    List<MyData> contents;
    ListView listView;
    ImageView imageView;
    private View stickyViewSpacer;
    RelativeLayout stickyView;
    TextView listview_title, listview_sub;
    String sub, name;
    int[] ad = {R.drawable.ad_facebook, R.drawable.ad_youtube, R.drawable.magazine};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.board_fragment_text, container, false);
        name = getArguments().getString("name");
        contents=whichContents(name);

        FrameLayout rug = (FrameLayout) rootView.findViewById(R.id.board_rug);
        rug.setAlpha((float)0.85);

        Typeface hanna = Typeface.createFromAsset(getContext().getAssets(), "fonts/HANNA.ttf");
        //제목, 부제
        listview_title = (TextView) rootView.findViewById(R.id.board_listview_title);
        listview_title.setTypeface(hanna);
        listview_sub = (TextView) rootView.findViewById(R.id.board_listview_sub);

        if(!name.equals("이해하면 무서운 이야기"))
            listview_title.setText(name);
        else
            listview_title.setText("이 무 이");
        listview_sub.setText(sub);

        //ListView

        TabListViewAdapter adapter = new TabListViewAdapter(getContext(), R.layout.list_low,contents);
        //parallax
        View listHeader = inflater.inflate(R.layout.board_list_header, null);
        listHeader.setEnabled(false);
        listHeader.setOnClickListener(null);

        imageView = (ImageView) rootView.findViewById(R.id.board_tab_image);
        stickyView = (RelativeLayout) rootView.findViewById(R.id.stickyView);
        stickyViewSpacer = listHeader.findViewById(R.id.stickyViewPlaceholder);

        Random random = new Random();
        int idx = random.nextInt(3);

        imageView.setImageResource(ad[idx]);

        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCQbKk4fa3B23YDmVXjv-j4w")));

            }
        });


        listView = (ListView) rootView.findViewById(R.id.board_tab_listView);
        listView.addHeaderView(listHeader, null, false);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(getContext(), Content.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra(Content.PARAM_INPUT_NAME, name);
                intent.putExtra(Content.PARAM_INPUT_FROM, "B");
                intent.putExtra(Content.PARAM_INPUT_INDEX, position-1);
                startActivity(intent);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (listView.getFirstVisiblePosition() == 0) {
                    View firstChild = listView.getChildAt(0);

                    int topY = 0;
                    if (firstChild != null) {
                        topY = firstChild.getTop();
                    }
                    int heroTopY = stickyViewSpacer.getTop();
                    stickyView.setY(Math.max(0, heroTopY + topY));
                    imageView.setY(topY * 0.75f);
                }
            }
        });
        return rootView;
    }

    private List<MyData> whichContents(String name)
    {
        switch (name)
        {
            case "지역괴담":
                contents = DataHouse.region;
                sub=DataHouse.sub.get(0);
                return contents;
            case "군대괴담":
                contents = DataHouse.millitary;
                sub=DataHouse.sub.get(1);
                return contents;
            case "실제이야기":
                contents = DataHouse.real;
                sub=DataHouse.sub.get(2);
                return contents;
            case "대학괴담":
                contents = DataHouse.college;
                sub=DataHouse.sub.get(3);
                return contents;
            //case "4컷 만화":
              //  contents = DataHouse.understand2;
               // sub=DataHouse.sub.get(4);
              //  return contents;
            case "로어":
                contents = DataHouse.lore;
                sub=DataHouse.sub.get(4);
                return contents;
            case "이해하면 무서운 이야기":
                contents = DataHouse.understand;
                sub=DataHouse.sub.get(5);
                return contents;
            case "도시괴담":
                contents = DataHouse.city;
                sub=DataHouse.sub.get(6);
                return contents;
        }
        return contents;
    }



}