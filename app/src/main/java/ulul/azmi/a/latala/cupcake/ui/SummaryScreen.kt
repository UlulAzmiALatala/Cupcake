package ulul.azmi.a.latala.cupcake.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import ulul.azmi.a.latala.cupcake.R
import ulul.azmi.a.latala.cupcake.data.OrderUiState
import ulul.azmi.a.latala.cupcake.ui.components.FormattedPriceLabel
import ulul.azmi.a.latala.cupcake.ui.theme.CupcakeTheme

/**
 * Composable untuk menampilkan ringkasan pemesanan cupcake, termasuk opsi untuk membatalkan atau mengirim pemesanan.
 * [orderUiState] untuk menampilkan status pemesanan, [onCancelButtonClicked] untuk membatalkan pesanan,
 * dan [onSendButtonClicked] untuk mengirimkan informasi pesanan.
 */
@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState, // Status pemesanan yang mencakup jumlah, rasa, tanggal, dan harga
    onCancelButtonClicked: () -> Unit, // Lambda untuk menangani pembatalan pesanan
    onSendButtonClicked: (String, String) -> Unit, // Lambda untuk mengirimkan pesanan (subjek dan ringkasan)
    modifier: Modifier = Modifier // Modifier opsional untuk pengaturan tampilan
) {
    // Mengakses sumber daya aplikasi untuk menampilkan teks dan format yang sesuai
    val resources = LocalContext.current.resources

    // Mengambil string yang diformat berdasarkan jumlah cupcake yang dipilih
    val numberOfCupcakes = resources.getQuantityString(
        R.plurals.cupcakes,
        orderUiState.quantity, // Jumlah cupcake yang dipilih
        orderUiState.quantity // Jumlah cupcake yang dipilih sebagai parameter
    )
    // Membuat dan memformat string ringkasan pesanan dengan parameter yang sesuai
    val orderSummary = stringResource(
        R.string.order_details,
        numberOfCupcakes,
        orderUiState.flavor,
        orderUiState.date,
        orderUiState.quantity
    )
    // Mendapatkan string untuk label pesanan baru
    val newOrder = stringResource(R.string.new_cupcake_order)

    // Membuat daftar item ringkasan pesanan untuk ditampilkan
    val items = listOf(
        // Ringkasan 1: menampilkan jumlah cupcake yang dipilih
        Pair(stringResource(R.string.quantity), numberOfCupcakes),
        // Ringkasan 2: menampilkan rasa cupcake yang dipilih
        Pair(stringResource(R.string.flavor), orderUiState.flavor),
        // Ringkasan 3: menampilkan tanggal pengambilan cupcake
        Pair(stringResource(R.string.pickup_date), orderUiState.date)
    )

    // Layout utama untuk menampilkan ringkasan pesanan
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween // Menyusun elemen secara vertikal dengan jarak antar elemen
    ) {
        // Menampilkan daftar ringkasan pesanan
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)) // Jarak antar elemen
        ) {
            // Menampilkan setiap item dari daftar ringkasan
            items.forEach { item ->
                Text(item.first.uppercase()) // Menampilkan label yang diubah ke huruf kapital
                Text(text = item.second, fontWeight = FontWeight.Bold) // Menampilkan nilai item dengan font tebal
                Divider(thickness = dimensionResource(R.dimen.thickness_divider)) // Pembatas antara item
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small))) // Spasi antar bagian
            // Menampilkan label harga dengan format tertentu
            FormattedPriceLabel(
                subtotal = orderUiState.price, // Menampilkan harga subtotal
                modifier = Modifier.align(Alignment.End) // Menyusun label di sebelah kanan
            )
        }
        // Menampilkan tombol untuk mengirimkan atau membatalkan pesanan
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)) // Jarak antar tombol
            ) {
                // Tombol untuk mengirimkan pesanan
                Button(
                    modifier = Modifier.fillMaxWidth(), // Tombol mengisi lebar layar
                    onClick = { onSendButtonClicked(newOrder, orderSummary) } // Menangani klik tombol untuk mengirimkan pesanan
                ) {
                    Text(stringResource(R.string.send)) // Teks tombol "Send"
                }
                // Tombol untuk membatalkan pesanan
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(), // Tombol mengisi lebar layar
                    onClick = onCancelButtonClicked // Menangani klik tombol untuk membatalkan pesanan
                ) {
                    Text(stringResource(R.string.cancel)) // Teks tombol "Cancel"
                }
            }
        }
    }
}

// Preview untuk menampilkan tampilan layar dengan data contoh
@Preview
@Composable
fun OrderSummaryPreview() {
    CupcakeTheme { // Menggunakan tema aplikasi
        OrderSummaryScreen(
            orderUiState = OrderUiState(0, "Test", "Test", "$300.00"), // Data contoh untuk tampilan
            onSendButtonClicked = { subject: String, summary: String -> }, // Placeholder untuk lambda klik
            onCancelButtonClicked = {}, // Placeholder untuk lambda klik
            modifier = Modifier.fillMaxHeight() // Mengisi tinggi layar
        )
    }
}
