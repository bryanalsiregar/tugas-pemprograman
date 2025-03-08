import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NullPointerException;
import java.util.Scanner;

class DataKaryawan {
    public String nama;
    public String NIP;
    public int masaKerja;
    public int gaji;

    public DataKaryawan(String nama, String NIP, int masaKerja, int gaji) {
        this.nama = nama;
        this.NIP = NIP;
        this.masaKerja = masaKerja;
        this.gaji = gaji;
    }
}

class ArrayDataKaryawanTerurut {
    private DataKaryawan dataKaryawan[];
    private int length;

    public ArrayDataKaryawanTerurut(int n) {
        this.dataKaryawan = new DataKaryawan[n];
        this.length = 0;
    }

    public int getTotalGaji() {
        int totalGaji = 0;
        for (int i = 0; i < this.length; i++) totalGaji += this.dataKaryawan[i].gaji;
        System.out.print("Total gaji karyawan adalah "); 
        System.out.println(totalGaji); 
        return totalGaji;
    }

    public void display() {
        System.out.println("Cetak Data Karyawan Terurut");
        for (int i = 0; i < this.length; i++) {
            System.out.print(i+1);
            System.out.println(". Nama: "+dataKaryawan[i].nama);
            System.out.println("NIP: "+dataKaryawan[i].NIP);
            System.out.println("Masa Kerja: "+dataKaryawan[i].masaKerja);
            System.out.println("Gaji: "+dataKaryawan[i].gaji);
            System.out.println("");
        }
    }

    public void updateMasaKerja(String namaKaryawan, int masaKerja) {
        int right = this.length-1;
        int left = 0;

        while (right >= left) {
            int i = left + (right - left)/2;
            System.out.println(this.length);
            if (this.dataKaryawan[i].nama.equalsIgnoreCase(namaKaryawan)) {
                this.dataKaryawan[i] = new DataKaryawan(
                    this.dataKaryawan[i].nama,
                    this.dataKaryawan[i].NIP,
                    masaKerja,
                    this.dataKaryawan[i].gaji
                );
                return;
            }
            if (namaKaryawan.compareToIgnoreCase(this.dataKaryawan[i].nama) > 0) left = i + 1;
            else if (namaKaryawan.compareToIgnoreCase(this.dataKaryawan[i].nama) < 0) right = i - 1;
        }
        System.out.println("Tidak ditemukan "+namaKaryawan+" untuk diupdate!");
    }

    public void updateGaji(String namaKaryawan, int gaji) {
        int right = this.length-1;
        int left = 0;

        while (right >= left) {
            int i = left + (right - left)/2;
            System.out.println(this.length);
            if (this.dataKaryawan[i].nama.equalsIgnoreCase(namaKaryawan)) {
                this.dataKaryawan[i] = new DataKaryawan(
                    this.dataKaryawan[i].nama,
                    this.dataKaryawan[i].NIP,
                    this.dataKaryawan[i].masaKerja,
                    gaji
                );
                return;
            }
            if (namaKaryawan.compareToIgnoreCase(this.dataKaryawan[i].nama) > 0) left = i + 1;
            else if (namaKaryawan.compareToIgnoreCase(this.dataKaryawan[i].nama) < 0) right = i - 1;
        }
        System.out.println("Tidak ditemukan "+namaKaryawan+" untuk diupdate!");
    }

    public void delete(String namaKaryawan) {
        int right = this.length-1;
        int left = 0;

        while (left <= right) {
            int i = left + (right - left)/2;
            if (this.dataKaryawan[i].nama.equalsIgnoreCase(namaKaryawan)) {
                try {
                    for (int j = i; j < this.length; j++) {

                        this.dataKaryawan[j] = new DataKaryawan(
                            this.dataKaryawan[j+1].nama,
                            this.dataKaryawan[j+1].NIP,
                            this.dataKaryawan[j+1].masaKerja,
                            this.dataKaryawan[j+1].gaji
                        );
                    }
                } catch (IndexOutOfBoundsException e) {
                    this.dataKaryawan[this.length+1] = null;
                } catch (NullPointerException e) {
                    this.dataKaryawan[this.length+1] = null;
                }

                this.length--;
                return;
            }

            if (namaKaryawan.compareToIgnoreCase(this.dataKaryawan[i].nama) > 0) left = i + 1;
            else if (namaKaryawan.compareToIgnoreCase(this.dataKaryawan[i].nama) < 0) right = i - 1;
        }
        System.out.println("Tidak ditemukan "+namaKaryawan+" untuk dihilangkan!");
    }

    public void find(String namaKaryawan) {
        int right = this.length-1;
        int left = 0;
        
        while (left <= right) {
            int i = left + (right - left)/2;
            if (this.dataKaryawan[i].nama.equalsIgnoreCase(namaKaryawan)) {
                System.out.println("Terdapat karyawan dengan nama: "+namaKaryawan);
                System.out.println("Nama: "+this.dataKaryawan[i].nama);
                System.out.println("NIP: "+this.dataKaryawan[i].NIP);
                System.out.println("Masa Kerja: "+this.dataKaryawan[i].masaKerja);
                System.out.println("Gaji: "+this.dataKaryawan[i].gaji);
                return;
            }

            if (namaKaryawan.compareToIgnoreCase(this.dataKaryawan[i].nama) > 0) left = i + 1;
            else if (namaKaryawan.compareToIgnoreCase(this.dataKaryawan[i].nama) < 0) right = i - 1;
        }
        System.out.println(namaKaryawan+" Tidak ditemukan!");
    }

