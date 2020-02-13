package modelo;

import java.util.Date;

public interface IPersona {

    public String getsDni();

    public void setsDni(String sDni);

    public String getsNombre();

    public void setsNombre(String sNombre);

    public String getsApellidos();

    public void setsApellidos(String sApellidos);

    public String getsDomicilio() ;

    public void setsDomicilio(String sDomicilio) ;

    public String getsEmail() ;

    public void setsEmail(String sEmail) ;

    public Date getdFechaNacimiento() ;

    public void setdFechaNacimiento(Date dFechaNacimiento) ;

}
