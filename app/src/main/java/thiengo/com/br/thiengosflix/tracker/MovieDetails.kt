package thiengo.com.br.thiengosflix.tracker

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import thiengo.com.br.thiengosflix.domain.Movie


/*
 * Uma implementação de ItemDetails fornece à biblioteca de seleção
 * acesso a informações sobre um item RecyclerView específico. Esta
 * classe é um componente chave no controle dos comportamentos da
 * biblioteca de seleção no contexto de uma atividade específica.
 * */
class MovieDetails(

    var movie: Movie? = null,
    var adapterPosition: Int = -1 ) : ItemDetailsLookup.ItemDetails<Long>() {

    /*
     * Retorna a entidade (objeto) que é a chave estável de seleção
     * do item.
     * */
    override fun getSelectionKey()
            = movie!!.id.toLong()

    /*
     * Retorna a posição do item no adaptador do RecyclerView
     * (ViewHolder.getAdapterPosition).
     * */
    override fun getPosition()
            = adapterPosition

    /*
     * Retorne "true" se o item tiver uma chave estável de seleção.
     * */
    override fun inSelectionHotspot( e: MotionEvent )
            = true
}