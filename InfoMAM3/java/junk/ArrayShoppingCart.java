/**
 * ArrayShoppingCart
 */
public class ArrayShoppingCart {

    private int maxBook;
    private String[] books;

    ArrayShoppingCart(int maxBook, String... books) throws Exception {
        
        ;
        if( maxBook < books.length){
            throw new Exception("maxBook doit etre plus grand que le nombre de livres");
        }
        this.maxBook = maxBook;
        this.books = new String[maxBook];
        for (int i = 0; i < books.length; i++) {
            this.books[i] = books[i];
        }
    }

    public void add(String book) {
        int i = 0;
        while (i < books.length && books[i] != null) {
            i++;
        }
        if (i < maxBook) {
            
            books[i] = book;
        }

    }

    public int nbBook() {
        int n = 0;
        for (String book : books) {
            if (book != null) {
                n++;
            }
        }
        return n;
    }

    public String longestTitle() {
        int max = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i].length() > books[max].length()) {
                max = i;
            }
        }
        return books[max];
    }

    public void removeAllOccurence(String book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == book) {
                books[i] = null;
            }
        }
    }

    public String toString() {
        String str = "";
        for (String book : books) {
            if (book != null) {
                str += " " + book;
            }

        }
        return str;
    }

    public static void main(String[] args) throws Exception {
        ArrayShoppingCart a = new ArrayShoppingCart(8, "Harry Potter", "Ainsi parlait Zarathoustra");
        System.out.println(a);
        a.add("Kafka sur le rivage");
        System.out.println(a);
        a.removeAllOccurence("Harry Potter");
        System.out.println(a);

    }

}