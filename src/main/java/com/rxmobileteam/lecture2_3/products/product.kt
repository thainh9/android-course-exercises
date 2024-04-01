package com.rxmobileteam.lecture2_3.products

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

enum class ProductCategory {
  LAPTOP,
  PHONE,
  HEADPHONES,
  SMART_WATCH,
  CAMERA,
}

data class Product(
  val id: String,
  val name: String,
  val price: Double,
  val category: ProductCategory,
  val favoriteCount: Int,
)

data class Order(
  val id: String,
  val products: List<Product>,
  val isDelivered: Boolean,
)

// TODO: Return a list of Product, sorted in the ascending by price. if prices are equal, sorted by favoriteCount descending
fun List<Product>.sortedByPriceAscendingThenByFavoriteCountDescending(): List<Product> {
  return this.sortedWith(compareBy<Product>{it.price}.thenByDescending { it.favoriteCount })
}

// TODO: Return a set of Products in the orders (The order doesn't matter).
fun List<Order>.getProductsSet(): Set<Product> {
  val setProduct = HashSet<Product>()
  this.forEach {
    setProduct.addAll(it.products.toSet())
  }
  return setProduct
}

// TODO: Return a list of Products in the orders, duplicates are allowed.
fun List<Order>.getProductsList(): List<Product> {
  val listProduct = ArrayList<Product>()
  this.forEach {
    listProduct.addAll(it.products.sortedBy { it.price })
  }
  return listProduct
}

// TODO: Return a list of delivered orders
fun List<Order>.getDeliveredOrders(): List<Order> {
  return this.filter { it.isDelivered }
}

// TODO: Return a list of products in the delivered orders
fun List<Order>.getDeliveredProductsList(): List<Product> {
  val listProduct = ArrayList<Product>()
  this.filter { it.isDelivered }.forEach { listProduct.addAll(it.products )}
  return listProduct
}

// TODO: Partition the orders into two lists: "delivered" and "not delivered"
fun List<Order>.partitionDeliveredAndNotDelivered(): Pair<List<Order>, List<Order>> {
  return Pair<List<Order>, List<Order>>(this.filter { it.isDelivered }, this.filter { !it.isDelivered })
}

// TODO: Return a map of product to count of this product in the orders
// eg. [Product1 -> 2, Product2 -> 1, Product3 -> 3]
fun List<Order>.countOfEachProduct(): Map<Product, Int> {
  val map = HashMap<Product, Int>()
  val list = this.getProductsList()
  for (element in list) {
    map[element] = map.getOrDefault(element, 0) + 1
  }
  return map
}

// TODO: Return the sum of product prices in the order
fun Order.sumProductPrice(): Double {
  var sum = 0.0
  this.products.forEach {
    sum += it.price
  }
  return sum
}

// TODO: Return the product with the maximum price in the order
fun Order.getMaxPriceProduct(): Product {
  return this.products.maxWith(compareBy { it.price })
}

// TODO: Return the product with the min price in the order
fun Order.getMinPriceProduct(): Product {
  return this.products.minWith(compareBy { it.price })
}

val product = Product(
  id = UUID.randomUUID().toString(),
  name = "Sandy Short Special Edition",
  price = 2.3,
  category = ProductCategory.LAPTOP,
  favoriteCount = 1,
)

val productList = listOf(
  product,
  Product(
    id = UUID.randomUUID().toString(),
    name = "Stacie Riddle",
    price = 6.7,
    category = ProductCategory.PHONE,
    favoriteCount = 2,
  ),
  Product(
    id = UUID.randomUUID().toString(),
    name = "Stacie Riddle",
    price = 6.7,
    category = ProductCategory.LAPTOP,
    favoriteCount = 3,
  ),
  Product(
    id = UUID.randomUUID().toString(),
    name = "Stacie Riddle",
    price = 6.7,
    category = ProductCategory.SMART_WATCH,
    favoriteCount = 4,
  ),
  Product(
    id = UUID.randomUUID().toString(),
    name = "Stacie Riddle",
    price = 1.0,
    category = ProductCategory.HEADPHONES,
    favoriteCount = 5,
  ),
  Product(
    id = UUID.randomUUID().toString(),
    name = "Stacie Riddle",
    price = 10.0,
    category = ProductCategory.CAMERA,
    favoriteCount = 0,
  ),
)

val orderList = listOf(
  Order(
    id = UUID.randomUUID().toString(),
    products = listOf(
      product,
      Product(
        id = UUID.randomUUID().toString(),
        name = "Stacie Riddle",
        price = 6.7,
        category = ProductCategory.PHONE,
        favoriteCount = 2,
      ),
    ),
    isDelivered = true,
  ),
  Order(
    id = UUID.randomUUID().toString(),
    products = listOf(
      product,
      Product(
        id = UUID.randomUUID().toString(),
        name = "Stacie Riddle",
        price = 100.0,
        category = ProductCategory.SMART_WATCH,
        favoriteCount = 3,
      ),
    ),
    isDelivered = false,
  ),
  Order(
    id = UUID.randomUUID().toString(),
    products = listOf(
      product,
      Product(
        id = UUID.randomUUID().toString(),
        name = "Stacie Riddle",
        price = 6.7,
        category = ProductCategory.PHONE,
        favoriteCount = 2,
      ),
      Product(
        id = UUID.randomUUID().toString(),
        name = "Efrain Hawkins",
        price = 100.0,
        category = ProductCategory.CAMERA,
        favoriteCount = 5235,
      ),
    ),
    isDelivered = true,
  ),
)

fun main() {
  //region sortedByPriceAscendingThenByFavoriteCountDescending
  println("sortedByPriceAscendingThenByFavoriteCountDescending")
  println(productList.sortedByPriceAscendingThenByFavoriteCountDescending())
  //endregion

  //region getProductsSet
  println("getProductsSet")
  println(orderList.getProductsSet())
  //endregion

  //region getProductsList
  println("getProductsList")
  println(orderList.getProductsList())
  //endregion

  //region getDeliveredOrders
  println("getDeliveredOrders")
  println(orderList.getDeliveredOrders())
  //endregion getDeliveredProductsList

  //region getDeliveredProductsList
  println("getDeliveredProductsList")
  println(orderList.getDeliveredProductsList())
  //endregion

  //region partitionDeliveredAndNotDelivered
  println("partitionDeliveredAndNotDelivered")
  println(orderList.partitionDeliveredAndNotDelivered())
  //endregion

  //region countOfEachProduct
  println("countOfEachProduct")
  println(orderList.countOfEachProduct())
  //endregion

  //region sumProductPrice
  println("sumProductPrice")
  println(orderList[0].sumProductPrice())
  //endregion

  //region getMaxPriceProduct, getMinPriceProduct
  println("getMaxPriceProduct, getMinPriceProduct")
  println(orderList[0].getMaxPriceProduct())
  println(orderList[0].getMinPriceProduct())
  //endregion
}
