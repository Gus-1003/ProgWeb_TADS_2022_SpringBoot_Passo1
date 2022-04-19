package ufrn.com.prog;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Aula2Controller {


    @Autowired
    ServletContext servletContext;

    @RequestMapping("/redirecionar")
    public void doRedirecionamento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8080/parte1");
    }
    
    @RequestMapping("/parte2")
    public void doParte2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mensagem = (String) request.getAttribute("mensagem");
        response.getWriter().println("parte 2 do processamento");
        response.getWriter().println(mensagem);
        //response.sendRedirect("http://localhost:8080/redirecionar");
    }

    @RequestMapping("/parte1")
    public void doEncaminhar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mensagem", "parte 1 do processamento");
        RequestDispatcher encaminhar = request.getRequestDispatcher("/parte2");
        encaminhar.forward(request, response);
    }

    @RequestMapping(value = "/principal", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var aula = request.getParameter("aula");

        String admin = servletContext.getInitParameter("admin");
        String teste = servletContext.getInitParameter("aula");



        response.setContentType("text/html");
        response.setStatus(210);
        response.getWriter().println("<html><body>Hello world "+ aula + " admin:" +admin+teste+ "</body></html>");
    }

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

    @Controller
    @RequestMapping("/atividade")
    public class AtividadeSessao {

        @GetMapping
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        HttpSession session = request.getSession();
        Integer acessoCount = (Integer) session.getAttribute("contador");
        
        if (acessoCount == null){
            acessoCount = 0;
        }else{
            acessoCount++;
        }
        
        session.setAttribute("contador", acessoCount);
        
        String id = session.getId();
        long creationTime = session.getCreationTime();
        long lastAccessedTime = session.getLastAccessedTime();
        var out = response.getWriter();
        
        out.println("id: "+id+" creationTime: " + creationTime + " lastAccessedTime: " + lastAccessedTime + " count: "+ acessoCount);

        }

    }
}
