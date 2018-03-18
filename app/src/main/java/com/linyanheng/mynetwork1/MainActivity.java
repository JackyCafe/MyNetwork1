package com.linyanheng.mynetwork1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String urlString = "https://sites.google.com/view/JackyCafe01/android-%E7%AD%86%E8%A8%98/activity/activity-%E7%94%9F%E5%91%BD%E9%80%B1%E6%9C%9F";
    HttpURLConnection conn = null;
    URL url ;
    String FILE_NAME = "out.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }).start();

    }



    public void getData()
    {
        try {
            url = new URL(urlString);
            //藉由Call URL.openConnection 並強制轉型為HttpConnection來取得一個 HttpURLConnection 物件
            conn = (HttpURLConnection) url.openConnection();
            InputStream in = conn.getInputStream();
            FileOutputStream out = openFileOutput(FILE_NAME,MODE_APPEND);
            BufferedReader reader =new BufferedReader(new InputStreamReader(in));
            String text ;
            StringBuilder sb = new StringBuilder();
            while ((text=reader.readLine() )!=null)
            {
                out.write(text.getBytes());
                sb.append(text);
            }
            out.flush();
            out.close();
            Log.v("Jacky",sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
