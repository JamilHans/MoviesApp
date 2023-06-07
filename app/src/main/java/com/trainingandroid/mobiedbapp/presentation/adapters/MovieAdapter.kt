package com.trainingandroid.mobiedbapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.trainingandroid.domain.model.movie.Movies
import com.trainingandroid.mobiedbapp.R
import com.trainingandroid.mobiedbapp.databinding.ItemMovieBinding

class MovieAdapter constructor(
    var movies: MutableList<Movies> = mutableListOf(),
    var onItemSelected: (Movies) -> Unit,
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemMovieBinding = ItemMovieBinding.bind(itemView)
        private val link: String = "https://image.tmdb.org/t/p/w200"

        fun bind(movie: Movies) {
            Picasso.get().load(link + movie.posterPath).error(R.drawable.ic_launcher_background).into(binding.imgMovie)
            binding.root.setOnClickListener {
                onItemSelected(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    fun updateList(movies: List<Movies>) {
        this.movies.addAll(movies)
        //this.movies = movies
        notifyDataSetChanged()
    }

}
