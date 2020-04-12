package plateformePFE.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Fiche")
public class FichePFE {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


}
