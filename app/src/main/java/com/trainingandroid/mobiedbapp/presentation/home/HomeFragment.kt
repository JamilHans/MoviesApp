package com.trainingandroid.mobiedbapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.trainingandroid.mobiedbapp.R
import com.trainingandroid.mobiedbapp.databinding.FragmentHomeBinding
import com.trainingandroid.mobiedbapp.presentation.adapters.MovieAdapter
import com.trainingandroid.mobiedbapp.presentation.common.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapterUpcoming: MovieAdapter
    private lateinit var adapterPopulate: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setUpAdapter()
        observers()

    }

    private fun setUpAdapter() {
        adapterUpcoming = MovieAdapter() { movies ->
            val id = movies.id
            val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
            Navigation.findNavController(binding.root).navigate(directions)
        }
        setUpcomingMoviesView()
        adapterPopulate = MovieAdapter() { movies ->
            val id = movies.id
            val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
            Navigation.findNavController(binding.root).navigate(directions)
        }
        setPopulateMoviesView()
    }

    private fun observers() {
        viewModel.stateUpcomingMovie.observe(viewLifecycleOwner) {
            updateUI(it)
        }
        viewModel.statePopulateMovieL.observe(viewLifecycleOwner) {
            updatePopulate(it)
        }

    }

    private fun updatePopulate(state: HomeState.PopulateMoviesState) {
        state.isLoading.let { condition ->
            if (condition) binding.progressBarPopulate.visibility = View.VISIBLE
            else binding.progressBarPopulate.visibility = View.GONE
        }
        state.error?.let { messageError ->
            requireContext().toast(messageError)
        }
        state.populateMovies?.let { moviesList ->
            adapterPopulate.updateList(moviesList)
        }
    }

    private fun updateUI(state: HomeState.UpComingMoviesState) {
        state.isLoading.let { condition ->
            if (condition) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        }
        state.error?.let { messageError ->
            requireContext().toast(messageError)
        }
        state.upComingMovies?.let { moviesList ->
            adapterUpcoming.updateList(moviesList)
        }
    }

    private fun setUpcomingMoviesView() {
        binding.rvUpcomingReleases.run {
            setHasFixedSize(false)
            adapter = adapterUpcoming
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val linearLayoutManager =
                        layoutManager as androidx.recyclerview.widget.LinearLayoutManager
                    if (linearLayoutManager.itemCount == (linearLayoutManager.findLastVisibleItemPosition() + 1) &&
                        viewModel.pageNowUpcomingMovies <= viewModel.pageTotalUpcomingMovies
                    ) {
                        viewModel.pageNowUpcomingMovies++
                        viewModel.getUpcomingMovies(viewModel.pageNowUpcomingMovies)
                    }
                }
            })
        }
    }

    private fun setPopulateMoviesView() {
        binding.rvPopulateMovies.run {
            setHasFixedSize(false)
            adapter = adapterPopulate
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val linearLayoutManager =
                        layoutManager as androidx.recyclerview.widget.LinearLayoutManager
                    if (linearLayoutManager.itemCount == (linearLayoutManager.findLastVisibleItemPosition() + 1) &&
                        viewModel.pageNowPopulateMovies <= viewModel.pageTotalPopulateMovies
                    ) {
                        viewModel.pageNowPopulateMovies++
                        viewModel.getPopulateMovies(viewModel.pageNowPopulateMovies)
                    }
                }
            })
        }
    }

}
