package ulul.azmi.a.latala.cupcake.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ulul.azmi.a.latala.cupcake.R

/**
 * Composable untuk menampilkan harga subtotal yang diformat.
 * [subtotal] adalah harga yang akan ditampilkan, dan [modifier] memungkinkan penyesuaian tampilan.
 */
@Composable
fun FormattedPriceLabel(subtotal: String, modifier: Modifier = Modifier) {
    // Menampilkan teks harga dengan format yang sesuai dari resource string
    Text(
        text = stringResource(R.string.subtotal_price, subtotal), // Menggunakan string resource untuk format harga
        modifier = modifier, // Menambahkan modifier untuk pengaturan tampilan
        style = MaterialTheme.typography.headlineSmall // Menetapkan gaya teks sesuai tema material
    )
}
