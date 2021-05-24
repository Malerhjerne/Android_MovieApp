package com.example.movie_app

import android.view.View

interface OnItemClickListener: View.OnClickListener {

        fun onItemClick(position: Int);
    }
