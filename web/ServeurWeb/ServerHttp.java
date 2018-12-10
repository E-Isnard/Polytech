import java.io.IOException;

public class ServerHttp extends Server {

    public ServerHttp(boolean verbose) throws IOException {
        super(1234, verbose);
    }

    public ServerHttp() throws IOException {
        super(1234, false);
    }

    public void dialogue() throws IOException {

        this.acceptConn();
        String req = this.getRequete();
        if (req != null && !(req.isEmpty())) {
            this.repRequete(req);
        }

        this.closeConn();

    }

    public String getRequete() throws IOException {
        String out = "";
        String head = this.readline();

        String line = "";
        boolean go = true;
        if (head != null) {
            String[] tab = head.split(" ");
            if (head.startsWith("POST") || head.startsWith("GET")) {
                if (tab.length == 3) {
                    while (go) {

                        line = this.readline();
                        if (line == null) {
                            go = false;
                        }

                        else if (line.isEmpty()) {
                            out += "\n";
                            go = false;
                            if (head.startsWith("POST")) {
                                out += this.readcars(12);
                                out += line;

                            }

                        } else {

                            out += line;
                            out += "\n";

                        }
                    }
                }

            }

        }
        return (out);
    }

    private void repRequete(String requete) throws IOException {
        this.writeline("\nMessage bien reçu ;-)");
        this.writeline("Voici votre requete");
        this.writeline(requete);

    }

    public static void main(String[] args) {

        ServerHttp s = null;

        try {
            s = new ServerHttp(true);
        } catch (IOException e) {
            System.out.println("Pb while creating the server :'c ");
            System.exit(-1);

        }

        try {
            while (true) {
                s.dialogue();
            }
        } catch (IOException e) {
            System.out.println("err while talking to the client >:C ");
        } finally {
            System.out.println("KILLING THE SERVER !");
            s.close();
        }
    }
}
