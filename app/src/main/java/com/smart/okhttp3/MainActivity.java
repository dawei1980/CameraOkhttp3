package com.smart.okhttp3;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getHttp();
        uploadFile();
    }

    private void getHttp(){
        String url = "http://172.18.5.248:8080/AICameraApi/camera/serverInfo";

        OkHttp3Utils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * 请求失败
                         * */
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String responeString = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /**do something
                         * 写Json解析
                         * */
                    }
                });
            }
        });
    }

    private void postHttp(){
        String url = "http://172.18.5.248:8080/AICameraApi/camera/serverInfo";
        Map<String, String> map = new HashMap<>();
        map.put("name","John");
        OkHttp3Utils.doPost(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String responeString = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /**do something
                         * 写Json解析
                         * */
                    }
                });
            }
        });
    }

    private void uploadFile(){
        HashMap<String,String> map = new HashMap<>();
        map.put("cameraID","S0001_0027");
        String url = "http://172.18.5.248:8080/AICameraApi/camera/uploadInfo";

        OkHttp3UploadUtils.doPostUploadRequest(url, map, initUploadFile(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * 请求失败
                         * */
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responeString = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"上传文件成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    //初始化上传文件的数据
    private List<String> initUploadFile(){
        List<String> fileNames = new ArrayList<>();
        fileNames.add(Environment.getExternalStorageDirectory()+"/"+"smartPhoneCamera/database/" + "module.db"); //txt文件
        fileNames.add(Environment.getExternalStorageDirectory()+"/"+"smartPhoneCamera/database/" + "module.db-journal"); //图片
        return fileNames;
    }
}
