package com.btpj.lib_base.widgets

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.btpj.lib_base.R
import com.btpj.lib_base.utils.StatusBarUtil

/**
 * 封装的Title标题栏
 *
 * @author LTP 16/9/19.
 */
class TitleLayout(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var mViewRed: View
    private var mTvMenu: TextView
    private var mIvMenu: ImageView
    private val mTvTitleText: TextView
    private val mIvBack: ImageView
    private val mClTitleBar: ConstraintLayout

    init {
        // 自定义TitleLayout的相关属性
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleLayout)
        val titleBackgroundColor = typedArray.getColor(
            R.styleable.TitleLayout_titleBackgroundColor,
            ContextCompat.getColor(context, R.color._6200EE)
        )
        val backIconRes =
            typedArray.getResourceId(R.styleable.TitleLayout_backIconRes, R.drawable.btn_back)
        val isShowBack = typedArray.getBoolean(R.styleable.TitleLayout_isShowBack, true)
        val titleTextColor = typedArray.getColor(
            R.styleable.TitleLayout_titleTextColor,
            ContextCompat.getColor(context, R.color._ffffff)
        )
        val titleTextSize = typedArray.getDimensionPixelSize(
            R.styleable.TitleLayout_titleTextSize,
            resources.getDimension(R.dimen.title_text_size).toInt()
        )
        val titleText = typedArray.getString(R.styleable.TitleLayout_titleText)
        typedArray.recycle()

        // 自定义TitleLayout的布局
        LayoutInflater.from(context).inflate(
            R.layout.layout_title,
            this,
            true
        ).apply {
            mClTitleBar = this.findViewById(R.id.cl_titleBar)
            mIvBack = this.findViewById(R.id.iv_back)
            mTvTitleText = this.findViewById(R.id.tv_titleText)
            mIvMenu = this.findViewById(R.id.iv_menu)
            mTvMenu = this.findViewById(R.id.tv_menu)
            mViewRed = this.findViewById(R.id.view_red)
        }


        // 设置TitleLayout的背景色
        mClTitleBar.setBackgroundColor(titleBackgroundColor)

        // TitleBar的返回键
        mIvBack.apply {
            visibility = if (isShowBack) View.VISIBLE else View.GONE
            setImageResource(backIconRes)
            setOnClickListener { (context as Activity).onBackPressed() }
        }

        // TitleBar的标题文本
        mTvTitleText.apply {
            setTextColor(titleTextColor)
            setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize.toFloat())
            text = titleText
            isSelected = true
        }
        setTitleHeightAndPadding()
    }

    /**
     * 设置状态栏高度和PaddingTop，当设置透明状态栏时PaddingTop = statusBarHeight
     */
    private fun setTitleHeightAndPadding() {
        val statusBarHeight = StatusBarUtil.getStatusBarHeight(context)
        // LogUtil.d("状态栏高度:$statusBarHeight")
        mClTitleBar.apply {
            setPadding(0, statusBarHeight, 0, 0)
            layoutParams.height =
                resources.getDimension(R.dimen.app_bar_height).toInt() + statusBarHeight
        }
    }

    /**
     * 设置返回键图标
     *
     * @param resId 返回键图标Id
     */
    fun setBackIcon(resId: Int): TitleLayout {
        mIvBack.setImageResource(resId)
        return this
    }

    /**
     * 设置Title背景色
     *
     * @param titleBackgroundColor Title背景色
     */
    fun setTitleBackgroundColor(titleBackgroundColor: Int): TitleLayout {
        mClTitleBar.setBackgroundColor(titleBackgroundColor)
        return this
    }

    /**
     * 设置Title左侧的返回键是否显示
     *
     * @param isVisible Title左侧的返回键是否显示
     */
    fun setBackVisible(isVisible: Boolean): TitleLayout {
        mIvBack.visibility = if (isVisible) View.VISIBLE else View.GONE
        return this
    }

    /**
     * 设置Title中间的标题文本名
     *
     * @param titleText Title中间的标题文本名
     */
    fun setTitleText(titleText: String): TitleLayout {
        mTvTitleText.text = titleText
        return this
    }

    /**
     * 设置Title中间的标题文本颜色
     *
     * @param titleTextColor Title中间的标题文本颜色
     */
    fun setTitleTextColor(titleTextColor: Int): TitleLayout {
        mTvTitleText.setTextColor(titleTextColor)
        return this
    }

    /**
     * 设置Title中间的标题文本大小
     *
     * @param titleTextSize Title中间的标题文本大小
     */
    fun setTitleTextSize(titleTextSize: Int): TitleLayout {
        mTvTitleText.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize.toFloat())
        return this
    }

    /**
     * 设置Title右测的TextView编辑菜单
     *
     * @param text            Title右测的TextView编辑菜单文本
     * @param onClickListener 菜单点击回调
     */
    fun setRightView(text: String, onClickListener: OnClickListener): TitleLayout {
        mIvMenu.visibility = View.GONE
        mTvMenu.apply {
            visibility = View.VISIBLE
            this.text = text
            setOnClickListener(onClickListener)
        }

        return this
    }

    /**
     * 设置Title右测的TextView编辑菜单
     *
     * @param rightViewBackground   Title右测的TextView背景色
     */
    fun setRightViewBackground(rightViewBackground: Int): TitleLayout {

        mIvMenu.visibility = View.GONE
        mTvMenu.apply {
            visibility = View.VISIBLE
            setBackgroundColor(rightViewBackground)
        }

        return this
    }

    /**
     * 设置Title右测的TextView编辑菜单
     *
     * @param text            Title右测的TextView编辑菜单文本
     * @param textColor       Title右测的TextView编辑菜单文本颜色
     * @param onClickListener 菜单点击回调
     */
    fun setRightView(text: String, textColor: Int, onClickListener: OnClickListener): TitleLayout {
        mIvMenu.visibility = View.GONE
        mTvMenu.apply {
            visibility = View.VISIBLE
            this.text = text
            setTextColor(textColor)
            setOnClickListener(onClickListener)
        }
        return this
    }

    /**
     * 设置Title右测的TextView编辑菜单
     *
     * @param text            Title右测的TextView编辑菜单文本
     */
    fun setRightView(text: String): TitleLayout {
        mIvMenu.visibility = View.GONE

        mTvMenu.apply {
            visibility = View.VISIBLE
            this.text = text
        }

        return this
    }

    /**
     * 设置Title右测的ImageView编辑菜单
     *
     * @param imageRes        Title右测的ImageView编辑菜单ImageViewResource
     * @param onClickListener 菜单点击回调
     */
    fun setRightView(imageRes: Int, onClickListener: OnClickListener): TitleLayout {
        mTvMenu.visibility = View.GONE
        mIvMenu.apply {
            visibility = View.VISIBLE
            setImageResource(imageRes)
            setOnClickListener(onClickListener)
        }

        return this
    }

    /**
     * 设置Title右测的ImageView编辑菜单
     *
     * @param imageRes        Title右测的ImageView编辑菜单ImageViewResource
     */
    fun setRightView(imageRes: Int): TitleLayout {
        mTvMenu.visibility = View.GONE
        mIvMenu.apply {
            visibility = View.VISIBLE
            setImageResource(imageRes)
        }

        return this
    }

    /**
     * 设置Title右侧的菜单上的提示红点是否显示
     *
     * @param isVisible 红点是否显示
     */
    fun setRedViewVisible(isVisible: Boolean): TitleLayout {
        mViewRed.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        return this
    }

    /**
     * 设置左键的点击事件
     */
    fun setLeftOnclick(l: OnClickListener): TitleLayout {
        mIvBack.setOnClickListener(l)
        return this
    }
}
