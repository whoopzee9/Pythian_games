package com.example.pythian_games.fragments.mainFragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pythian_games.R
import com.example.pythian_games.viewModels.RegistrationViewModel

class CardFragment : Fragment() {

    companion object {
        fun newInstance() = CardFragment()
    }

    private lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegistrationViewModel::class.java)
        // TODO: Use the ViewModel

        //TEST
        val tv = requireView().findViewById<TextView>(R.id.tv_cardNum)
        tv.text = arguments?.getString("cardNum") ?: "card(null)"
    }

}