package com.example.machinetest.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.machinetest.R
import com.example.machinetest.databinding.FragmentHomeBinding
import com.example.machinetest.databinding.FragmentRegisterBinding
import com.example.machinetest.repository.UserRepository
import com.example.machinetest.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddProduct.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_addProductFragment)
        }



        binding.allProduct.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_allProductFragment)
        }


        binding.btnCart.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }



        binding.btnOrder.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_orderFragment)
        }


        binding.btnLogout.setOnClickListener {

            tokenManager.clear()
            //findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            findNavController().popBackStack()

        }


    }

}