package Survey.SurveyController;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.SurveyService;

public class deleteSurveyIngController implements Controller {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      String str_surIngIndex = request.getParameter("surIngIndex");
      int surIngIndex = Integer.parseInt(str_surIngIndex);
      
      SurveyService surService = SurveyService.getInstance();
      boolean isSuccess = surService.deleteSurveyGoing(surIngIndex);
      
      if(isSuccess)
      {
         PrintWriter script = response.getWriter();
         script.println("<script>");
         script.println("alert('Success Delete')");
         script.println("location.href='/PeerSys/page_adm/SurveyStarting/goingSurvey.jsp';");
         script.println("</script>");
         script.close();
      }
      else{
         PrintWriter script = response.getWriter();
         script.println("<script>");
         script.println("alert('참여한 학생이 있습니다.')");
         script.println("history.back();");
         script.println("</script>");
         script.close();
     }
   }

}