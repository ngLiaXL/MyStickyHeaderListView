package com.ldroid.sticky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Adapter mAdapter;

    private View mStickyHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list_view);
        mStickyHeader = findViewById(R.id.fl_stick_header);

        mListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_blank_header, null));
        mListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_stick_header, null));
        mAdapter = new Adapter();


        mListView.setAdapter(mAdapter);


        ArrayList<String> listData = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            listData.add("第" + i + "行");
        }
        mAdapter.setListData(listData);


        mStickyHeader.setVisibility(View.GONE);


        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 1) {
                    mStickyHeader.setVisibility(View.VISIBLE);
                } else {
                    mStickyHeader.setVisibility(View.GONE);
                }
            }
        });


    }


    class Adapter extends BaseAdapter {

        ArrayList<String> listData;


        public void setListData(ArrayList<String> listData) {
            this.listData = listData;
        }

        @Override
        public int getCount() {
            return listData != null ? listData.size() : 0;
        }

        @Override
        public String getItem(int position) {
            return (listData != null && !listData.isEmpty()) ? listData.get(position) : null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout
                        .layout_list_item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(getItem(position));
            return convertView;
        }
    }

    class ViewHolder {
        TextView textView;

        public ViewHolder(View view) {
            textView = (TextView) view.findViewById(R.id.text_view_item);
        }
    }
}
