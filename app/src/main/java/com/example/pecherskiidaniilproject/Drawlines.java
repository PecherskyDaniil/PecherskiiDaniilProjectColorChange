package com.example.pecherskiidaniilproject;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import androidx.annotation.Nullable;

import java.util.ArrayList;




public class Drawlines extends View {
    Path path = new Path();
    Paint paint = new Paint();
    Bitmap bitmap;
    Canvas canvas;
    public boolean startLine;
    boolean clearCanvas = false;
    public ArrayList<Float> coordinatx = new ArrayList<>();
    public ArrayList<Float> coordinaty = new ArrayList<>();

    public Drawlines(Context context) {
        super(context);
    }

    public Drawlines(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Drawlines(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        coordinaty.add(event.getY());
        coordinatx.add(event.getX());
        if (event.getAction() == MotionEvent.ACTION_UP) {
            clearCanvas = true;
            startLine = true;
            coordinaty.clear();
            coordinatx.clear();
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(coordinatx.get(coordinatx.size() - 1), coordinaty.get(coordinaty.size() - 1));
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(coordinatx.get(coordinatx.size() - 1), coordinaty.get(coordinaty.size() - 1));
                break;
            case MotionEvent.ACTION_UP:
                canvas.drawPath(path, paint);
                path.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.drawPath(path, paint);
        if (clearCanvas == true) {
            canvas.drawColor(Color.WHITE);
            bitmap.eraseColor(Color.TRANSPARENT);
            clearCanvas = false;
        }
    }



}

