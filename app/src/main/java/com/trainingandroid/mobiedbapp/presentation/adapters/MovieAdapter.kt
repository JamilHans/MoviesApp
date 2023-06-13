package com.trainingandroid.mobiedbapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trainingandroid.domain.model.movie.Movies
import com.trainingandroid.mobiedbapp.R
import com.trainingandroid.mobiedbapp.databinding.ItemMovieAdapterBinding

class MovieAdapter constructor(
    private val movies: MutableList<Movies> = mutableListOf(),
    private val onItemSelected: (Movies) -> Unit,
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemMovieAdapterBinding = ItemMovieAdapterBinding.bind(itemView)
        private val link: String = "https://image.tmdb.org/t/p/w200"

        fun bind(movie: Movies) {
            binding.apply {
                itemCardView.apply {
                    setImg(link + movie.posterPath)
                    setTitle(movie.title)
                    setRatingBar(movie.voteAverage.toFloat())
                }
                root.setOnClickListener {
                    onItemSelected(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_adapter, parent, false)
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
        notifyDataSetChanged()
    }

}
