package com.example.draganddropandswipe.utils

import android.icu.text.Transliterator.Position

interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int,toPosition: Int)
    fun onItemDismsiss(position:Int)
}