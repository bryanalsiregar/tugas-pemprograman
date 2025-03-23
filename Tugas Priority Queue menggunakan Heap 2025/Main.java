import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NullPointerException;
import java.util.Scanner;


class PesawatTerbang {
    String namaMaskapai;
    String kotaTujuan;
    String noPenerbangan;
    int nilaiPrioritas;

    public PesawatTerbang(String maskapai, String tujuan, String no, int prioritas) {
        this.namaMaskapai = maskapai;
        this.kotaTujuan = tujuan;
        this.noPenerbangan = no;
        this.nilaiPrioritas = prioritas;
    }
}

class PriorityQueuePesawatTerbang {
    // left -> 2i + 1
    // right -> 2i + 2
    private PesawatTerbang data[];
    private int length;
    private int n;

    public PriorityQueuePesawatTerbang(int n) {
        this.data = new PesawatTerbang[n];
        this.length = 0;
        this.n = n;
    }

    public void display() {
        if (this.length < 1) {
            System.out.println("Cannot display because priority queue is empty!");
            return;
        }

        for (int i = 0; i < this.length; i++) { 
            System.out.print(this.data[i].nilaiPrioritas);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public void enqueue(PesawatTerbang value) {
        if (this.length < 1) {
            this.insert(0, value);
            this.length++;
            return;
        }

        if (this.length >= this.n) {
            System.out.println("Priority Queue is full!");
            return;
        } 

        bubblingUp(this.length, value);
        length++;
    }

    public void dequeue() {
        if (this.length < 1) {
            System.out.println("Cannot dequeue from Prioritry Query!");
            return;
        }
        this.swap(0, length-1);
        this.data[length-1] = null;

        bubblingDown(0);
        
        length--;
    }

    public void romoveMax() {
        if (this.length < 1) {
            System.out.println("Cannot removeMax because priority queue is empty!");
            return;
        }

        int stop = this.length;
        for (int i = 0; i < stop; i++) {
            System.out.print("(");
            System.out.print(this.peek().nilaiPrioritas);
            System.out.print(", ");
            System.out.print(this.peek().namaMaskapai);
            System.out.print(", ");
            System.out.print(this.peek().noPenerbangan);
            System.out.print(", ");
            System.out.print(this.peek().kotaTujuan);
            System.out.print(")");
            System.out.print(" ");
            this.dequeue();
        }
        System.out.print("\n");
    }

    public PesawatTerbang peek() {
        return this.data[0];
    }

    private void bubblingDown(int root) {
        int i = root;
        int left = this.getLeft(root);
        int right = this.getRight(root);

        while (!this.isLeaf(i)) {
            if (this.data[left] == null) {
                if (this.data[i].nilaiPrioritas <= this.data[right].nilaiPrioritas) {
                    this.swap(i, right);
                    i = right; left = getLeft(i); right = getRight(i);
                    continue;
                } else {
                    break;
                }
            }
            
            if (this.data[right] == null) {
                if (this.data[i].nilaiPrioritas <= this.data[left].nilaiPrioritas) {
                    this.swap(i, left);
                    i = left; left = getLeft(i); right = getRight(i);
                    continue;
                } else {
                    break;
                }
            }
            
            if (this.data[right].nilaiPrioritas == this.data[left].nilaiPrioritas){
                this.swap(i, left);
                i = left; left = getLeft(i); right = getRight(i);
                continue;
            }

            int maxPrioritas = Math.max(this.data[left].nilaiPrioritas, this.data[right].nilaiPrioritas);

            if (maxPrioritas == this.data[left].nilaiPrioritas) {
                this.swap(i, left);
                i = left; left = getLeft(i); right = getRight(i);
                continue;
            }

            if (maxPrioritas == this.data[right].nilaiPrioritas) {
                this.swap(i, right);
                i = right; left = getLeft(i); right = getRight(i);
                continue;
            }
        }
    }

    private void bubblingUp(int lastIndex, PesawatTerbang value) {
        int i = lastIndex;
        this.insert(i, value);
        while (this.data[i].nilaiPrioritas > this.data[this.getParent(i)].nilaiPrioritas) {            
            this.swap(i, this.getParent(i));
            i = this.getParent(i);
        }
    }

    private boolean isLeaf(int i) {
        if  (
            (this.getLeft(i) > this.length) || (this.getRight(i) > this.length)
            ) return true;

        if (
            (this.data[this.getLeft(i)] == null) && (this.data[this.getRight(i)] == null)
            ) return true;
        
        return false;
    }

    private void insert(int i, PesawatTerbang value) {
        this.data[i] = new PesawatTerbang(
            value.namaMaskapai,
            value.kotaTujuan,
            value.noPenerbangan,
            value.nilaiPrioritas
        );
    }

    private int getLeft(int i) {
        return 2*i + 1;
    }
    
    private int getRight(int i) {
        return 2*i + 2;
    }

    private int getParent(int i) {
        return (i-1)/2;
    }

    private void swap(int i, int j) {
        PesawatTerbang temp = new PesawatTerbang(
            this.data[j].namaMaskapai,
            this.data[j].kotaTujuan,
            this.data[j].noPenerbangan,
            this.data[j].nilaiPrioritas
        );
        
        this.insert(j, this.data[i]);
        this.insert(i, temp);
    }
}

class Main {
    public static void main(String[] args) {
        String filePath = "file.txt";
        
        File file = new File(filePath);

        PriorityQueuePesawatTerbang bandara = new PriorityQueuePesawatTerbang(20);
        try (Scanner scanner = new Scanner(file)) {
            PesawatTerbang value = new PesawatTerbang("", "", "", -1);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String lineSplit[] = line.split(",");
            
                value.namaMaskapai = lineSplit[0];
                value.noPenerbangan = lineSplit[1];
                value.kotaTujuan = lineSplit[2];
                value.nilaiPrioritas = Integer.parseInt(lineSplit[3]);

                bandara.enqueue(value);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file.txt is not found!");
        }

        System.out.println("Filling priority queue");
        bandara.display();
        System.out.println("Remove 4 elements queue");
        bandara.dequeue();
        bandara.dequeue();
        bandara.dequeue();
        bandara.dequeue();
        bandara.display();
        System.out.println("Remove max function");
        bandara.romoveMax();
    }
}