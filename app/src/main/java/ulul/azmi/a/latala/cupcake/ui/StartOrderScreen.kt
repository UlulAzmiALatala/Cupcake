package ulul.azmi.a.latala.cupcake.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ulul.azmi.a.latala.cupcake.R
import ulul.azmi.a.latala.cupcake.data.DataSource
import ulul.azmi.a.latala.cupcake.ui.theme.CupcakeTheme

/**
 * Composable yang memungkinkan pengguna memilih jumlah cupcake yang diinginkan,
 * dan menerima lambda [onNextButtonClicked] yang mengirimkan jumlah yang dipilih
 * serta menavigasi ke layar berikutnya.
 */
@Composable
fun StartOrderScreen(
    quantityOptions: List<Pair<Int, Int>>, // Opsi jumlah cupcake yang bisa dipilih (label dan nilai)
    onNextButtonClicked: (Int) -> Unit, // Lambda yang menerima jumlah yang dipilih dan melanjutkan ke layar berikutnya
    modifier: Modifier = Modifier // Modifier opsional untuk pengaturan tampilan
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween // Menyusun elemen dengan jarak antar mereka
    ) {
        // Menampilkan bagian atas dari tampilan, dengan gambar dan teks
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally, // Menyusun elemen secara horizontal di tengah
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)) // Menyusun elemen dengan jarak kecil antar mereka
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium))) // Menambahkan spasi
            Image(
                painter = painterResource(R.drawable.cupcake), // Menampilkan gambar cupcake
                contentDescription = null, // Tidak ada deskripsi untuk gambar ini
                modifier = Modifier.width(300.dp) // Menetapkan lebar gambar
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium))) // Menambahkan spasi lagi
            Text(
                text = stringResource(R.string.order_cupcakes), // Menampilkan teks untuk pesan pemesanan
                style = MaterialTheme.typography.headlineSmall // Menetapkan gaya teks
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small))) // Menambahkan spasi kecil
        }

        // Menampilkan tombol pilihan jumlah cupcake yang bisa dipilih oleh pengguna
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally, // Menyusun elemen secara horizontal di tengah
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.padding_medium) // Menyusun elemen dengan jarak medium antar mereka
            )
        ) {
            quantityOptions.forEach { item -> // Iterasi melalui daftar opsi jumlah cupcake
                // Menampilkan tombol untuk setiap opsi jumlah
                SelectQuantityButton(
                    labelResourceId = item.first, // Menampilkan label dari sumber daya string
                    onClick = { onNextButtonClicked(item.second) }, // Menangani klik pada tombol untuk memilih jumlah
                    modifier = Modifier.fillMaxWidth() // Membuat tombol mengisi lebar layar
                )
            }
        }
    }
}

/**
 * Customizable button composable yang menampilkan [labelResourceId]
 * dan menjalankan [onClick] lambda saat tombol ini diklik
 */
@Composable
fun SelectQuantityButton(
    @StringRes labelResourceId: Int, // ID sumber daya untuk label tombol
    onClick: () -> Unit, // Callback yang dipanggil saat tombol diklik
    modifier: Modifier = Modifier // Modifier opsional untuk pengaturan tampilan
) {
    Button(
        onClick = onClick, // Menangani klik tombol
        modifier = modifier.widthIn(min = 250.dp) // Menetapkan lebar tombol minimum
    ) {
        Text(stringResource(labelResourceId)) // Menampilkan label tombol yang diambil dari sumber daya string
    }
}

// Preview untuk menampilkan tampilan layar dengan data contoh
@Preview
@Composable
fun StartOrderPreview() {
    CupcakeTheme { // Menggunakan tema aplikasi
        StartOrderScreen(
            quantityOptions = DataSource.quantityOptions, // Menggunakan data pilihan jumlah cupcake dari sumber
            onNextButtonClicked = {}, // Placeholder untuk lambda klik
            modifier = Modifier
                .fillMaxSize() // Mengisi seluruh ukuran layar
                .padding(dimensionResource(R.dimen.padding_medium)) // Menambahkan padding
        )
    }
}
