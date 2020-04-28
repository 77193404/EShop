package cn.eatan.base_libs.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MotionEvent
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import cn.eatan.base_libs.utils.CommonUtil
import cn.eatan.base_libs.utils.KeyBoardUtil

/**
 * Created by Eatan on 2020/4/28 22:52
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * 返回布局文件
     */
    @LayoutRes
    protected abstract fun attachLayoutRes():Int

    /**
     * 初始化数据
     */
    open fun initData(){}

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 返回是否使用 EventBus
     * 在需要使用EventBus的Activity中重写该方法，并返回true
     */
    open fun useEventBus(): Boolean = false

    /**
     * 设置状态栏的颜色[color]
     */
    fun setStatusBarColor(@ColorInt color : Int){

    }

    /**
     * 设置状态栏字体的颜色，当[dark]为  true：黑色    false：白色
     */
    fun setStatusBarIcon(dark: Boolean){
        if (dark){

        }else{

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //强制竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //设置布局
        setContentView(attachLayoutRes())
        //注册EventBus
        if (useEventBus()){

        }
        initView()
        initData()
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_UP) {
            val v = currentFocus
            // 如果不是落在EditText区域，则需要关闭输入法
            if (KeyBoardUtil.isHideKeyboard(v, ev)) {
                KeyBoardUtil.hideKeyBoard(this, v)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()){
            //注销EventBus
        }
        CommonUtil.fixInputMethodManagerLeak(this)
    }
}