package com.amit.drawables;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Animatable;
import android.util.Property;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.amit.interfaces.OnAnimEndListener;
import com.amit.interfaces.OnAnimUpdateListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CircleAnimDrawable extends Drawable implements Animatable
{
    private static final Interpolator SWEEP_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    private static final int SWEEP_ANIMATOR_DURATION = 800;
    private static final float ANGLE_MULTIPLIER = 3.6f;
    public static final int DURATION_INSTANT = 1;
    private final RectF fBounds = new RectF();

    private ObjectAnimator mObjectAnimatorSweep;
    private Paint mPaint, mCancelPaint;
    private float mCurrentSweepAngle;
    private float mBorderWidth;
    private boolean mRunning;
    private boolean mIndeterminateProgressMode;
    private OnAnimEndListener mListener;
    private int mCustomSweepDuration;
    private float cancelButtonSpokeLength;
    private float maxAngle = 360f;
    private float minAngle = 0f;
    private boolean customProgressMode = false;
    private float customProgress = -1;
    private OnAnimUpdateListener onAnimUpdateListener;

    public CircleAnimDrawable(int color, int cancelColor, float borderWidth)
    {
        mBorderWidth = borderWidth;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(borderWidth);
        mPaint.setColor(color);

        mCancelPaint = new Paint();
        mCancelPaint.setAntiAlias(true);
        mCancelPaint.setStyle(Paint.Style.STROKE);
        mCancelPaint.setStrokeWidth(borderWidth);
        mCancelPaint.setColor(cancelColor);
    }

    @Override
    public void draw(@NonNull Canvas canvas)
    {
        canvas.drawArc(fBounds, 270, mCurrentSweepAngle, false, mPaint);
        cancelButtonSpokeLength = (int) fBounds.width();

        canvas.drawLine(
                fBounds.left + cancelButtonSpokeLength,
                fBounds.bottom - cancelButtonSpokeLength,
                fBounds.right - cancelButtonSpokeLength,
                fBounds.top + cancelButtonSpokeLength,
                mCancelPaint);

        canvas.drawLine(
                fBounds.left + cancelButtonSpokeLength,
                fBounds.top + cancelButtonSpokeLength,
                fBounds.right - cancelButtonSpokeLength,
                fBounds.bottom - cancelButtonSpokeLength,
                mCancelPaint);

    }

    @Override
    public void setAlpha(int alpha)
    {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter)
    {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity()
    {
        return PixelFormat.TRANSPARENT;
    }

    @Override
    protected void onBoundsChange(Rect bounds)
    {
        super.onBoundsChange(bounds);

        fBounds.left = bounds.left + mBorderWidth / 2f + .5f;
        fBounds.right = bounds.right - mBorderWidth / 2f - .5f;
        fBounds.top = bounds.top + mBorderWidth / 2f + .5f;
        fBounds.bottom = bounds.bottom - mBorderWidth / 2f - .5f;
    }

    private Property<CircleAnimDrawable, Float> mSweepProperty =
            new Property<CircleAnimDrawable, Float>(Float.class, "outerCircleRadiusProgress")
            {
                @Override
                public Float get(CircleAnimDrawable object)
                {
                    return object.getCurrentSweepAngle();
                }

                @Override
                public void set(CircleAnimDrawable object, Float value)
                {
                    if (onAnimUpdateListener != null)
                    {
                        onAnimUpdateListener.onAnimValueUpdate(value);
                    }

                    object.setCurrentSweepAngle(value);
                }
            };

    public void initAnimations()
    {
        setupAnimations(customProgressMode ? minAngle : maxAngle);
    }

    private void setupAnimations(float angleToDraw)
    {
        mObjectAnimatorSweep = ObjectAnimator.ofFloat(this, mSweepProperty, angleToDraw);
        mObjectAnimatorSweep.setInterpolator(SWEEP_INTERPOLATOR);

        if (mIndeterminateProgressMode)
        {
            mObjectAnimatorSweep.setDuration(SWEEP_ANIMATOR_DURATION);
            mObjectAnimatorSweep.setRepeatMode(ValueAnimator.RESTART);
            mObjectAnimatorSweep.setRepeatCount(ValueAnimator.INFINITE);
        }
        else
        {
            mObjectAnimatorSweep.setDuration(mCustomSweepDuration == -1 ? SWEEP_ANIMATOR_DURATION : mCustomSweepDuration);
        }

        mObjectAnimatorSweep.addListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animation)
            {

            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                if (customProgressMode)
                {
                    if (customProgress != 360)
                    {
                        return;
                    }
                }

                if (mListener != null)
                {
                    mListener.onAnimationEnd();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation)
            {

            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {

            }
        });

        mObjectAnimatorSweep.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                if (customProgressMode)
                {
                    return;
                }
                if (onAnimUpdateListener != null)
                {
                    onAnimUpdateListener.onAnimTimeUpdate((int) animation.getCurrentPlayTime(), mCustomSweepDuration);
                }
            }
        });
    }

    @Override
    public void start()
    {
        if (isRunning())
        {
            return;
        }

        mRunning = true;
        mObjectAnimatorSweep.start();
        invalidateSelf();
    }

    @Override
    public void stop()
    {
        if (!isRunning())
        {
            return;
        }

        mRunning = false;
        mObjectAnimatorSweep.cancel();
        invalidateSelf();
    }

    @Override
    public boolean isRunning()
    {
        return mRunning;
    }

    public float getCurrentSweepAngle()
    {
        return mCurrentSweepAngle;
    }

    public void setCurrentSweepAngle(float currentSweepAngle)
    {
        mCurrentSweepAngle = currentSweepAngle;
        invalidateSelf();
    }

    public void setCurrentSweepAngleAndTimeRemaining(float currentSweepAngle, int timeRemaining)
    {
        mCurrentSweepAngle = currentSweepAngle;
        mCustomSweepDuration = timeRemaining;

        stop();
        initAnimations();
        start();
    }

    public void setListener(OnAnimEndListener listener)
    {
        mListener = listener;
    }

    public void drawProgress(int angle)
    {
        stop();
        customProgress = angle * ANGLE_MULTIPLIER > maxAngle ? angle * ANGLE_MULTIPLIER - maxAngle : angle * ANGLE_MULTIPLIER;
        setupAnimations(customProgress);
        start();
    }

    public void drawProgress(float angle)
    {
        stop();
        customProgress = angle;
        mCustomSweepDuration = DURATION_INSTANT;
        setupAnimations(customProgress);
        start();
        mCustomSweepDuration = -1;
    }

    public void setCustomProgressMode(boolean customProgressMode)
    {
        this.customProgressMode = customProgressMode;
    }

    public void setmCustomSweepDuration(int mCustomSweepDuration)
    {
        this.mCustomSweepDuration = mCustomSweepDuration;
    }

    public void setOnAnimationUpdateListener(OnAnimUpdateListener onAnimationUpdateListener)
    {
        this.onAnimUpdateListener = onAnimationUpdateListener;
    }

    public void setmIndeterminateProgressMode(boolean mIndeterminateProgressMode)
    {
        this.mIndeterminateProgressMode = mIndeterminateProgressMode;
    }
}
