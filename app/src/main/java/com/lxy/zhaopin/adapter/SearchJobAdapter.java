package com.lxy.zhaopin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lxy.zhaopin.R;
import com.lxy.zhaopin.bean.Search;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/1.
 */

public class SearchJobAdapter extends BaseAdapter {

    List<Search.PositionDataListBean> listBeen;
    Context context;

    public SearchJobAdapter(List<Search.PositionDataListBean> listBeen, Context context) {
        this.listBeen = listBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_job, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.jobName.setText(listBeen.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.job_name)
        TextView jobName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
