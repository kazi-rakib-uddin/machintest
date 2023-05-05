package com.example.machinetest.ui.allProduct

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.machinetest.data.AddTable
import com.example.machinetest.databinding.FragmentAllProductBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class AllProductFragment : Fragment() {

    private var _binding: FragmentAllProductBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AllProductViewModel>()
    private lateinit var arrayList : ArrayList<AddTable>
    private lateinit var productAdapter: AllProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAllProductBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrayList = ArrayList()

        productAdapter = AllProductAdapter(requireContext(),arrayList, authViewModel)

        binding.rvAllProduct.apply {

            adapter = productAdapter
        }


        authViewModel.allProduct().observe(requireActivity(), Observer {

            productAdapter.setData(it as ArrayList<AddTable>)

            if (it.isEmpty())
            {
                Toast.makeText(requireContext(),"No Product Available", Toast.LENGTH_SHORT).show()
            }

        })



        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                filter(charSequence.toString())
            }
            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input

                //filter1(editable.toString())
            }
        })



    }



    private fun filter(text: String) {

        // %" "% because our custom sql query will require that
        val searchQuery = "%$text%"

        authViewModel.searchProduct(searchQuery).observe(this, Observer {

            if (!searchQuery.isEmpty()) {
                productAdapter.setData(it as ArrayList<AddTable>)
            }
        })

    }



}