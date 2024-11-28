package ulul.azmi.a.latala.cupcake.data

/**
 * Data class yang mewakili state UI saat ini dalam konteks pemesanan cupcake.
 * Berisi informasi tentang jumlah cupcake, rasa, tanggal pengambilan, dan harga.
 */
data class OrderUiState(
    /** Jumlah cupcake yang dipilih (misalnya 1, 6, 12) */
    val quantity: Int = 0,

    /** Rasa cupcake dalam pesanan (misalnya "Coklat", "Vanilla", dll..) */
    val flavor: String = "",

    /** Tanggal yang dipilih untuk pengambilan pesanan (misalnya "1 Jan") */
    val date: String = "",

    /** Harga total untuk pesanan */
    val price: String = "",

    /** Daftar tanggal pengambilan yang tersedia untuk pesanan */
    val pickupOptions: List<String> = listOf() // Menyimpan pilihan tanggal pengambilan
)

