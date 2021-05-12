package by.gstu.app

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.gstu.app.adapter.PlatformRecyclerViewAdapter
import by.gstu.app.bean.Abonent
import by.gstu.app.bean.AbonentPlatformCrossRef
import by.gstu.app.bean.Platform
import by.gstu.app.databinding.ActivityManageAbonentBinding
import by.gstu.app.listener.BaseQueryResultListener
import by.gstu.app.listener.CardClickListener
import by.gstu.app.repository.AbonentPlatformCrossRefRepository
import by.gstu.app.repository.AbonentPlatformCrossRefRepositoryImpl
import by.gstu.app.repository.AbonentRepositoryImpl
import by.gstu.app.util.toast
import by.gstu.app.viewmodel.ManageAbonentViewModel

class ManageAbonentActivity
    : AppCompatActivity(), BaseQueryResultListener, CardClickListener<Platform> {

    private lateinit var manageAbonentViewModel: ManageAbonentViewModel
    lateinit var crossRefRepository: AbonentPlatformCrossRefRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityManageAbonentBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_manage_abonent)

        manageAbonentViewModel = ViewModelProvider(this)
            .get(ManageAbonentViewModel::class.java)

        val repository = AbonentRepositoryImpl(this.applicationContext)
        val recyclerView: RecyclerView = binding.platformList
        val entity = intent.getSerializableExtra(Abonent::class.java.simpleName)
        val adapter = PlatformRecyclerViewAdapter(this)

        entity?.let {
            val abonent = it as Abonent
            fillManageAbonentViewModel(abonent, manageAbonentViewModel, adapter)
        }
        crossRefRepository = AbonentPlatformCrossRefRepositoryImpl(applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        manageAbonentViewModel.listener = this
        manageAbonentViewModel.repository = repository

        repository.listener = manageAbonentViewModel
        binding.viewmodel = manageAbonentViewModel
        binding.adapter = adapter
    }

    private fun fillManageAbonentViewModel(abonent: Abonent,
                              viewModel: ManageAbonentViewModel,
                              adapter: PlatformRecyclerViewAdapter) {
        viewModel.abonent = abonent
        viewModel.name = abonent.name
        viewModel.age = abonent.age.toString()

        val retrievePlatformsOfAbonent = viewModel.retrievePlatformsOfAbonent(
                abonent,
                AbonentPlatformCrossRefRepositoryImpl(applicationContext))
        retrievePlatformsOfAbonent
                .observe(this, {
                    adapter.setData(it.platforms)
                })
    }

    override fun onSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onFailure(message: String) {
        toast(message)
    }

    override fun onCardClick(obj: Platform) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Input user identifier")
        val dialogLayout = LayoutInflater.from(this)
                .inflate(R.layout.platform_alert_dialog, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.user_identifier)
        lateinit var crossRef: AbonentPlatformCrossRef

        crossRefRepository.getCrossRefByPlatformName(obj.platformName)
                .observe(this, {
                    editText.setText(it.userIdentifier)
                    crossRef = it
                })
        builder.setView(dialogLayout)
        builder.setPositiveButton("ok") { _, _ ->
            crossRefRepository.update(AbonentPlatformCrossRef(
                    crossRef.abonentId, crossRef.platformName, editText.text.toString()
            ))
        }
        builder.setNegativeButton("cancel") { _, _ -> toast("Canceled") }
        builder.show()

    }
}