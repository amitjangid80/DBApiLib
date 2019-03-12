package com.amit.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.amit.dbapilibrary.R

/**
 * Created by AMIT JANGID on 12/03/2019.
 **/
class BottomAppBarItemView @JvmOverloads constructor(context: Context,
                                                     attrs: AttributeSet? = null,
                                                     defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr)
{
    private val layoutView = LayoutInflater.from(context).inflate(R.layout.bottom_app_bar_layout, this, true)
    private val textView = layoutView.findViewById<AppCompatTextView>(R.id.textView)
    private val imageView = layoutView.findViewById<AppCompatImageView>(R.id.imageView)
    private val layoutImageView = layoutView.findViewById<FrameLayout>(R.id.layoutImageView)

    private var translateUpAnimation: TranslateAnimation? = null
    private var translateDownAnimation: TranslateAnimation?= null

    private lateinit var bottomAppBarItemConfig: BottomAppBarItemConfig

    init
    {
        setOnClickListener {
            if (bottomAppBarItemConfig.selected)
            {
                return@setOnClickListener
            }

            select()
            bottomAppBarItemConfig.selected = true
        }
    }

    fun setItemConfig(bottomAppBarItemConfig: BottomAppBarItemConfig)
    {
        this.bottomAppBarItemConfig = bottomAppBarItemConfig
        textView.text = bottomAppBarItemConfig.text
        imageView.setImageDrawable(bottomAppBarItemConfig.drawable)
        layoutImageView.visibility = if (bottomAppBarItemConfig.selected) View.VISIBLE else View.INVISIBLE
    }

    fun setTabColor(tabColor: Int)
    {
        layoutImageView.setBackgroundColor(tabColor)
    }

    fun setTextSize(textSize: Float)
    {
        textView.textSize = textSize
    }

    fun setTextColor(textColor: Int)
    {
        textView.setTextColor(textColor)
    }

    fun getItemIndex(): Int = bottomAppBarItemConfig.index

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int)
    {
        super.onSizeChanged(w, h, oldw, oldh)
        initializeAnimations()
    }

    fun select()
    {
        layoutImageView.startAnimation(translateUpAnimation)
    }

    fun deselect()
    {
        layoutImageView.startAnimation(translateDownAnimation)
    }

    private fun initializeAnimations()
    {
        translateUpAnimation = TranslateAnimation(0f, 0f, height.toFloat(), 0f).apply {
            duration = ANIMATION_DURATION
            interpolator = AccelerateDecelerateInterpolator()

            setAnimationListener(object : Animation.AnimationListener
            {
                override fun onAnimationRepeat(animation: Animation?)
                {

                }

                override fun onAnimationEnd(animation: Animation?)
                {

                }

                override fun onAnimationStart(animation: Animation?)
                {
                    layoutImageView.visibility = View.VISIBLE
                }
            })
        }

        translateDownAnimation = TranslateAnimation(0f, 0f, 0f, height.toFloat()).apply {
            duration = ANIMATION_DURATION
            interpolator = AccelerateDecelerateInterpolator()

            setAnimationListener(object : Animation.AnimationListener
            {
                override fun onAnimationRepeat(animation: Animation?)
                {

                }

                override fun onAnimationEnd(animation: Animation?)
                {
                    layoutImageView.visibility = View.INVISIBLE
                }

                override fun onAnimationStart(animation: Animation?)
                {

                }
            })
        }
    }

    companion object
    {
        private const val ANIMATION_DURATION = 300L
    }
}
