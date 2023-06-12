package com.trainingandroid.mobiedbapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
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
    private val adapterUpcoming: MovieAdapter by lazy {
        MovieAdapter() { movies ->
            val id = movies.id
            val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
            Navigation.findNavController(binding.root).navigate(directions)
        }
    }
    private val adapterPopulate: MovieAdapter by lazy {
        MovieAdapter() { movies ->
            val id = movies.id
            val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
            Navigation.findNavController(binding.root).navigate(directions)
        }
    }

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

        setUpcomingMoviesView()

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
        when (state) {
            is HomeState.PopulateMoviesState.Error -> requireContext().toast(state.message)
            is HomeState.PopulateMoviesState.ShowLoading -> {
                if (state.isLoading) binding.progressBarPopulate.visibility = View.VISIBLE
                else binding.progressBarPopulate.visibility = View.GONE
            }
            is HomeState.PopulateMoviesState.ShowPopulateMovies -> adapterPopulate.updateList(
                state.populateMovies
            )
        }
    }

    private fun updateUI(state: HomeState.UpComingMoviesState) {
        when (state) {
            is HomeState.UpComingMoviesState.Error -> requireContext().toast(state.message)
            is HomeState.UpComingMoviesState.ShowLoading -> {
                if (state.isLoading) binding.progressBar.visibility = View.VISIBLE
                else binding.progressBar.visibility = View.GONE
            }
            is HomeState.UpComingMoviesState.ShowUpcomingMovies -> adapterUpcoming.updateList(
                state.upComingMovies
            )
        }
    }

    private fun setUpcomingMoviesView() {
        binding.rvUpcomingReleases.run {
            val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.HORIZONTAL)
            ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
                ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
            //val dividerItemDecoration = DividerItemDecoration(context,RecyclerView.HORIZONTAL)

            addItemDecoration(dividerItemDecoration)
            setHasFixedSize(false)
            adapter = adapterUpcoming
            adapterUpcoming.itemCount
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
