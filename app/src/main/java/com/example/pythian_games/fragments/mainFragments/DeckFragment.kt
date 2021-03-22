package com.example.pythian_games.fragments.mainFragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pythian_games.R
import com.example.pythian_games.viewModels.DeckViewModel

class DeckFragment : Fragment() {

    companion object {
        fun newInstance() = DeckFragment()
    }

    private lateinit var viewModel: DeckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deck, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DeckViewModel::class.java)
        // TODO: Use the ViewModel
    }

}