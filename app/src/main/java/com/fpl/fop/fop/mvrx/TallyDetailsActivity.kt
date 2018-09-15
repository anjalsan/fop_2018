package com.fpl.fop.fop.mvrx

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.airbnb.mvrx.BaseMvRxActivity
import com.fpl.fop.fop.R


class TallyDetailsActivity : BaseMvRxActivity() {
    private val navController: NavController
        get() = findNavController(R.id.nav_host)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tally_details)
        setupActionBarWithNavController(navController)
        navController.addOnNavigatedListener { _, destination ->
        }
    }

    companion object {
        @JvmStatic
        fun startingIntent(context: Context): Intent {
            return Intent(context, TallyDetailsActivity::class.java)
        }
    }
}
