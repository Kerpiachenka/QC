package by.gstu.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import by.gstu.app.adapter.AbonentRecyclerViewAdapter
import by.gstu.app.database.AppDatabase
import by.gstu.app.databinding.ActivityMainBinding
import by.gstu.app.databinding.ActivityManageAbonentBinding
import by.gstu.app.listener.MainActivityListener
import by.gstu.app.repository.AbonentRepositoryImpl
import by.gstu.app.viewmodel.MainActivityViewModel
import by.gstu.app.viewmodel.ManageAbonentViewModel

class MainActivity : AppCompatActivity(), MainActivityListener {

    lateinit var viewModel: MainActivityViewModel
    private val adapter = AbonentRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_main)

        // Bind RecyclerView
        val recyclerView: RecyclerView = binding.abonentList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        // Init ViewModel
        viewModel = ViewModelProvider(this)
                .get(MainActivityViewModel::class.java)
        viewModel.mainActivityListener = this
        viewModel.abonentRepository = AbonentRepositoryImpl(this.applicationContext)

        // Init adapter
        binding.viewmodel = viewModel
        binding.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateList()
        viewModel.getAllAbonent().observe(this, {
            adapter.setData(it)
        })
    }

    override fun onOpenManageAbonentActivity() {
        val intent = Intent(this, ManageAbonentActivity::class.java)
        startActivity(intent)
    }
}