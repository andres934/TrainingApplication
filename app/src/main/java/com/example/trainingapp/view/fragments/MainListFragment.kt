package com.example.trainingapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingapp.R
import com.example.trainingapp.view.adapters.DataAdapter
import com.example.trainingapp.models.DataModel
import com.example.trainingapp.tools.BaseFragment
import com.example.trainingapp.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.fragment_main_list.*

/**
 * A simple [Fragment] subclass.
 */
class MainListFragment : BaseFragment() {

    private val adapter = lazy {
        DataAdapter(emptyList(), this)
    }

    private lateinit var dataViewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)
        dataViewModel.getContentList()?.observe(this, Observer<List<DataModel>> {
            adapter.value.setData(it)
            Log.d(this::class.java.simpleName, "Data Changed!")

        })
    }

    private fun initRecycler() {
        activity?.apply {
            recycler.layoutManager = LinearLayoutManager(this)
            recycler.adapter = adapter.value
        }
    }

    override fun onItemClickListener(idContent: String) {
        val directions =
            MainListFragmentDirections.actionMainListFragmentToDetailsFragment(
                idContent
            )
        findNavController().navigate(directions)
        Toast.makeText(activity, "Item ID: $idContent", Toast.LENGTH_SHORT).show()
    }

}
