package com.example.machinetest.ui.order

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetest.data.CartTable
import com.example.machinetest.data.OrderTable
import com.example.machinetest.data.toOrderTable
import com.example.machinetest.databinding.CustomCartProductBinding
import com.example.machinetest.databinding.CustomOrderProductBinding

class OrderAdapter(
    private val context: Context,
    private var arrayList: ArrayList<OrderTable>,
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomOrderProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    fun setData(arrayList: ArrayList<OrderTable>)
    {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.binding.productName.text = arrayList.get(position).productName
        holder.binding.txtPrice.text = "₹ ${arrayList.get(position).price}"
        holder.binding.desc.text = "Desc : ${arrayList.get(position).desc}"
        holder.binding.category.text = "Category : ${arrayList.get(position).category}"
        holder.binding.quantity.text = "Quantity : ${arrayList.get(position).quantity}"


    }

    inner class ViewHolder(val binding: CustomOrderProductBinding) : RecyclerView.ViewHolder(binding.root)


}
