package com.djt.githubrepos


data class trendinglist(
    val rank: Int,
    val username: String,
    val reponame: String,
    val description: String,
    val language: String,
    val totalStars: Int,
    val builtby: String
    ) {
}


