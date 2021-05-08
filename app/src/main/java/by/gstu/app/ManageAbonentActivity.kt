package by.gstu.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import by.gstu.app.bean.Abonent
import by.gstu.app.databinding.ActivityManageAbonentBinding
import by.gstu.app.listener.BaseQueryResultListener
import by.gstu.app.listener.ManageAbonentListener
import by.gstu.app.repository.AbonentRepositoryImpl
import by.gstu.app.util.toast
import by.gstu.app.viewmodel.ManageAbonentViewModel

class ManageAbonentActivity : AppCompatActivity(), BaseQueryResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityManageAbonentBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_manage_abonent)

        val viewModel: ManageAbonentViewModel = ViewModelProvider(this)
            .get(ManageAbonentViewModel::class.java)

        val entity = intent.getSerializableExtra(Abonent::class.java.simpleName)
        entity?.let {
            val abonent = it as Abonent
            viewModel.abonent = abonent
            viewModel.name = abonent.name
            viewModel.age = abonent.age.toString()
        }

        val repository = AbonentRepositoryImpl(this.applicationContext)
        repository.listener = viewModel
		viewModel.listener = this
        binding.viewmodel = viewModel
        viewModel.repository = repository
    }

    override fun onSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}