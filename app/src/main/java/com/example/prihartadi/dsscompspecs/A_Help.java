package com.example.prihartadi.dsscompspecs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class A_Help extends AppCompatActivity {

    ListView listHelp;
    ArrayAdapter adapter;
    final String[] titles = {"Fitur Main","Fitur Custom","Fitur Update","Fitur Saved","Fitur Help","Fitur About","Kategori Umum","Kategori Gaming","Kategori Multimedia","Kategori Profesional","Komponen Processor","Komponen Motherboard","Komponen Ram","Komponen Gpu","Komponen PowerSupply","Komponen Storage","Komponen Monitor","Komponen Casing","Komponen Mouse","Komponen Keyboard"};
    final String[] details = {
            "Pencarian spesifikasi komputer desktop lengkap yang terdiri dari processor, motherboard, ram, gpu, power supply, storage, monitor, casing, mouse, keyboard sesuai dengan kategori penggunaan pilihan dan dana",
            "Pencarian spesifikasi komputer desktop personal sesuai dengan kategori penggunaan pilihan dan dana dengan fitur tambahan yaitu mengunci komponen pilihan untuk diikutsertakan dalam spesifikasi. Jika mode upgrade diaktifkan maka komponen yang dikunci, harganya tidak diikutsertakan dalam proses pencarian.",
            "Pembaharuan data komponen dalam database aplikasi",
            "Daftar spesifikasi hasil pencarian yang telah disimpan",
            "Daftar penjelasan fitur dan istilah dalam aplikasi",
            "Informasi tentang Developer",
            "Komputer desktop kategori umum adalah komputer yang ditujukan untuk komputasi ringan sehari-hari seperti bekerja dengan dokumen, berselancar web, dan konsumsi media ringan. Tidak ada spesifikasi khusus untuk komputer kategori ini",
            "Komputer desktop kategori gaming adalah komputer yang ditujukan untuk bermain game. Komputer jenis ini membutuhkan komponen-komponen dengan spesifikasi tertentu agar game yang dimainkan dapat berjalan dengan lancar. Komponen processor, ram, dan gpu card diutamakan untuk komputer desktop kategori gaming",
            "Komputer desktop kategori multimedia adalah komputer yang ditujukan khusus untuk konsumsi media. Komputer jenis ini mengutamakan kapasitas storage untuk menyimpan library media seperti film dan lagu serta monitor yang mumpuni",
            "Komputer desktop kategori professional adalah komputer desktop yang ditujukan  untuk pekerja professional yang membutuhkan komputer dengan komputasi tinggi dan multitasking dapat diandalkan untuk bekerja dalam waktu lama. Komponen komputer kategori ini condong ke processor dan ram disandingkan dengan motherboard dan power supply yang handal",
            "Processor atau Central Processing Unit (CPU) adalah otak dari komputer. Fungsi dari processor ini adalah memproses dan mengolah semua kalkulasi dan perintah-perintah yang membuat komputer dapat dioperasikan. Spesifikasi processor yang biasa digunakan sebagai pertimbangan dalam memilih adalah socket, jumlah core, serta frekuensi kecepatan processor",
            "Motherboard atau mainboard adalah komponen utama yang membangun sebuah komputer. Berbentuk papan persegi dengan slot-slot untuk memasukkan komponen-komponen lain. Fungsinya untuk menghubungkan seluruh komponen PC. Spesifikasi motherboard yang harus diperhatikan dalam membangun sebuah komputer adalah pilihan socket dan i/o bus yang harus disesuikan dengan komponen komputer lain",
            "RAM atau Random Access Memory berfungsi sebagai tempat transit data sementara untuk operasi-operasi yang tengah dijalankan oleh CPU. RAM bersifat volatile, artinya perangkat ini tidak meyimpan data secara permanen, hanya untuk operasi yang dibutuhkan saja. Spesifikasi dari ram yang perlu diperhatikan adalah tipe dari ram yang biasanya menyesuaikan dengan generasi processor yang digunakan. Selain itu, ukuran memory dan kecepatan ram juga perlu dipertimbangkan sesuai dengan kebutuhan",
            "GPU adalah processor khusus yang berfungsi mengolah data grafik yang akan ditampilkan oleh monitor. GPU biasanya terdapat pada kartu Video Graphic Array (VGA). Selain kartu VGA, pada jenis-jenis processor terbaru juga biasanya sudah terintegrasi GPU didalamnya. Dalam memilih GPU, jenis dan ukuran memory serta bitrate dapat dijadikan acuan. Tetapi pada umumnya performa GPU dapat diketahui dari generasi dan kode nama dari GPU itu sendiri",
            "PSU berfungsi sebagai pengkonversi dan penyalur energi listrik dari outlet sumber (misalnya listrik PLN) ke bentuk energi listrik yang dapat digunakan untuk menjalankan komponen komputer yang berada di dalam casing. Pemilihan PSU menyesuaikan dengan total daya yang dibutuhkan oleh komponen lain. Selain itu, efisiensi dari PSU juga dapat menjadi pertimbangan",
            "Storage memory berfungsi sebagai tempat penyimpanan data utama dalam sebuah sistem komputer. Sistem Operasi, aplikasi, dan dokumen-dokumen disimpan pada storage memory ini. Pada PC terdapat berapa macam storage memory, yang paling umum adalah Hard Disk Drive (HDD) dan Solid State Drive (SSD). Keduanya memiliki fungsi yang sama, namun SSD menawarkan keceparan transfer data yang lebih cepat. Kecepatan membaca dan menulis data serta ukuran memory merupakan acuan utama dalam memilih storage memory",
            "Disebut juga screen atau display. Fungsi dari layar monitor adalah untuk menampilkan video dan informasi grafis yang dihasilkan dari komputer melalui alat yang disebut kartu grafis (VGA Card). Pemilihan monitor umumnya mengacu pada ukuran layar dan resolusi yang disesuikan kebutuhan",
            "Casing merupakan sebuah wadah atau kontainer dan umunya berbentuk kotak yang digunakan untuk menampung perangkat-perangkat komputer seperti CPU, motherboard, RAM dan peranti-peranti yang lain",
            "Keyboard berfungsi sebagai alat input untuk memasukkan perintah teks, karakter, atau menggerakkan objek pada antarmuka grafis untuk diproses oleh komputer",
            "Mouse berfungsi sebagai alat input untuk menggerakkan pointer pada layar"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__help);

        Log.v("LOG_titles",titles.length+"");
        Log.v("LOG_details",details.length+"");

        listHelp = (ListView)findViewById(R.id.listHelp);
        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, titles);
        listHelp.setAdapter(adapter);
        listHelp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), A_HelpDetail.class);
                intent.putExtra("title", titles[i]);
                intent.putExtra("detail", details[i]);
                startActivity(intent);
            }
        });
    }
}
