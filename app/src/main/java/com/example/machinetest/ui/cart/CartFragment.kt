package com.example.machinetest.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.machinetest.data.CartTable
import com.example.machinetest.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<CartProductViewModel>()
    private lateinit var arrayList : ArrayList<CartTable>
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arrayList = ArrayList()

        cartAdapter = CartAdapter(requireContext(),arrayList,authViewModel, binding.btnOrder)

        binding.rvAllProduct.apply {

            adapter = cartAdapter
        }


        authViewModel.allProduct().observe(requireActivity(), Observer {


            cartAdapter.setData(it as ArrayList<CartTable>)

            if (it.isEmpty())
            {
                binding.btnOrder.visibility = View.GONE
                Toast.makeText(requireContext(),"No Cart Product Available",Toast.LENGTH_SHORT).show()
            }

        })

    }

}