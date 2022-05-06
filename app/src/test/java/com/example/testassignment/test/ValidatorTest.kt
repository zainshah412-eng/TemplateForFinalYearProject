package com.example.testassignment.test

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest {

    @Test
    fun whenInputIsValid() {
        val searchWord = "Some random keyword"
        val result = Validator.validateInput(searchWord)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputIsEmpty() {
        val searchWord = ""
        val result = Validator.validateInput(searchWord)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputIsInteger() {
        val searchWord = "12345"
        val result = Validator.validateInput(searchWord)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputIsExceedMaximumInputLimit() {
        val searchWord = "Some random keyword Some random keyword Some random keyword Some random keyword Some random keyword Some random keyword Some random keyword" +
                "Some random keyword Some random keyword Some random keyword Some random keyword Some random keyword Some random keyword Some random keyword Some random keyword"+
                "Some random keyword Some random keyword Some random keyword Some random keyword"
        val result = Validator.validateInput(searchWord)
        assertThat(result).isEqualTo(true)
    }


}