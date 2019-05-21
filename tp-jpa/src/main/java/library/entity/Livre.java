package library.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="livre")
public class Livre {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="TITRE",length=255,nullable=false)
	private String titre;
	
	@Column(name="AUTEUR",length=50,nullable=false)
	private String auteur;
	
	@ManyToMany
	@JoinTable(name="COMPO",
		joinColumns=@JoinColumn(name="ID_LIV",referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="ID_EMP",referencedColumnName="ID")
	)
	private Set<Emprunt> emprunts;
	
	
	public Livre() {	
		emprunts= new HashSet<Emprunt>();
	}
	
	public void setId(Integer newId) {
		id=newId;
	}
	public Integer getId() {
		return id;
	}
	
	public void setTitre(String newTitre) {
		titre=newTitre;
	}
	public String getTitre() {
		return titre;
	}
	
	public void setAuteur(String newAuteur) {
		auteur=newAuteur;
	}
	public String getAuteur() {
		return auteur;
	}
	
}
