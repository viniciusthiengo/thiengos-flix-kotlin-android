package thiengo.com.br.thiengosflix


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import kotlinx.android.synthetic.main.fragment_favorites.*
import thiengo.com.br.thiengosflix.data.Database
import thiengo.com.br.thiengosflix.domain.Movie
import thiengo.com.br.thiengosflix.tracker.item.MovieKeyProvider
import thiengo.com.br.thiengosflix.tracker.item.MovieLookup
import thiengo.com.br.thiengosflix.tracker.item.MoviePredicate

class FavoritesFragment : Fragment() {

    companion object {
        const val KEY = "favorites-fragment"
        const val SELECTION_TRACKER_KEY = "selection-tracker-movie"
    }

    val movies = Database.getMovies()
    lateinit var selectionTracker: SelectionTracker<Long>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        /*
         * Para manter o objeto do fragmento em memória.
         * */
        retainInstance = true

        return inflater
            .inflate(
                R.layout.fragment_favorites,
                container,
                false
            )
    }


    override fun onActivityCreated( savedInstanceState: Bundle? ) {
        super.onActivityCreated( savedInstanceState )

        rv_movies.setHasFixedSize( true )

        val layoutManager = LinearLayoutManager( activity )
        rv_movies.layoutManager = layoutManager

        rv_movies.adapter = MoviesAdapter( movies )

        configSelectionTracker( savedInstanceState )
    }

    /*
     * Invoque este método de configuração do SelectionTracker
     * somente depois de ter já finalizada a configuração do
     * RecyclerView.
     * */
    private fun configSelectionTracker( savedInstanceState: Bundle? ){

        selectionTracker = SelectionTracker.Builder<Long>(
            SELECTION_TRACKER_KEY,
            rv_movies,
            MovieKeyProvider( movies ),
            MovieLookup( rv_movies ),
            StorageStrategy.createLongStorage()
        )
        .withSelectionPredicate( MoviePredicate() )
        .build()

        (rv_movies.adapter as MoviesAdapter).selectionTracker = selectionTracker

        /*
         * Parte da configuração para reter o estado da lista
         * marcada caso haja reconstrução de atividade / fragmento.
         * */
        if( savedInstanceState != null ){
            selectionTracker.onRestoreInstanceState( savedInstanceState )
        }
    }

    /*
     * Método responsável por remover itens selecionados,
     * incluindo suas chaves de seleção.
     * */
    fun removeItemsSelected(){

        /*
         * Cláusula de guarda para quando não houver nenhum
         * item selecionado nao prosseguir com a execução
         * do método.
         * */
        if( selectionTracker.selection.size() == 0 )
            return

        val moviesToRemove = mutableListOf<Movie>()

        for( key in selectionTracker.selection ){
            val movie = movies.filter{ m -> m.id.toLong() == key }.single()
            moviesToRemove.add( movie )
        }

        selectionTracker.clearSelection()
        movies.removeAll( moviesToRemove )
        (rv_movies.adapter as MoviesAdapter).notifyDataSetChanged()

        removeMessage( moviesToRemove.size )
    }

    private fun removeMessage( amountRemoved: Int ){
        val messageId = if( amountRemoved == 1 )
                R.string.favorites_removed_movie
            else
                R.string.favorites_removed_movies

        Toast
            .makeText(
                activity,
                getString( messageId ),
                Toast.LENGTH_SHORT
            )
            .show()
    }

    /*
     * Método responsável por conter o algoritmo que invoca
     * o código de atualização de título da atividade.
     * */
    override fun onResume() {
        super.onResume()
        (activity as MainActivity)
            .updateActivityTitle( getString( R.string.frag_favorites ) )
    }

    /*
     * Parte da configuração para reter o estado da lista
     * marcada caso haja reconstrução de atividade / fragmento.
     * */
    override fun onSaveInstanceState( outState: Bundle ) {
        super.onSaveInstanceState( outState )
        selectionTracker.onSaveInstanceState( outState )
    }
}
