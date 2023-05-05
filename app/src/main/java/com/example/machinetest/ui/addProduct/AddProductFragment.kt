package com.example.machinetest.ui.addProduct

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.machinetest.databinding.FragmentAddProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                lifecycleScope.launch {

                    authViewModel.addProduct(productName,price,desc, category,getBitmap())
                    showToast("Add Product")
                }

            }


        }
    }


    fun showToast(msg : String)
    {
        Toast.makeText(requireContext(),msg, Toast.LENGTH_SHORT).show()
    }


    private suspend fun getBitmap(): Bitmap {
        val loading = ImageLoader(requireContext())
        val request = ImageRequest.Builder(requireContext())
            .data("https://avatars3.githubusercontent.com/u/14994036?s=400&u=2832879700f03d4b37ae1c09645352a352b9d2d0&v=4")
            .build()

        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }

}