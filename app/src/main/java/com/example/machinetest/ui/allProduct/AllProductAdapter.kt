package com.example.machinetest.ui.allProduct

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.machinetest.data.AddTable
import com.example.machinetest.databinding.CustomAllProductBinding


class AllProductAdapter(
    private val context: Context,
    private var arrayList: ArrayList<AddTable>,
    val authViewModel: AllProductViewModel
) : RecyclerView.Adapter<AllProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            CustomAllProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    fun setData(arrayList: ArrayList<AddTable>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentProduct = arrayList[position]

        holder.binding.productName.text = arrayList.get(position).productName
        holder.binding.txtPrice.text = "₹ ${arrayList.get(position).price}"
        holder.binding.desc.text = "Desc : ${arrayList.get(position).desc}"
        holder.binding.category.text = "Category : ${arrayList.get(position).category}"
        holder.binding.image.load(arrayList.get(position).image)

        holder.binding.btnEdit.setOnClickListener {

            val action = AllProductFragmentDirections.actionAllProductFragmentToEditProductFragment(
                currentProduct
            )

            it.findNavController().navigate(action)
        }

        holder.binding.btnAddToCart.setOnClickListener {

            if (authViewModel.checkProductIsAlreadyInCart(arrayList.get(position).id.toString()))
            {
                Toast.makeText(context,"Already in cart",Toast.LENGTH_SHORT).show()
            }
            else {

                val productName = arrayList.get(position).productName
                val price = arrayList.get(position).price
                val desc = arrayList.get(position).desc
                val category = arrayList.get(position).category
                val quantity = "1"

                authViewModel.addToCart(
                    productName = productName,
                    price = price,
                    desc = desc,
                    category = category,
                    quantity = quantity,
                    productId = arrayList[position].id.toString()
                )

                Toast.makeText(context, "Add to cart successfully", Toast.LENGTH_SHORT).show()
            }
        }

    }

    inner class ViewHolder(val binding: CustomAllProductBinding) :
        RecyclerView.ViewHolder(binding.root)


    fun filterList(filterlist: ArrayList<AddTable>) {
        this.arrayList = filterlist
        notifyDataSetChanged()
    }


}