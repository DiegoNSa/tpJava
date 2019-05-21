package banque.entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="operation")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Operation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE",nullable=false)
	private Date date;
	
	@Column(name="MONTANT",length=100,nullable=false)
	private Double montant;
	
	@Column(name="MOTIF",length=250)
	private String motif;
	
	@ManyToOne
	@JoinColumn(name="ID")
	private Compte compte;

	
	//----- get set -----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
}
