package com.zhimei.customcircleprogressbar.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.zhimei.customcircleprogressbar.R;

/**
 * Created by 张佳亮 on 2016/7/4.
 */

public class MyCircleProgressBar extends View {
    private Paint mPaint;
    /**
     * 圆弧的宽度
     */
    private int circleWidth=40;
    private int progress=0;
    /**
     * 绘制的速度
     */
    private int speed=20;
    /**
     * 圆弧的半径
     */
    private int radius=250;
    /**
     * 线性着色器，使画笔在这三种色彩上形成渐变的效果
     */
    private Shader mLinearGradient1 = null;
    /**
     * 绘制过程中，三种渐变的色彩
     */
    private int firstColor=Color.RED;
    private int secondColor=Color.GREEN;
    private int thirdColor=Color.BLUE;
    /**
     * 进度或者分数
     */
    private int grade=60;
    private int grade2=0;
    /**
     * 控件中间的小图片
     */
    private Bitmap icon;
    /**
     * 字体的大小和颜色
     */
    private int textSize=150;
    private int textColor;
    /**
     * 是否为虚线
     */
    private boolean isDashedLine=true;

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setCircleWidth(int circleWidth) {
        this.circleWidth = circleWidth;
    }



    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void setRadius(int radius) {
        this.radius = radius;
    }


    public void setFirstColor(int firstColor) {
        this.firstColor = firstColor;
    }


    public void setSecondColor(int secondColor) {
        this.secondColor = secondColor;
    }


    public void setThirdColor(int thirdColor) {
        this.thirdColor = thirdColor;
    }


    public void setGrade(int grade) {
        this.grade = grade;
    }


    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }


    public void setDashedLine(boolean dashedLine) {
        isDashedLine = dashedLine;
    }


    public MyCircleProgressBar(Context context) {
        this(context, null);

    }

    public MyCircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context,attrs,defStyleAttr);
        mPaint=new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(circleWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        if(isDashedLine){
            PathEffect effect = new DashPathEffect(new float[] { 10, 10}, 1);
            mPaint.setPathEffect(effect);
        }

        mLinearGradient1 = new LinearGradient(0, 0, 0, 2*radius, new int[] {
                firstColor, secondColor, thirdColor }, null,
                Shader.TileMode.CLAMP);



        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(progress<360){
                        progress++;
                    }else {
                        break;
                    }
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        postInvalidate();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int count =360*speed;
                while (true){
                    if(grade2<grade){
                        grade2++;
                    }else {
                        break;
                    }
                    try {
                        int spaceTime=count/grade;
                        Thread.sleep(spaceTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        postInvalidate();
                    }
                }

            }
        }).start();
    }


    /**
     * 如果测量模式不是EXACTLY（即精确测量），我们就将该View的长和宽都设置为250dp。
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int viewWidth,viewHeight;

        /**
         * 设置宽度
         * 获取测量模式和测量的大小
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            Log.e("xxx", "EXACTLY");
            viewWidth = specSize;
        } else{
            /**
             * 如果view直接赋值为250，则是250sp，通过下面的代码，将sp转化为dip(dp).
             */
            viewWidth=(int)(2*TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,250,getResources().getDisplayMetrics()));
        }

        /**
         * 设置高度
         */
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            Log.e("xxx", "EXACTLY");
            viewHeight = specSize;
            Log.d("liang","viewHeight"+viewHeight);
        } else{
            viewHeight=(int)(2*TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,250,getResources().getDisplayMetrics()));
        }

        setMeasuredDimension(viewWidth, viewHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShader(mLinearGradient1);
        RectF rectF=new RectF(50,50,2*radius,2*radius);
        //Rect rect=new Rect(50,50,2*radius,2*radius);
        //canvas.drawRect(rect,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF,-90,progress,false,mPaint);

        Paint paint=new Paint();
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textSize);
        paint.setSubpixelText(false);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();

        float y = radius - fontMetrics.descent + (fontMetrics.bottom - fontMetrics.top) / 2;

        int margin=(int)(radius*0.2);
        int offest=(int)(radius*0.28);

        Rect rect1=new Rect(radius-margin,radius-margin-offest,
                radius+margin,radius+margin-offest);
        canvas.drawBitmap(icon,null,rect1,paint);
        canvas.drawText(grade2+"",radius,y+70,paint);

    }

    /**
     * 获取自定义属性
     * @param context
     * @param attrs
     * @param defStyleAttr
     */

    void getAttrs(Context context, AttributeSet attrs, int defStyleAttr){
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, defStyleAttr, 0);
        int circleWidth1=(int)a.getDimension(R.styleable.CustomProgressBar_circleWidth,40);
        circleWidth=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,circleWidth1,
                getResources().getDisplayMetrics());
        speed=a.getInt(R.styleable.CustomProgressBar_speed,20);
        radius=(int)a.getDimension(R.styleable.CustomProgressBar_radius,250);

        firstColor=a.getColor(R.styleable.CustomProgressBar_firstColor,Color.RED);
        secondColor=a.getColor(R.styleable.CustomProgressBar_secondColor,Color.GREEN);
        thirdColor=a.getColor(R.styleable.CustomProgressBar_thirdColor,Color.BLUE);
        grade=a.getInt(R.styleable.CustomProgressBar_grade,20);
        Drawable drawable=a.getDrawable(R.styleable.CustomProgressBar_icons);
        if(null!=drawable){
            BitmapDrawable bd = (BitmapDrawable)drawable;
            icon = bd.getBitmap();
        }else{
            icon= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        }


        textSize=(int)a.getDimension(R.styleable.CustomProgressBar_textSizes,150);
        textColor=a.getColor(R.styleable.CustomProgressBar_textColor,Color.RED);
        a.recycle();






    }


}
