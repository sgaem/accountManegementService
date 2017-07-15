package com.aem.sgaem.project.servlets;

import com.adobe.cq.account.api.AccountManagementService;
import java.io.IOException;
import javax.jcr.RepositoryException;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

@SlingServlet(paths = "/bin/requestPassword")
public class RequestPassword extends SlingAllMethodsServlet {

  @Reference
  private AccountManagementService accountManagementService;

  @Override
  protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {
    try {

      String userId = request.getParameter("userId");
      accountManagementService
          .requestPasswordReset(userId, "http://localhost:4502",
              "/content/resetPasswordProperties");
      response.getWriter().print("Your request for change password is sent to your mail id");
    } catch (RepositoryException e) {
      e.getStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
