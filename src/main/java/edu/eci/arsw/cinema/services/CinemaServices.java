/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */
@Service
public class CinemaServices {
    @Autowired
    CinemaPersitence cps=null;
    
    public void addNewCinema(Cinema c){
        
    }
    
    public Set<Cinema> getAllCinemas() throws CinemaPersistenceException{
        return cps.getAllCinema();
    }
    
    /**
     * 
     * @param name cinema's name
     * @return the cinema of the given name created by the given author
     * @throws CinemaException
     * @throws edu.eci.arsw.cinema.persistence.CinemaPersistenceException
     */
    public Cinema getCinemaByName(String name) throws CinemaException, CinemaPersistenceException{
        return cps.getCinema(name);
        //throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException{
        //throw new UnsupportedOperationException("Not supported yet."); 
        cps.buyTicket(row, col, cinema, date, movieName);
    }
    
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        //throw new UnsupportedOperationException("Not supported yet.");
        return cps.getFunctionsbyCinemaAndDate(cinema, date);
    }
    
    public List<Movie> getFunctionsbyCinemaDateAndGenre(String cinema, String date,String genre){
    	return cps.getFunctionsbyCinemaDateAndGenre(cinema, date, genre);
    }

    public List<Movie> getFunctionsbyCinemaDateAndEmptySeats(String cinema, String date,int EmptySeats){
    	return cps.getFunctionsbyCinemaDateAndEmptySeats(cinema, date, EmptySeats);
    }
    
    public CinemaFunction getFunctionsbyCinemaDateAndMovie(String cinema, String date, String movie) {
        System.out.println("edu.eci.arsw.cinema.services.CinemaServices.getFunctionsbyCinemaDateAndMovie()");
        return cps.getFunctionsbyCinemaDateAndMovie(cinema, date, movie);
    }
    
    public void addFunction(String cinema,CinemaFunction func){
        cps.addFunction(cinema, func);
    }

	public void actualizar(String cine, CinemaFunction o) {
		cps.actualizar(cine,o);
		
	}

	
    
    	


}
