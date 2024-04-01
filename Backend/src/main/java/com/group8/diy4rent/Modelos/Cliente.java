import javax.persistence.*;

@Entity
@Table(name = "clientes")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;
    
    @Column(name = "email")
    private String email;

    @Column(name = "coordenadas")
    private String coordenadas;

    @Column(name = "iban")
    private String iban;

    public Cliente() {

    }

    public Cliente(String nombre, String apellidos, String email, String coordenadas, String iban) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.coordenadas = coordenadas;
        this.iban = iban;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Long getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

}