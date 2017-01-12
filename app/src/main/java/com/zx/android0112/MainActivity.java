package com.zx.android0112;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText audioEdt; //要合成语音的文字

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioEdt = (EditText) findViewById(R.id.audio_edt);
        findViewById(R.id.audio_play_btn).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AudioUtils.getInstance(this).destroy();
    }

    @Override
    public void onClick(View v) {
        String text = audioEdt.getText().toString().trim();
        if(!TextUtils.isEmpty(text)){
            AudioUtils.getInstance(this).speakText(text); //合成语音
        }
    }
}
