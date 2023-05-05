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
    var filterdNames = ArrayList<AddTable>()

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
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input

                filter1(editable.toString())
            }
        })



    }


    private fun filter(text: String) {
       /* //new array list that will hold the filtered data
        val filterdNames = ArrayList<AddTable>()

        //looping through existing elements
        for (s in 1..arrayList.size) {
            //if the existing elements contains the search input
            if (s.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                //adding the element to filtered list
                filterdNames.add(s)
            }
        }

        //calling a method of the adapter class and passing the filtered list
        adapter.filterList(filterdNames)*/


        for (s in arrayList)
        {
            if (s.productName.equals(text))
            {
                filterdNames.add(s)
            }
        }

        productAdapter.setData(filterdNames)

    }



    private fun filter1(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<AddTable> = ArrayList()

        // running a for loop to compare elements.
        for (item in arrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.productName.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT))) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            //Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            productAdapter.filterList(filteredlist)
        }
    }



}