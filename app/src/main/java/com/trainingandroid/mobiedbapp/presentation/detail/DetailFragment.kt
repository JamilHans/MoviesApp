package com.trainingandroid.mobiedbapp.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.trainingandroid.mobiedbapp.R
import com.trainingandroid.mobiedbapp.databinding.FragmentDetailBinding
import com.trainingandroid.mobiedbapp.domain.model.detail.DetailMovie
import com.trainingandroid.mobiedbapp.presentation.common.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val arguments: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        init()
        observers()
    }

    private fun init() {
        arguments.movieId.let { uuid ->
            viewModel.getDetailMovie(uuid)
        }
    }

    private fun observers() {

        viewModel.state.observe(viewLifecycleOwner) {
            updateUI(it)
        }

    }

    private fun updateUI(state: DetailState?) {
        state?.isLoading?.let { condition ->
            if (condition) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        }

        state?.error?.let { messageError ->
            requireContext().toast(messageError)
        }

        state?.detailMovie?.let { detailMovie ->
            setUIDetailMovie(detailMovie)
        }
    }

    private fun setUIDetailMovie(detailMovie: DetailMovie) = with(binding){
        tvTitle.text = detailMovie.originalTitle
        tvDate.text = detailMovie.releaseDate
        tvLanguage.text = detailMovie.originalLanguage
        tvRate.text = detailMovie.popularity.toString()
        tvDescription.text = detailMovie.overview
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+detailMovie.posterPath).into(ivPoster)
    }


}