package com.example.countriesapp

data class Country (
    val name: String,
    val capital: String,
    val population: Long,
    val area: Long,
    val languages: List<Language>,
    val flag: String
        )

// если список параметров
data class Language(
    val name: String
    )

data class Name(
    val name: String
)
