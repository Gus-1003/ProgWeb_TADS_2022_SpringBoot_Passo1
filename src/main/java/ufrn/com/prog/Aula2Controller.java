package ufrn.com.prog;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Aula2Controller {

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
