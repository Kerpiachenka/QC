package by.gstu.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.gstu.app.adapter.PlatformRecyclerViewAdapter
import by.gstu.app.bean.Abonent
import by.gstu.app.bean.Platform
import by.gstu.app.databinding.ActivityActiveGroupBinding
import by.gstu.app.listener.CardClickListener
import by.gstu.app.repository.PlatformRepositoryImpl
import by.gstu.app.viewmodel.ActiveGroupViewModel

class ActiveGroupActivity : AppCompatActivity(), CardClickListener<Platform> {

    companion object {
        var STANDARD_DATA: List<Platform> = arrayListOf(
                Platform(1, "Telegram", false,null, "Add name and key."),
                Platform(2, "Twitter",false, null, "Haven't yet.")
        )
    }

    lateinit var viewModel: ActiveGroupViewModel
    private lateinit var adapter: PlatformRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityActiveGroupBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_active_group)

        // Bind RecyclerView
        val recyclerView: RecyclerView = binding.platformList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        viewModel = ViewModelProvider(this)
            .get(ActiveGroupViewModel::class.java)
        viewModel.platformRepository = PlatformRepositoryImpl(applicationContext)
        binding.viewmodel = viewModel

        adapter = PlatformRecyclerViewAdapter(this)
        binding.adapter = adapter

        viewModel.updatePlatformsCount()
        viewModel.getPlatformsCount().observe(this, {
            if (it == 0) {
                viewModel.initializePlatforms(STANDARD_DATA)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateList()
        viewModel.getAllPlatform().observe(this, {
            adapter.setData(it)
        })
    }

    override fun onCardClick(obj: Platform) {
        val intent = Intent(this, ManagePlatformActivity::class.java)
        intent.putExtra(Platform::class.java.simpleName, obj)
        startActivity(intent)
    }
}