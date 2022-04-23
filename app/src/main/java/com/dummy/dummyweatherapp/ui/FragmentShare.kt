package com.dummy.dummyweatherapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dummy.dummyweatherapp.commons.ui.BaseFragment
import com.dummy.dummyweatherapp.commons.ui.showMessage
import com.dummy.dummyweatherapp.databinding.FragmentShareBinding
import com.dummy.dummyweatherapp.di.DashboardProivderComponent
import com.dummy.dummyweatherapp.view.HomeView
import com.dummy.dummyweatherapp.viewmodel.HomeViewModel
import javax.inject.Inject

class FragmentShare : BaseFragment<HomeView>(),
    HomeView {

    @Inject
    lateinit var viewModel: HomeViewModel

    lateinit var binding: FragmentShareBinding
    override fun createView(): HomeView = this

    companion object {
//        fun newInstance() = DashboardFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.applicationContext as DashboardProivderComponent).provideMoreComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShareBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.view = createView()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun showError(error: String?) {
        activity?.showMessage(error)
    }
}