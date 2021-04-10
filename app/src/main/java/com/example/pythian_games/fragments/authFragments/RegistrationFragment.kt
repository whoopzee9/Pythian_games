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
import com.example.pythian_games.data.User
import com.example.pythian_games.databinding.FragmentLoginBinding
import com.example.pythian_games.databinding.FragmentRegistrationBinding
import com.example.pythian_games.firebaseDB.FirebaseDB
import com.example.pythian_games.main.StartActivity
import com.example.pythian_games.viewModels.LoginViewModel
import com.example.pythian_games.viewModels.RegistrationViewModel
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private lateinit var viewModel: RegistrationViewModel
    private var auth = FirebaseAuth.getInstance()
    private var firebase = FirebaseDB()

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

        val btn = requireView().findViewById<Button>(R.id.btn_registration)
        btn.setOnClickListener {
            onRegistrationClicked(it)
        }
    }

    private fun onRegistrationClicked(view: View) {
        val name = viewModel.getName().value
        val email = viewModel.getEmail().value
        val password = viewModel.getPassword().value
        val repPassword = viewModel.getRepeatPassword().value
        if (name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty() || repPassword.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Missing information!", Toast.LENGTH_SHORT).show()
        } else if (password.equals(repPassword)) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val currUser = auth.currentUser
                    val user = User(currUser!!.uid, name, email)
                    firebase.createUser(user)
                    Toast.makeText(requireContext(), "Registration succeeded!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, StartActivity::class.java)
                    startActivity(intent)
                }
            }
        } else {
            Toast.makeText(requireContext(), "Passwords are not the same!", Toast.LENGTH_SHORT).show()
        }
    }

}