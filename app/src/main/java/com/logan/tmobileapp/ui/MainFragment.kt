package com.logan.tmobileapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.logan.tmobileapp.adapters.CardAdapter
import com.logan.tmobileapp.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var cardAdapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resultsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cardAdapter
        }

        mainViewModel.results.observe(viewLifecycleOwner, Observer {
            cardAdapter.loadData(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}