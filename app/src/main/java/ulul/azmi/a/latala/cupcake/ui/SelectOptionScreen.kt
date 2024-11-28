package ulul.azmi.a.latala.cupcake.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ulul.azmi.a.latala.cupcake.R
import ulul.azmi.a.latala.cupcake.ui.components.FormattedPriceLabel
import ulul.azmi.a.latala.cupcake.ui.theme.CupcakeTheme

/**
 * Composable yang menampilkan daftar opsi sebagai [RadioButton],
 * dengan lambda [onSelectionChanged] yang memberi tahu parent composable saat nilai baru dipilih,
 * [onCancelButtonClicked] untuk membatalkan pesanan saat tombol cancel diklik,
 * dan [onNextButtonClicked] untuk melanjutkan ke layar berikutnya saat tombol next diklik
 */
@Composable
fun SelectOptionScreen(
    subtotal: String, // Harga subtotal yang akan ditampilkan
    options: List<String>, // Daftar pilihan opsi
    onSelectionChanged: (String) -> Unit = {}, // Callback untuk perubahan pilihan
    onCancelButtonClicked: () -> Unit = {}, // Callback untuk tombol cancel
    onNextButtonClicked: () -> Unit = {}, // Callback untuk tombol next
    modifier: Modifier = Modifier // Modifier opsional untuk pengaturan tampilan
) {
    // Mengelola state untuk menyimpan pilihan yang dipilih
    var selectedValue by rememberSaveable { mutableStateOf("") }

    // Menyusun layout utama dalam kolom vertikal
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween // Menyusun elemen dengan jarak antar mereka
    ) {
        // Menampilkan daftar opsi yang bisa dipilih
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            options.forEach { item -> // Iterasi melalui daftar opsi
                Row(
                    modifier = Modifier.selectable( // Menjadikan baris sebagai elemen yang bisa dipilih
                        selected = selectedValue == item, // Menandakan pilihan yang dipilih
                        onClick = {
                            selectedValue = item // Menyimpan nilai yang dipilih
                            onSelectionChanged(item) // Memanggil callback untuk memberitahu perubahan
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically // Menyusun elemen secara vertikal
                ) {
                    // RadioButton untuk memilih opsi
                    RadioButton(
                        selected = selectedValue == item, // Menandakan apakah opsi ini dipilih
                        onClick = {
                            selectedValue = item // Menyimpan nilai yang dipilih
                            onSelectionChanged(item) // Memanggil callback perubahan pilihan
                        }
                    )
                    // Menampilkan teks untuk opsi yang tersedia
                    Text(item)
                }
            }

            // Divider untuk memisahkan bagian opsi dengan bagian lainnya
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider), // Menentukan ketebalan garis pemisah
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium)) // Padding bawah
            )

            // Menampilkan label harga subtotal
            FormattedPriceLabel(
                subtotal = subtotal, // Mengirimkan subtotal harga
                modifier = Modifier
                    .align(Alignment.End) // Menyusun label ke kanan
                    .padding(
                        top = dimensionResource(R.dimen.padding_medium), // Padding atas
                        bottom = dimensionResource(R.dimen.padding_medium) // Padding bawah
                    )
            )
        }

        // Menyusun tombol Cancel dan Next di bagian bawah
        Row(
            modifier = Modifier
                .fillMaxWidth() // Membuat row mengisi lebar layar
                .padding(dimensionResource(R.dimen.padding_medium)), // Padding
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)), // Jarak antar tombol
            verticalAlignment = Alignment.Bottom // Menyusun tombol di bagian bawah
        ) {
            // Tombol Cancel
            OutlinedButton(
                modifier = Modifier.weight(1f), // Menyusun tombol agar memiliki lebar yang sama
                onClick = onCancelButtonClicked // Memanggil callback saat tombol cancel diklik
            ) {
                Text(stringResource(R.string.cancel)) // Teks untuk tombol Cancel
            }
            // Tombol Next, hanya aktif jika ada pilihan yang dipilih
            Button(
                modifier = Modifier.weight(1f), // Menyusun tombol agar memiliki lebar yang sama
                enabled = selectedValue.isNotEmpty(), // Tombol hanya aktif jika ada pilihan
                onClick = onNextButtonClicked // Memanggil callback saat tombol next diklik
            ) {
                Text(stringResource(R.string.next)) // Teks untuk tombol Next
            }
        }
    }
}

// Preview untuk menampilkan tampilan layar dengan data contoh
@Preview
@Composable
fun SelectOptionPreview() {
    CupcakeTheme { // Menggunakan tema aplikasi
        SelectOptionScreen(
            subtotal = "299.99", // Menampilkan harga subtotal
            options = listOf("Option 1", "Option 2", "Option 3", "Option 4"), // Daftar opsi contoh
            modifier = Modifier.fillMaxHeight() // Mengisi tinggi layar untuk pratinjau
        )
    }
}
