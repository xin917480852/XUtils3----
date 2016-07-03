package com.example.xutils3_demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.xutils3_demo.R;
import com.example.xutils3_demo.adapter.ChildAdapter;
import com.example.xutils3_demo.domain.Child;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小新 on 2016/7/2.
 */
@ContentView(R.layout.inject_view)
public class InjectFragment extends Fragment {
    @ViewInject(R.id.listview)
    ListView listView;
    @ViewInject(R.id.button)
    Button button;
    List<Child> children = new ArrayList<Child>();
    Child child;
    public InjectFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i=0;i<100;i++){
            child = new Child("child"+i);
            children.add(child);


        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setAdapter(new ChildAdapter(getActivity(),children));
    }


//    button.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//        }
//    });
@Event(type = View.OnClickListener.class,value =R.id.button)
    //必须是私有的  默认的type = View.OnClickListener.class
//  private void testInectOnClick(View view)
//public void onClick(View v) {
//void和void对应
//        View view 和View view对应
    private void testInectOnClick(View view){
    Snackbar.make(view,"onClickListener",Snackbar.LENGTH_SHORT).show();

    }

//    button.setOnLongClickListener(new View.OnLongClickListener() {
//        @Override
//        public boolean onLongClick(View v) {
//            return false;
//        }
//    });
    @Event(type =View.OnLongClickListener.class,value = R.id.button)
    private boolean testOnLongClickListener(View view){
        Snackbar.make(view,"testOnLongClickListener",Snackbar.LENGTH_SHORT).show();
        return true;
    }

//    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//        }
//    });
    @Event(type = AdapterView.OnItemClickListener.class,value = R.id.listview)
    private void testonItemClick(AdapterView<?> parent, View view, int position, long id){
        Snackbar.make(view,children.get(position).getName(),Snackbar.LENGTH_SHORT).show();
    }

}
