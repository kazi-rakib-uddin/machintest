package com.example.machinetest.ui.cart

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

class CartAdapter(
    private val context: Context,
    private var arrayList: ArrayList<CartTable>,
    var authViewModel: CartProductViewModel,
    val btnOrder: Button
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var holdQuantity= 1
    lateinit var orderTable: List<OrderTable>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomCartProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    fun setData(arrayList: ArrayList<CartTable>)
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
        holder.binding.IncDecText.text = arrayList.get(position).quantity


        holder.binding.increaseButton.setOnClickListener {

            holdQuantity++

            authViewModel.updateQuantity(arrayList.get(position).id!!, holdQuantity.toString())
        }


        holder.binding.decreaseButton.setOnClickListener {

            if (holdQuantity > 1)
            {
                holdQuantity = holdQuantity -1

                authViewModel.updateQuantity(arrayList.get(position).id!!, holdQuantity.toString())
            }


        }


        holder.binding.btnDelete.setOnClickListener {



            dialog(arrayList.get(position).id!!)
        }


        btnOrder.setOnClickListener {

            orderTable = ArrayList()

            arrayList.forEach {
                (orderTable as ArrayList<OrderTable>).add(it.toOrderTable())
            }

           authViewModel.allOrder(orderTable as ArrayList<OrderTable>)
            authViewModel.deleteCartTable()
        }

    }

    inner class ViewHolder(val binding: CustomCartProductBinding) : RecyclerView.ViewHolder(binding.root)


    fun dialog(id: Long)
    {
        val builder = AlertDialog.Builder(context)
        //set title for alert dialog
        builder.setTitle("Delete")
        //set message for alert dialog
        builder.setMessage("are you sure you want to delete this item")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes"){dialogInterface, which ->

            authViewModel.deleteProduct(id = id)
        }
        builder.setNegativeButton("No"){dialogInterface, which ->

        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}
