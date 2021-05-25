package com.example.movie_app.adapters

import android.view.View

interface OnItemClickListener: View.OnClickListener {

        fun onItemClick(position: Int)
    }
