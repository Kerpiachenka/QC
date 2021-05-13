package by.gstu.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import by.gstu.app.bean.Abonent
import by.gstu.app.bean.Platform
import by.gstu.app.databinding.ActivityManageAbonentBinding
import by.gstu.app.databinding.ActivityManagePlatformBinding
import by.gstu.app.listener.BaseQueryResultListener
import by.gstu.app.repository.AbonentPlatformCrossRefRepositoryImpl
import by.gstu.app.repository.AbonentRepositoryImpl
import by.gstu.app.repository.PlatformRepositoryImpl
import by.gstu.app.util.toast
import by.gstu.app.viewmodel.ManageAbonentViewModel
import by.gstu.app.viewmodel.ManagePlatformViewModel

class ManagePlatformActivity : AppCompatActivity(), BaseQueryResultListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityManagePlatformBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_manage_platform)

        val viewModel: ManagePlatformViewModel = ViewModelProvider(this)
            .get(ManagePlatformViewModel::class.java)

        val platform = intent.getSerializableExtra(Platform::class.java.simpleName) as Platform
        viewModel.platform = platform
        viewModel.data = platform.data
        viewModel.name = platform.platformName
        viewModel.instruction = platform.instruction

        val repository = PlatformRepositoryImpl(applicationContext)
        val crossRefRepository = AbonentPlatformCrossRefRepositoryImpl(applicationContext)

        repository.listener = viewModel
        crossRefRepository.listener = viewModel
        viewModel.listener = this

        viewModel.repository = repository
        viewModel.crossRefRepository = crossRefRepository
        binding.viewmodel = viewModel
    }

    override fun onSuccess() {
        val intent = Intent(this, ActiveGroupActivity::class.java)
        startActivity(intent)
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}