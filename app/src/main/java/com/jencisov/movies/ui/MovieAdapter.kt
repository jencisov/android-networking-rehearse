package com.jencisov.movies.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jencisov.movies.R
import com.jencisov.movies.data.TmdbMovie

class MovieAdapter(private val movies: List<TmdbMovie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_movie_item, parent, false) as TextView
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.textView.text = movie.title
    }

    class MovieViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

}