package com.amit.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.amit.dbapilibrary.R;
import com.amit.drawables.CircularAnimDrawable;
import com.amit.drawables.CircularRevealAnimDrawable;
import com.amit.interfaces.AnimBtn;
import com.amit.interfaces.CustomizeByCode;
import com.amit.interfaces.OnAnimEndListener;
import com.amit.utilities.Utils;

public class ProgressBtn extends AppCompatButton implements AnimBtn, CustomizeByCode
{
    private enum State
    {
        PROGRESS, IDLE, DONE, STOPPED
    }

    private GradientDrawable mGradientDrawable;
    private boolean mIsMorphingInProgress;

    private State mState;
    private CircularAnimDrawable mAnimatedDrawable;
    private CircularRevealAnimDrawable mRevealDrawable;
    private AnimatorSet mAnimatorSet;

    private int mFillColorDone;
    private int progress;

    private Bitmap mBitmapDone;
    private Params mParams;

    private boolean doneWhileMorphing;
    private boolean shouldStartAnimation;
    private boolean layoutDone;

    /**
     * @param context
     */
    public ProgressBtn(Context context)
    {
        super(context);
        init(context, null, 0, 0);
    }

    /**
     * @param context
     * @param attrs
     */
    public ProgressBtn(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public ProgressBtn(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    @TargetApi(23)
    public ProgressBtn(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Commom initializer method.
     *
     * @param context Context
     * @param attrs   Atributes passed in the XML
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        mParams = new Params();
        mParams.mPaddingProgress = 0f;
        BackgroundAndMorphingDrawables drawables;

        if (attrs == null)
        {
            drawables = loadGradientDrawable(Utils.getDrawable(getContext(), R.drawable.btn_default_shape));
        }
        else
        {
            int[] attrsArray = new int[]{
                    android.R.attr.background, // 0
            };

            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ProgressBtn, defStyleAttr, defStyleRes);
            TypedArray taBg = context.obtainStyledAttributes(attrs, attrsArray, defStyleAttr, defStyleRes);

            drawables = loadGradientDrawable(taBg.getDrawable(0));
            mParams.mInitialCornerRadius = ta.getDimension(R.styleable.ProgressBtn_initialCornerRadius, 0);
            mParams.mFinalCornerRadius = ta.getDimension(R.styleable.ProgressBtn_finalCornerRadius, 100);
            mParams.mSpinningBarWidth = ta.getDimension(R.styleable.ProgressBtn_progressBarWidth, 10);

            mParams.mSpinningBarColor = ta.getColor(R.styleable.ProgressBtn_progressBarColor,
                    Utils.getColorWrapper(context, android.R.color.black));

            mParams.mPaddingProgress = ta.getDimension(R.styleable.ProgressBtn_paddingProgress, 0);
            ta.recycle();
            taBg.recycle();
        }

        mState = State.IDLE;
        mParams.mText = this.getText().toString();
        mParams.mDrawables = this.getCompoundDrawablesRelative();

        if (drawables != null)
        {
            mGradientDrawable = drawables.morphingDrawable;

            if (drawables.backGroundDrawable != null)
            {
                setBackground(drawables.backGroundDrawable);
            }
        }

        resetProgress();
    }

    @Override
    public void setBackgroundColor(int color)
    {
        mGradientDrawable.setColor(color);
    }

    @Override
    public void setBackgroundResource(@ColorRes int resId)
    {
        mGradientDrawable.setColor(ContextCompat.getColor(getContext(), resId));
    }

    /**
     * finds or creates the drawable for the morphing animation and the drawable to set the background to
     *
     * @param drawable Drawable set with android:background setting
     * @return BackgroundAndMorphingDrawables object holding the Drawable to morph and to set a background
     */
    @Nullable
    static BackgroundAndMorphingDrawables loadGradientDrawable(Drawable drawable)
    {
        BackgroundAndMorphingDrawables mGradientDrawable = new BackgroundAndMorphingDrawables();

        if (drawable == null)
        {
            return null;
        }
        else
        {
            if (drawable instanceof GradientDrawable)
            {
                mGradientDrawable.setBothDrawables((GradientDrawable) drawable);
            }
            else if (drawable instanceof ColorDrawable)
            {
                ColorDrawable colorDrawable = (ColorDrawable) drawable;
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setColor(colorDrawable.getColor());
                mGradientDrawable.setBothDrawables(gradientDrawable);
            }
            else if (drawable instanceof InsetDrawable && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            {
                InsetDrawable insetDrawable = (InsetDrawable) drawable;
                mGradientDrawable = loadGradientDrawable(insetDrawable.getDrawable());

                // use the original inset as background to keep margins,
                // and use the inner drawable for morphing
                mGradientDrawable.backGroundDrawable = insetDrawable;
            }
            else if (drawable instanceof StateListDrawable)
            {
                StateListDrawable stateListDrawable = (StateListDrawable) drawable;

                //try to get the drawable for an active, enabled, unpressed button
                stateListDrawable.setState(new int[]{
                        android.R.attr.state_enabled,
                        android.R.attr.state_active,
                        -android.R.attr.state_pressed});

                Drawable current = stateListDrawable.getCurrent();
                mGradientDrawable = loadGradientDrawable(current);
            }

            if (mGradientDrawable.morphingDrawable == null)
            {
                throw new RuntimeException("Error reading background... Use a shape or a color in xml!");
            }
        }

        return mGradientDrawable;
    }

    @Override
    public void setSpinningBarColor(int color)
    {
        mParams.mSpinningBarColor = color;

        if (mAnimatedDrawable != null)
        {
            mAnimatedDrawable.setLoadingBarColor(color);
        }
    }

    @Override
    public void setSpinningBarWidth(float width)
    {
        mParams.mSpinningBarWidth = width;
    }

    @Override
    public void setDoneColor(int color)
    {
        mParams.mDoneColor = color;
    }

    @Override
    public void setPaddingProgress(float padding)
    {
        mParams.mPaddingProgress = padding;
    }

    @Override
    public void setInitialHeight(int height)
    {
        mParams.mInitialHeight = height;
    }

    @Override
    public void setInitialCornerRadius(float radius)
    {
        mParams.mInitialCornerRadius = radius;
    }

    @Override
    public void setFinalCornerRadius(float radius)
    {
        mParams.mFinalCornerRadius = radius;
    }

    /**
     * This method is called when the button and its dependencies are going to draw it selves.
     *
     * @param canvas Canvas
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        layoutDone = true;

        if (shouldStartAnimation)
        {
            startAnimation();
        }

        if (mState == State.PROGRESS && !mIsMorphingInProgress)
        {
            drawProgress(canvas);
        }
        else if (mState == State.DONE)
        {
            drawDoneAnimation(canvas);
        }
    }

    /**
     * If the mAnimatedDrawable is null or its not running, it get created. Otherwise its draw method is
     * called here.
     *
     * @param canvas Canvas
     */
    private void drawProgress(Canvas canvas)
    {
        if (mAnimatedDrawable == null || !mAnimatedDrawable.isRunning())
        {
            mAnimatedDrawable = new CircularAnimDrawable(this,
                    mParams.mSpinningBarWidth,
                    mParams.mSpinningBarColor);

            int offset = (getWidth() - getHeight()) / 2;
            int left = offset + mParams.mPaddingProgress.intValue();
            int right = getWidth() - offset - mParams.mPaddingProgress.intValue();
            int bottom = getHeight() - mParams.mPaddingProgress.intValue();
            int top = mParams.mPaddingProgress.intValue();

            mAnimatedDrawable.setBounds(left, top, right, bottom);
            mAnimatedDrawable.setCallback(this);
            mAnimatedDrawable.start();
        }
        else
        {
            mAnimatedDrawable.setProgress(progress);
            mAnimatedDrawable.draw(canvas);
        }
    }


    /**
     * @param progress set a progress to switch displaying a determinate circular progress
     */
    public void setProgress(int progress)
    {
        progress = Math.max(CircularAnimDrawable.MIN_PROGRESS,
                Math.min(CircularAnimDrawable.MAX_PROGRESS, progress));

        this.progress = progress;
    }

    /**
     * resets a given progress and shows an indeterminate progress animation
     */
    public void resetProgress()
    {
        this.progress = CircularAnimDrawable.MIN_PROGRESS - 1;
    }

    /**
     * Stops the animation and sets the button in the STOPPED state.
     */
    public void stopAnimation()
    {
        if (mState == State.PROGRESS && !mIsMorphingInProgress)
        {
            mState = State.STOPPED;
            mAnimatedDrawable.stop();
        }
    }

    /**
     * Call this method when you want to show a 'completed' or a 'done' status.
     * You have to choose the * color and the image to be shown.
     * If your loading progress ended with a success status,
     * you probably want to put a icon for "success" and a blue color,
     * otherwise red and a failure icon.
     * You can also show that a music is completed... or show some status on a game... be creative!
     *
     * @param fillColor - color to fill after animation is completed.
     * @param bitmap - The image that will be shown in the button.
     */
    public void doneLoadingAnimation(int fillColor, Bitmap bitmap)
    {
        if (mState != State.PROGRESS)
        {
            return;
        }

        if (mIsMorphingInProgress)
        {
            doneWhileMorphing = true;
            mFillColorDone = fillColor;
            mBitmapDone = bitmap;
            return;
        }

        mState = State.DONE;
        mAnimatedDrawable.stop();
        mRevealDrawable = new CircularRevealAnimDrawable(this, fillColor, bitmap);

        int left = 0;
        int right = getWidth();
        int bottom = getHeight();
        int top = 0;

        this.setBackgroundColor(fillColor); // this code changes the background color of the button.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            this.setElevation(5);
        }

        mRevealDrawable.setBounds(left, top, right, bottom);
        mRevealDrawable.setCallback(this);
        mRevealDrawable.start();
    }

    /**
     * Method called on the onDraw when the button is on DONE status
     *
     * @param canvas Canvas
     */
    private void drawDoneAnimation(Canvas canvas)
    {
        mRevealDrawable.draw(canvas);
    }

    public void revertAnimation()
    {
        revertAnimation(null);
    }

    public void revertAnimation(final OnAnimEndListener onAnimationEndListener)
    {
        if (mState == State.IDLE)
        {
            return;
        }

        mState = State.IDLE;
        resetProgress();

        if (mAnimatedDrawable != null && mAnimatedDrawable.isRunning())
        {
            stopAnimation();
        }

        if (mIsMorphingInProgress)
        {
            mAnimatorSet.cancel();
        }

        setClickable(false);
        int fromWidth = getWidth();
        int fromHeight = getHeight();

        int toHeight = mParams.mInitialHeight;
        int toWidth = mParams.mInitialWidth;
        ObjectAnimator cornerAnimation = null;

        if (mGradientDrawable != null)
        {
            cornerAnimation = ObjectAnimator.ofFloat(
                    mGradientDrawable,
                    "cornerRadius",
                    mParams.mFinalCornerRadius,
                    mParams.mInitialCornerRadius);
        }

        ValueAnimator widthAnimation = ValueAnimator.ofInt(fromWidth, toWidth);

        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.width = val;
                setLayoutParams(layoutParams);
            }
        });

        ValueAnimator heightAnimation = ValueAnimator.ofInt(fromHeight, toHeight);

        heightAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = val;
                setLayoutParams(layoutParams);
            }
        });

        /*ValueAnimator strokeAnimation = ValueAnimator.ofFloat(
                getResources().getDimension(R.dimen.stroke_login_button),
                getResources().getDimension(R.dimen.stroke_login_button_loading));

        strokeAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                ((ShapeDrawable)mGradientDrawable).getPaint().setStrokeWidth((Float)animation.getAnimatedValue());
            }
        });*/

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(300);

        if (mGradientDrawable != null)
        {
            mAnimatorSet.playTogether(cornerAnimation, widthAnimation, heightAnimation);
        }
        else
        {
            mAnimatorSet.playTogether(widthAnimation, heightAnimation);
        }

        mAnimatorSet.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                setClickable(true);
                mIsMorphingInProgress = false;
                setText(mParams.mText);

                setCompoundDrawablesRelative(
                        mParams.mDrawables[0],
                        mParams.mDrawables[1],
                        mParams.mDrawables[2],
                        mParams.mDrawables[3]);

                if (onAnimationEndListener != null)
                {
                    onAnimationEndListener.onAnimationEnd();
                }
            }
        });

        mIsMorphingInProgress = true;
        mAnimatorSet.start();
    }

    @Override
    public void dispose()
    {
        if (mAnimatedDrawable != null)
        {
            mAnimatedDrawable.dispose();
        }

        if (mRevealDrawable != null)
        {
            mRevealDrawable.dispose();
        }
    }

    /**
     * Method called to start the animation. Morphs in to a ball and then starts a loading spinner.
     */
    public void startAnimation()
    {
        if (mState != State.IDLE)
        {
            return;
        }

        if (!layoutDone)
        {
            shouldStartAnimation = true;
            return;
        }

        shouldStartAnimation = false;

        if (mIsMorphingInProgress)
        {
            mAnimatorSet.cancel();
        }
        else
        {
            mParams.mInitialWidth = getWidth();
            mParams.mInitialHeight = getHeight();
        }

        mState = State.PROGRESS;
        mParams.mText = getText().toString();

        this.setCompoundDrawables(null, null, null, null);
        this.setText(null);
        setClickable(false);

        int toHeight = mParams.mInitialHeight;
        int toWidth = toHeight; //Making a perfect circle

        ObjectAnimator cornerAnimation = ObjectAnimator.ofFloat(
                mGradientDrawable,
               "cornerRadius",
                mParams.mInitialCornerRadius,
                mParams.mFinalCornerRadius);

        ValueAnimator widthAnimation = ValueAnimator.ofInt(mParams.mInitialWidth, toWidth);

        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.width = val;
                setLayoutParams(layoutParams);
            }
        });

        ValueAnimator heightAnimation = ValueAnimator.ofInt(mParams.mInitialHeight, toHeight);

        heightAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = val;
                setLayoutParams(layoutParams);
            }
        });

        /*ValueAnimator strokeAnimation = ValueAnimator.ofFloat(
                getResources().getDimension(R.dimen.stroke_login_button),
                getResources().getDimension(R.dimen.stroke_login_button_loading));

        strokeAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                ((ShapeDrawable)mGradientDrawable).getPaint().setStrokeWidth((Float)animation.getAnimatedValue());
            }
        });*/

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(300);
        mAnimatorSet.playTogether(cornerAnimation, widthAnimation, heightAnimation);

        mAnimatorSet.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                mIsMorphingInProgress = false;

                if (doneWhileMorphing)
                {
                    doneWhileMorphing = false;

                    Runnable runnable = new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            doneLoadingAnimation(mFillColorDone, mBitmapDone);
                            // doneLoadingAnimation(mBitmapDone);
                        }
                    };

                    new Handler().postDelayed(runnable, 50);
                }
            }
        });

        mIsMorphingInProgress = true;
        mAnimatorSet.start();
    }


    /**
     * Check if button is animating
     */
    public Boolean isAnimating()
    {
        return mState == State.PROGRESS;
    }

    /**
     * Class with all the params to configure the button.
     */
    private class Params
    {
        private float mSpinningBarWidth;
        private int mSpinningBarColor;
        private int mDoneColor;
        private Float mPaddingProgress;
        private Integer mInitialHeight;
        private int mInitialWidth;
        private String mText;
        private float mInitialCornerRadius;
        private float mFinalCornerRadius;
        private Drawable[] mDrawables;
    }

    static class BackgroundAndMorphingDrawables
    {
        Drawable backGroundDrawable;
        GradientDrawable morphingDrawable;

        void setBothDrawables(GradientDrawable drawable)
        {
            this.backGroundDrawable = drawable;
            this.morphingDrawable = drawable;
        }
    }
}
