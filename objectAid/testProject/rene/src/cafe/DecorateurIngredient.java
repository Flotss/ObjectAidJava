package cafe;

public abstract class DecorateurIngredient extends Boisson {
	protected Boisson composant;
	protected double prix;

	
	public DecorateurIngredient(double p, String d, Boisson c){
		this.prix=p;
		this.description=d;
		this.composant=c;
	}
	
	public String getDescription(){
		return this.description + composant.getDescription();
	}
	
	public double cout(){
		return this.prix + composant.cout();
	}

	public String toString(){
		return this.getDescription() + " cout : " + this.cout() + " euros";
	}

		
}
