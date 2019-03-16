import java.io.*;
import java.util.*;

public class Fichier {
    private String nom;

    public Fichier(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return (nom);
    }

    public void aleatoire(int n) {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(this.nom));
            Random r = new Random();
            int k;

            for (int i = 0; i < n; i++) {
                k = r.nextInt(100);
                dos.writeInt(k);

            }
            dos.close();

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                dos.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }

    public String toString() {
        String str = "";
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(this.nom));
            while (true) {
                str += dis.readInt() + " ";
            }
        } catch (EOFException eof) {
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        return (str);
    }

    public int min() {
        int min = 101;
        int n;
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(this.nom));
            while (true) {
                n = dis.readInt();
                if (n < min) {
                    min = n;
                }
            }
        } catch (EOFException eof) {
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        return (min);

    }

    public Fichier impair() {
        DataInputStream dis = null;
        DataOutputStream dos = null;
        int n;
        try {
            dis = new DataInputStream(new FileInputStream(this.nom));
            dos = new DataOutputStream(new FileOutputStream(this.nom + "_impairs"));
            while (true) {
                n = dis.readInt();
                if (n % 2 == 1) {
                    dos.writeInt(n);
                }
            }
        } catch (EOFException eof) {
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                dis.close();
                dos.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        return (new Fichier(this.nom + "_impairs"));
    }

    public Vector copy() {
        Vector v = new Vector<Integer>();
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(this.nom));
            while (true) {
                v.add(dis.readInt());

            }

        } catch (EOFException eof) {
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                System.err.println(e);
            }

        }
        return (v);
    }

    public static int minVect(Vector<Integer> v) {
        if (v.isEmpty()) {
            return (Integer.MAX_VALUE);
        }
        int m=Integer.MAX_VALUE;
        for(int i:v){
            if(i<m){
                m = i;
            }
        }
        return(m);
    }

    public Fichier fusionner(Fichier f) throws IOException {
        Vector v1 = new Vector<Integer>();
        Vector v2 = new Vector<Integer>();
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("fusion_" + this.nom + "_" + f.getNom()));
        int m1 = 0;
        int m2 = 0;
        v1 = this.copy();
        v2 = f.copy();
        while (!(v1.isEmpty() && v2.isEmpty())) {
            m1 = minVect(v1);
            m2 = minVect(v2);
            if (m1 >= m2) {
                if (!(v2.isEmpty())) {
                    v2.remove((Integer) m2);
                }
                dos.writeInt(m2);

            } else {
                if (!(v1.isEmpty())) {
                    v1.remove((Integer) m1);
                }
                dos.writeInt(m1);

            }

        }
        dos.close();
        return (new Fichier("fusion_" + this.nom + "_" + f.getNom()));

    }

}