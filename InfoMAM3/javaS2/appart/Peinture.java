package appart;

/**
 * Peinture Un pot = 1L
 */
public class Peinture {
	Double reference;
	Double pvCouvrant;

	public Double getReference() {
		return reference;
	}

	public Double getPvCouvrant() {
		return pvCouvrant;
	}

	public Peinture(Double reference, Double pvCouvrant) {
		this.reference = reference;
		this.pvCouvrant = pvCouvrant;
	}

}