package pl.mobisoftware.firebaseat.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import pl.mobisoftware.firebaseat.R
import pl.mobisoftware.firebaseat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this, ViewModelFactory()).get(MainViewModel::class.java) }

    val binding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.viewModel = viewModel
    }
}
