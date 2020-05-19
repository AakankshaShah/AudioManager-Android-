package com.example.demoappjava;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
MediaPlayer memor;
SeekBar vc,scrubber;
AudioManager am;
    @Override
    protected void onStart() {
        super.onStart();
        memor.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        memor = MediaPlayer.create(this, R.raw.mar);
        vc = (SeekBar) findViewById(R.id.vc);
        scrubber = (SeekBar) findViewById(R.id.scrubber);
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxvol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        vc.setMax(maxvol);
        vc.setProgress(currentVol);
        scrubber.setMax(memor.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubber.setProgress(memor.getCurrentPosition());
            }
        }, 0, 10000);


        vc.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

            }


            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });
        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                memor.seekTo(progress);


            }


            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    public void pauseClick(View view) {
        memor.pause();
    }

    public void playClick(View view) {memor.start();
    }
}