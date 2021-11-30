package com.argahutama.submission.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.argahutama.submission.core.navigation.NavigationDirection
import com.argahutama.submission.custom_ui.CustomSnack
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {
    open val viewModel: BaseViewModel? = null
    private var job: Job? = null
    private var _binding: ViewBinding? = null
        get() {
            if (field == null) field = createBinding()
            return field
        }

    protected val binding get() = _binding

    abstract fun createBinding(): ViewBinding
    abstract fun initView()
    abstract fun initAction()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setup()
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    open fun setup() {
        initView()
        initAction()
    }

    private fun getBaseActivity(): BaseActivity? =
        if (activity is BaseActivity) requireActivity() as BaseActivity else null

    fun showSnackbar(message: String, type: Int = CustomSnack.SUCCESS) =
        getBaseActivity()?.showSnackbar(message, type)

    private fun getBaseApp() = getBaseActivity()?.getBaseApp()

    fun navigateTo(direction: NavigationDirection) =
        getBaseApp()?.navigateTo(requireContext(), direction)

    protected fun debounce(delayInMs: Long = 200L, action: () -> Unit) {
        job?.cancel()
        job = lifecycleScope.launch {
            delay(delayInMs)
            action()
        }
    }
}