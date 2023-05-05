package com.example.machinetest.data

fun CartTable.toOrderTable():OrderTable{
    return OrderTable(
        productName = productName,
        price = price,
        quantity = quantity,
        desc = desc,
        category = category
    )
}

