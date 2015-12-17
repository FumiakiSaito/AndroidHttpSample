package com.example.fumiaki.myhttpapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    // ログ出力時のタグ名
    private static final String LOG_PREFIX = "TESTLOG";

    // Volleyでリクエスト時に設定するタグ名。キャンセル時に利用する。
    private static final Object TAG_REQUEST_QUEUE = new Object();

    // リクエスト先
    private static final String REQUEST_URL_GET  = "http://hoge/get.php?param1=value1&param2=value2";
    private static final String REQUEST_URL_POST = "http://hoge/post.php";

    // RequestQueueのインスタンス用
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RequestQueueのインスタンス用を取得
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

    }

    /*
     * onCreateの後に呼び出される。
     */
    @Override
    public void onStart(){
        super.onStart();

        // GETリクエスト
        doGetRequest();

        // POSTリクエスト
        doPostRequest();
    }

    /*
     * アプリが見えなくなったときに呼び出される。
     * （他のアプリを立ち上げたりした時など）
     */
    @Override
    public void onStop(){
        super.onStop();
        mRequestQueue.cancelAll(TAG_REQUEST_QUEUE);
    }

    /*
     * GETリクエスト処理
     */
    private void doGetRequest(){

        StringRequest request = new StringRequest(

                Request.Method.GET,
                REQUEST_URL_GET,

                // レスポンス成功時のリスナー
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response == null) {
                            return;
                        }
                        Log.v(LOG_PREFIX, "RESPONSE=" + response);
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                },

                // レスポンス失敗時のリスナー
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.v(LOG_PREFIX, "ERROR=" + error);
                        Toast.makeText(getApplicationContext(), "onErrorResponse", Toast.LENGTH_LONG).show();

                    }
                }
        );

        // タグを設定する
        request.setTag(TAG_REQUEST_QUEUE);

        // リクエスト＆レスポンス情報の設定を追加
        mRequestQueue.add(request);

        // リクエスト開始
        mRequestQueue.start();
    }


    /*
     * POSTリクエスト処理
     */
    private void doPostRequest() {

        StringRequest request = new StringRequest(

                Request.Method.POST,
                REQUEST_URL_POST,

                // レスポンス成功時のリスナー
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response == null) {
                            return;
                        }
                        Log.v(LOG_PREFIX, "RESPONSE=" + response);
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                },

                // レスポンス失敗時のリスナー
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.v(LOG_PREFIX, "ERROR=" + error);
                        Toast.makeText(getApplicationContext(), "onErrorResponse", Toast.LENGTH_LONG).show();

                    }
                }) {
                    protected Map<String, String> getParams() {
                        //POSTで送信したい値を設定する
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("param1", "value1");
                        return params;
                    }
                };

        // タグを設定する
        request.setTag(TAG_REQUEST_QUEUE);

        // リクエスト＆レスポンス情報の設定を追加
        mRequestQueue.add(request);

        // リクエスト開始
        mRequestQueue.start();
    }


}
