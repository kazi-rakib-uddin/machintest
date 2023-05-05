package com.example.machinetest.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.machinetest.R
import com.example.machinetest.data.CartTable
import com.example.machinetest.data.OrderTable
import com.example.machinetest.databinding.FragmentLoginBinding
import com.example.machinetest.databinding.FragmentOrderBinding
import com.example.machinetest.ui.cart.CartAdapter
import com.example.machinetest.ui.cart.CartProductViewModel
import com.example.machinetest.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<OrderViewModel>()
    private lateinit var arrayList : ArrayList<OrderTable>
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrayList = ArrayList()

        orderAdapter = OrderAdapter(requireContext(),arrayList)

        binding.rvAllProduct.apply {

            adapter = orderAdapter
        }


        authViewModel.allOrderProduct().observe(requireActivity(), Observer {

            orderAdapter.setData(it as ArrayList<OrderTable>)

            if (it.isEmpty())
            {

                Toast.makeText(requireContext(),"No Order Available", Toast.LENGTH_SHORT).show()
            }

        })

    }

}