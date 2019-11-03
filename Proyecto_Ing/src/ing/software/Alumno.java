
package ing.software;

public class Alumno {
    
    private String nombre;
    private String apellido_pa;
    private String apellido_ma;
    private String curp;
    private String telefono;
    private String alergia;
    private String email;
    private String enfermedad;
    private int edad;
    private boolean estudia;
    private float promedio;
    private boolean[] curso;
    
    private String nom_padre;
    private String ap_padre;
    private String cel_padre;
    private String nom_madre;
    private String ap_madre;
    private String cel_madre;
    private String email_padre;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido_pa, String apellido_ma, String curp, String telefono, String alergia, String email, String enfermedad, int edad, boolean estudia, float promedio, boolean[] curso, String nom_padre, String ap_padre, String cel_padre, String nom_madre, String ap_madre, String cel_madre, String email_padre) {
        this.nombre = nombre;
        this.apellido_pa = apellido_pa;
        this.apellido_ma = apellido_ma;
        this.curp = curp;
        this.telefono = telefono;
        this.alergia = alergia;
        this.email = email;
        this.enfermedad = enfermedad;
        this.edad = edad;
        this.estudia = estudia;
        this.promedio = promedio;
        this.curso = curso;
        this.nom_padre = nom_padre;
        this.ap_padre = ap_padre;
        this.cel_padre = cel_padre;
        this.nom_madre = nom_madre;
        this.ap_madre = ap_madre;
        this.cel_madre = cel_madre;
        this.email_padre = email_padre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_pa() {
        return apellido_pa;
    }

    public void setApellido_pa(String apellido_pa) {
        this.apellido_pa = apellido_pa;
    }

    public String getApellido_ma() {
        return apellido_ma;
    }

    public void setApellido_ma(String apellido_ma) {
        this.apellido_ma = apellido_ma;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isEstudia() {
        return estudia;
    }

    public void setEstudia(boolean estudia) {
        this.estudia = estudia;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public boolean[] getCurso() {
        return curso;
    }

    public void setCurso(boolean[] curso) {
        this.curso = curso;
    }

    public String getNom_padre() {
        return nom_padre;
    }

    public void setNom_padre(String nom_padre) {
        this.nom_padre = nom_padre;
    }

    public String getAp_padre() {
        return ap_padre;
    }

    public void setAp_padre(String ap_padre) {
        this.ap_padre = ap_padre;
    }

    public String getCel_padre() {
        return cel_padre;
    }

    public void setCel_padre(String cel_padre) {
        this.cel_padre = cel_padre;
    }

    public String getNom_madre() {
        return nom_madre;
    }

    public void setNom_madre(String nom_madre) {
        this.nom_madre = nom_madre;
    }

    public String getAp_madre() {
        return ap_madre;
    }

    public void setAp_madre(String ap_madre) {
        this.ap_madre = ap_madre;
    }

    public String getCel_madre() {
        return cel_madre;
    }

    public void setCel_madre(String cel_madre) {
        this.cel_madre = cel_madre;
    }

    public String getEmail_padre() {
        return email_padre;
    }

    public void setEmail_padre(String email_padre) {
        this.email_padre = email_padre;
    }
}
 