package library.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="client")
public class Client {
	@Id
	private Integer id;


	@Column(name="NOM",length=50,nullable=false)
	private String nom;
	
	@Column(name="PRENOM",length=50,nullable=false)
	private String prenom;
	
	@OneToMany(mappedBy="client")
	private Set<Emprunt> emprunts;

	public Client(){
		emprunts = new HashSet<Emprunt>();
	}
	//-------get set-----------------
	public void setId(Integer newId) {
		id=newId;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setNom(String newNom) {
		nom=newNom;
	}
	public String getNom() {
		return nom;
	}
	
	public void setPrenom(String newPrenom) {
		prenom=newPrenom;
	}
	public String getPrenom() {
		return prenom;
	}
	
	public Set<Emprunt> getEmprunts(){
		return emprunts;
	}
	
	public void setEmprunts(Set<Emprunt> newEmprunts) {
		emprunts = newEmprunts;
	}
}
