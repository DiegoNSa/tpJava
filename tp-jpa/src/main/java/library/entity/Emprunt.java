package library.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="emprunt")
public class Emprunt {
	@Id
	private Integer id;

	@ManyToMany
	@JoinTable(name="COMPO",
		joinColumns=@JoinColumn(name="ID_EMP",referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="ID_LIV",referencedColumnName="ID")
	)
	private Set<Livre> livres;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_DEBUT",nullable=false)
	private Date dateDebut;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_FIN",nullable=false)
	private Date dateFin;
	
	@Column(name="DELAI",length=10,nullable=false)
	private Integer delai;
	
	
	@ManyToOne
	@JoinColumn(name="ID_CLIENT")
	private Client client;
	
	public Emprunt() {
		livres= new HashSet<Livre>();
	}
	
	//-------get set-----------------
	public void setId(Integer newId) {
		id=newId;
	}
	public Integer getId() {
		return id;
	}
	
	public Date getDateDebut() {
		return dateDebut;
	}
	
	public void setDateDebut(Date newDate) {
		dateDebut = newDate;
	}
	
	public Date getDateFin() {
		return dateFin;
	}
	
	public void setDateFin(Date newDate) {
		dateFin = newDate;
	}
	
	public Integer getDelai() {
		return delai;
	}
	public void setDelai(Integer newDelai) {
		delai = newDelai;
	}
	
	public Set<Livre> getLivres(){
		return livres;
	}
	
	public void setLivres(Set<Livre> newLivres) {
		livres = newLivres;
	}
	
	public Client getClient(){
		return client;
	}
	
	public void setClient(Client newClient) {
		client = newClient;
	}
}
