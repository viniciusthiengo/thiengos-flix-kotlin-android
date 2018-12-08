package thiengo.com.br.thiengosflix

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.selection.SelectionTracker
import com.squareup.picasso.Picasso
import thiengo.com.br.thiengosflix.domain.Movie
import thiengo.com.br.thiengosflix.domain.Rating
import thiengo.com.br.thiengosflix.tracker.MovieDetails

class MoviesAdapter( val movies: List<Movie> ):
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    lateinit var selectionTracker : SelectionTracker<Long>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layout = LayoutInflater
            .from( parent.context )
            .inflate(
                R.layout.movie_item,
                parent,
                false
            )

        return ViewHolder( layout )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int )
    {

        holder.setModel( movies[ position ], position )
    }


    inner class ViewHolder( itemView: View):
        RecyclerView.ViewHolder( itemView ) {

        val ivBanner : ImageView

        val tvTitle : TextView
        val tvDirectors : TextView

        val ivStar_01 : ImageView
        val ivStar_02 : ImageView
        val ivStar_03 : ImageView
        val ivStar_04 : ImageView
        val ivStar_05 : ImageView

        val tvRatingAmount: TextView
        val tvCategory: TextView
        val tvResume: TextView

        val movieDetails: MovieDetails

        init{
            ivBanner = itemView.findViewById( R.id.iv_banner )

            tvTitle = itemView.findViewById( R.id.tv_title )
            tvDirectors = itemView.findViewById( R.id.tv_directors )

            ivStar_01 = itemView.findViewById( R.id.iv_star_01 )
            ivStar_02 = itemView.findViewById( R.id.iv_star_02 )
            ivStar_03 = itemView.findViewById( R.id.iv_star_03 )
            ivStar_04 = itemView.findViewById( R.id.iv_star_04 )
            ivStar_05 = itemView.findViewById( R.id.iv_star_05 )

            tvRatingAmount = itemView.findViewById( R.id.tv_rating_amount )
            tvCategory = itemView.findViewById( R.id.tv_category )
            tvResume = itemView.findViewById( R.id.tv_resume )

            movieDetails = MovieDetails()
        }

        fun setModel( movie: Movie, position: Int ){

            ivBanner.contentDescription = movie.title
            Picasso
                .get()
                .load( movie.urlBanner )
                .placeholder( R.drawable.ic_load_image )
                .error( R.drawable.ic_image_broken )
                .into( ivBanner )

            tvTitle.text = movie.title
            tvDirectors.text = movie.directors

            setRating( movie.rating )

            tvCategory.text = movie.category
            tvResume.text = movie.resume

            /*
             * Trabalhando a seleção via elementos da biblioteca
             * SelectionTracker.
             *
             * onBindViewHolder() é invocado também quando há a
             * seleção / desseleção de um item, já com o status
             * alterado.
             * */
            movieDetails.movie = movie
            movieDetails.adapterPosition = position

            if( selectionTracker.isSelected( movieDetails.getSelectionKey() ) ){
                itemView.isActivated = true
                setUIItemSelected()
            }
            else{
                itemView.isActivated = false
                setUIItemNotSelected()
            }
        }

        private fun setRating( rating: Rating ){
            tvRatingAmount.text = String.format( "(%d)", rating.amount )

            setRatingStar( rating.stars, ivStar_01, 1 )
            setRatingStar( rating.stars, ivStar_02, 2 )
            setRatingStar( rating.stars, ivStar_03, 3 )
            setRatingStar( rating.stars, ivStar_04, 4 )
            setRatingStar( rating.stars, ivStar_05, 5 )
        }

        /*
         * Método responsável por colocar a imagem de estrela
         * correta em cada ImageView de estrela de avalíação.
         * */
        private fun setRatingStar( rating: Float, star: ImageView, position: Int ){

            val idimage = if( rating >= position.toFloat() )
                R.drawable.ic_star_full /* Estrela preenchida. */
            else if( rating < position.toFloat() && Math.ceil( rating.toDouble() ) == position.toDouble() )
                R.drawable.ic_star_half /* Estrela com metada preenchida. */
            else
                R.drawable.ic_star_empty /* Estrela vazia. */

            star.setImageResource( idimage )
        }

        private fun setUIItemSelected(){
            itemView.setBackgroundResource( R.drawable.item_selected_background )

            tvCategory.setTextColor( Color.YELLOW )

            val textColor = ContextCompat
                .getColor(
                    itemView.context,
                    R.color.colorItemSelectedText
                )

            tvDirectors.setTextColor( textColor )
            tvRatingAmount.setTextColor( textColor )
            tvResume.setTextColor( textColor )
        }

        private fun setUIItemNotSelected(){
            itemView.setBackgroundResource( R.drawable.item_background )

            tvCategory.setTextColor(
                ContextCompat
                    .getColor(
                        itemView.context,
                        R.color.colorItemCategory
                    )
            )

            val textColor = ContextCompat
                .getColor(
                    itemView.context,
                    R.color.colorItemText
                )

            tvDirectors.setTextColor( textColor )
            tvRatingAmount.setTextColor( textColor )
            tvResume.setTextColor( textColor )
        }
    }
}