package com.example.machinetest.ui.order

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetest.data.AddTable
import com.example.machinetest.databinding.CustomAllProductBinding

class OrderProductAdapter(
    private val context: Context,
    private var arrayList: ArrayList<AddTable>
) : RecyclerView.Adapter<OrderProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomAllProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    fun setData(arrayList: ArrayList<AddTable>)
    {
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


    }

    inner class ViewHolder(val binding: CustomAllProductBinding) : RecyclerView.ViewHolder(binding.root)

}