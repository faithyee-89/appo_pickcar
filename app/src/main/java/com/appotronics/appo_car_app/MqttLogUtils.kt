package com.appotronics.appo_car_app

import android.util.Log

/**
 * @desc: 日志封装
 * @author: yewei
 * @email: 11196649@qq.com
 * @tel: 18680333215
 * @data: 2023/3/15 15:42
 */
object MqttLogUtils {
    var className: String? = null
    var methodName: String? = null
    var lineNumber = 0
    var isDebuggable = true //是否debug

    fun createLog(log: String?): String {
        val buffer = StringBuffer()
        buffer.append("AppoSDK ====== ").append(methodName)
        buffer.append("(").append(className).append(":").append(lineNumber).append(")==:")
        buffer.append(log)
        return buffer.toString()
    }

    /**
     * 获取文件名、方法名、所在行数
     *
     * @param sElements
     */
    private fun getMethodNames(sElements: Array<StackTraceElement>) {
        className = sElements[1].fileName
        methodName = sElements[1].methodName
        lineNumber = sElements[1].lineNumber
    }

    fun e(message: String?) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.e(className, createLog(message))
    }

    fun i(message: String?) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.i(className, createLog(message))
    }

    fun d(message: String?) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.d(className, createLog(message))
    }

    fun v(message: String?) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.v(className, createLog(message))
    }

    fun w(message: String?) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.w(className, createLog(message))
    }
}