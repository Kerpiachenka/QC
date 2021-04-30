package by.gstu.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import by.gstu.app.databinding.ActivityMainBinding
import by.gstu.app.databinding.ActivityManageAbonentBinding
import by.gstu.app.listener.MainActivityListener
import by.gstu.app.viewmodel.MainActivityViewModel
import by.gstu.app.viewmodel.ManageAbonentViewModel

class MainActivity : AppCompatActivity(), MainActivityListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_main)

        val viewModel: MainActivityViewModel = ViewModelProvider(this)
                .get(MainActivityViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.mainActivityListener = this
    }

    override fun onOpenManageAbonentActivity() {
        val intent = Intent(this, ManageAbonentActivity::class.java)
        startActivity(intent)
    }
}