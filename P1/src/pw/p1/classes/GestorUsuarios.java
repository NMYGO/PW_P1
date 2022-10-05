package pw.p1.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

//CREAR USUARIO CON LA FECHA ACTUAL DEL SISTEMA

/**
 * Clase que Gestiona los usuarios
 * @author 
 * */

public class GestorUsuarios {
	
	/* Atributos */
	
	public ArrayList<Usuario> arrayUsuarios = new ArrayList<Usuario>();
	
	/**
	 * Funcion que registra un usuario comprobando que no exista antes
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param nacimiento Nacimiento del usuario
	 * @param correo Correo unico del usuario
	 * @author 
	 * */

	public Boolean registrarUsuario(String nombre, String apellidos, LocalDate nacimiento, String correo) {
		for (int i = 0; i < arrayUsuarios.size(); i++) {
			if (correo == arrayUsuarios.get(i).getCorreo()) {
				return false; //El usuario ya se encuentra registrado
			}
		}
		Usuario newUsuario = new Usuario(nombre, apellidos, nacimiento, correo);
		arrayUsuarios.add(newUsuario);				
		return true;//El usuario se añade exitosamente
	}
	
	/**
	 * Funcion para modificar un usuario existente
	 * @param correo Correo unico del usuario
	 * @return Devuelve TRUE  
	 * */

	public Boolean ModificarUsuario(String correo) { //TRUE si el usuario a modificar se encuentra en la lista
		for (int i = 0; i < arrayUsuarios.size(); i++) {
			if (correo == arrayUsuarios.get(i).getCorreo()) {
				Scanner modificacion = new Scanner(System.in); //Para leer las variables introducidas
				
				Integer opcion = 1;
				while (opcion != 0) {
					System.out.println("Introduzca una opcion:\n"
					+ "0: Terminar modificacion.\n"
					+ "1: Cambiar Nombre.\n"
					+ "2: Cambiar Apellidos.\n"
					+ "3: Cambiar fecha de nacimiento.\n"
					+ "4: Cambiar direccion de Correo."); 
					
					opcion = modificacion.nextInt();
					//opcion = System.in.read();
					
					switch (opcion) {
					case 0: 
						System.out.println("Terminando Modificaciones"); 
						
						break;
					case 1:		//Cambio de Nombre
						System.out.println("Introduzca el nuevo nombre");
						String nuevoNombre = modificacion.next();
						arrayUsuarios.get(i).setNombre(nuevoNombre);
	
						break;
							
					case 2:		//Cambio de Apellidos
						System.out.println("Introduzca los nuevos apellidos");
						String nuevoApellidos = modificacion.next();
						arrayUsuarios.get(i).setApellidos(nuevoApellidos);
	
						break;
					case 3:		//Cambio de fecha de nacimiento
						System.out.println("Introduzca la nueva fecha de nacimiento");
						String nuevaFechaNacimiento = modificacion.next();
						DateTimeFormatter formato= DateTimeFormatter.ofPattern("dd-MMM-yyyy");
						LocalDate date = LocalDate.parse(nuevaFechaNacimiento, formato);
						arrayUsuarios.get(i).setNacimiento(date);
						
						break;
						
					case 4:		//Cambio de correo
						System.out.println("Introduzca la nueva direccion de correo");
						String nuevoCorreo = modificacion.next();
						arrayUsuarios.get(i).setCorreo(nuevoCorreo);
	
						break;
					default:
						System.out.println("Opcion no reconocida");
						
						break;
					}
				}
				modificacion.close();
				return true;
			}
		}
		return false; //NO SE HA ENCONTRADO EL USUARIO
	}
	
	public void listarUsuarios() {
		for (int i = 0; i < arrayUsuarios.size(); i++) {
			System.out.println(arrayUsuarios.get(i).toString());
		}
	}
}
