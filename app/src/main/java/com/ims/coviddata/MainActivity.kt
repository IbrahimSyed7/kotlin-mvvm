package com.ims.coviddata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.ims.coviddata.database.MainRoomDatabase
import com.ims.coviddata.databinding.ActivityMainBinding
import com.ims.coviddata.fragments.CasesFragment
import com.ims.coviddata.fragments.StatesFragment
import com.ims.coviddata.fragments.TestsFragment
import com.ims.coviddata.network.NetworkService
import com.ims.coviddata.repo.MainRepository
import com.ims.coviddata.utils.Navigator
import com.ims.coviddata.viewmodel.MainViewModel
import com.ims.coviddata.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val networkService = NetworkService.getInstance()
    lateinit var  mainRoomDatabase : MainRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainRoomDatabase = MainRoomDatabase.invoke(context = this)
        viewModel = ViewModelProvider(this, ViewModelFactory(MainRepository(networkService,mainRoomDatabase))).get(
            MainViewModel::class.java
        )

        val casesFragment = CasesFragment()
        val statesFragment = StatesFragment()
        val testsFragment = TestsFragment()


        replaceFragment(casesFragment)


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {



                when (tab!!.position) {
                    0 -> replaceFragment(casesFragment)
                    1 -> replaceFragment(statesFragment)
                    2 -> replaceFragment(testsFragment)

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.bookmark.setOnClickListener {
            Navigator.navigateToBookMark(this)
        }

        viewModel.getAllData()
    }

    fun replaceFragment(fragment: Fragment) {
        var fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(binding.container.id, fragment).commitNow()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllData()
    }
}
