import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Employee;
import util.UtilDBLaBreck;

@WebServlet("/MyServletHibernateDB")
public class MyServletHibernateDBLaBreck extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MyServletHibernateDBLaBreck() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");

      // #1
      UtilDBLaBreck.createEmployees("user3", "33", "402-333-3333");
      UtilDBLaBreck.createEmployees("user4", "44", "402-444-4444");
      
      // #2
      retrieveDisplayData(response.getWriter());
   }

   void retrieveDisplayData(PrintWriter out) {
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      List<Employee> listEmployees = UtilDBLaBreck.listEmployees();
      for (Employee employee : listEmployees) {
         System.out.println("[DBG] " + employee.getId() + ", " //
               + employee.getName() + ", " //
               + employee.getAge() + ", " //
         	   + employee.getPhone());

         out.println("<li>" + employee.getId() + ", " //
               + employee.getName() + ", " //
               + employee.getAge() + ", " //
         	   + employee.getPhone() + "</li>");
      }
      out.println("</ul>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
