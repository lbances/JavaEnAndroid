package firebase.app.crudconfirebase_leonardobances;

import android.os.Parcel;
import android.os.Parcelable;

public class Alumno implements Parcelable {

    private int dni;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasema;

    public Alumno() {
    }

    public Alumno(int dni, String nombre, String apellido, String correo, String contrasema) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasema = contrasema;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasema() {
        return contrasema;
    }

    public void setContrasema(String contrasema) {
        this.contrasema = contrasema;
    }

    @Override
    public String toString() {
        return nombre;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.dni);
        dest.writeString(this.nombre);
        dest.writeString(this.apellido);
        dest.writeString(this.correo);
        dest.writeString(this.contrasema);
    }

    public void readFromParcel(Parcel source) {
        this.dni = source.readInt();
        this.nombre = source.readString();
        this.apellido = source.readString();
        this.correo = source.readString();
        this.contrasema = source.readString();
    }

    protected Alumno(Parcel in) {
        this.dni = in.readInt();
        this.nombre = in.readString();
        this.apellido = in.readString();
        this.correo = in.readString();
        this.contrasema = in.readString();
    }

    public static final Parcelable.Creator<Alumno> CREATOR = new Parcelable.Creator<Alumno>() {
        @Override
        public Alumno createFromParcel(Parcel source) {
            return new Alumno(source);
        }

        @Override
        public Alumno[] newArray(int size) {
            return new Alumno[size];
        }
    };
}
