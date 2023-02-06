package com.example.snimaczvuka_bacani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button a, b, c, d, e, f, g, g1;

    private static int MICROPHONE_PERMISSION_CODE = 200;
    MediaRecorder mediaRecorder;
    int noteA, noteB, noteC, noteD, noteE, noteF, noteG, noteG1;
    SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(MicrophonePermission()){
            getMicrophonePermission();
        }

        a=(Button) findViewById(R.id.BtnA);
        b=(Button) findViewById(R.id.BtnB);
        c=(Button) findViewById(R.id.BtnC);
        d=(Button) findViewById(R.id.BtnD);
        e=(Button) findViewById(R.id.BtnE);
        f=(Button) findViewById(R.id.BtnF);
        g=(Button) findViewById(R.id.BtnG);
        g1=(Button) findViewById(R.id.BtnG1);

        soundPool = new SoundPool.Builder().setMaxStreams(5).build();

        noteA = soundPool.load(this, R.raw.a,1);
        noteB = soundPool.load(this, R.raw.b,1);
        noteC = soundPool.load(this, R.raw.c1,1);
        noteD = soundPool.load(this, R.raw.d,1);
        noteE = soundPool.load(this, R.raw.e,1);
        noteF = soundPool.load(this, R.raw.f,1);
        noteG = soundPool.load(this, R.raw.g,1);
        noteG1 = soundPool.load(this, R.raw.g1,1);

        Context context = getApplicationContext();
        CharSequence text1 = "Recording has started.";
        CharSequence text2 = "Recording has stopped.";
        int duration = Toast.LENGTH_SHORT;



        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat((MediaRecorder.OutputFormat.THREE_GPP));
                    mediaRecorder.setOutputFile(getFilePath());
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.prepare();
                    mediaRecorder.start();

                    Toast toast = Toast.makeText(context, text1, duration);
                    toast.show();
                }

                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;

                Toast toast = Toast.makeText(context, text2, duration);
                toast.show();
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(noteC, 1,1,0,0,1);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(noteD, 1,1,0,0,1);
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(noteE, 1,1,0,0,1);
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(noteF, 1,1,0,0,1);
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(noteG, 1,1,0,0,1);
            }
        });
        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(noteG1, 1,1,0,0,1);
            }
        });


    }

    private boolean MicrophonePermission(){
        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            return true;
        }
        else{
            return false;
        }
    }

    private void getMicrophonePermission (){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
        }
    }

    private String getFilePath(){
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, "testRecordingFile" + ".mp3");
        return file.getPath();
    }
}