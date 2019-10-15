import java.util.Date;
import java.text.SimpleDateFormat;

public class Request {

    private int type;
    private String ress;
    private int ressStatus;
    private String data;
    //Cette variable n'était pas dans le TD
    private static String error404 = "<!DOCTYPE html>\n<html>\n<body>\n<p style=\"color:red\">ERROR 404\n</p>\n</body>\n</html>";

    public Request(int t, String r) {
        this.type = t;
        this.ress = r;
        this.ressStatus = -1;
        this.data = "";
    }

    public void findRessourceType() {
        if (this.ress.equals("/date/")) {
            this.ressStatus = 0;
        }
        if (this.ress.equals("/date")) {
            this.ressStatus = 2;
        }

    }

    public int getRessourceType() {
        return this.ressStatus;
    }

    public String getHttpReplyCode() {
        if (this.ressStatus == 0) {
            return "200 OK";
        }
        else if (this.ressStatus == -1) {
            return "404 NOT FOUND";
        }
        else if (this.ressStatus==2){
            return "301 MOVED PERMANENTLY";
        }
        return null;

    }

    public String getMime() {
        if (this.ressStatus == 0 || this.ressStatus == 2) {
            return "text/plain";
        }
        else if(this.ressStatus==-1){
            return "text/html";
        }
        return null;
    }

    public int getCL() {
        if (ressStatus == 0) {
            this.data = getDate();
            return this.data.length();
        }
        if (ressStatus == -1) {
            this.data = error404;
            return(-1);
        }
        if (ressStatus == 2) {
            this.data = "301 MOVED PERMANENTLY";
            return this.data.length();
        }
        return 0;
    }

    public String getDate() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd MM yyyy HH:mm:ss");
        Date date = new Date();
        return (dateFormat.format(date));
    }

    public String getData() {
        return this.data;
    }

}