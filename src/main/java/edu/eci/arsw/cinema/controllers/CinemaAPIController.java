/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.ws.http.HTTPException;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author cristian
 */

// ARREGLAR EL NOT FOUN 404 EN VEZ DE BLA BLA BLA
@RestController
@RequestMapping(value = "/cinema")
@Service
public class CinemaAPIController {
    
    @Autowired
    CinemaServices cs;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> GetCinemas() throws CinemaPersistenceException{
        try {            
            return new ResponseEntity<>(cs.getAllCinemas(),HttpStatus.ACCEPTED);
        } catch (HTTPException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.toString(),HttpStatus.valueOf(ex.getStatusCode()));
        }      
    }
    
    
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/{name}")
    public ResponseEntity<?> GetCinemaByName(@PathVariable String name) throws CinemaException, CinemaPersistenceException {        
        try {
            return new ResponseEntity<>(cs.getCinemaByName(name),HttpStatus.ACCEPTED);
        } catch (HTTPException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);            
            return new ResponseEntity<>(ex.toString(),HttpStatus.valueOf(ex.getStatusCode()));
        }      
    }
    
    @GetMapping("/{name}/{date}")
    public ResponseEntity<?> GetCinemaByNameAndDate(@PathVariable String name,@PathVariable String date){        
        try {
        	List<CinemaFunction> l=cs.getFunctionsbyCinemaAndDate(name, date);        	
        	if(l.equals(new ArrayList<>())) {
        		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        	}else
        		return new ResponseEntity<>(cs.getFunctionsbyCinemaAndDate(name, date),HttpStatus.ACCEPTED);
        } catch (HTTPException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.toString(),HttpStatus.valueOf(ex.getStatusCode()));
        }      
    }
    
        
    @GetMapping("/{name}/{date}/{moviename}")
    public ResponseEntity<?> GetCinemaByNameDateAndMovie(@PathVariable String name,@PathVariable String date,@PathVariable String moviename){
        
        try {
            return new ResponseEntity<>(cs.getFunctionsbyCinemaDateAndMovie(name, date, moviename),HttpStatus.ACCEPTED);
        } catch (HTTPException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.toString(),HttpStatus.valueOf(ex.getStatusCode()));
        }      
    }
    
    
    
    @RequestMapping(value="/{cine}",method =RequestMethod.POST)	
    public ResponseEntity<?> registFunction(@RequestBody CinemaFunction o,@PathVariable String cine){
        try {
            System.out.println("Entre a registrar");
            cs.addFunction(cine, o);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(HTTPException ex){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.toString(),HttpStatus.valueOf(ex.getStatusCode()));
        }  
    }
    

    //curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://localhost:8080/cinema/cinemaX/ -d '{"movie":{"name":"prueba1","genre":"Test"},"date":"2018-12-18 15:30"}'
    


    @RequestMapping(value="/{cine}",method =RequestMethod.PUT)	
    public ResponseEntity<?> updateFunction(@PathVariable String cine,@RequestBody CinemaFunction o){
    	 try {
    		 cs.actualizar(cine,o);
             return new ResponseEntity<>(HttpStatus.CREATED);
         }catch(Exception ex){
             Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
             return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
         }  
    }

}
