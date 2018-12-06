package thiengo.com.br.thiengosflix.tracker.item

import androidx.recyclerview.selection.ItemKeyProvider
import thiengo.com.br.thiengosflix.domain.Movie

/*
 * Classe responsável por manter as chaves estáveis dos itens
 * selecionados. É com essas chaves estáveis que é possível
 * em uma reconstrução de atividade, por exemplo, manter
 * os itens anteriormente selecionados ainda com o mesmo
 * estado. É importante manter chaves estáveis que sejam
 * únicas para cada item. São possíves três tipos de
 * chaves: Parcelable (e subclasses); Long; e String.
 * */
class MovieKeyProvider( val movies: List<Movie> )
    : ItemKeyProvider<Long>( ItemKeyProvider.SCOPE_MAPPED ) {

    /*
     * Retornar a chave estável do item na posição informada.
     * Em nosso caso a chave estável é o ID do filme, pois
     * independente das atualizações necessárias no objeto
     * o identificador único dele se manterá o mesmo.
     * */
    override fun getKey( position: Int )
        = movies[ position ].id.toLong()

    /*
     * Retornar a posição do item de acordo com a chave
     * estável informada como parâmetro.
     * */
    override fun getPosition( key: Long )
        = movies
            .indexOf(
                movies.filter {
                    movie -> movie.id.toLong() == key
                }
                .single()
            )
}