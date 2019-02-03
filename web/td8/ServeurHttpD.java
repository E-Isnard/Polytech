import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class ServeurHttpD extends Server {

	public ServeurHttpD(int port) throws IOException {
		super(port, true);
	}

	public void dialogue() throws IOException {
		try {
			Request req = null;
			acceptConn();
			req = getRequete();
			if (req != null)
				repRequete(req);
			closeConn();
		} catch (IOException e) {
			System.out.println("Problem! Closing the connection");
			closeConn();
		}
	}

	private void repRequete(Request req) throws IOException {
		sendHttpHeader(req);
		sendData(req);
		
	}

	private void sendHttpHeader(Request req) throws IOException{
		writeline("HTTP/1.1 " + req.getHttpReplyCode());
		writeline("Content-type: " + req.getMimeType());
		writeline("Content-length: " + req.getCL());
		if (req.getRessourceType() == 1)
			writeline("Location: " + req.getNewLoc());
		writeline("Connection: close");
		writeline("");
	}

	private void sendData(Request req) throws IOException {
		if (req.getRessourceType() == 0 || req.getRessourceType() == -1) {
			writeline(req.getData());
		}
		if (req.getRessourceType() == 2){

			int l = (int) req.getCL();
			InputStream flux = new FileInputStream(req.getRess().substring(1));
			//DataInputStream ds = new DataInputStream(flux);
			byte[] buf = new byte[l];

			flux.read(buf);
			write(buf);
			flux.close();

		}
	}

	private Request getRequete() throws IOException {
		Request req = null;
		String h;
		String preligne = null;
		String httpArgs = null;
		String ligne;
		preligne = readline();
		if (preligne != null)
			req = analyseReq(preligne);
		if (req == null)
			return null;

		while (true) {
			ligne = readline();
			if (ligne == null)
				return null;
			if (ligne.isEmpty())
				break;
			ligne = ligne.replaceAll("\\s", "");
			if (ligne.startsWith("Host"))
				req.setHost(analyseHost(ligne));
		}

		if (preligne.startsWith("POST")) {
			httpArgs = readcars(5); // Ceci est pour l'instant un nombre arbitraire
			debug("reçu " + httpArgs);
		}

		req.findRessourceType();

		return req;
	}
	

	private Request analyseReq(String ligne) {
		String[] treq = ligne.split(" ");
		if (treq.length != 3)
			return null;
		if (treq[0].equals("GET"))
			return (new Request(1, treq[1])); // 1 = GET
		if (treq[0].equals("POST"))
			return (new Request(2, treq[1])); // 2 = POST
		return null;
	}

	private String analyseHost(String ligne) {
		String[] treq = ligne.split(":");
		if (treq.length < 2)
			return null;
		return ligne.replaceAll("Host:", "");
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

class Request {
	int typereq;
	String ress;
	String h;
	int typeress;
	String data;
	String mimetype;
	String newloc;
	HashMap<String, String> mimetypes = new HashMap<String, String>();

	public Request(int typereq, String ress) {
		this.typereq = typereq;
		this.ress = ress;
		mimetypes.put("txt", "text/plain");
		mimetypes.put("html", "text/html");
		mimetypes.put("jpg", "image/jpeg");
		mimetypes.put("png", "image/png");
		mimetypes.put("mpeg", "video/mpeg");
		mimetypes.put("pdf", "application/pdf");
		mimetypes.put("css", "text/css");

	}

	public void findRessourceType() {
		if (ress.equals("/date/")) {
			typeress = 0; // 0 = /date/
			return;
		}
		if (ress.equals("/date")) {
			typeress = 1; // 1 = redirection
			newloc = "http://" + h + ress + "/";
			return;
		}
		File file = new File(ress.substring(1));
		if(file.exists()){
			typeress = 2;
			return;
		}
		if(file.isDirectory()){
			typeress = 2;
			if(!(ress.endsWith("/"))){
				ress += "/";
			}
			
			File dir = new File(ress);
			String list[] = dir.list();
			int i = 0;
			if(Arrays.asList(list).contains("index.html")){
				ress = ress.substring(1)+"index.html";
				return;
			}
			if(Arrays.asList(list).contains("index.pdf")){
				ress = ress.substring(1)+"index.pdf";
				return;
			}

			if(Arrays.asList(list).contains("index.txt")){
				ress = ress.substring(1)+"index.txt";
				return;
			}

			
		}


		typeress = -1; // -1 = error
	}

	public String getRess(){
		return ress;
	}

	public String getHttpReplyCode() {
		switch (typeress) {
		case 0:
			return "200 OK";
		case 1:
			return "301 Moved Permanently";
		case 2:
			return "200 OK";
		default:
			return "404 Not Found";
		}
	}

	public String getMimeType() {
		if (typeress < 2){
			return(mimetypes.get(getExt(this.ress.substring(1))));
		}
		return null;
	}

	public static String getExt(String ress) {
		String[] out = ress.split(".");
		int l = out.length;

		if (l!=0) {return out[l-1];} else {return(null);}

	}

	public long getCL() throws IOException{
		if (typeress == 0) {
			data = getDate();
			return(data.length());
		}
		if (typeress == 1) {
			return 0; // 0 car pas de corps de reponse pour l'instant
		}
		if (typeress == 2){
			File file = new File(ress.substring(1));
			return(file.length());
	
		}
		
			
		
		data = "La ressource " + ress + " est introuvable";
		return (data.length());
	}

	public int getRessourceType() {
		return typeress;
	}

	public String getData() {
		return data;
	}

	private String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd MMM yyyy HH:mm:ss");
		Date date = new Date();
		return (dateFormat.format(date));
	}


	public String getNewLoc() {
		return newloc;
	}

	public void setHost(String h) {
		this.h = h;
	}

}
