package service;

import java.net.Socket;

import bibliotheque.Bibliotheque;

public class ServiceFactory {
	
	public static Service creerService(Socket socket, Bibliotheque bibliotheque,int port){
		Service service = null;
		
		if(port==2600){
			service= new ServiceEmprunt(socket,bibliotheque);
		}
		else if(port==2500){
			service= new ServiceReservation(socket,bibliotheque);
		}
		else if(port==2700){
			service= new ServiceRetour(socket,bibliotheque);
		}
		
		return service;	
	}

}
