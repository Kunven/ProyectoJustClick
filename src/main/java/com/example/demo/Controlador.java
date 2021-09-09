package com.example.demo;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import com.example.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import eu.bitwalker.useragentutils.*;
 
@Controller
public class Controlador {
  private final DatosService service;
  @Autowired
  public Controlador(DatosService service){
    this.service = service;
  }
  //Presenta el formulario
  @GetMapping("/index")
  public String greetingForm(Model model,@CookieValue(value = "username", defaultValue = "Ninguno") String username) {
    model.addAttribute("string", new string());    
    return "formulario";
  }
  //Actualiza y retorna resultado
  @PostMapping("/consultar")
  public String greetingSubmit(@ModelAttribute string string, Model model,HttpServletResponse response,HttpServletRequest request) {    
    DataMan data = new DataMan();
    model.addAttribute("string", string);
    //Guardo el registro original
    String original = string.name;
    string string2 = string;
    //Busco si el registro ingresado existe
    if(data.existe(string2.name)){
      //Si Existe consulto el registro que no se haya pasado del limite
      string2 = data.Consultar(string2.name);
      if(string2 == null){
        //si el obj es null la consulta con validacion de limite retorno nada
        string2 = new string();
        string.mensaje = "El Registro "+ original + " Se A Pasado Del Limite De Consultas Posibles";
      }else{
        //Una vez obtenido mi obj consultado actualizo el contador
        string2 = data.Actualizar(string2.name);
        //Construyo el mensaje a presentar
        if(string2 == null){
          string2 = new string();
          string.mensaje = "El Registro "+ original +" Se A Quedado Sin Consultas";
        }else{
          string.mensaje = "El Registro "+ original + " Tiene " + string2.counter + " De " + string2.maxvalue + " Consultas Restantes";
        }
        
        
      }      
    }else{
      //Si No Existe
      string.mensaje = "El Registro " + original + " No Existe";
    }    
    //Creacion del objeto de elastic search
    UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));    
    Datos dat = new Datos();
    dat.parametro = string.name;
    dat.OSname = System.getProperty("os.name").replaceAll("\\s+","");
    dat.OSversion =  System.getProperty("os.version").replaceAll("\\s+","");
    dat.Browser = userAgent.getBrowser().toString().replaceAll("\\s+","");
    dat.BrowserVersion = userAgent.getBrowserVersion().toString().replaceAll("\\s+","");
    //Creacion de cookies    
    Cookie cookie1 = new Cookie("OSname", dat.OSname);
    response.addCookie(cookie1);        
    Cookie cookie2 = new Cookie("OSversion",dat.OSversion);
    response.addCookie(cookie2);        
    Cookie cookie3 = new Cookie("Browser", dat.Browser );
    response.addCookie(cookie3);
    Cookie cookie4 = new Cookie("BrowserVersion", dat.BrowserVersion);
    response.addCookie(cookie4);   
    //grabo en la instancia de elasticsearch localhost:9200
    save(dat);
    //retorno la vista de resultado
    return "result";
  }    
  //Graba en la instancia de elastic search
  public void save(Datos datos){
    service.save(datos);
  }  
}