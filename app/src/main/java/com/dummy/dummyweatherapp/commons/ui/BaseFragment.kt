package com.dummy.dummyweatherapp.commons.ui

import androidx.fragment.app.Fragment

abstract class BaseFragment<V : BaseView> : Fragment() {
    abstract fun createView(): V
}