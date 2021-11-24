package com.argahutama.submission.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment : Fragment() {
    open val viewModel: BaseViewModel? = null
    private var _binding: ViewBinding? = null
        get() {
            if (field == null) field = createBinding()
            return field
        }

    protected val binding get() = _binding!!

    abstract fun createBinding(): ViewBinding
    abstract fun initView()
    abstract fun initAction()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setup()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    open fun setup() {
        initView()
        initAction()
    }

    fun getBaseActivity(): BaseActivity? =
        if (activity is BaseActivity) requireActivity() as BaseActivity else null
}