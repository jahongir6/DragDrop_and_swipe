package com.example.draganddropandswipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.draganddropandswipe.adapter.PersonAdapter
import com.example.draganddropandswipe.databinding.ActivityMainBinding
import com.example.draganddropandswipe.models.Person
import com.example.draganddropandswipe.utils.ItemTouchHelperAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var list: ArrayList<Person>
    private lateinit var personAdapter: PersonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        personAdapter = PersonAdapter(list)
        binding.rv.adapter = personAdapter

        val itemTouchHelperAdapter = object :ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags,swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                personAdapter.onItemMove(viewHolder.adapterPosition,target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                personAdapter.onItemDismsiss(viewHolder.adapterPosition)
            }
        }
        val itemTouch = ItemTouchHelper(itemTouchHelperAdapter)
        itemTouch.attachToRecyclerView(binding.rv)
    }

    private fun loadData() {
        list = ArrayList()
        for (i in 1..100) {
            list.add(Person("jahongir", 19))
        }
    }

}