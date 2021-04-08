package com.example.pythian_games.fragments.authFragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.example.pythian_games.R
import com.example.pythian_games.databinding.FragmentLoginBinding
import com.example.pythian_games.databinding.FragmentRegistrationBinding
import com.example.pythian_games.viewModels.LoginViewModel
import com.example.pythian_games.viewModels.RegistrationViewModel

class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return RegistrationViewModel() as T
            }
        }).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentRegistrationBinding>(
            inflater,
            R.layout.fragment_registration,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.registrationViewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}