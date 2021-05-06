package by.gstu.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.gstu.app.adapter.AbonentRecyclerViewAdapter
import by.gstu.app.adapter.PlatformRecyclerViewAdapter
import by.gstu.app.bean.Platform
import by.gstu.app.databinding.ActivityMainBinding
import by.gstu.app.databinding.ActivityManagePlatformBinding
import by.gstu.app.listener.CardClickListener
import by.gstu.app.repository.PlatformRepositoryImpl
import by.gstu.app.viewmodel.MainActivityViewModel
import by.gstu.app.viewmodel.ManagePlatformViewModel

class ManagePlatformActivity : AppCompatActivity(), CardClickListener<Platform> {
    lateinit var viewModel: ManagePlatformViewModel
    private lateinit var adapter: PlatformRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityManagePlatformBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_manage_platform)

        // Bind RecyclerView
        val recyclerView: RecyclerView = binding.platformList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        viewModel = ViewModelProvider(this)
            .get(ManagePlatformViewModel::class.java)
        viewModel.platformRepository = PlatformRepositoryImpl(applicationContext)
        binding.viewmodel = viewModel

        adapter = PlatformRecyclerViewAdapter(this)
        binding.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateList()
        viewModel.getAllPlatform().observe(this, {
            adapter.setData(it)
        })
    }

    override fun onCardClick(obj: Platform) {
        TODO("Not yet implemented")
    }
}