package com.example.trainingapp.view.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.trainingapp.R
import com.example.trainingapp.view.adapters.DataAdapter
import com.example.trainingapp.fragments.DetailsFragmentBinding
import com.example.trainingapp.models.DataModel
import com.example.trainingapp.viewmodel.DataViewModel
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {

    private lateinit var dataViewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Get the id from bundle to get the data before it loads the view
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)

        updateCurrentItemById(arguments)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //As the repository is an object, it will save the last item downloaded
        //At this point it should already have the passed data for the item

        dataViewModel.getContentItem()?.observe(this, Observer<DataModel> { data ->
            try {
                Picasso.get()
                    .load(data.poster)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_STORE)
                    .into(ivPosterFull)
            } catch (e: Exception) {
                Log.e(DataAdapter::class.java.simpleName, "Load Poster Exception: $e")
            }

            val auxBinding: DetailsFragmentBinding? = DataBindingUtil.bind(view)
            auxBinding?.details = data
        })

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(R.id.search)
        item.isVisible = true
    }

    override fun onDetach() {
        dataViewModel.postManualItem()
        super.onDetach()
    }

    fun updateCurrentItemById(args: Bundle?) {
        args?.let {
            val idContent = DetailsFragmentArgs.fromBundle(
                it
            ).idContent
            idContent?.let { id ->
                dataViewModel.getContentById(id)
            }
        }
    }


}
