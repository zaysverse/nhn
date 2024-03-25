package com.nhnacademy.model.domain.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.nhnacademy.model.Config;
import com.nhnacademy.model.domain.Point;
import com.nhnacademy.model.interfaces.HitListener;
import com.nhnacademy.model.interfaces.Paintable;
import com.nhnacademy.model.interfaces.Regionable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ball implements Regionable, Paintable, HitListener {
    private static int count = 0;
    private int id = ++count;

    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    private Rectangle region;
    private Color color;
    HitListener hitListener;

    public Ball(int x, int y, int radius) {
        this(x, y, radius, Config.BALL_DEFAULT_COLOR);
    }

    public Ball(int x, int y, int radius, Color color) {
        if (radius <= 0) {
            throw new IllegalArgumentException("반지름은 0보다 커야 합니다.");
        }

        if ((x + (long) radius > Integer.MAX_VALUE)
                || (x - (long) radius < Integer.MIN_VALUE)
                || (y + (long) radius > Integer.MAX_VALUE)
                || (y - (long) radius < Integer.MIN_VALUE)) {
            throw new IllegalArgumentException("볼이 정수 공간을 벗어납니다.");
        }

        region = new Rectangle(x - radius, y - radius, 2 * radius, 2 * radius);
        // logger.trace("Ball created : {}, {}, {}", x, y, radius);
        this.color = color;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public int getX() {
        return (int) region.getCenterX();
    }

    public int getY() {
        return (int) region.getCenterY();
    }

    public Color getColor() {
        return color;
    }

    void setX(int x) {
        region.setLocation(x - getRadius(), getY() - getRadius());
    }

    void setY(int y) {
        region.setLocation(getX() - getRadius(), y - getRadius());
    }

    public Point getLocation() {
        return new Point((int) region.getCenterX(), (int) region.getCenterY());
    }

    void setLocation(Point location) {
        region.setLocation(location.getX() - getRadius(), location.getY() - getRadius());
    }

    public int getRadius() {
        return (int) region.getWidth() / 2;
    }

    public Rectangle getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%d)", getX(), getY(), getRadius());
    }

    /**
     * @param color
     * @throws IllegalArgumentException color는 null 허용하지 않습니다
     */
    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException();
        }

        this.color = color;
    }

    @Override
    public void paint(Graphics g) {
        if (g == null) {
            throw new IllegalArgumentException();
        }

        Color originalColor = g.getColor();

        g.setColor(getColor());
        g.fillOval(getX() - getRadius(), getY() - getRadius(), getRadius() * 2, getRadius() * 2);

        g.setColor(originalColor);
    }

    @Override
    public void hit(Regionable other) {
        if (hitListener != null) {
            hitListener.hit(other);
        }
    }

    @Override
    public void setHitListener(HitListener listener) {
        this.hitListener = listener;
    }

    public int getMinX() {
        return (int) region.getMinX();
    }

    public int getCenterX() {
        return (int) region.getCenterX();
    }

    public int getMaxX() {
        return (int) region.getMaxX();
    }

    public int getMinY() {
        return (int) region.getMinY();
    }

    public int getCenterY() {
        return (int) region.getCenterY();
    }

    public int getMaxY() {
        return (int) region.getMaxY();
    }

    public int getWidth() {
        return (int) region.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) region.getHeight();
    }

}
