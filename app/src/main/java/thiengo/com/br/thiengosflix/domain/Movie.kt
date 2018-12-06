package thiengo.com.br.thiengosflix.domain

class Movie(
    val id: Int,
    val title: String,
    val urlBanner: String,
    val directors: String,
    val rating: Rating,
    val category: String,
    val resume: String ) {
}