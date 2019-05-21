package banque.entity;

import javax.persistence.*;

@Entity
@Table(name="livretA")
public class LivretA extends Compte{
	
	@Column(name="TAUX",length=50,nullable=false)
	private Double taux;

	
	//----- get set -----

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}
	
	
}
