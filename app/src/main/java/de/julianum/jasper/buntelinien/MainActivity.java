package de.julianum.jasper.buntelinien;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends Activity {

    private BLSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout);

        this.surfaceView = findViewById(R.id.BLSurfaceView2);

        ((SeekBar) findViewById(R.id.seekBar)).setProgress(10);
        ((SeekBar) findViewById(R.id.seekBar2)).setProgress(10);

        addListeners();
    }

    private void addListeners() {
        ((Switch) findViewById(R.id.switchMirrorX)).setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            surfaceView.setMirrorX(isChecked);
        });

        ((Switch) findViewById(R.id.switchMirrorY)).setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            surfaceView.setMirrorY(isChecked);
        });

        ((SeekBar) findViewById(R.id.seekBar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                surfaceView.setSpeed1((progress+1)/10.0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ((SeekBar) findViewById(R.id.seekBar2)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                surfaceView.setSpeed2((progress+1)/10.0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
