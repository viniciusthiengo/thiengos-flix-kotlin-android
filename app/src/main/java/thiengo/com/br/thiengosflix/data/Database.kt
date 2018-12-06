package thiengo.com.br.thiengosflix.data

import thiengo.com.br.thiengosflix.domain.Movie
import thiengo.com.br.thiengosflix.domain.Rating

class Database {
    companion object {
        fun getMovies()
            = mutableListOf(
                Movie(
                    54,
                    "Capitã Marvel",
                    "https://www.cineriado.com.br/wp-content/uploads/2018/09/captain-marvel-1134387-1280x0.jpeg",
                    "Anna Boden, Ryan Fleck",
                    Rating(4.5F, 177),
                    "Fantasia / aventura",
                    "Captain Marvel é um futuro filme Norte americano " +
                                "de super-herói de 2019, baseado na personagem " +
                                "de mesmo nome da Marvel Comics, produzido pela " +
                                "Marvel Studios e distribuído pela Walt Disney " +
                                "Studios Motion Pictures, sendo o vigésimo " +
                                "primeiro filme do Universo Cinematográfico Marvel."
                ),
                Movie(
                    7758,
                    "Vingadores 4",
                    "https://observatoriodocinema.bol.uol.com.br/wp-content/uploads/2018/09/Vingadores-Guerra-Infinita.png",
                    "Anthony Russo, Joe Russo",
                    Rating(4F, 82),
                    "Fantasia / ficção científica",
                    "Avengers 4 [1] é um futuro filme estadunidense de " +
                                "super-herói de 2019, baseado na equipe Os " +
                                "Vingadores da Marvel Comics, produzido pela " +
                                "Marvel Studios e distribuído pela Walt Disney " +
                                "Studios Motion Pictures, sendo a sequência de " +
                                "Marvel's The Avengers (2012), Avengers: Age " +
                                "of Ultron (2015), e Avengers: Infinity War " +
                                "(2018), e o vigésimo segundo filme do Universo " +
                                "Cinematográfico Marvel."
                ),
                Movie(
                    163,
                    "Vidro",
                    "https://i.ytimg.com/vi/9zNINvh0eMc/maxresdefault.jpg",
                    "M. Night Shyamalan",
                    Rating(5F, 214),
                    "Fantasia / mistério",
                    "Glass é um futuro filme americano escrito, " +
                                "co-produzido e dirigido por M. Night Shyamalan.O " +
                                "filme destina-se a ser a terceira e última parte " +
                                "do que foi referido como a trilogia Eastrail 177, " +
                                "que inclui Unbreakable e Split."
                ),
                Movie(
                    5579,
                    "Homem-Aranha: De Volta ao Lar 2",
                    "https://i.ytimg.com/vi/_H5Vcqk0URM/maxresdefault.jpg",
                    "Jon Watts",
                    Rating(4.5F, 32),
                    "Fantasia / ficção científica",
                    "Spider-Man: Far From Home é um futuro filme " +
                                "estadunidense de ação, comédia, aventura e ficção " +
                                "científica dirigido por Jon Watts, e é escrito por " +
                                "Chris McKenna e Erik Sommers. É produzido pela " +
                                "Marvel Studios e Columbia Pictures e é distribuído " +
                                "pela Sony."
                )
            )
    }
}