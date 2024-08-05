package domain.impl

import domain.api.MoviesInteractor
import domain.api.MoviesRepository
import util.Resource
import java.util.concurrent.Executors

class MoviesInteractorImpl (private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun findMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        executor.execute {
            when (val resource = repository.findMovies(expression)) {
                is Resource.Success -> {
                    consumer.consume(resource.data, null)
                }

                is Resource.Error -> {
                    consumer.consume(null, resource.message)
                }
            }
        }
    }
}

//class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {
//
//    override fun findMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
//        val t = Thread {
//            consumer.consume(repository.findMovies(expression))
//        }
//        t.start()
//    }
//}