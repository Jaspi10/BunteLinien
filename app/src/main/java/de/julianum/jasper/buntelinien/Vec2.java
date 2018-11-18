package de.julianum.jasper.buntelinien;

public class Vec2 {

    public final double x;
    public final double y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 add(Vec2 other) {
        return new Vec2(this.x + other.x, this.y + other.y);
    }

    public Vec2 scale(double factor) {
        return new Vec2(this.x * factor, this.y * factor);
    }

    public Vec2 normalize() {
        return this.scale(1.0 / this.len());
    }

    public Vec2 rescale(double length) {
        return this.normalize().scale(length);
    }

    public Vec2 rotate(double angle) {
        return new Vec2(x * Math.cos(angle) - y * Math.sin(angle),
                        x * Math.sin(angle) + y * Math.cos(angle));
    }

    public double len() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public double dot(Vec2 other) {
        return this.x * other.x + this.y * other.y;
    }
}
