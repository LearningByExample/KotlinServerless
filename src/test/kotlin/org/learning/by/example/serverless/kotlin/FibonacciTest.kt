package org.learning.by.example.serverless.kotlin

import com.google.gson.JsonObject
import org.amshove.kluent.`should equal to`
import org.junit.Test

class FibonacciTest {

  @Test
  fun `fibonacci 0 should work`() {
    val numbers = fibonacci(0)

    numbers.size `should equal to` 1

    numbers[0] `should equal to` 0
  }

  @Test
  fun `fibonacci 1 should work`() {
    val numbers = fibonacci(1)

    numbers.size `should equal to` 1

    numbers[0] `should equal to` 1
  }

  @Test
  fun `fibonacci with a parameter should work`() {
    val numbers = fibonacci(5)

    numbers.size `should equal to` 5

    numbers[0] `should equal to` 1
    numbers[1] `should equal to` 1
    numbers[2] `should equal to` 2
    numbers[3] `should equal to` 3
    numbers[4] `should equal to` 5
  }

  @Test
  fun `paramsOrElse should work with a parameter`() {
    val request = JsonObject()
    request.addProperty("numbers", 5)

    request.paramOrElse("numbers", 100) `should equal to` 5
  }

  @Test
  fun `paramsOrElse should work without a parameter`() {
    val request = JsonObject()

    request.paramOrElse("numbers", 100) `should equal to` 100
  }

  @Test
  fun `throughFunction should work`() {
    val n: Long = 5
    val response = n.throughFunction {
      (1..it).toList().toTypedArray()
    }
    response.has("result") `should equal to` true
    val result = response.getAsJsonArray("result")

    result.size() `should equal to` 5

    result[0].asLong `should equal to` 1
    result[1].asLong `should equal to` 2
    result[2].asLong `should equal to` 3
    result[3].asLong `should equal to` 4
    result[4].asLong `should equal to` 5
  }

  @Test
  fun `main should work with a parameter`() {
    val request = JsonObject()
    request.addProperty("numbers", 5)

    val response = main(request)

    response.has("result") `should equal to` true
    val result = response.getAsJsonArray("result")

    result.size() `should equal to` 5

    result[0].asLong `should equal to` 1
    result[1].asLong `should equal to` 1
    result[2].asLong `should equal to` 2
    result[3].asLong `should equal to` 3
    result[4].asLong `should equal to` 5

  }

  @Test
  fun `main should work without a parameter`() {
    val request = JsonObject()
    val response = main(request)

    val result = response.getAsJsonArray("result")

    result.size() `should equal to` 10

    result[0].asLong `should equal to` 1
    result[1].asLong `should equal to` 1
    result[2].asLong `should equal to` 2
    result[3].asLong `should equal to` 3
    result[4].asLong `should equal to` 5
    result[5].asLong `should equal to` 8
    result[6].asLong `should equal to` 13
    result[7].asLong `should equal to` 21
    result[8].asLong `should equal to` 34
    result[9].asLong `should equal to` 55
  }
}
