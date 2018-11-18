package de.julianum.jasper.buntelinien;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Line {

    private Vec2 p1;
    private Vec2 p2;

    private int color;

    private int age;

    public Line(Vec2 p1, Vec2 p2, int color, int age) {
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
        this.age = age;
    }

    public int decrementAge() {
        return age -= 1;
    }

    public void draw(Canvas c, Paint p, boolean mirrorX, boolean mirrorY) {
        p.setColor(color);
        c.drawLine((float) p1.x, (float) p1.y, (float) p2.x, (float) p2.y, p);

        if (mirrorX) {
            c.drawLine((float) (c.getWidth()-p1.x), (float) p1.y, (float) (c.getWidth()-p2.x), (float) p2.y, p);
        }

        if (mirrorY) {
            c.drawLine((float) p1.x, (float) (c.getHeight()-p1.y), (float) p2.x, (float) (c.getHeight()-p2.y), p);
        }

        if (mirrorX && mirrorY) {
            c.drawLine((float) (c.getWidth()-p1.x), (float) (c.getHeight()-p1.y), (float)(c.getWidth()-p2.x), (float) (c.getHeight()-p2.y), p);
        }
    }
}
