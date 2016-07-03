package com.example.xutils3_demo.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.tv.TvContract;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.xutils3_demo.R;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.io.File;

/**
 * Created by 小新 on 2016/7/2.
 */
@ContentView(R.layout.http_view)
public class HttpFragment extends Fragment {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    String url = "http://www.baidu.com";
    @Event(R.id.get)
    private void get(View view){
        //请求的时候提示一个对话框
        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("请稍后...");
        RequestParams requestParams = new RequestParams(url);
        requestParams.addQueryStringParameter("name","xiaoxin");
        requestParams.addQueryStringParameter("password","123456");
        //发送Get请求，回调函数
        Callback.Cancelable cacelable=x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            //网络请求成功，回调的方法  result是服务器端返回的内容
            public void onSuccess(String result) {

            }

            @Override
            //请求发生异常回调的方法 ex异常的信息 isOnCallback异常的来源
            //为true异常来源于回调方法
            public void onError(Throwable ex, boolean isOnCallback) {

            }


            @Override
            //我们主动调用请求取消时回调的方法
            public void onCancelled(CancelledException cex) {

            }

            @Override
            //不管返回成功还是失败都要执行的方法
            public void onFinished() {
                //不管请求成功还是失败都要取消掉这个对话框
                progressDialog.cancel();

            }
        });
        cacelable.cancel();//我们主动调用的话，回调的onCancelled()方法会调用
    }
    @Event(R.id.post)
    private void post(View view){
       RequestParams requestParams = new RequestParams(url);
        //将username添加到网络请求的包类中
        requestParams.addBodyParameter("username","xiaoxin");
        //addParameter如果当前请求是get会将password添加到url后面
        //如果是当前请求是post会将password添加到包类中
        requestParams.addParameter("password","123456");
        //为请求添加头
        requestParams.addHeader("head","head");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

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
    @Event(R.id.other)
    private void other(View v) {
        RequestParams params = new RequestParams(url);
        params.addParameter("username", "xiaoxin");
        //HttpMethod有网络请求的所有方法
//        HttpMethod.CONNECT;
//        HttpMethod.TRACE;
//        HttpMethod.PUT;
        x.http().request(HttpMethod.PUT, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

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

    @Event(R.id.download)
    private void download(View v){
        //下载地址
        url = "http://124.193.230.157/dd.myapp.com/16891/28F3DE528CE9DAE149E2D39A26BB94CA.apk?mkey=5735c90b910ce983&f=8e5d&c=0&fsname=com.jikexueyuan.geekacademy_4.2.0-4f71632_421.apk&p=.apk";
        RequestParams params = new RequestParams(url);
        //自定义下载的路径
        params.setSaveFilePath(Environment.getExternalStorageDirectory()+"/xiaoxin/");
        //自定义下载文件的名字name=com.jikexueyuan.geekacademy_4.2.0-4f71632_421.apk是下载路径中的名字
        params.setAutoRename(true);
        //post请求
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                Log.e("download","onSuccess");
                //是否安装
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(result),"application/vnd.android.package-archive");
                getActivity().startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("download","onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("download","onCancelled");
            }

            @Override
            public void onFinished() {
                Log.e("download","onFinished");


            }

            @Override
            public void onWaiting() {
                Log.e("download","onWaiting");

            }

            @Override
            public void onStarted() {
                Log.e("download","onStarted");

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                //下载过程中 total总的大小  current当前的大小  isDownloading是否正在下载
                ProgressDialog progress = new ProgressDialog(getActivity());
                progress.setMessage("total"+total+"download..."+current);
                progress.show();
                Log.i("houn.xu","current<<"+current +" total<<"+total);
                Log.e("download","onLoading");
            }
        });
    }

    @Event(R.id.cache)
    private void cache(View v) {
        RequestParams params = new RequestParams(url);
        //缓存的时间60s
        params.setCacheMaxAge(1000*60);
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("xiaoxin1","onSuccess<<"+result);
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

            @Override
            public boolean onCache(String result) {
                //false 不相信本地缓存 每次请求的时候都发送一次
                //true 相信本地缓存 在60秒内多次请求同一个的话，使用缓存，只发送一次
                Log.i("xiaoxin","cache<<"+result);
                return true;
            }
        });
    }

}
