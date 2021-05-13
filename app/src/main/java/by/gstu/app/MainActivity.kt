package by.gstu.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.gstu.app.adapter.AbonentRecyclerViewAdapter
import by.gstu.app.bean.Abonent
import by.gstu.app.bean.AbonentPlatformCrossRef
import by.gstu.app.bean.Platform
import by.gstu.app.database.AppDatabase
import by.gstu.app.databinding.ActivityMainBinding
import by.gstu.app.listener.ButtonClickListener
import by.gstu.app.listener.CardClickListener
import by.gstu.app.listener.MainActivityListener
import by.gstu.app.repository.AbonentPlatformCrossRefRepository
import by.gstu.app.repository.AbonentPlatformCrossRefRepositoryImpl
import by.gstu.app.repository.AbonentRepositoryImpl
import by.gstu.app.repository.PlatformRepositoryImpl
import by.gstu.app.util.toast
import by.gstu.app.viewmodel.MainActivityViewModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(),
        MainActivityListener, CardClickListener<Abonent>, ButtonClickListener<Abonent> {

    companion object {
        var STANDARD_DATA: List<Platform> = arrayListOf(
                Platform("Telegram", false, null, "Add name and key."),
                Platform("Twitter", false, null, "Haven't yet.")
        )
    }

    lateinit var viewModel: MainActivityViewModel
    lateinit var repository: AbonentPlatformCrossRefRepository
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
		adapter = AbonentRecyclerViewAdapter(this, this)
        binding.adapter = adapter

        viewModel.updateList()
        viewModel.getAllAbonent().observe(this, {
            adapter.setData(it)
        })
        repository = AbonentPlatformCrossRefRepositoryImpl(applicationContext)
        checkPlatforms() // TODO: make changes in record according changes in platform initialization
    }

    private fun checkPlatforms() {
        viewModel.platformRepository = PlatformRepositoryImpl(applicationContext)
        viewModel.updatePlatformsCount()
        viewModel.getPlatformsCount().observe(this, {
            if (it == 0) {
                viewModel.initializePlatforms(STANDARD_DATA)
            }
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

    override fun onButtonClick(obj: Abonent) {
        val builder = AlertDialog.Builder(this)
        repository.getPlatformsOfAbonent(obj.abonentId)
                .observe(this, {
                    val data = arrayListOf<String>()
                    for (platform in it.platforms) {
                        data.add(platform.platformName)
                    }
                    configureBuilder(builder, data.toTypedArray())
                })
    }

    private fun configureBuilder(builder: AlertDialog.Builder, items: Array<String>) {
        val selectedList = ArrayList<Int>()
        builder.setTitle("This is list choice dialog box")
        val messageInput = EditText(this)
        messageInput.inputType = InputType.TYPE_CLASS_TEXT

        builder.setMultiChoiceItems(items, null) { _, which, isChecked ->
            if (isChecked) {
                selectedList.add(which)
            } else if (selectedList.contains(which)) {
                selectedList.remove(Integer.valueOf(which))
            }
        }
        builder.setView(messageInput)

        builder.setPositiveButton("send") { _, _ ->
            val message = messageInput.text.toString()
            for (j in selectedList.indices) {
                toast(items[selectedList[j]])
            }
        }
        builder.setNegativeButton("cancel") { dialogInterface, _ -> dialogInterface.cancel() }

        builder.show()
    }
}