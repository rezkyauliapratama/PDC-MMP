package android.rezkyauliapratama.com.mmppdc.common

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.view.View.GONE
import android.view.animation.LinearInterpolator
import android.view.animation.DecelerateInterpolator
import android.graphics.Paint.Cap
import android.support.v4.content.ContextCompat
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.rezkyauliapratama.com.mmppdc.R
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View


/**
 * Loading indicator which shows two spinning circles
 *
 */
class DualProgressView : View {

    /**
     * Draw outer progress
     */
    private var mOuterCirclePaint: Paint? = null
    /**
     * Draw inner progress
     */
    private var mInnerCirclePaint: Paint? = null
    /**
     * Thickness of the progress
     */
    private var mThickness: Float = 0.toFloat()
    /**
     * Padding between the two circles
     */
    private var mInnerPadding: Float = 0.toFloat()
    /**
     * Animation duration
     */
    private var mAnimDuration: Int = 0
    /**
     * Rect for drawing outer circle
     */
    private var mOuterCircleRect: RectF? = null
    /**
     * Rect for drawing inner circle
     */
    private var mInnerCircleRect: RectF? = null

    /**
     * Outer Circle Color
     */
    @ColorInt
    private var mOuterCircleColor: Int = 0
    /**
     * Inner Circle Color
     */
    @ColorInt
    private var mInnerCircleColor: Int = 0
    /**
     * Number of step in the Animation
     */
    private var mSteps: Int = 0

