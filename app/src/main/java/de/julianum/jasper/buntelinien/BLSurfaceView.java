package de.julianum.jasper.buntelinien;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BLSurfaceView extends SurfaceView implements Runnable {

    private SurfaceHolder holder = this.getHolder();
    private Context context;
    private Thread thread;

    private Particle p1;
    private Particle p2;
    private List<Line> lines;

    private boolean mirrorX;
    private boolean mirrorY;

    public BLSurfaceView(Context context) {
        this(context, null);
    }

    public BLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        if (!isInEditMode()) {
            new Thread(this).start();
        }
    }

    @Override
    public void run() {
        int width = holder.getSurfaceFrame().width();
        int height = holder.getSurfaceFrame().height();

        p1 = new Particle(new Vec2(200, 500), new Vec2(5, -7), width, height);
        p2 = new Particle(new Vec2(600, 1200), new Vec2(-8, 3), width, height);

        p1.setSpeed(1.0);
        p2.setSpeed(1.0);

        lines = new ArrayList<>();

        Paint p = new Paint();
        p.setStrokeWidth(8.0f);
        p.setColor(Color.RED);

        float[] hsv = new float[3];
        hsv[0] = 0.0f;
        hsv[1] = 1.0f;
        hsv[2] = 1.0f;

        //Main Loop
        while (true) {
            synchronized (this) {
                if (holder.getSurface().isValid()) {
                    Canvas canvas = holder.lockCanvas();

                    canvas.drawColor(Color.rgb(0, 0, 0));

                    //Log.d("BLSurfaceView", "w: " + width + ", h: " + height);

                    hsv[0] = (hsv[0] + 3.0f) % 360.0f;
                    lines.add(new Line(p1.getPosition(), p2.getPosition(), Color.HSVToColor(hsv), 30));

                    for (Line line : lines) {
                        line.draw(canvas, p, mirrorX, mirrorY);
                    }

                    lines = lines.stream().filter(line -> line.decrementAge() > 0).collect(Collectors.toList());

                    p.setColor(Color.RED);
                    //p1.draw(canvas, p);
                    //p2.draw(canvas, p);

                    p1.update(4.0);
                    p2.update(4.0);

                    holder.unlockCanvasAndPost(canvas);
                }
            }

            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        p1.setConstraints(w, h);
        p2.setConstraints(w, h);
    }

    public synchronized void setMirrorX(boolean mirrorX) {
        this.mirrorX = mirrorX;
    }

    public synchronized void setMirrorY(boolean mirrorY) {
        this.mirrorY = mirrorY;
    }

    public synchronized void setSpeed1(double speed) {
        p1.setSpeed(speed);
    }

    public synchronized void setSpeed2(double speed) {
        p2.setSpeed(speed);
    }
}
