package com.example.trainingapp.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import com.example.trainingapp.R
import com.example.trainingapp.viewmodel.DataViewModel
import com.example.trainingapp.viewmodel.DataViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var vmFactory: DataViewModelFactory<DataViewModel>

    lateinit var dataViewModel: DataViewModel

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val component = DaggerDataViewModelFactoryComponent.create()
        component.inject(this)*/

        dataViewModel = ViewModelProviders.of(this, vmFactory)[DataViewModel::class.java]

        /*
        Using Navigation component, so fragment transactions are disabled

        if (savedInstanceState == null ) {
            val fragment = MainListFragment()
            supportFragmentManager.commitNow(true) {
                add(fragment, "ListFragment")
            }
        } */
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.searchBar)?.actionView as SearchView).apply {
            searchView = this
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchQuery(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }

        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        closeSearchView()
    }

    @VisibleForTesting
    private fun closeSearchView() {
        if (!searchView.isIconified) {
            searchView.isIconified = true
            supportActionBar?.collapseActionView()
        }
    }

    fun searchQuery(query: String?) {
        query?.apply {
            if (!isNullOrBlank()) {
                dataViewModel.getContentByName(query)
            }
        }
    }
}
