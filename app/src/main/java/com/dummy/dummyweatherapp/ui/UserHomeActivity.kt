package com.dummy.dummyweatherapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.dummy.dummyweatherapp.R
import com.dummy.dummyweatherapp.commons.ui.BaseActivity
import com.dummy.dummyweatherapp.commons.ui.setupWithNavController
import com.dummy.dummyweatherapp.di.DashboardProivderComponent
import com.dummy.dummyweatherapp.view.HomeView
import com.dummy.dummyweatherapp.viewmodel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class UserHomeActivity : BaseActivity<HomeView>(), HomeView {

    companion object {
        val TAG: String = UserHomeActivity::class.java.simpleName
        const val REQUEST_UPDATE_CODE = 1
    }

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun createView(): HomeView = this

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as DashboardProivderComponent).provideMoreComponent()
            .inject(this)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.hide()
        setupBottomNavigationBar()
    }

    override fun onResume() {

        super.onResume()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, intent)

        if (requestCode == REQUEST_UPDATE_CODE) {
            if (resultCode != RESULT_OK) {
                // If the update is cancelled or fails, you can request to start the update again.
                Log.e(TAG, "Update flow failed! Result code: $resultCode")
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        Log.e("OK TESTING", "CALLING 2")
//        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
//
        val navGraphIds = listOf(
            R.navigation.navigation_today,
            R.navigation.navigation_weekly,
            R.navigation.navigation_share
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }


    override fun showError(error: String?) {
//        showMessage(error)
    }
}