package by.gstu.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.gstu.app.adapter.AbonentRecyclerViewAdapter
import by.gstu.app.bean.Abonent
import by.gstu.app.databinding.ActivityMainBinding
import by.gstu.app.listener.CardClickListener
import by.gstu.app.listener.MainActivityListener
import by.gstu.app.repository.AbonentRepositoryImpl
import by.gstu.app.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), MainActivityListener, CardClickListener<Abonent> {

    lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: AbonentRecyclerViewAdapter

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
		binding.viewmodel = viewModel

        // Init adapter
		adapter = AbonentRecyclerViewAdapter(this)
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

    override fun onOpenManagePlatformActivity() {
        val intent = Intent(this, ActiveGroupActivity::class.java)
        startActivity(intent)
    }

    override fun onCardClick(obj: Abonent) {
        val intent = Intent(this, ManageAbonentActivity::class.java)
        intent.putExtra(Abonent::class.java.simpleName, obj)
        startActivity(intent)
    }
}