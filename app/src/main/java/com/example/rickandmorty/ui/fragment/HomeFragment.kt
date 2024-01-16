package com.example.rickandmorty.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentHomeBinding
import com.example.rickandmorty.ui.adapter.CharacterAdapter
import com.example.rickandmorty.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        filterByName()
        setAdapter()

        homeViewModel.total.observe(viewLifecycleOwner){
            if(it==0){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        }
        return binding.root
    }

    private fun setAdapter(){
        homeViewModel.getAllCharacters()
        homeViewModel.characters.observe(viewLifecycleOwner){
            val adapter = CharacterAdapter{findNavController().navigate(R.id.action_homeFragment_to_singleCharacterFragment,it)}
            adapter.submitList(it)
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
            binding.recyclerView.adapter = adapter
        }
    }

    private fun filterByName(){
        binding.edtSearch.doAfterTextChanged {
            homeViewModel.filterByName(it.toString())
        }
    }


}