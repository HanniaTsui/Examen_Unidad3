
public class Alumno {

	private int matricula;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String telefono;
    private String paisNacimiento;
    private String email;
    private int anio;
    private String casa;
    private String genero;
    private String rutaFoto;

    // Constructor
    public Alumno(int matricula, String nombres, String apellidos, String fechaNacimiento,
                  String telefono, String paisNacimiento, String email, int anio,
                  String casa, String genero) {
        this.matricula = matricula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.paisNacimiento = paisNacimiento;
        this.email = email;
        this.anio = anio;
        this.casa = casa;
        this.genero = genero;
    }

    public Alumno() {
        this.matricula = 0;
        this.nombres = "";
        this.apellidos = "";
        this.fechaNacimiento = "";
        this.telefono = "";
        this.paisNacimiento = "";
        this.email = "";
        this.anio = 0;
        this.casa = "";
        this.genero = "";
    }
    
    public Alumno(int matricula) {
        this.matricula = matricula;
    }
    
    
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPaisNacimiento() {
		return paisNacimiento;
	}

	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getCasa() {
		return casa;
	}

	public void setCasa(String casa) {
		this.casa = casa;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	
	public String getRutaFoto() {
		return rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}



}
