package com.arabiait.werdy.utils

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

operator fun <T> MutableLiveData<MutableList<T>>.plusAssign(values: MutableList<T>) {
    val value = this.value ?: mutableListOf()
    value.addAll(values)
    this.value = value
}

fun Context.showToast(msg:Any) {
    Toast.makeText(this,msg.toString(),Toast.LENGTH_SHORT).show()
}

fun printLog(tag:String,msg:Any) {
    Log.e(tag,msg.toString())
}

fun Context.getScreenWidth(): Int {
    return this.resources.displayMetrics.widthPixels
}

fun Context.getScreenHeight(): Int {
    return this.resources.displayMetrics.heightPixels
}

fun  dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().getDisplayMetrics().density).toInt()
}
