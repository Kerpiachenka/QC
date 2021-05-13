package by.gstu.app

import android.content.Intent
import android.os.Bundle
import android.text.InputType
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
import by.gstu.app.repository.PlatformRepositoryImpl
import by.gstu.app.util.toast
import by.gstu.app.viewmodel.ManageAbonentViewModel

class ManageAbonentActivity
    : AppCompatActivity(), BaseQueryResultListener, CardClickListener<Platform> {

    private lateinit var manageAbonentViewModel: ManageAbonentViewModel
    lateinit var crossRefRepository: AbonentPlatformCrossRefRepository
    lateinit var abonent: Abonent

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
            abonent = it as Abonent
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

        viewModel.retrieveAllAvailablePlatforms(PlatformRepositoryImpl(this))
                .observe(this, {
                    adapter.setData(it.filter { platform -> platform.isActive })
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
        builder.setTitle("Input user identifier (chat id)")
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        val crossRef = AbonentPlatformCrossRef(abonent.abonentId, obj.platformName, "")

        manageAbonentViewModel
                .getCrossRef(abonent.abonentId, obj.platformName, crossRefRepository)
                .observe(this, {
            if (it != null) {
                input.setText(it.userIdentifier)
                crossRef.userIdentifier = it.userIdentifier
            }
        })

        builder.setPositiveButton("ok") { _, _ ->
            manageAbonentViewModel.addCrossRef(AbonentPlatformCrossRef(
                    crossRef.abonentId, crossRef.platformName, input.text.toString()
            ), crossRefRepository)
        }

        builder.setNegativeButton("cancel") { dialogInterface, _ -> dialogInterface.cancel() }
        builder.show()

    }
}