package com.example.machinetest.ui.addProduct

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.machinetest.R
import com.example.machinetest.databinding.FragmentAddProductBinding
import com.example.machinetest.databinding.FragmentRegisterBinding
import com.example.machinetest.ui.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AddProductViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {

            val productName = binding.productName.text.toString()
            val price = binding.price.text.toString()
            val desc = binding.desc.text.toString()
            val category = binding.category.text.toString()

            if (productName.isBlank())
            {
                showToast("Product Name is required")
            }
            else if (price.isBlank())
            {
                showToast("Price is required")
            }
            else if (desc.isBlank())
            {
                showToast("Description is required")
            }
            else
            {
                authViewModel.addProduct(productName,price,desc, category)
                showToast("Add Product")
            }


        }
    }


    fun showToast(msg : String)
    {
        Toast.makeText(requireContext(),msg, Toast.LENGTH_SHORT).show()
    }

}