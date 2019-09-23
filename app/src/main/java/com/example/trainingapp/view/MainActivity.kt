package com.example.trainingapp.view

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import com.example.trainingapp.R
import com.example.trainingapp.repositories.DataRepository
import com.example.trainingapp.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var dataViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)
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
        (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
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

    fun searchQuery(query: String?) {
        query?.apply {
            if (!isNullOrBlank()) {
                dataViewModel.getContentByName(query)
            }
        }
    }
}