    public void insert(DataKaryawan karyawan) {
        if (this.length == this.dataKaryawan.length) {
            System.err.println("Maaf, array sudah penuh!");
            return;
        }
        
        if (this.length == 0) {
            this.dataKaryawan[0] = new DataKaryawan(
                karyawan.nama,
                karyawan.NIP,
                karyawan.masaKerja,
                karyawan.gaji);
            
            this.length++;
            return;
        }

        int right = this.length-1; 
        int left = 0;
        int n = left + (right - left)/2;
        while (right >= left) { 
            n = left + (right - left)/2;  
            if (n == this.length-1 & karyawan.nama.compareToIgnoreCase(this.dataKaryawan[n].nama) >= 0) {
                this.dataKaryawan[n+1] = new DataKaryawan(karyawan.nama, karyawan.NIP, karyawan.masaKerja, karyawan.gaji);
                this.length++;
                return;
            }
            try {
                if (
                    karyawan.nama.compareToIgnoreCase(this.dataKaryawan[n].nama) <= 0 & 
                    karyawan.nama.compareToIgnoreCase(this.dataKaryawan[n-1].nama) >= 0) {
                    
                    for (int i = this.length-1; i >= n; i--) {
                        this.dataKaryawan[i+1] = new DataKaryawan(
                            this.dataKaryawan[i].nama,
                            this.dataKaryawan[i].NIP,
                            this.dataKaryawan[i].masaKerja,
                            this.dataKaryawan[i].gaji
                        );
                    }
                    this.dataKaryawan[n] = new DataKaryawan(karyawan.nama, karyawan.NIP, karyawan.masaKerja, karyawan.gaji);
                    this.length++;
                    return;
                }
            } catch (IndexOutOfBoundsException e) {
                if (n == 0 & karyawan.nama.compareToIgnoreCase(this.dataKaryawan[n].nama) <= 0) {
                    for (int i = this.length-1; i >= n; i--) {
                        this.dataKaryawan[i+1] = new DataKaryawan(
                            this.dataKaryawan[i].nama,
                            this.dataKaryawan[i].NIP,
                            this.dataKaryawan[i].masaKerja,
                            this.dataKaryawan[i].gaji
                        );
                    }
                    this.dataKaryawan[n] = new DataKaryawan(karyawan.nama, karyawan.NIP, karyawan.masaKerja, karyawan.gaji);
                    this.length++;
                    return;
                }
            }
            if (karyawan.nama.compareToIgnoreCase(this.dataKaryawan[n].nama) > 0) left = n + 1;
            else if (karyawan.nama.compareToIgnoreCase(this.dataKaryawan[n].nama) < 0) right = n-1;
        }
    }

}

class Main {
    public static void main(String[] args) {
        String filePath = "file.txt";
        
        File file = new File(filePath);

        ArrayDataKaryawanTerurut arrayKaryawan = new ArrayDataKaryawanTerurut(20);
        try (Scanner scanner = new Scanner(file)) {
            DataKaryawan karyawan = new DataKaryawan("", "", -1, -1);
            int count = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                switch (count) {
                    case 1:
                        karyawan.nama = line;
                        break;
                    case 2:
                        karyawan.NIP = line;
                        break;
                    case 3:
                        karyawan.masaKerja = Integer.parseInt(line);
                        break;
                    case 4:
                        karyawan.gaji = Integer.parseInt(line);
                        break;
                }
                count++;
                if (count == 5) {
                    arrayKaryawan.insert(karyawan);
                    count = 1;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file.txt is not found!");
        }
        
        System.out.println("Array terurut yang tersedia dari file.txt");
        arrayKaryawan.display();
        System.out.println("-----------------------------------------");
        System.out.println("Mencari Prabowo Subianto");
        arrayKaryawan.find("Prabowo Subianto");
        System.out.println("Mencari Tono Prabowo");
        arrayKaryawan.find("Tono Prabowo");
        System.out.println("Mencari Joko Widodo");
        arrayKaryawan.find("Joko Widodo");
        System.out.println("Mencari Agus Rahman");
        arrayKaryawan.find("Agus Rahman");
        System.out.println("Mencari Nia Dewi");
        arrayKaryawan.find("Nia Dewi");
        System.out.println("-----------------------------------------");
        System.out.println("Menghapus Bryan Al Hilal Siregar");
        arrayKaryawan.delete("Bryan Al Hilal Siregar");
        System.out.println("Menghapus Tono Prabowo");
        arrayKaryawan.delete("Tono Prabowo");
        System.out.println("Menghapus Joko Widodo");
        arrayKaryawan.delete("Joko Widodo");
        System.out.println("Menghapus Agus Rahman");
        arrayKaryawan.delete("Agus Rahman");
        arrayKaryawan.display();
        System.out.println("-----------------------------------------");
        System.out.println("Update Gaji Siti Aisyah menjadi 1000000");
        arrayKaryawan.updateGaji("Siti Aisyah", 1000000);
        System.out.println("Update Gaji Tim Cook menjadi 100000000");
        arrayKaryawan.updateGaji("Tim Cook", 100000000);
        System.out.println("Update Gaji Lita Sari menjadi 300000");
        arrayKaryawan.updateGaji("Lita Sari", 300000);
        arrayKaryawan.display();
        System.out.println("-----------------------------------------");
        System.out.println("Update Masa Kerja Siti Aisyah menjadi 1");
        arrayKaryawan.updateMasaKerja("Siti Aisyah", 1);
        System.out.println("Update Masa Kerja Elon Musk menjadi 10000");
        arrayKaryawan.updateMasaKerja("Elon Musk", 10000);
        System.out.println("Update Masa Kerja Lita Sari menjadi 300000");
        arrayKaryawan.updateMasaKerja("Lita Sari", 100);
        arrayKaryawan.display();
        System.out.println("-----------------------------------------");
        System.out.println("Menampilkan total gaji karyawan");
        arrayKaryawan.getTotalGaji();
    }
}