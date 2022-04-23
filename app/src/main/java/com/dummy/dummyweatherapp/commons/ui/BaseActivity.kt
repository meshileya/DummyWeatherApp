package com.dummy.dummyweatherapp.commons.ui

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<V : BaseView> : AppCompatActivity() {

    abstract fun createView(): V
}