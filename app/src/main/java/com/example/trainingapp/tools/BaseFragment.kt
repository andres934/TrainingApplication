package com.example.trainingapp.tools

import androidx.fragment.app.Fragment
import com.example.trainingapp.interfaces.AdapterItemClickListener

open class BaseFragment: Fragment(), AdapterItemClickListener {

    override fun onItemClickListener(idContent: String) {}
}