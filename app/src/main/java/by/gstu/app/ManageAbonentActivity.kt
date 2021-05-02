package by.gstu.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import by.gstu.app.databinding.ActivityManageAbonentBinding
import by.gstu.app.listener.ManageAbonentListener
import by.gstu.app.repository.AbonentRepositoryImpl
import by.gstu.app.util.toast
import by.gstu.app.viewmodel.ManageAbonentViewModel

class ManageAbonentActivity : AppCompatActivity(), ManageAbonentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityManageAbonentBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_manage_abonent)

        val viewModel: ManageAbonentViewModel = ViewModelProvider(this)
            .get(ManageAbonentViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.manageAbonentListener = this
        viewModel.repository = AbonentRepositoryImpl(this.applicationContext)
    }

    override fun onStarted() {
        toast("On started")
    }

    override fun onSuccess() {
        toast("On success")
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}