package com.example.pythian_games.fragments.authFragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.example.pythian_games.R
import com.example.pythian_games.databinding.FragmentLoginBinding
import com.example.pythian_games.main.StartActivity
import com.example.pythian_games.viewModels.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LoginViewModel() as T
            }
        }).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.loginViewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
        val btn = requireView().findViewById<Button>(R.id.btn_login)
        btn.setOnClickListener {
            onLoginClicked(it)
        }
    }

    private fun onLoginClicked(view: View) {
        val email = viewModel.getEmail().value
        val password = viewModel.getPassword().value
        if (email?.isNotEmpty() == true && password?.isNotEmpty() == true) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(context, StartActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(context, "Email or password is incorrect!", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Missing data!", Toast.LENGTH_SHORT).show()
        }
    }

}