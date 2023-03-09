import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer=response.getWriter();
        response.setContentType("text/html");
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HashMap<String,String> user= new HashMap<>();
        user.put("username", "user1");
        user.put("password", "12345");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String pw = request.getParameter("password");
        try {
            if(user.get("username").equals(username) && user.get("password").equals(pw))
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("username",username);
                session.setAttribute("password",pw);
                out.write("Name/Password match");
            }
            else
            {
                out.write("Name/Password does not match");
            }
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}