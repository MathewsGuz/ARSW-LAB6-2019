/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<?> GetCinemas(){
        try {
            
            return new ResponseEntity<>(cs.getAllCinemas(),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
        }      
    }
    
    
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/{name}")
    public ResponseEntity<?> GetCinemaByName(@PathVariable String name){
        
        try {
            return new ResponseEntity<>(cs.getCinemaByName(name),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            // probar arreglar con esto HttpStatus::is4xxServerError,response ->...
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
        }      
    }
    
    @GetMapping("/{name}/{date}")
    public ResponseEntity<?> GetCinemaByNameAndDate(@PathVariable String name,@PathVariable String date){
        
        try {
            return new ResponseEntity<>(cs.getFunctionsbyCinemaAndDate(name, date),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
        }      
    }
    
        
    @GetMapping("/{name}/{date}/{moviename}")
    public ResponseEntity<?> GetCinemaByNameDateAndMovie(@PathVariable String name,@PathVariable String date,@PathVariable String moviename){
        
        try {
            return new ResponseEntity<>(cs.getFunctionsbyCinemaDateAndMovie(name, date, moviename),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
        }      
    }
        
    
   
}
