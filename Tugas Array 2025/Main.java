import java.io.File;
import java.io.FileNotFoundException;
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
    public int max;
    public int min;

    public ArrayDataKaryawanTerurut(int n) {
        this.dataKaryawan = new DataKaryawan[n];
        this.length = 0;
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
            
            this.min = karyawan.masaKerja;
            this.max = karyawan.masaKerja;
            this.length++;
            return;
        }

        int n = this.length/2;
        int right = this.length-1;
        int left = 0;
        int gap = this.length/2;
        while (true) { 
            if (n == this.length-1 & karyawan.masaKerja >= this.dataKaryawan[n].masaKerja) {
                this.dataKaryawan[n+1] = new DataKaryawan(karyawan.nama, karyawan.NIP, karyawan.masaKerja, karyawan.gaji);
                this.max = karyawan.masaKerja;
                this.length++;
                return;
            }
            if (n == 0 & karyawan.masaKerja <= this.dataKaryawan[n].masaKerja) {
                for (int i = this.length-1; i >= n; i--) {
                    this.dataKaryawan[i+1] = new DataKaryawan(
                        this.dataKaryawan[i].nama,
                        this.dataKaryawan[i].NIP,
                        this.dataKaryawan[i].masaKerja,
                        this.dataKaryawan[i].gaji
                    );
                }
                this.dataKaryawan[n] = new DataKaryawan(karyawan.nama, karyawan.NIP, karyawan.masaKerja, karyawan.gaji);
                this.min = karyawan.masaKerja;
                this.length++;
                return;
            }
            if (karyawan.masaKerja <= this.dataKaryawan[n].masaKerja & karyawan.masaKerja >= this.dataKaryawan[n-1].masaKerja) {
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
            if (karyawan.masaKerja > dataKaryawan[n].masaKerja) {
                left = n;
                n = right - (gap/2);
                gap = (right - left)/2;
            } else if (karyawan.masaKerja < dataKaryawan[n].masaKerja) {
                right = n;
                n = left + (gap/2); 
                gap = (right - left)/2;
            }
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

        arrayKaryawan.display();
    }
}