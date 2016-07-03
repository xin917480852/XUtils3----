package com.example.xutils3_demo.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.xutils3_demo.R;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

/**
 * Created by 小新 on 2016/7/2.
 */
@ContentView(R.layout.image_view)
public class ImageFragment extends Fragment {
    @ViewInject(R.id.imageview1)
    ImageView imageView1;
    @ViewInject(R.id.imageview2)
    ImageView imageView2;
    @ViewInject(R.id.imageview3)
    ImageView imageView3;
    @ViewInject(R.id.imageview4)
    ImageView imageView4;
    @ViewInject(R.id.imageview5)
    ImageView imageView5;
    @ViewInject(R.id.imageview6)
    ImageView imageView6;
    String[] urls = {
            "http://img4.duitang.com/uploads/item/201209/03/20120903121445_nx5wk.jpeg",
            "http://i7.qhimg.com/t01755f436ab31acc3e.jpg",
            "http://www.dianziyan668.com/images/mnsg4lteovuxiylom4xgg33n/uploads/item/201203/11/20120311204006_GraP2.jpeg",
            "http://img4.imgtn.bdimg.com/it/u=2141450888,3610011306&fm=11&gp=0.jpg",
            "http://img3.duitang.com/uploads/item/201509/20/20150920214014_dYSRj.jpeg",
            "http://p2.gexing.com/G1/M00/85/40/rBACFFPoibzDeIc6AAC_zw6Zw1Y995_600x.jpg"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPic();
    }

    private void setPic() {
        x.image().bind(imageView1, urls[0]);

        ImageOptions imageOptions = new ImageOptions.Builder()
                .setConfig(Bitmap.Config.ARGB_8888)//设置图片的质量
                .setFadeIn(true)//设置为true的时候图片有淡入的效果
                .setCircular(true)//设置为true的话，图片会展示圆形
                .setAutoRotate(true)//设置为ture，图片会自动旋转
                .build();
        //设置图片的大小为200*200 记得要setCrop(true)
        ImageOptions imageOptions1 = new ImageOptions.Builder().setCrop(true).setSize(200, 200).build();

        x.image().bind(imageView2, urls[1], imageOptions);


        x.image().bind(imageView3, urls[2], new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        x.image().bind(imageView4, urls[3], imageOptions1, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        Callback.Cancelable cancelable= x.image().loadDrawable(urls[4], imageOptions, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                //给imageView5设置图片
                imageView5.setImageDrawable(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        //cancelable.cancel();//取消的时候回调public void onCancelled(CancelledException cex)方法
        //    当我们通过bingd()或者loadDrawable()方法加载以一张图片之后他会保存到本地文件中
//    当我们需要他的时候我们需要查找，这个时候我们就可以通过网络的url地址找到
//    在本地中的图片
        x.image().loadFile(urls[0], imageOptions, new Callback.CacheCallback<File>() {
            @Override
            public boolean onCache(File result) {
                //图片另存为等操作
                return true;//为true是相信本地缓存，不在进行网络请求
            }
            @Override
            public void onSuccess(File result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }




}
