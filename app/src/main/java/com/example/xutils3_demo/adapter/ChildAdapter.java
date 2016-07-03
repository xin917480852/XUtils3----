package com.example.xutils3_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xutils3_demo.R;
import com.example.xutils3_demo.domain.Child;


import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by 小新 on 2016/7/2.
 */
public class ChildAdapter extends BaseAdapter {

    List<Child> children;
    LayoutInflater layoutInflater;

    public ChildAdapter(Context context,List<Child> children) {
        this.children = children;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return children.size();
    }

    @Override
    public Object getItem(int position) {
        return children.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.child_view,null);
            viewHolder = new ViewHolder();
            //2.ViewHolder的注解
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Child child = children.get(position);
            viewHolder.textView.setText(child.getName());
        return convertView;
    }

    public class ViewHolder{
        //1.ViewHolder的创建，加载里面的控件
        @ViewInject(R.id.textview)
        TextView textView;

    }
}
