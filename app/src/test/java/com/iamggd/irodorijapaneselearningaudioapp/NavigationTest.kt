package com.iamggd.irodorijapaneselearningaudioapp

import org.junit.Assert.assertEquals
import org.junit.Test

class NavigationTest {
    @Test
    fun homeRoute_isStable() {
        assertEquals("home", Screen.Home.route)
    }

    @Test
    fun bookContentRoute_isStable() {
        assertEquals("book_content/{bookId}", Screen.BookContent.route)
    }

    @Test
    fun bookContentRoute_buildsWithBookId() {
        assertEquals("book_content/A1", Screen.BookContent.createRoute("A1"))
    }
}
