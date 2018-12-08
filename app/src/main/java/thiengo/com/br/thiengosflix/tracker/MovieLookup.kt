package thiengo.com.br.thiengosflix.tracker

import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import thiengo.com.br.thiengosflix.MoviesAdapter

/*
 * ItemDetailsLookup permite que a biblioteca de seleção acesse
 * informações sobre os itens do RecyclerView que receberam um
 * MotionEvent. Ele é efetivamente uma factory para instâncias
 * de ItemDetails que são submetidas a backup (ou extraídas de)
 * de uma ocorrência de RecyclerView.ViewHolder.
 *
 * Com a nossa DetailsLookup nós estamos provendo à biblioteca
 * de seleção o acesso a qualquer item do RecyclerView.
 * */
class MovieLookup( val rvMovies: RecyclerView )
    : ItemDetailsLookup<Long>() {

    override fun getItemDetails( event: MotionEvent ): ItemDetails<Long>? {

        val view = rvMovies.findChildViewUnder( event.x, event.y )

        if( view != null ){
            val holder = rvMovies.getChildViewHolder( view )

            return (holder as MoviesAdapter.ViewHolder).movieDetails
        }

        return null
    }
}