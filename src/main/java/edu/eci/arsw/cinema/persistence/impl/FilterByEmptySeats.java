/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author 2108263
 */
public class FilterByEmptySeats implements CinemaPersitence{
    private final Map<String,Cinema> cinemas=new HashMap<>();
    
    @Override
    public List<Movie> getFunctionsbyCinemaDateAndEmptySeats(String cinema, String date, int EmptySeats) {
            List<CinemaFunction> a =getFunctionsbyCinemaAndDate(cinema, date);
            List<Movie> b=new ArrayList<>();
            for(CinemaFunction cf:a){
                    if(cf.EmptySeats()>=EmptySeats){
                            b.add(cf.getMovie());
                    }
            }
            return b;
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
            List<CinemaFunction> funciones=new ArrayList<>();
            Cinema c=cinemas.get(cinema);
            for(CinemaFunction cf:c.getFunctions()){
                    if(cf.getDate()==date){
                            funciones.add(cf);

                    }
            }
            return funciones;
            //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getFunctionsbyCinemaDateAndGenre(String cinema, String date, String genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveCinema(Cinema cinema) throws CinemaPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Cinema> getAllCinema() throws CinemaPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CinemaFunction getFunctionsbyCinemaDateAndMovie(String cinema, String date, String movie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addFunction(String name, CinemaFunction func) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public List<Movie> getFunctionsbyCinema(String name) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void actualizar(String cine, CinemaFunction o) {
		throw new UnsupportedOperationException("Not supported yet.");		
	}
}
