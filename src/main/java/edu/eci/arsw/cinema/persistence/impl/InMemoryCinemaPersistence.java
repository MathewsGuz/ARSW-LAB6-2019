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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */
@Service
public class InMemoryCinemaPersistence implements CinemaPersitence{

	private final Map<String,Cinema> cinemas=new HashMap<>();


	public InMemoryCinemaPersistence() {
		//load stub data
		String functionDate = "2018-12-18 15:30";
		List<CinemaFunction> functions= new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie","Action"),functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),functionDate);
		functions.add(funct1);
		functions.add(funct2);
		Cinema c=new Cinema("cinemaX",functions);
		cinemas.put("cinemaX", c);
                
                
                String functionDate2 = "2018-03-07 20:30";
		List<CinemaFunction> functionsCC= new ArrayList<>();
		CinemaFunction funct3 = new CinemaFunction(new Movie("Capitana Marvel","Scifi"),functionDate2);
		CinemaFunction funct4 = new CinemaFunction(new Movie("End Game","Action"),functionDate2);
		functionsCC.add(funct3);
		functionsCC.add(funct4);
		Cinema c1=new Cinema("CineColombia",functionsCC);
		cinemas.put("CineColombia", c1);
                
                String functionDate3 = "2018-03-09 18:30";
                String functionDate4 = "2018-12-18 16:30";
		List<CinemaFunction> functionsProcinal= new ArrayList<>();
		CinemaFunction funct5 = new CinemaFunction(new Movie("EL PASEO 4","Comedy"),functionDate3);
		CinemaFunction funct6 = new CinemaFunction(new Movie("Como entrenar a tu dragon 3","Cartoon"),functionDate4);
		functionsProcinal.add(funct5);
		functionsProcinal.add(funct6);
		Cinema c2=new Cinema("Procinal",functionsProcinal);
		cinemas.put("Procinal", c2);
                
	}    

	@Override
	public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
		//throw new UnsupportedOperationException("Not supported yet.");
		Cinema c=cinemas.get(cinema);
		CinemaFunction cf1 = null;
		for(CinemaFunction cf:c.getFunctions()){
			if(cf.getMovie().getName().equals(movieName) && cf.getDate()==date){
				cf1=cf;
				break;
			}
		}
		cf1.buyTicket(row, col);
	}

	@Override
	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
		List<CinemaFunction> funciones=new ArrayList<>();
		Cinema c=cinemas.get(cinema);
		for(CinemaFunction cf:c.getFunctions()){
                    //System.out.println("--------------------------------------------------");
                    //System.out.println(cf.getDate()+" Esta es la fecha parametro "+date+"  "+cf.getDate().equals(date) );
			if(cf.getDate().equals(date)){
				funciones.add(cf);

			}
		}
		return funciones;
		//throw new UnsupportedOperationException("Not supported yet.");
	}
	@Override
	public void saveCinema(Cinema c) throws CinemaPersistenceException {
		if (cinemas.containsKey(c.getName())){
			throw new CinemaPersistenceException("The given cinema already exists: "+c.getName());
		}
		else{
			cinemas.put(c.getName(),c);
		}   
	}

	@Override
	public Cinema getCinema(String name) throws CinemaPersistenceException {

		if (cinemas.containsKey(name)){
			Cinema ans = cinemas.get(name);
			return ans;
		}
		else{
			throw new CinemaPersistenceException("The given name dont corresponde to any cinema");
		}
	}
	// forma de spring de hacer main
	//    public static void main(String[] args) {
	//		SpringApplication.run(CollabPaintApplication.class, args);
	//	}

	@Override
	public List<Movie> getFunctionsbyCinemaDateAndGenre(String cinema, String date, String genre) {
		List<CinemaFunction> a =getFunctionsbyCinemaAndDate(cinema, date);
		List<Movie> b=new ArrayList<>();
		for(CinemaFunction cf:a){
			if(cf.getMovie().getGenre()==genre){
				b.add(cf.getMovie());

			}
		}
		return b;
	}

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
    public Set<Cinema> getAllCinema() throws CinemaPersistenceException {
        Set<Cinema> ans = new HashSet<Cinema>(cinemas.values());
//        Set<Cinema> ans= HashSet<Cinema>();
//        ans.addAll(cinemas.values());
        return ans;
    }

    @Override
    public CinemaFunction getFunctionsbyCinemaDateAndMovie(String cinema, String date, String movie) {
        CinemaFunction funciones=new CinemaFunction();
        Cinema c=cinemas.get(cinema);
        for(CinemaFunction cf:c.getFunctions()){
            //System.out.println("--------------------------------------------------");
            //System.out.println(cf.getDate()+" Esta es la fecha parametro "+date+" "+cf.getMovie().getName().equals(movie) );
                if(cf.getDate().equals(date) && cf.getMovie().getName().equals(movie)){
                        funciones=cf;

                }
        }
        return funciones;
    }

    @Override
    public void addFunction(String name, CinemaFunction func) {
        Cinema c=cinemas.get(name);
        c.getFunctions().add(func);
    }

	@Override
	public List<Movie> getFunctionsbyCinema(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(String cine, CinemaFunction o) {
		Boolean esta=false;
		if (cinemas.get(cine)!=null) {
			Cinema c=cinemas.get(cine);
			for(CinemaFunction cf:c.getFunctions()) {
				if(cf.getMovie().getName().equals(o.getMovie().getName())) {
					cf.setDate(o.getDate());
					esta=true;
				}
			}
		}
		if(!esta) {
			addFunction(cine, o);
		}                
		                             
				
	}
	
}
