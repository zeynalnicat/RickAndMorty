package com.example.rickandmorty.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentSingleCharacterBinding
import com.example.rickandmorty.viewmodel.SingleCharacterViewModel


class SingleCharacterFragment : Fragment() {
    private lateinit var binding: FragmentSingleCharacterBinding
    private lateinit var singleCharacterViewModel: SingleCharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        singleCharacterViewModel = ViewModelProvider(this)[SingleCharacterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleCharacterBinding.inflate(inflater)
        setLayout()
        setNavigation()
        return binding.root
    }

    private fun setLayout() {
        var id: Int = 0
        arguments?.let {
            id = it.getInt("CharId")
        }
        singleCharacterViewModel.getCharacter(id)
        singleCharacterViewModel.character.observe(viewLifecycleOwner) {
            Glide.with(binding.root)
                .load(it.image)
                .into(binding.imgCharacter)
            binding.txtStatus.text = it.status
            binding.txtName.text = it.name
            binding.isAlive.setCardBackgroundColor(
                when (it.status) {
                    "Alive" -> ContextCompat.getColor(requireContext(), R.color.green)
                    "Dead" -> ContextCompat.getColor(requireContext(), R.color.red)
                    else -> ContextCompat.getColor(requireContext(), R.color.black)
                }
            )

            binding.imgGender.setImageResource(
                when (it.gender) {
                    "Male" -> R.drawable.male_sign
                    "Female" -> R.drawable.female_sign
                    else -> R.drawable.unknown_sign
                }
            )
            binding.txtSpecies.text = it.species
            binding.txtLocation.text = it.location.name
        }
    }

    private fun setNavigation(){
        binding.iconBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}