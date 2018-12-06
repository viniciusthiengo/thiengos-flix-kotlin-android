package thiengo.com.br.thiengosflix.tracker.item

import androidx.recyclerview.selection.SelectionTracker

/*
 * SelectionTracker.SelectionPredicate é utilizada para definir
 * quais itens poderão ser selecionados e quantos.
 * */
class MoviePredicate: SelectionTracker.SelectionPredicate<Long>() {

    /*
     * Retorne true se puder ter múltipla seleção e false para
     * somente uma seleção.
     * */
    override fun canSelectMultiple()
        = true

    /*
     * Retorne true se o item referente a key puder ser definido
     * como nextState.
     * */
    override fun canSetStateForKey( key: Long, nextStatus: Boolean )
        = if( key == 7758L )
            false
        else
            true

    /*
     * Retorne true se o item referente a position puder ser definido
     * como nextState.
     * */
    override fun canSetStateAtPosition(p0: Int, p1: Boolean): Boolean {
        return true
    }
}