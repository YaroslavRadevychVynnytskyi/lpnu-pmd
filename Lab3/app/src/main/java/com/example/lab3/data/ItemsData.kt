package com.example.lab3.data

/**
 * ItemsData - singleton class (only one instance) can be the example of shared data source
 * You can get the data from this ItemsData object from any place in the code.
 */
object ItemsData {
    // Static list with the items of Item
    val itemsList = listOf(
        Item(1, "Vysoky Zamok", "Vysoky Zamok in Lviv is a picturesque park spread over Castle Hill."
                + " It's area is 36 hectars and it is located in the very center of Lviv"),
        Item(2, "Khortytsia", "Khortytsia is the largest island on the Dnipro Riverm. The island played"
                + " an important role in the history of Ukraine, especially in the history of the Zaporozhian Cossaks"),
        Item(3, "Khreshchatyk", "Khreshchatyk  is the main street of Kyiv, the capital of Ukraine. "
                + "The street is 1.2 kilometers long, and runs in a northeast-southwest direction."),
    )
}

// Item class
class Item(val id: Int, val title: String, val description: String)
