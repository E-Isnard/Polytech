public interface ArbreBinaire<T extends Comparable<T>> {
	
	public boolean isEmpty();

	public T getValue() throws ArbreVideException;

	
	public ArbreBinaire<T> getLeft() throws ArbreVideException;

	public ArbreBinaire<T> getRight() throws ArbreVideException;
}
