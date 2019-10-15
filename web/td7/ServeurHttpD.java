import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServeurHttpD extends Server {

	public ServeurHttpD(int port) throws IOException {
		super(port, true);
	}

	public void dialogue() throws IOException {
		try {
			acceptConn();
			Request request = getRequete();
			if (request != null)
				repRequete(request);
			closeConn();
		} catch (IOException e) {
			System.out.println("Problem! Closing the connection");
			closeConn();
		}
	}

	private void sendHttpHeader(Request req) {
		writeline("HTTP/1.1 " + req.getHttpReplyCode());
		writeline("Content-Type: " + req.getMime());
		writeline("Content-Length: " + req.getCL());
		if (req.getRessourceType() == 2) {
			writeline("Location: http://localhost:1234/date/");
		}
		writeline("Connection: close" + "\n");
	}

	private void sendData(Request req) {
		if (req.getRessourceType() == 0 || req.getRessourceType() == -1 || req.getRessourceType() == 2) {
			writeline(req.getData());
		}
	}

	private void repRequete(Request req) throws IOException {
		sendHttpHeader(req);
		sendData(req);

	}

	private Request getRequete() throws IOException {
		Request request = null;
		String preligne = null;
		String httpArgs, ligne;
		preligne = readline();
		if (preligne != null)
			request = analyseReq(preligne);
		if (request == null)
			return null;
		boolean fin = false;

		while (!fin) {
			ligne = readline();
			if (ligne == null)
				return null;
			if (ligne.isEmpty())
				fin = true;
		}
		if (preligne.startsWith("POST")) {
			httpArgs = readcars(5);
			debug("reçu " + httpArgs);
		}
		request.findRessourceType();
		return request;
	}

	private Request analyseReq(String ligne) {
		Request request = null;
		String[] treq = ligne.split(" ");
		if (treq.length != 3)
			return null;
		if (!(treq[0].equals("GET") || treq[0].equals("POST"))) {
		}
		if (treq[0].equals("GET")) {
			request = new Request(1, treq[1]);
		}
		if (treq[0].equals("POST")) {
			request = new Request(2, treq[1]);
		}
		return request;
	}

	public static void main(String[] args) {
		ServeurHttpD myserver = null;
		try {
			myserver = new ServeurHttpD(1234);
		} catch (IOException e) {
			System.out.println("Problem while creating server!");
			System.exit(-1);
		}
		try {
			while (true) {
				myserver.dialogue();
			}
		} catch (IOException e) {
			System.out.println("Problem while comm... Closing the connection");
		} finally {
			System.out.println("Killing the server");
			myserver.close();
		}
	}
}
