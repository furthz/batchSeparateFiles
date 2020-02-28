package pe.soapros.bean;

public class Client {

	private String nombres;
	
	private String direccion;
	
	private String ubicacion;
	
	private String pan;
	
	private String numDOc;

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getNumDOc() {
		return numDOc;
	}

	public void setNumDOc(String numDOc) {
		this.numDOc = numDOc;
	}

	@Override
	public String toString() {
		return "Client [nombres=" + nombres + ", direccion=" + direccion + ", ubicacion=" + ubicacion + ", pan=" + pan
				+ ", numDOc=" + numDOc + "]";
	}

	public Client(String nombres, String direccion, String ubicacion, String pan, String numDOc) {
		super();
		this.nombres = nombres;
		this.direccion = direccion;
		this.ubicacion = ubicacion;
		this.pan = pan;
		this.numDOc = numDOc;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		
		Client cli = (Client) obj;
		
		if( this.pan.equals(cli.getPan())) {
			return true;
		}else {
			return false;
		}
		
		//return super.equals(obj);
	}

	public Client() {
		
	}
}
