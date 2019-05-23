package banque.entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="client")
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NOM",length=50,nullable=false)
	private String nom;

	@Column(name="PRENOM",length=50,nullable=false)
	private String prenom;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_NAISSANCE",nullable=false)
	private Date dateNaissance;
	
	@ManyToOne
	@JoinColumn(name="BANQUE_ID")
	private Banque banque;

	@Embedded
	private Adresse adresse;

	@ManyToMany
	@JoinTable(name="CLI_COM",
	joinColumns=@JoinColumn(name="ID_CLI",referencedColumnName="ID"),
	inverseJoinColumns=@JoinColumn(name="ID_COM",referencedColumnName="ID"))
	private Set<Compte> comptes;
	
	public Client() {
		comptes = new HashSet<Compte>();
	}
	//----- get set -----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	public Set<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}

}
