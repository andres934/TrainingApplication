package com.example.trainingapp.tools

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.trainingapp.interfaces.AdapterItemClickListener
import dagger.android.support.AndroidSupportInjection

open class BaseFragment: Fragment(), AdapterItemClickListener {

    override fun onItemClickListener(idContent: String) {}

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}