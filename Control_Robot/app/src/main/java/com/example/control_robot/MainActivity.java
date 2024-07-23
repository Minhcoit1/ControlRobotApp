package com.example.control_robot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.os.Handler;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //For control robot
    private OkHttpClient client = new OkHttpClient();
    private Call currentCall;
    private Button ForwardButton;
    private Button BackButton;
    private Button LeftButton;
    private Button RightButton;
    private Button FLeftButton;
    private Button FRightButton;
    private Button BLeftButton;
    private Button BRightButton;
    private String robotIP;
    private TextView TextViewIP;
    private String ipESP = "10.42.0.173";
    private WebView superWebView;
    private ImageView superImageView;
    private ProgressBar superProgressBar;
    private WebView webView_sensor;

    private Executor executor = Executors.newSingleThreadExecutor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //control robot
        ForwardButton = findViewById(R.id.btn_forward);
        BackButton = findViewById(R.id.btn_back);
        LeftButton = findViewById(R.id.btn_Left);
        RightButton = findViewById(R.id.btn_Right);
        FLeftButton = findViewById(R.id.btn_FLeft);
        FRightButton = findViewById(R.id.btn_FRight);
        BLeftButton = findViewById(R.id.btn_BLeft);
        BRightButton = findViewById(R.id.btn_BRight);

        superProgressBar = findViewById(R.id.myProgressBar);
        superImageView = findViewById(R.id.myrImageView);        superWebView = findViewById(R.id.myWebView);

        //for webView sensor
        webView_sensor = findViewById(R.id.webView_sensor);

        //enable js for webnview
        WebSettings webSettings_sensor = webView_sensor.getSettings();
        webSettings_sensor.setJavaScriptEnabled(true);

        //Set WebChromeClint for each WebView
        webView_sensor.loadUrl("http://10.42.0.1:8080/");


        ForwardButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","F");
                        }
                    });
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","S");
                            controlRobot("http://" + ipESP + "/","");
                        }
                    });
                    return false;
                }
                return false;
            }
        });

        BackButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","B");
                        }
                    });
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","S");
                            controlRobot("http://" + ipESP + "/","");
                        }
                    });
                    return false;
                }
                return false;
            }
        });

        LeftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","R");
                        }
                    });
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","S");
                            controlRobot("http://" + ipESP + "/","");
                        }
                    });
                    return false;
                }
                return false;
            }
        });

        RightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","L");
                        }
                    });
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","S");
                            controlRobot("http://" + ipESP + "/","");
                        }
                    });
                    return false;
                }
                return false;
            }
        });

        FLeftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","I");
                        }
                    });
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","S");
                            controlRobot("http://" + ipESP + "/","");
                        }
                    });
                    return false;
                }
                return false;
            }
        });

        FRightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","G");
                        }
                    });
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","S");
                            controlRobot("http://" + ipESP + "/","");
                        }
                    });
                    return false;
                }
                return false;
            }
        });

        BLeftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","J");
                        }
                    });
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","S");
                            controlRobot("http://" + ipESP + "/","");
                        }
                    });
                    return false;
                }
                return false;
            }
        });

        BRightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","H");
                        }
                    });
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            controlRobot("http://" + ipESP + "/","S");
                            controlRobot("http://" + ipESP + "/","");
                        }
                    });
                    return false;
                }
                return false;
            }
        });



        SeekBar seekBar_Speed = findViewById(R.id.seekBar_speed);

        seekBar_Speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

               // Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        controlRobot("http://" + ipESP + "/",String.valueOf(progress));
                    }
                });
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                //Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {

                        controlRobot("http://" + ipESP + "/","S");
                        controlRobot("http://" + ipESP + "/","");
                    }
                });
            }
        });

        superProgressBar.setMax(100);
        superWebView.loadUrl("http://10.42.0.1:8000");
        superWebView.getSettings().setJavaScriptEnabled(true);
        superWebView.getSettings().setLoadWithOverviewMode(true);
        superWebView.getSettings().setUseWideViewPort(true);
        superWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        superWebView.setScrollbarFadingEnabled(true);

        superWebView.setWebViewClient(new WebViewClient());
        superWebView.setWebChromeClient(new WebChromeClient()
            {
                @Override
                public void onProgressChanged(WebView view, int newProgress)
                {
                    super.onProgressChanged(view, newProgress);
                    superProgressBar.setProgress(newProgress);
                }

                @Override
                public void onReceivedTitle(WebView view, String title)
                {
                    //super.onReceivedTitle(view, title);
                    getSupportActionBar().setTitle("EAE to the Moon");
                }

                @Override
                public void onReceivedIcon(WebView view, Bitmap icon)
                {
                    super.onReceivedIcon(view, icon);
                    superImageView.setImageBitmap(icon);
                }
            }
        );
    }

    @Override
    public void onBackPressed()
    {
        if(superWebView.canGoBack())
        {
            superWebView.goBack();
        }else {
            finish();
        }
    }
    private void controlRobot(String url, String value){

        RequestBody formBody = new FormBody.Builder()
                .add("State",value)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        currentCall = client.newCall(request);

        currentCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });
    }
}






















