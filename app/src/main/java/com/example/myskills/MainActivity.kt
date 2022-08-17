package com.example.myskills

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myskills.databinding.ActivityMainBinding
import com.google.android.material.badge.BadgeDrawable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewmodel : MainViewModel by viewModel()
    private val scope = CoroutineScope(Dispatchers.IO)
    var totalcount = -1


    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!viewmodel.isfirstTime()){
            scope.launch {
                viewmodel.createMenu()
                viewmodel.createOrder()
                delay(500)
                viewmodel.showmenu1()
            }

        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_menu, R.id.navigation_order, R.id.navigation_cart
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        viewmodel.order.observe(this) {
            totalcount = it.totalcount
        }

        viewmodel.getOrder()




        viewmodel.badge.observe(this) {


            val menuItemId: Int = navView.menu.getItem(2).itemId
            val badge: BadgeDrawable = navView.getOrCreateBadge(menuItemId)

            if (it == 0 ) {
                badge.clearNumber()
                badge.backgroundColor = getColor(R.color.white)

            } else {

                badge.backgroundColor = getColor(R.color.purple_700)
                badge.number = it
                totalcount = it

            }


        }
        viewmodel.getbedgenumber()

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        scope.launch {
            viewmodel.getOrder()
            delay(300)
            viewmodel.resetbadge(totalcount)

        }


    }


}