    /**
     * Actual size of the complete circle.
     */
    private var mSize: Int = 0
    /**
     * Starting Angle to start the progress Animation.
     */
    private var mStartAngle: Float = 0.toFloat()
    /***
     * Sweep Angle
     */
    private var mIndeterminateSweep: Float = 0.toFloat()
    /**
     * Rotation offset
     */
    private var mIndeterminateRotateOffset: Float = 0.toFloat()
    /**
     * Progress Animation set
     */
    private var mIndeterminateAnimator: AnimatorSet? = null


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr)
    }


    /**
     * Initialize all drawing parameters from the custom Attributes.
     */
    protected fun init(attrs: AttributeSet?, defStyle: Int) {

        mOuterCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mInnerCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG)

        mOuterCircleRect = RectF()
        mInnerCircleRect = RectF()

        val resources = getResources()

        val a = getContext().obtainStyledAttributes(
                attrs, R.styleable.DualProgressView, defStyle, 0)
        mThickness = a.getDimensionPixelSize(R.styleable.DualProgressView_dpv_thickness,
                resources.getDimensionPixelSize(R.dimen.default_thickness)).toFloat()
        mInnerPadding = a.getDimensionPixelSize(R.styleable.DualProgressView_dpv_inner_padding,
                resources.getDimensionPixelSize(R.dimen.default_inner_padding)).toFloat()

        mOuterCircleColor = a.getColor(R.styleable.DualProgressView_dpv_inner_color,
                ContextCompat.getColor(getContext(), R.color.progress_outer_color))
        mInnerCircleColor = a.getColor(R.styleable.DualProgressView_dpv_outer_color,
                ContextCompat.getColor(getContext(), R.color.progress_inner_color))
        mAnimDuration = a.getInteger(R.styleable.DualProgressView_dpv_anim_duration,
                resources.getInteger(R.integer.default_anim_duration))
        mSteps = resources.getInteger(R.integer.default_anim_step)
        mStartAngle = resources.getInteger(R.integer.default_start_angle).toFloat()
        a.recycle()
        setPaint()
    }

    /**
     * Set the two paint object with
     * supplied color for drawing.
     */
    private fun setPaint() {

        mOuterCirclePaint!!.setColor(mOuterCircleColor)
        mOuterCirclePaint!!.setStyle(Paint.Style.STROKE)
        mOuterCirclePaint!!.setStrokeWidth(mThickness)
        mOuterCirclePaint!!.setStrokeCap(Paint.Cap.BUTT)
        mInnerCirclePaint!!.setColor(mInnerCircleColor)
        mInnerCirclePaint!!.setStyle(Paint.Style.STROKE)
        mInnerCirclePaint!!.setStrokeWidth(mThickness)
        mInnerCirclePaint!!.setStrokeCap(Paint.Cap.BUTT)
    }


    protected override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //Draw outer circle progress
        canvas.drawArc(mOuterCircleRect, mStartAngle + mIndeterminateRotateOffset,
                mIndeterminateSweep, false, mOuterCirclePaint)
        //Draw inner circle progress
        canvas.drawArc(mInnerCircleRect, mStartAngle + mIndeterminateRotateOffset + 180f,
                mIndeterminateSweep, false, mInnerCirclePaint)

    }

    protected override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val xPad = getPaddingLeft() + getPaddingRight()
        val yPad = getPaddingTop() + getPaddingBottom()
        val width = getMeasuredWidth() - xPad
        val height = getMeasuredHeight() - yPad
        mSize = if (width < height) width else height
        setMeasuredDimension(mSize + xPad, mSize + yPad)
        updateRectAngleBounds()
    }

    /**
     * Set two rectangle bounds for drawing two circles.
     */
    private fun updateRectAngleBounds() {
        val paddingLeft = getPaddingLeft()
        val paddingTop = getPaddingTop()
        mOuterCircleRect!!.set(paddingLeft + mThickness, paddingTop + mThickness,
                mSize.toFloat() - paddingLeft.toFloat() - mThickness, mSize.toFloat() - paddingTop.toFloat() - mThickness)
        mInnerCircleRect!!.set(paddingLeft.toFloat() + mThickness + mInnerPadding,
                paddingTop.toFloat() + mThickness + mInnerPadding,
                mSize.toFloat() - paddingLeft.toFloat() - mThickness - mInnerPadding,
                mSize.toFloat() - paddingTop.toFloat() - mThickness - mInnerPadding)
    }

    protected override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mSize = if (w < h) w else h
        updateRectAngleBounds()
    }


    /**
     * Create the Circle Progress Animation sequence
     */
    private fun createIndeterminateAnimator(step: Float): AnimatorSet {

        val maxSweep = 360f * (mSteps - 1) / mSteps + INDETERMINANT_MIN_SWEEP
        val start = -90f + step * (maxSweep - INDETERMINANT_MIN_SWEEP)

        // Extending the front of the arc
        val frontEndExtend = ValueAnimator.ofFloat(INDETERMINANT_MIN_SWEEP,
                maxSweep)
        frontEndExtend.setDuration((mAnimDuration / mSteps / 2).toLong())
        frontEndExtend.setInterpolator(DecelerateInterpolator(1f))
        frontEndExtend.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator) {
                mIndeterminateSweep = animation.getAnimatedValue() as Float
                invalidate()
            }
        })
        frontEndExtend.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                frontEndExtend.removeAllListeners()
                frontEndExtend.cancel()
            }
        })

        // Overall rotation
        val rotateAnimator1 = ValueAnimator.ofFloat(step * 720f / mSteps,
                (step + .5f) * 720f / mSteps)
        rotateAnimator1.setDuration((mAnimDuration / mSteps / 2).toLong())
        rotateAnimator1.setInterpolator(LinearInterpolator())
        rotateAnimator1.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator) {
                mIndeterminateRotateOffset = animation.getAnimatedValue() as Float
            }
        })

        rotateAnimator1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                rotateAnimator1.removeAllListeners()
                rotateAnimator1.cancel()
            }
        })

        // Followed by...

        // Retracting the back end of the arc
        val backEndRetract = ValueAnimator.ofFloat(start,
                start + maxSweep - INDETERMINANT_MIN_SWEEP)
        backEndRetract.setDuration((mAnimDuration / mSteps / 2).toLong())
        backEndRetract.setInterpolator(DecelerateInterpolator(1f))
        backEndRetract.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator) {
                mStartAngle = animation.getAnimatedValue() as Float
                mIndeterminateSweep = maxSweep - mStartAngle + start
                invalidate()
                if (mStartAngle > RESETTING_ANGLE) {
                    resetAnimation()
                }
            }
        })

        backEndRetract.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                backEndRetract.cancel()
                backEndRetract.removeAllListeners()
            }
        })

        // More overall rotation
        val rotateAnimator2 = ValueAnimator.ofFloat((step + .5f) * 720f / mSteps,
                (step + 1) * 720f / mSteps)
        rotateAnimator2.setDuration((mAnimDuration / mSteps / 2).toLong())
        rotateAnimator2.setInterpolator(LinearInterpolator())
        rotateAnimator2.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator) {
                mIndeterminateRotateOffset = animation.getAnimatedValue() as Float
            }
        })

        rotateAnimator2.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                rotateAnimator2.removeAllListeners()
                rotateAnimator2.cancel()
            }
        })

        val set = AnimatorSet()
        set.play(frontEndExtend).with(rotateAnimator1)
        set.play(backEndRetract).with(rotateAnimator2).after(rotateAnimator1)
        return set
    }

    fun resetAnimation() {

        mStartAngle = getResources().getInteger(R.integer.default_start_angle).toFloat()

        if (mIndeterminateAnimator != null && mIndeterminateAnimator!!.isRunning()) {
            mIndeterminateAnimator!!.cancel()
        }
        mIndeterminateSweep = INDETERMINANT_MIN_SWEEP

        // Build the whole AnimatorSet
        mIndeterminateAnimator = AnimatorSet()
        var prevSet: AnimatorSet? = null
        var nextSet: AnimatorSet
        for (k in 0 until mSteps) {
            nextSet = createIndeterminateAnimator(k.toFloat())
            val builder = mIndeterminateAnimator!!.play(nextSet)
            if (prevSet != null) {
                builder.after(prevSet)
            }
            prevSet = nextSet

        }
        mIndeterminateAnimator!!.start()
    }

    /**
     * Starts the progress bar animation.
     * (This is an alias of resetAnimation() so it does the same thing.)
     */
    fun startAnimation() {
        resetAnimation()
    }

    /**
     * Stops the animation
     */

    fun stopAnimation() {
        if (mIndeterminateAnimator != null) {
            mIndeterminateAnimator!!.cancel()
            mIndeterminateAnimator!!.removeAllListeners()
            mIndeterminateAnimator = null
        }
    }

    protected override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAnimation()
    }

    protected override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopAnimation()
    }

    override fun setVisibility(visibility: Int) {
        val currentVisibility = getVisibility()
        super.setVisibility(visibility)
        if (visibility != currentVisibility) {
            if (visibility == View.VISIBLE) {
                resetAnimation()
            } else if (visibility == View.GONE || visibility == View.INVISIBLE) {
                stopAnimation()
            }
        }
    }

    companion object {


        private val INDETERMINANT_MIN_SWEEP = 15f
        val RESETTING_ANGLE = 620
    }


}