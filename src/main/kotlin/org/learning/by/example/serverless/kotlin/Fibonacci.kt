package org.learning.by.example.serverless.kotlin

import com.google.gson.JsonArray
import com.google.gson.JsonObject

fun fibonacci(numbers: Long): Array<Long> {
  if (numbers == 0L) return arrayOf(0L)
  if (numbers == 1L) return arrayOf(1L)

  var previous = 1L
  var current = 1L
  var temp: Long

  return arrayOf(1L, 1L) + (1..(numbers - 2)).map {
    temp = current + previous
    previous = current
    current = temp
    current
  }.toList().toTypedArray()
}

fun JsonObject.paramOrElse(name: String, elseValue: Long): Long = if (this.has(name))
  this.getAsJsonPrimitive(name).asLong else elseValue

fun Long.throughFunction(operation: (Long) -> Array<Long>): JsonObject {
  val result = JsonObject()
  val elements = JsonArray()
  operation(this).forEach(elements::add)
  result.add("result", elements)
  return result
}

fun main(args: JsonObject) = args.paramOrElse("numbers", 10L).throughFunction(::fibonacci)
