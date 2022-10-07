package pw.p1.classes;
import pw.p1.classes.Pista.Dificultad;
import pw.p1.factory_reserva.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorReservas {

	/* Atributos */
	
	public ArrayList<Reserva> arrayReservaIndividual = new ArrayList<Reserva>();
	ArrayList<RBonoCreador> bonos = new ArrayList<RBonoCreador>();
	
	/**
	 * Constructor por defecto
	 **/	
	public GestorReservas(){}
	
	/**
	 * Realiza una reserva individual
	 **/
	
	public boolean ReservaIndividualInfantil(GestorReservas GestorReservas_, GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, int nParticipantes, Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");
		for (int i = 0; i < GestorUsuarios_.arrayUsuarios.size(); i++) {
			if (GestorUsuarios_.arrayUsuarios.get(i).getCorreo().equals(correo)) {
				if ((GestorUsuarios_.arrayUsuarios.get(i).getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
					System.out.println("Introduzca el numero de participantes (niños)");
						int participantes= Integer.parseInt(scan_.nextLine());
						System.out.println("");
					ArrayList<Pista> lpista = GestorPistas_.pistasLibres(scan_, participantes, Dificultad.INFANTIL);			
					if(lpista.size() > 0) {
						System.out.println("Pistas libres:");
						for (int j = 0; j < lpista.size(); j++) {
							System.out.println(lpista.get(j).toString());
						}
						System.out.println("");
						System.out.println("Escoja una pista mediante su nombre");
						String pista = scan_.nextLine();
						System.out.println("");
						boolean valido = false;
						while(!valido) {
							for (int j = 0; j < lpista.size(); j++) {
								if(!lpista.get(j).getNombre().equals(pista)) {
									System.out.println("Escoja una pista mediante su nombre");
									pista = scan_.nextLine();
									System.out.println("");
								}else {
									valido = true;
								}
							}
						}
						float precio = 0;
						int descuento = 0;
						System.out.println("Introduzca la duración de la reserva (30/60/90 minutos)");
							int duracion = Integer.parseInt(scan_.nextLine());
						System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
							LocalDate fecha = LocalDate.parse(scan_.nextLine());
							switch(duracion) {
							case 30:
								precio = 20;
								break;
							case 60:
								precio = 30;
								break;
							case 90:
								precio = 40;
								break;
							}	
						if(GestorUsuarios_.arrayUsuarios.get(i).getInscripcion().isBefore(LocalDate.now().minusYears(2))) {
							descuento = 10;
						}
						
						RIndividualCreador individualCreador = new RIndividualCreador();
						RInfantil newReserva = individualCreador.creaRInf(GestorUsuarios_.arrayUsuarios.get(i).getNombre(), fecha, duracion, pista, precio, descuento, participantes);
						arrayReservaIndividual.add(newReserva);
						System.out.println("Reserva creada con exito");
						System.out.println("-------------------------------------");
						System.out.println("");
					} else {
						System.out.println("Error. No existen pistas libres");
						System.out.println("");
						return false;
					}
				} else {
					System.out.println("Error. Las reservas se realizan solo por adultos");
					System.out.println("");
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	public boolean ReservaIndividualAdulto(Usuario usuario, Pista pista, int nParticipantes, Scanner scan_, GestorPistas GestorPistas_) {
		if ((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
			ArrayList<Kart> dkart = pista.consultarKartsDisponibles(GestorPistas_.arrayKarts);
			int karts = 0;
			for (int i = 0;i < dkart.size() ; i++) {
				if ((dkart.get(i)).isTipo() == false) {
					karts++;
				}
			}
			if(karts>=nParticipantes) {
				RIndividualCreador individualCreador = new RIndividualCreador();
				RAdulto reserva = individualCreador.creaRAdu();
				System.out.println("Introduzca la duración de la reserva (30/60/90 minutos)");
				int tiempo = Integer.parseInt(scan_.nextLine());
				System.out.println("Introduzca la fecha de la reserva");
				LocalDate fecha = LocalDate.parse(scan_.nextLine());
				reserva.setDur(tiempo);
				reserva.setFecha(fecha);
				reserva.setParticipantes(nParticipantes);
				reserva.setUsuario(usuario.getNombre());
				reserva.setPista(pista.getNombre());
				switch(tiempo) {
				case 30:
					reserva.setPrecio(20);
					break;
				case 60:
					reserva.setPrecio(30);
					break;
				case 90:
					reserva.setPrecio(40);
					break;
				}
				if(usuario.getInscripcion().isBefore(LocalDate.now().minusYears(2))) {
					reserva.setDesc(10);
				}
				else reserva.setDesc(0);
			}
			else {
				System.out.println("No hay suficientes karts en esa pista para los participantes.\n");
				return false;
			}
		}
		else {
			System.out.println("No se puede hacer una reserva para adultos con un usuario menor de edad.\n");
			return false;
		}
		
		return true;
	}
	
	
	
	public boolean ReservaIndividualFamiliar(Usuario usuario, Pista pista, Scanner scan_, GestorPistas GestorPistas_) {
		if ((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
			ArrayList<Kart> dkart = pista.consultarKartsDisponibles(GestorPistas_.arrayKarts);
			int karts = 0;
			int nParticipantes;
			System.out.println("Introduzca numero de adultos");
			int adultos = Integer.parseInt(scan_.nextLine());
			System.out.println("Introduzca numero de niños");
			int ninos = Integer.parseInt(scan_.nextLine());
			for (int i = 0;i < adultos ; i++) {
				if ((dkart.get(i)).isTipo() == false) {
					karts++;
				}
			}
			for (int i = 0; i< ninos; i++) {
				if ((dkart.get(i)).isTipo() == true) {
					karts++;
				}
			}
			nParticipantes=adultos+ninos;
			if(dkart.size()>=nParticipantes) {
				RIndividualCreador individualCreador = new RIndividualCreador();
				RFamiliar reserva = individualCreador.creaRFam();
				System.out.println("Introduzca la duración de la reserva (30/60/90 minutos)");
				int tiempo = Integer.parseInt(scan_.nextLine());
				System.out.println("Introduzca la fecha de la reserva");
				LocalDate fecha = LocalDate.parse(scan_.nextLine());
				reserva.setDur(tiempo);
				reserva.setFecha(fecha);
				reserva.setadultos(adultos);
				reserva.setninos(ninos);
				reserva.setUsuario(usuario.getNombre());
				reserva.setPista(pista.getNombre());
				switch(tiempo) {
				case 30:
					reserva.setPrecio(20);
					break;
				case 60:
					reserva.setPrecio(30);
					break;
				case 90:
					reserva.setPrecio(40);
					break;
				}
				if(usuario.getInscripcion().isBefore(LocalDate.now().minusYears(2))) {
					reserva.setDesc(10);
				}
				else reserva.setDesc(0);
			}
			else {
				System.out.println("No hay suficientes karts en esa pista para los participantes.\n");
				return false;
			}
		}
		else {
			System.out.println("No se puede hacer una reserva para adultos con un usuario menor de edad.\n");
			return false;
		}
		
		return true;
	}
	
	
	
	public boolean ReservaBono(Usuario usuario, Pista pista, int nparticipantes, int cBono) {
		boolean encontrado=false;
		for (int i = 0; i < bonos.size(); i++)
			if (cBono == bonos.get(i).getId())
				encontrado=true;
		if(!encontrado) {
			System.out.println("El bono usado no existe\n");
			return false;
		}
			
		return true;
	}
}
