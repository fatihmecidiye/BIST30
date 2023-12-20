package com.example.bist30monitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StockAdapter(private val stockList: MutableList<Stock>) :
    RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    inner class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewStockName: TextView = itemView.findViewById(R.id.textViewStockName)
        val textViewStockPrice: TextView = itemView.findViewById(R.id.textViewStockPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stock, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val currentStock = stockList[position]
        holder.textViewStockName.text = "Stock Name: ${currentStock.name}"
        holder.textViewStockPrice.text = "Stock Price: ${currentStock.price}"
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    fun getStockList(): MutableList<Stock> {
        return stockList
    }
}