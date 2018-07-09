package com.example.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.activity.R;

public class GuaGuaLe_View extends View {

    private Paint paint;
    private Path path;
    private Bitmap background_pic;
    private Bitmap background_color;
    private Canvas mcanvas;
    private Paint paintFont;

    public GuaGuaLe_View(Context context) {
        super(context);
        //初始化操作
        init();
    }

    public GuaGuaLe_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化操作
        init();
    }

    public GuaGuaLe_View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GuaGuaLe_View(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    //初始化操作
    public void init(){
        //创建画笔对象
        paint = new Paint();
        //设置画笔的透明度
        paint.setAlpha(100);
        //设置画笔的宽度
        paint.setStrokeWidth(100);
        //设置画笔的style
        paint.setStyle(Paint.Style.STROKE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        //设置画笔的路劲为椭圆
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        //画笔的路劲
        path = new Path();

        //设置背景图
        background_pic = BitmapFactory.decodeResource(getResources(),R.mipmap.gril05);
        //设置图层的颜色
        background_color = Bitmap.createBitmap(1080, 1920, Bitmap.Config.ARGB_8888);
        //初始化图层画布
        mcanvas = new Canvas(background_color);
        //创建内容（字体）画笔

        paintFont = new Paint();
        //设置画笔的属性：宽高，颜色等等
        paintFont.setStrokeWidth(200);
        paintFont.setColor(Color.WHITE);
        paintFont.setTextSize(100);

        //设置图层上的颜色与字体
        mcanvas.drawColor(Color.GRAY);
        //字体及其位置
        mcanvas.drawText("刮刮看咯~~",background_color.getWidth()/3,background_color.getHeight()/3,paintFont);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //清空路劲
                path.reset();
                path.moveTo(event.getX(),event.getY());
                //获取滑动的xy坐标
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
                break;
        }
        mcanvas.drawPath(path,paint);
        invalidate();
        return true;
    }

    //画布
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画背景图
        canvas.drawBitmap(background_pic,0,0,null);
        //画图层
        canvas.drawBitmap(background_color,0,0,null);
    }
}

