package com.example.trainingapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.models.DataModel
import com.example.trainingapp.tools.BaseFragment
import com.example.trainingapp.view.adapters.DataAdapter
import com.example.trainingapp.viewmodel.DataViewModel
import com.example.trainingapp.viewmodel.DataViewModelFactory
import kotlinx.android.synthetic.main.fragment_main_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MainListFragment : BaseFragment() {

    private val adapter by lazy {
        DataAdapter(emptyList(), this)
    }

    @Inject
    lateinit var factory: DataViewModelFactory<DataViewModel>

    private lateinit var dataViewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.apply {
            dataViewModel = ViewModelProviders.of(this, factory)[DataViewModel::class.java]
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        dataViewModel.getContentList().observe(this, Observer<List<DataModel>> {
            updateAdapterItems(it)
        })
    }

    override fun onItemClickListener(idContent: String) {
        handleItemClick(idContent)
    }

    //End Override methods

    fun handleItemClick(idContent: String) {
        val directions =
            MainListFragmentDirections.actionMainListFragmentToDetailsFragment(
                idContent
            )
        findNavController().navigate(directions)
        //Toast.makeText(activity, "Item ID: $idContent", Toast.LENGTH_SHORT).show()
    }

    fun initRecycler(): RecyclerView? {
        activity?.apply {
            recycler.layoutManager = LinearLayoutManager(this)
            recycler.adapter = adapter
        }
        return recycler
    }

    fun updateAdapterItems(data: List<DataModel>) {
        adapter.setData(data)
        Log.d(this::class.java.simpleName, "Data Changed!")
    }

}
