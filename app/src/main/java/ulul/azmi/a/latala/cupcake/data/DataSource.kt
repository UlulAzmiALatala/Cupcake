package ulul.azmi.a.latala.cupcake.data

import ulul.azmi.a.latala.cupcake.R

/**
 * Object singleton yang menyediakan sumber data untuk aplikasi cupcake
 * Menyediakan daftar rasa cupcake dan pilihan kuantitas.
 */
object DataSource {

    // Daftar rasa cupcake yang tersedia, mengacu pada string resource
    val flavors = listOf(
        R.string.vanilla, // Rasa vanilla
        R.string.chocolate, // Rasa coklat
        R.string.red_velvet, // Rasa red velvet
        R.string.salted_caramel, // Rasa salted caramel
        R.string.coffee // Rasa kopi
    )

    // Daftar pilihan kuantitas cupcake, disertai dengan label dan jumlah cupcake
    val quantityOptions = listOf(
        Pair(R.string.one_cupcake, 1), // Pilihan satu cupcake
        Pair(R.string.six_cupcakes, 6), // Pilihan enam cupcake
        Pair(R.string.twelve_cupcakes, 12) // Pilihan dua belas cupcake
    )
}
