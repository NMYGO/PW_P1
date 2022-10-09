package pw.p1.classes;
import pw.p1.classes.Pista.Dificultad;
import pw.p1.factory_reserva.RBonoCreador.Tipo;
import pw.p1.factory_reserva.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorReservas {

	/* Atributos */
	
	public ArrayList<Reserva> arrayReservaIndividual = new ArrayList<Reserva>();
	public ArrayList<RBonoCreador> arrayBonos = new ArrayList<RBonoCreador>();
	
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
						System.out.println("Introduzca la duración de la reserva (60/90/120 minutos)");
							int duracion = Integer.parseInt(scan_.nextLine());
						System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
							LocalDate fecha = LocalDate.parse(scan_.nextLine());
							switch(duracion) {
							case 60:
								precio = 20;
								break;
							case 90:
								precio = 30;
								break;
							case 120:
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
	
	
	
	public boolean ReservaIndividualAdulto(GestorReservas GestorReservas_, GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, int nParticipantes, Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");
		for (int i = 0; i < GestorUsuarios_.arrayUsuarios.size(); i++) {
			if (GestorUsuarios_.arrayUsuarios.get(i).getCorreo().equals(correo)) {
				if ((GestorUsuarios_.arrayUsuarios.get(i).getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
					System.out.println("Introduzca el numero de participantes (adultos)");
					int participantes= Integer.parseInt(scan_.nextLine());
					System.out.println("");
					ArrayList<Pista> lpista = GestorPistas_.pistasLibres(scan_, participantes, Dificultad.ADULTO);			
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
						System.out.println("Introduzca la duración de la reserva (60/90/120 minutos)");
							int duracion = Integer.parseInt(scan_.nextLine());
						System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
							LocalDate fecha = LocalDate.parse(scan_.nextLine());
							switch(duracion) {
							case 60:
								precio = 20;
								break;
							case 90:
								precio = 30;
								break;
							case 120:
								precio = 40;
								break;
							}	
						if(GestorUsuarios_.arrayUsuarios.get(i).getInscripcion().isBefore(LocalDate.now().minusYears(2))) {
							descuento = 10;
						}
						
						RIndividualCreador individualCreador = new RIndividualCreador();
						RAdulto newReserva = individualCreador.creaRAdu(GestorUsuarios_.arrayUsuarios.get(i).getNombre(), fecha, duracion, pista, precio, descuento, participantes);
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
	
	
	
	public boolean ReservaIndividualFamiliar(GestorReservas GestorReservas_, GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, int nParticipantes, Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");
		for (int i = 0; i < GestorUsuarios_.arrayUsuarios.size(); i++) {
			if (GestorUsuarios_.arrayUsuarios.get(i).getCorreo().equals(correo)) {
				if ((GestorUsuarios_.arrayUsuarios.get(i).getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
					System.out.println("Introduzca el numero de participantes (niños)");
						int participantesInfantiles= Integer.parseInt(scan_.nextLine());
						System.out.println("");
						System.out.println("Introduzca el numero de participantes (adultos)");
						int participantesAdultos= Integer.parseInt(scan_.nextLine());
						System.out.println("");
						int participantes = participantesAdultos + participantesInfantiles;
					ArrayList<Pista> lpista = GestorPistas_.pistasLibres(scan_, participantes, Dificultad.FAMILIAR);			
					for (int j = 0; j < lpista.size(); j++) {
						ArrayList<Kart> lkart = new ArrayList<Kart>();
						lkart = lpista.get(j).getLkart();
						int nKartInfantil = 0;
						int nKartAdulto = 0;
						for (int k = 0; k < lkart.size(); k++) {
							if (lkart.get(k).isTipo()) {
								nKartInfantil += 1;
							}else {
								nKartAdulto += 1;
							}
						}
						if (participantesInfantiles > nKartInfantil || participantesAdultos > nKartAdulto) {
							lpista.remove(j);
						}
					}
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
						System.out.println("Introduzca la duración de la reserva (60/90/120 minutos)");
							int duracion = Integer.parseInt(scan_.nextLine());
						System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
							LocalDate fecha = LocalDate.parse(scan_.nextLine());
							switch(duracion) {
							case 60:
								precio = 20;
								break;
							case 90:
								precio = 30;
								break;
							case 120:
								precio = 40;
								break;
							}	
						if(GestorUsuarios_.arrayUsuarios.get(i).getInscripcion().isBefore(LocalDate.now().minusYears(2))) {
							descuento = 10;
						}
						
						RIndividualCreador individualCreador = new RIndividualCreador();
						RFamiliar newReserva = individualCreador.creaRFam(GestorUsuarios_.arrayUsuarios.get(i).getNombre(), fecha, duracion, pista, precio, descuento, participantesInfantiles, participantesAdultos);
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
	
	
	
	public boolean ReservaBonoInfantil(GestorReservas GestorReservas_, GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");
		for (int i = 0; i < GestorUsuarios_.arrayUsuarios.size(); i++) {
			if (GestorUsuarios_.arrayUsuarios.get(i).getCorreo().equals(correo)) {
				if ((GestorUsuarios_.arrayUsuarios.get(i).getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
					if(arrayBonos.size() == 0) {
						System.out.println("No existe ningun bono. Creando nuevo bono...");
						RBonoCreador newBono = new RBonoCreador(1, 0, GestorUsuarios_.arrayUsuarios.get(i).getNombre(), Tipo.INFANTIL);
						arrayBonos.add(newBono);
					}
					boolean encontrado = false;
					for (int k = 0; k < arrayBonos.size(); k++) {						
						for (int e = 0; e < arrayBonos.size(); e++) {
							if(GestorUsuarios_.arrayUsuarios.get(i).getNombre().equals(arrayBonos.get(e).getbUsuario())) {
								encontrado = true;
								break;
							}
						}
						if(!encontrado) {
							System.out.println("No hay un bono asociado a este usuario. Creando nuevo bono...");
								RBonoCreador newBono = new RBonoCreador(arrayBonos.size()+1, 0, GestorUsuarios_.arrayUsuarios.get(i).getNombre(), Tipo.INFANTIL);
								arrayBonos.add(newBono);
							System.out.println("");
						}
						if(arrayBonos.get(k).getbUsuario().equals(GestorUsuarios_.arrayUsuarios.get(i).getNombre())) {
							if (arrayBonos.get(k).arrayReservas.size() < 5) {
								if(arrayBonos.get(k).getTipo() == Tipo.INFANTIL) {
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
										int descuento = 5;
										System.out.println("Introduzca la duración de la reserva (60/90/120 minutos)");
											int duracion = Integer.parseInt(scan_.nextLine());
										System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
											LocalDate fecha = LocalDate.parse(scan_.nextLine());
											switch(duracion) {
											case 60:
												precio = 20;
												break;
											case 90:
												precio = 30;
												break;
											case 120:
												precio = 40;
												break;
											}
										
										RBonoCreador BonoCreador = new RBonoCreador();
										RInfantil newReserva = BonoCreador.creaRInf(GestorUsuarios_.arrayUsuarios.get(i).getNombre(), fecha, duracion, pista, precio, descuento, participantes);
										arrayBonos.get(k).arrayReservas.add(newReserva);
										int sesion = arrayBonos.get(k).getSesion();
										arrayBonos.get(k).setSesion(sesion + 1);
										System.out.println("Reserva creada con exito");
										System.out.println("-------------------------------------");
										System.out.println("");
									} else {
										System.out.println("Error. No existen pistas libres");
										System.out.println("");
										return false;
									}
								} else {
									System.out.println("Error. Las reservas de un bono solo pueden ser de un tipo");
									System.out.println("");
									return false;
								}
							} else {
								System.out.println("Error. Solo puede haber hasta 5 reservas en un bono");
								System.out.println("");
								return false;
							}
						}
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
	
	public boolean ReservaBonoAdulto(GestorReservas GestorReservas_, GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");
		for (int i = 0; i < GestorUsuarios_.arrayUsuarios.size(); i++) {
			if (GestorUsuarios_.arrayUsuarios.get(i).getCorreo().equals(correo)) {
				if ((GestorUsuarios_.arrayUsuarios.get(i).getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
					if(arrayBonos.size() == 0) {
						System.out.println("No existe ningun bono. Creando nuevo bono...");
						RBonoCreador newBono = new RBonoCreador(1, 0, GestorUsuarios_.arrayUsuarios.get(i).getNombre(), Tipo.ADULTO);
						arrayBonos.add(newBono);
					}
					boolean encontrado = false;
					for (int k = 0; k < arrayBonos.size(); k++) {						
						for (int e = 0; e < arrayBonos.size(); e++) {
							if(GestorUsuarios_.arrayUsuarios.get(i).getNombre().equals(arrayBonos.get(e).getbUsuario())) {
								encontrado = true;
								break;
							}
						}
						if(!encontrado) {
							System.out.println("No hay un bono asociado a este usuario. Creando nuevo bono...");
								RBonoCreador newBono = new RBonoCreador(arrayBonos.size()+1, 0, GestorUsuarios_.arrayUsuarios.get(i).getNombre(), Tipo.ADULTO);
								arrayBonos.add(newBono);
							System.out.println("");
						}
						if(arrayBonos.get(k).getbUsuario().equals(GestorUsuarios_.arrayUsuarios.get(i).getNombre())) {
							if (arrayBonos.get(k).arrayReservas.size() < 5) {
								if(arrayBonos.get(k).getTipo() == Tipo.ADULTO) {
									System.out.println("Introduzca el numero de participantes (adultos)");
									int participantes= Integer.parseInt(scan_.nextLine());
									System.out.println("");
									ArrayList<Pista> lpista = GestorPistas_.pistasLibres(scan_, participantes, Dificultad.ADULTO);			
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
										int descuento = 5;
										System.out.println("Introduzca la duración de la reserva (60/90/120 minutos)");
											int duracion = Integer.parseInt(scan_.nextLine());
										System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
											LocalDate fecha = LocalDate.parse(scan_.nextLine());
											switch(duracion) {
											case 60:
												precio = 20;
												break;
											case 90:
												precio = 30;
												break;
											case 120:
												precio = 40;
												break;
											}
										
										RBonoCreador BonoCreador = new RBonoCreador();
										RAdulto newReserva = BonoCreador.creaRAdu(GestorUsuarios_.arrayUsuarios.get(i).getNombre(), fecha, duracion, pista, precio, descuento, participantes);
										arrayBonos.get(k).arrayReservas.add(newReserva);
										int sesion = arrayBonos.get(k).getSesion();
										arrayBonos.get(k).setSesion(sesion + 1);
										System.out.println("Reserva creada con exito");
										System.out.println("-------------------------------------");
										System.out.println("");
									} else {
										System.out.println("Error. No existen pistas libres");
										System.out.println("");
										return false;
									}
								} else {
									System.out.println("Error. Las reservas de un bono solo pueden ser de un tipo");
									System.out.println("");
									return false;
								}
							} else {
								System.out.println("Error. Solo puede haber hasta 5 reservas en un bono");
								System.out.println("");
								return false;
							}
						}
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
	
	public boolean ReservaBonoFamiliar(GestorReservas GestorReservas_, GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");
		for (int i = 0; i < GestorUsuarios_.arrayUsuarios.size(); i++) {
			if (GestorUsuarios_.arrayUsuarios.get(i).getCorreo().equals(correo)) {
				if ((GestorUsuarios_.arrayUsuarios.get(i).getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
					if(arrayBonos.size() == 0) {
						System.out.println("No existe ningun bono. Creando nuevo bono...");
						RBonoCreador newBono = new RBonoCreador(1, 0, GestorUsuarios_.arrayUsuarios.get(i).getNombre(), Tipo.FAMILIAR);
						arrayBonos.add(newBono);
					}
					boolean encontrado = false;
					for (int k = 0; k< arrayBonos.size(); k++) {
						for (int e = 0; e < arrayBonos.size(); e++) {
							if(GestorUsuarios_.arrayUsuarios.get(i).getNombre().equals(arrayBonos.get(e).getbUsuario())) {
								encontrado = true;
								break;
							}
						}
						if(!encontrado) {
							System.out.println("No hay un bono asociado a este usuario. Creando nuevo bono...");
								RBonoCreador newBono = new RBonoCreador(arrayBonos.size()+1, 0, GestorUsuarios_.arrayUsuarios.get(i).getNombre(), Tipo.FAMILIAR);
								arrayBonos.add(newBono);
							System.out.println("");
						}
						if(arrayBonos.get(k).getbUsuario().equals(GestorUsuarios_.arrayUsuarios.get(i).getNombre())) {
							if (arrayBonos.get(k).arrayReservas.size() < 5) {
								if(arrayBonos.get(k).getTipo() == Tipo.FAMILIAR) {
									System.out.println("Introduzca el numero de participantes (niños)");
									int ninos= Integer.parseInt(scan_.nextLine());
									System.out.println("");
									System.out.println("Introduzca el numero de participantes (adultos)");
									int adultos= Integer.parseInt(scan_.nextLine());
									System.out.println("");
									int participantes=ninos+adultos;
									ArrayList<Pista> lpista = GestorPistas_.pistasLibres(scan_, participantes, Dificultad.FAMILIAR);			
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
										int descuento = 5;
										System.out.println("Introduzca la duración de la reserva (60/90/120 minutos)");
											int duracion = Integer.parseInt(scan_.nextLine());
										System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
											LocalDate fecha = LocalDate.parse(scan_.nextLine());
											switch(duracion) {
											case 60:
												precio = 20;
												break;
											case 90:
												precio = 30;
												break;
											case 120:
												precio = 40;
												break;
											}
										
										RBonoCreador BonoCreador = new RBonoCreador();
										RFamiliar newReserva = BonoCreador.creaRFam(GestorUsuarios_.arrayUsuarios.get(i).getNombre(), fecha, duracion, pista, precio, descuento, ninos, adultos);
										arrayBonos.get(k).arrayReservas.add(newReserva);
										int sesion = arrayBonos.get(k).getSesion();
										arrayBonos.get(k).setSesion(sesion + 1);
										System.out.println("Reserva creada con exito");
										System.out.println("-------------------------------------");
										System.out.println("");
									} else {
										System.out.println("Error. No existen pistas libres");
										System.out.println("");
										return false;
									}
								} else {
									System.out.println("Error. Las reservas de un bono solo pueden ser de un tipo");
									System.out.println("");
									return false;
								}
							} else {
								System.out.println("Error. Solo puede haber hasta 5 reservas en un bono");
								System.out.println("");
								return false;
							}
						}
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
	
	public void consultarReservasFuturasIndividuales(Scanner scan_){
		System.out.println("Introduzca su nombre de usuario");
		String usuario = scan_.nextLine();
		System.out.println("");
		for(int i = 0; i < arrayReservaIndividual.size(); i++) {
				if(arrayReservaIndividual.get(i).getUsuario().equals(usuario) && arrayReservaIndividual.get(i).getFecha().isAfter(LocalDate.now())) {
					System.out.println(arrayReservaIndividual.get(i).toString());
				}
		}
	}
	
	/**
	 * @return
	 */
	public void consultarReservasFuturasBono(Scanner scan_){
		System.out.println("Introduzca su nombre de usuario");
		String usuario = scan_.nextLine();
		System.out.println("");
		for(int i = 0; i < arrayBonos.size(); i++) {
				if(arrayBonos.get(i).getbUsuario().equals(usuario)) {
					System.out.println(arrayBonos.get(i).toString());
					for(int j = 0; j < arrayBonos.get(i).getArrayReservas().size(); j++) {
						if(arrayBonos.get(i).getArrayReservas().get(j).getFecha().isAfter(LocalDate.now())) {
							System.out.println(arrayBonos.get(i).getArrayReservas().get(j).toString());
						}
					}
				}
		}
	}

	/**
	 * @param nombrepista
	 * @param fechareserva
	 */
	public void consultarReservaEspecifica(GestorPistas GestorPistas_, GestorReservas GestorReservas_, Scanner scan_){
		System.out.println("Introduzca su nombre de usuario");
			String usuario = scan_.nextLine();
		System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
			LocalDate fecha = LocalDate.parse(scan_.nextLine());
		System.out.println("Introduzca la pista de la reserva");
			String pista = scan_.nextLine();
			System.out.println("");
		System.out.println("0. Consultar reserva individual");
        System.out.println("1. Consultar reserva bono");
        	System.out.println("");
        System.out.println("Elija una opcion escribiendo su numero");
        int option_= Integer.parseInt(scan_.nextLine());
        if(option_ == 0) {
        	for(int i=0; i < arrayReservaIndividual.size() ; i++){
        		if(arrayReservaIndividual.get(i).getUsuario().equals(usuario)) {
	        		if(arrayReservaIndividual.get(i).getFecha().isEqual(fecha) && arrayReservaIndividual.get(i).getPista().equals(pista)){
	    				System.out.println(arrayReservaIndividual.get(i).toString());
	    			}
        		}
        	}
        }else if(option_ == 1) {
        	for(int i = 0; i < arrayBonos.size(); i++){
        		if(arrayBonos.get(i).getbUsuario().equals(usuario)) {
	        		for(int j = 0; j < arrayBonos.size(); j++){
		        		if(arrayBonos.get(i).getArrayReservas().get(j).getFecha().isEqual(fecha) && arrayBonos.get(i).getArrayReservas().get(j).getPista().equals(pista)){
		        			System.out.println(arrayBonos.get(i).toString());
		        			System.out.println(arrayBonos.get(i).getArrayReservas().get(j).toString());
		    			}
	        		}
        		}
        	}
        }else {
        	System.out.println("Opcion no reconocida");
			System.out.println("");
        }
	}

	/**
	 * @param usuario
	 * @param fecha
	 * @param duracion
	 * @param pista
	 * @param precio
	 * @param descuento
	 */
	public void eliminarReserva(Scanner scan_){
		System.out.println("Introduzca su nombre de usuario");
		String usuario = scan_.nextLine();
		System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
		LocalDate fecha = LocalDate.parse(scan_.nextLine());
		System.out.println("Introduzca la pista de la reserva");
		String pista = scan_.nextLine();
		System.out.println("");
		int duracion = Integer.parseInt(scan_.nextLine());
		System.out.println("");
		for(int i=0 ; i< arrayReservaIndividual.size(); i++){
			if (arrayReservaIndividual.get(i).getUsuario() == usuario &&
				arrayReservaIndividual.get(i).getFecha() == fecha &&
				arrayReservaIndividual.get(i).getDur() == duracion &&
				arrayReservaIndividual.get(i).getPista() == pista
			){
				arrayReservaIndividual.remove(i);
			}
		}
	}

	/**
	 * @param usuario
	 * @param fecha
	 * @param nuevafecha
	 * @param nuevaduracion
	 * @param nuevapista
	 * @param nuevoprecio
	 * @param nuevodescuento
	 */
	public void modificarReserva(Scanner scan_){
		System.out.println("Introduzca su nombre de usuario");
		String usuario = scan_.nextLine();
		System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
		LocalDate fecha = LocalDate.parse(scan_.nextLine());
		for(int i=0 ; i< arrayReservaIndividual.size(); i++){
			if (arrayReservaIndividual.get(i).getUsuario() == usuario && arrayReservaIndividual.get(i).getFecha().isEqual(fecha)){
				System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
				LocalDate nuevafecha = LocalDate.parse(scan_.nextLine());
				System.out.println("Introduzca la pista de la reserva");
				String nuevapista = scan_.nextLine();
				System.out.println("");
				int nuevaduracion = Integer.parseInt(scan_.nextLine());
				System.out.println("");
				int nuevoprecio=0;
				switch(nuevaduracion) {
				case 60:
					nuevoprecio = 20;
					break;
				case 90:
					nuevoprecio = 30;
					break;
				case 120:
					nuevoprecio = 40;
					break;
				}
				arrayReservaIndividual.get(i).setFecha(nuevafecha);
				arrayReservaIndividual.get(i).setDur(nuevaduracion);
				arrayReservaIndividual.get(i).setPista(nuevapista);
				arrayReservaIndividual.get(i).setPrecio(nuevoprecio);
			}
		}
}
}


