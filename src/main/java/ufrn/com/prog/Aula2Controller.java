package ufrn.com.prog;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Aula2Controller {


    @Autowired
    ServletContext servletContext;

    @RequestMapping("/redirecionar")
    public void doRedirecionamento(HttpServletRequest request, HttpServletResponse response) throws IOException{}
    
    @RequestMapping("/parte2")
    public void doParte2(HttpServletRequest request, HttpServletResponse response) throws IOException{}

    @RequestMapping("/parte1")
    public void doParte1(HttpServletRequest request, HttpServletResponse response) throws IOException{}

    @RequestMapping(value = "/principal", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws SevletException{}

    @RequestMapping(value = "/cadastraform", method = RequestMethod.GET)
    public void doCadastraForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var nome = request.getParameter("nome");
        var idade = request.getParameter("idade");
        var prefs = request.getParameterValues("prefs");

        var writer = response.getWriter();

        writer.println(nome);
        writer.println(idade);

        for (var p : prefs) {
            writer.println(prefs);
        }

    }
}
