## Kotlin Serverless
This is a small example illustrating [this blog post](https://www.juan-medina.com/2017/07/21/kotlin-serverless/) 
about doing Serverless functions in Kotlin.

First I recommend to read this [article](https://martinfowler.com/articles/serverless.html) by [Mike Roberts](https://twitter.com/mikebroberts) from Martin Fowler website to get a bit more understanding on what is a Serverless architecture.

Them we should take a loot to the [System Overview](https://github.com/apache/incubator-openwhisk/blob/master/docs/about.md) of [Apache Openwhisk](http://openwhisk.incubator.apache.org/).
 
You need to have OpenWhisk up and running, them fist build this function:

```shell
$ mvnw package
```

Now you could add an action to OpenWhisk with:

```shell
$ cd target
$ wsk action create Fibonacci KotlinServerless-1.0-SNAPSHOT-jar-with-dependencies.jar --main \
  org.learning.by.example.serverless.kotlin.FibonacciKt
```

To test the new action just run:

```shell
$ wsk action invoke --result Fibonacci --param numbers 5 
```

This will output something like:

```json
{
  "result": [
      1,
      1,
      2,
      3,
      5
  ]
}
```
