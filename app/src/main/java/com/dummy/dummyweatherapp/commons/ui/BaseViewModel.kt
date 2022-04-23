package com.dummy.dummyweatherapp.commons.ui

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<V : BaseView> : ViewModel() {

    /**
     * Customs viewmodel inheriting this class will have direct access to the view passed in
     * as a type. This view is expected to be initialised in either a fragment or activity.
     */
    open lateinit var view: V
}