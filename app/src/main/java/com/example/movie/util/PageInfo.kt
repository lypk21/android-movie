package com.example.movie.util

class PageInfo {
    var page: Int = 1
    var pageSize: Int? = 10
    
    fun nextPage() {
        page++
    }

    fun reset() {
        page = 1
    }

    val isFirstPage: Boolean
        get() = page == 1

    fun prePage() {
        page--
    }

    fun setPageSize(pageSize: Int) {
        this.pageSize = pageSize
    }

}