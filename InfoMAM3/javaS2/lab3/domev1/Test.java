package lab3.domev1;

 class Test {
	
	public static void main(String[] s) {
		// la base de données
		Database db = new Database();
		// un CD
		CD c1 = new CD("Cello","bumcello",10,23);
		db.addCD(c1);
		db.list();
		c1.setComment("très bien");
		db.list();
		// un DVD
		DVD d = new DVD("Terminator","Moi",200);
		db.addDVD(d);
		db.list();
	}
}
