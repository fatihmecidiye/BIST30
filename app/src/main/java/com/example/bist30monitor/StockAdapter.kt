package com.example.bist30monitor

import android.graphics.Color.green
import android.graphics.Color.red
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/*class StockAdapter(private val stockList: MutableList<Stock>) :
    RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    inner class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewStockName: TextView = itemView.findViewById(R.id.textViewStockName)
        val textViewStockPrice: TextView = itemView.findViewById(R.id.textViewStockPrice)
        val textViewStockDailyChange: TextView = itemView.findViewById(R.id.textViewStockDailyChange)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stock, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val currentStock = stockList[position]
        holder.textViewStockName.text = "Hisse: ${currentStock.name}"
        holder.textViewStockPrice.text = "Fiyat: ${currentStock.price} ₺"
        holder.textViewStockDailyChange.text = "Günlük Değişim: ${currentStock.dailyChange}%"
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    fun getStockList(): MutableList<Stock> {
        return stockList
    }
}*/

class StockAdapter(private val stockList: MutableList<Stock>) :
    RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    // Interface to handle item click events
    interface OnStockClickListener {
        fun onStockClick(stock: Stock)
    }
    private var onStockClickListener: OnStockClickListener? = null

    fun setOnStockClickListener(listener: OnStockClickListener) {
        this.onStockClickListener = listener
    }
    inner class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewStockName: TextView = itemView.findViewById(R.id.textViewStockName)
        val textViewStockPrice: TextView = itemView.findViewById(R.id.textViewStockPrice)
        val textViewStockDailyChange: TextView = itemView.findViewById(R.id.textViewStockDailyChange)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onStockClickListener?.onStockClick(stockList[position])
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stock_table_row, parent, false) // Use the updated table row layout
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val currentStock = stockList[position]
        holder.textViewStockName.text = currentStock.name
        holder.textViewStockPrice.text = "${currentStock.price} ₺"
        holder.textViewStockDailyChange.text = "${currentStock.dailyChange}%"

        // Set background color based on daily change
        val dailyChange = currentStock.dailyChange
        if (dailyChange > 0) {
            holder.textViewStockDailyChange.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.transparentGreen))
        } else {
            holder.textViewStockDailyChange.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.transparentRed))
        }

    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    fun getStockList(): MutableList<Stock> {
        return stockList
    }
}
