package de.julianum.jasper.buntelinien;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Particle {

    private Vec2 position;
    private Vec2 velocity;

    //Dimensions of the Space, in which the Particle is confined
    private int width;
    private int height;

    public Particle(Vec2 position, Vec2 velocity, int width, int height) {
        this.position = position;
        this.velocity = velocity;
        this.width = width;
        this.height = height;

    }

    public void update(double deltaTime) {
        position = position.add(velocity.scale(deltaTime));

        if (position.x > width) {
            position = new Vec2(width, position.y);
            velocity = new Vec2(-velocity.x, velocity.y);
        }

        if (position.x < 0) {
            position = new Vec2(0, position.y);
            velocity = new Vec2(-velocity.x, velocity.y);
        }

        if (position.y > height) {
            position = new Vec2(position.x, height);
            velocity = new Vec2(velocity.x, -velocity.y);
        }

        if (position.y < 0) {
            position = new Vec2(position.x, 0);
            velocity = new Vec2(velocity.x, -velocity.y);
        }
    }

    public void draw(Canvas c, Paint p) {
        c.drawArc((float) position.x - 20.0f, (float) position.y - 20.0f, (float) position.x + 20.0f, (float) position.y + 20.0f,0.0f, 360.0f, false, p);
    }

    public Vec2 getPosition() {
        return this.position;
    }

    public void setSpeed(double speed) {
        velocity = velocity.rescale(speed);
    }

    public void setConstraints(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
