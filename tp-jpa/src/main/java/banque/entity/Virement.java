package banque.entity;

import javax.persistence.*;

@Entity
@Table(name="virement")
public class Virement extends Operation {
	@Column(name="BENFICIAIRE",length=200,nullable=false)
	private String beneficiaire;

	
	//----- get set -----
	public String getBeneficiaire() {
		return beneficiaire;
	}

	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}
}
