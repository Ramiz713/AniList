package com.itis2019.anilist.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.itis2019.anilist.di.di
import com.itis2019.anilist.ui.ViewModelFactory
import org.kodein.di.generic.instance

abstract class BaseFragment : Fragment() {

    private val viewModelFactory: ViewModelFactory by di.instance()

    protected abstract val viewModel: BaseViewModel

    protected abstract fun initObservers()

    protected abstract fun initViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initObservers()
        observeError(view)
    }

    protected fun observeLoading(view: View) =
        viewModel.isLoading().observe(viewLifecycleOwner, Observer {
            view.visibility = if (it) View.VISIBLE else View.GONE
        })

    private fun observeError(view: View) =
        viewModel.error().observe(viewLifecycleOwner, Observer {
            Snackbar.make(view, it.localizedMessage, Snackbar.LENGTH_SHORT).show()
        })
}
