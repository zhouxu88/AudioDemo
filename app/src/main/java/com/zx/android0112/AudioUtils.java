package com.zx.android0112;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * Created by 周旭 on 2017/1/12.
 *
 * 讯飞语音工具类（文字合成语音）
 */

public class AudioUtils implements SynthesizerListener {


    private static final String XF_TTS_ROLE = "xiaoyan"; //设置发音人
    private static final String XF_TTS_SPEED = "60"; //设置语速
    private static final String XF_TTS_VOLUME = "100"; //设置音量，范围0~100
    private static final String XF_TTS_PITCH = "50"; // 设置语调

    private Context mContext;
    private static AudioUtils audioUtils;
    private SpeechSynthesizer mSpeechSynthesizer; // 合成对象
    private boolean isFinish = true;

    private AudioUtils(Context context) {
        //getApplicationContext避免单例造成的内存泄漏
        mContext = context.getApplicationContext();
        init();
    }

    //获取工具类的单例
    public synchronized static AudioUtils getInstance(Context context) {
        if (audioUtils == null) {
            audioUtils = new AudioUtils(context);
        }
        return audioUtils;
    }

    private void init() {
        mSpeechSynthesizer = SpeechSynthesizer.createSynthesizer(mContext, initListener);
        initSpeechSynthesizer();
    }

    /**
     * 使用SpeechSynthesizer合成语音，不弹出合成Dialog.
     *
     * @param
     */
    public void speakText(String speakText) {
        if (!isFinish) {
            return;
        }
        if (null == mSpeechSynthesizer) {
            init();
        }
        // 进行语音合成.
        mSpeechSynthesizer.startSpeaking(speakText, this);

    }

    /**
     * 使用SpeechSynthesizer合成语音，不弹出合成Dialog.
     *
     * @param
     */
    public void speakText(String speakText, String role) {
        if (!isFinish) {
            return;
        }
        if (null == mSpeechSynthesizer) {
            init();
        }
        // 进行语音合成.
        mSpeechSynthesizer.setParameter(SpeechConstant.VOICE_NAME, role);
        mSpeechSynthesizer.startSpeaking(speakText, this);

    }

    private InitListener initListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d("mySynthesiezer:", "InitListener init() code = " + code);
        }
    };

    public void stopSpeaking() {
        if (mSpeechSynthesizer != null)
            mSpeechSynthesizer.stopSpeaking();
    }

    public void startSpeaking() {
        isFinish = true;
    }

    private void initSpeechSynthesizer() {
        // 设置发音人
        //Log.i("TTS", "----->>>>initSpeechSynthesizer: " + XF_TTS_ROLE);
        mSpeechSynthesizer.setParameter(SpeechConstant.VOICE_NAME, XF_TTS_ROLE);
        // 设置语速
        mSpeechSynthesizer.setParameter(SpeechConstant.SPEED, XF_TTS_SPEED);
        // 设置音量
        mSpeechSynthesizer.setParameter(SpeechConstant.VOLUME, XF_TTS_VOLUME);
        // 设置语调
        mSpeechSynthesizer.setParameter(SpeechConstant.PITCH, XF_TTS_PITCH);
        // 设置云端
        //		mSpeechSynthesizer.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant
        // .TYPE_CLOUD);
        //设置语言类型，可选值：en_us（英语）、zh_cn（汉语
        //mSpeechSynthesizer.setParameter(SpeechConstant.LANGUAGE,"zh_cn");
    }

    @Override
    public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onCompleted(SpeechError arg0) {
        // TODO Auto-generated method stub
        isFinish = true;
    }

    @Override
    public void onSpeakBegin() {
        // TODO Auto-generated method stub
        isFinish = false;
    }

    @Override
    public void onSpeakPaused() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSpeakProgress(int percent, int beginPos, int endPos) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSpeakResumed() {
        // TODO Auto-generated method stub

    }

    //退出时，释放资源
    public void destroy() {
        if (mSpeechSynthesizer != null) {
            mSpeechSynthesizer.stopSpeaking();
        }
    }

    @Override
    public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
        // TODO Auto-generated method stub

    }


}
