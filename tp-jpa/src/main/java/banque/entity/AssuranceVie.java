package banque.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class AssuranceVie extends Compte{
	@Column(name="TAUX",length=50,nullable=false)
	private Double taux;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_FIN",nullable=false)
	private Date dateFin;

	//----- get set -----
	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}
	
}
