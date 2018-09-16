public class Complexe {
    private double réel;
    private double img;

    public static Complexe I = new Complexe(0, 1);

    public String toString() {
        return ("(" + réel + "," + img + ")");
    }

    public double Rpart() {
        return (this.réel);
    }

    public double Ipart() {
        return (this.img);
    }

    public double rho() {
        return (Math.sqrt(this.img * this.img + this.réel * this.réel));
    }

    public double theta() {
        return (Math.atan2(this.img, this.réel));
    }

    public Complexe(double réel, double img) {
        this.réel = réel;
        this.img = img;
    }

    public Complexe() {
        réel = 0;
        img = 0;
    }

    public static Complexe polComplexe(double r, double t) {
        return (new Complexe(r * Math.cos(t), r * Math.sin(t)));

    }

    public Complexe plus(Complexe c){
        return(new Complexe(this.réel + c.Rpart(),this.img + c.Ipart()));
    }

    public Complexe moins(Complexe c){
        return(new Complexe(this.réel - c.Rpart(),this.img - c.Ipart()));
    }

    public Complexe mult(Complexe c){
        return(new Complexe(this.réel*c.Rpart()-this.img*c.Ipart(),this.img*c.Ipart()+this.réel*c.Ipart()));
    }

    public Complexe conj(){
        return(new Complexe(this.réel,-this.img));

    }

    public Complexe div(Complexe c2){
        Complexe c1 = new Complexe(this.réel,this.img);
        double r = c1.rho()/c2.rho();
        double t = c1.theta()-c2.theta();
        return(polComplexe(r, t));
    }

    public boolean égal(Complexe c){
        return(this.réel == c.Rpart() && this.img == c.Ipart());
    }

    public boolean différent(Complexe c){
        return(!(this.réel == c.Rpart() && this.img == c.Ipart()));
    }

}

