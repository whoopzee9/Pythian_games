package com.example.pythian_games.fragments.mainFragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pythian_games.R
import com.example.pythian_games.databinding.FragmentCardBinding
import com.example.pythian_games.databinding.FragmentLoginBinding
import com.example.pythian_games.viewModels.CardViewModel
import com.example.pythian_games.viewModels.LoginViewModel

class CardFragment : Fragment() {

    companion object {
        fun newInstance() = CardFragment()
    }

    private lateinit var viewModel: CardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CardViewModel() as T
            }
        }).get(CardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCardBinding>(
            inflater,
            R.layout.fragment_card,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.cardViewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CardViewModel::class.java)
        // TODO: Use the ViewModel

        val num = arguments?.getString("cardNum")?.trim()?.substring(1)?.trim()?.toInt()

        println("------------------------------------------------")
        println(arguments?.getString("cardNum"))
        println("$num)")
        if (num != null) {
            viewModel.setCurrentQuestion(num)
        }
        println("---------------------------------")
        //TEST
        val tv = requireView().findViewById<TextView>(R.id.tv_question)
        println(tv.text)
        println("---------------------------------")

    }

}