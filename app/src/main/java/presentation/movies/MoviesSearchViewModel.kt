package presentation.movies

import SingleLiveEvent
import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import util.Creator
import com.itproger.spr_15_clean_architecture_films.R
import domain.api.MoviesInteractor
import domain.models.Movie
import ui.movies.models.MoviesState

class MoviesSearchViewModel(
    private val moviesInteractor: MoviesInteractor //changed
    ) : ViewModel() { //changed

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L

//        fun getViewModelFactory(): ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                MoviesSearchViewModel(this[APPLICATION_KEY] as Application)
//            }
//        }
    }

  //   Creator.provideMoviesInteractor(getApplication<Application>())
    private val handler = Handler(Looper.getMainLooper())

    private val stateLiveData = MutableLiveData<MoviesState>()
    fun observeState(): LiveData<MoviesState> = stateLiveData

    private val showToast = SingleLiveEvent<String?>()
    fun observeToastState(): SingleLiveEvent<String?> = showToast

    private var latestSearchText: String? = null

    private val movies = ArrayList<Movie>() //No!

    private val searchRunnable = Runnable {
        val newSearchText = latestSearchText ?: ""
        searchRequest(newSearchText)
    }

    override fun onCleared() {
        handler.removeCallbacks(searchRunnable)
    }

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }
        this.latestSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
         renderState(MoviesState.Loading)

            moviesInteractor.findMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {

                        if (foundMovies != null) {
                            movies.addAll(foundMovies) //
                        }
                        when {
                            errorMessage != null -> {
                                renderState(
                                    MoviesState.Error
                                )
                                showToast.postValue(errorMessage)
                            }

                            movies.isEmpty() -> {
                                renderState(
                                    MoviesState.Empty
                                    )
                            }

                            else -> {
                                renderState(
                                    MoviesState.Content(
                                        movies = movies
                                    )
                                )
                            }
                        }
                }
            })
        }
    }

    private fun renderState(state: MoviesState) {
        stateLiveData.postValue(state)
    }
}