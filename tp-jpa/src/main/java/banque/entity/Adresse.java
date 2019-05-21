package banque.entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Embeddable
public class Adresse {
	
	@Column(name="NUMERO",length=10,nullable=false)
	private Integer numero;
	
	@Column(name="RUE",length=250,nullable=false)
	private String rue;
	
	@Column(name="COOD_POSTAL",length=10,nullable=false)
	private Integer codePostal;
	
	@Column(name="VILLE",length=100,nullable=false)
	private String ville;

	
	//----- get set -----
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
}
