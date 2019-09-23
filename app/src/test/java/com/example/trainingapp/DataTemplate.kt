package com.example.trainingapp

import com.example.trainingapp.models.DataModel
import com.example.trainingapp.models.RatingModel

//Ratings
var ratingBruceTemplate: List<RatingModel> = listOf(
    RatingModel(
        source =  "Internet Movie Database",
        value =  "6.7/10"
    ),
    RatingModel(
        source =  "Rotten Tomatoes",
        value =  "48%"
    ),
    RatingModel(
        source =  "Metacritic",
        value =  "46/100"
    )
)
var ratingAvengersTemplate: List<RatingModel> = listOf(
    RatingModel(
        source =  "Internet Movie Database",
        value =  "8.6/10"
    ),
    RatingModel(
        source =  "Rotten Tomatoes",
        value =  "94%"
    ),
    RatingModel(
        source =  "Metacritic",
        value =  "78/100"
    )
)

//Users
var movieTemplateEmpty: DataModel = DataModel()
var movieLstTemplateBruce: DataModel = DataModel(
    title = "Bruce Almighty",
    year = "2003",
    rated = "PG-13",
    released =  "23 May 2003",
    runtime =  "101 min",
    genre =  "Comedy, Drama, Fantasy",
    director =  "Tom Shadyac",
    writer =  "Steve Koren (story), Mark O'Keefe (story), Steve Koren (screenplay), Mark O'Keefe (screenplay), Steve Oedekerk (screenplay)",
    actors =  "Jim Carrey, Morgan Freeman, Jennifer Aniston, Philip Baker Hall",
    plot =  "A guy who complains about God too often is given almighty powers to teach him how difficult it is to run the world.",
    language =  "English, Spanish",
    country =  "USA",
    awards =  "7 wins & 8 nominations.",
    poster =  "https://m.media-amazon.com/images/M/MV5BNzMyZDhiZDUtYWUyMi00ZDQxLWE4NDQtMWFlMjI1YjVjMjZiXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
    ratings =  ratingBruceTemplate,
    metascore =  "46",
    imdbRating =  "6.7",
    imdbVotes =  "349,698",
    imdbID =  "tt0315327",
    type =  "movie",
    dVD =  "25 Nov 2003",
    boxOffice =  "$242,589,580",
    production =  "Universal Pictures",
    website =  "http://www.brucealmighty.com/",
    response =  "True"
)
var movieLstTemplateAvengers: DataModel = DataModel(
    title =  "Avengers: Endgame",
    year =  "2019",
    rated =  "PG-13",
    released =  "26 Apr 2019",
    runtime =  "181 min",
    genre =  "Action, Adventure, Sci-Fi",
    director =  "Anthony Russo, Joe Russo",
    writer =  "Christopher Markus (screenplay by), Stephen McFeely (screenplay by), Stan Lee (based on the Marvel comics by), Jack Kirby (based on the Marvel comics by), Jim Starlin (Thanos,  Gamora & Drax created by), Jack Kirby (Groot created by)",
    actors =  "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
    plot =  "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
    language =  "English, Japanese, Xhosa",
    country =  "USA",
    awards =  "N/A",
    poster =  "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_SX300.jpg",
    ratings =  ratingAvengersTemplate,
    metascore =  "78",
    imdbRating =  "8.6",
    imdbVotes =  "531,604",
    imdbID =  "tt4154796",
    type =  "movie",
    dVD =  "30 Jul 2019",
    boxOffice =  "N/A",
    production =  "Marvel Studios",
    website =  "N/A",
    response =  "True"
)
var movieTemplateBruce: DataModel = DataModel(
    title = "Bruce Almighty",
    year = "2003",
    imdbID =  "tt0315327",
    type =  "movie",
    poster =  "https://m.media-amazon.com/images/M/MV5BNzMyZDhiZDUtYWUyMi00ZDQxLWE4NDQtMWFlMjI1YjVjMjZiXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg"
)
var movieTemplateAvengers: DataModel = DataModel(
    title =  "Avengers: Endgame",
    year =  "2019",
    imdbID =  "tt4154796",
    type =  "movie",
    poster =  "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_SX300.jpg"
)

//Lists
var emptyList: List<DataModel> = emptyList()
var moviesLstDefaultTemplate: List<DataModel> = listOf(
    movieTemplateEmpty,
    movieTemplateBruce,
    movieTemplateAvengers
)