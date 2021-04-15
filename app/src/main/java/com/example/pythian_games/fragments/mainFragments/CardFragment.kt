package com.example.pythian_games.fragments.mainFragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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


        val b1 = requireView().findViewById<Button>(R.id.btn_ans1)
        val b2 = requireView().findViewById<Button>(R.id.btn_ans2)
        val b3 = requireView().findViewById<Button>(R.id.btn_ans3)
        val b4 = requireView().findViewById<Button>(R.id.btn_ans4)
        b1.setOnClickListener { handleAnswer(1) }
        b2.setOnClickListener { handleAnswer(2) }
        b3.setOnClickListener { handleAnswer(3) }
        b4.setOnClickListener { handleAnswer(4) }
    }

    private fun handleAnswer(answeredNum: Int) {
        if (answeredNum == viewModel.getCurrentQuestion().question.correctAns) {
            Toast.makeText(context, "Правильный ответ!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Неправильный ответ! Попробуйте ещё", Toast.LENGTH_SHORT).show()
        }
    }

}