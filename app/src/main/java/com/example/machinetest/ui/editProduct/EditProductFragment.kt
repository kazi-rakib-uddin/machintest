package com.example.machinetest.ui.editProduct

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
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.machinetest.databinding.FragmentEditProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditProductFragment : Fragment() {

    private val args by navArgs<EditProductFragmentArgs>()

    private var _binding: FragmentEditProductBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<EditProductViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditProductBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productName.setText(args.currentProduct.productName)
        binding.price.setText(args.currentProduct.price)
        binding.desc.setText(args.currentProduct.desc)
        binding.category.setText(args.currentProduct.category)


        binding.btnUpdate.setOnClickListener {

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

                    authViewModel.updateProduct(args.currentProduct.id!!,productName,price,desc, category,getBitmap())
                    showToast("Update Product Successfully")
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