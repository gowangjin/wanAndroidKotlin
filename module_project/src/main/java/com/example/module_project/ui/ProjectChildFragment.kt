package com.example.module_project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.module_project.R
import com.example.module_project.databinding.FragmentChildBinding

class ProjectChildFragment : Fragment() {
    private lateinit var mBinding:FragmentChildBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_child,container,false)
        return mBinding.root
    }

}