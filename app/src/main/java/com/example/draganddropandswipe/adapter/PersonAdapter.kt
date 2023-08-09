package com.example.draganddropandswipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.draganddropandswipe.databinding.ItemRvBinding
import com.example.draganddropandswipe.models.Person
import com.example.draganddropandswipe.utils.ItemTouchHelperAdapter
import java.util.*
import kotlin.collections.ArrayList

class PersonAdapter(var list: ArrayList<Person>) : RecyclerView.Adapter<PersonAdapter.Vh>(),
    ItemTouchHelperAdapter {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(person: Person) {
            itemRvBinding.name.text = person.name
            itemRvBinding.number.text = person.number.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
//bu kochirish uchun
    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition > toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i + 1)
            }
        }else{
            for (i in fromPosition until toPosition+1){
                Collections.swap(list,i,i)
            }
        }
        notifyItemMoved(fromPosition,toPosition)
    }
//bu ochirish uchun
    override fun onItemDismsiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

}