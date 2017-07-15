package com.aem.sgaem.project.servlets;

import com.adobe.cq.account.api.AccountManagementService;
import java.util.HashMap;
import java.util.Map;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

@SlingServlet(paths = "/bin/registeration", methods = "post")
public class RegisterServlet extends SlingAllMethodsServlet {

  @Reference
  private AccountManagementService accountManagementService;

  @Override
  public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {
    String fname = request.getParameter("fname");
    String password = request.getParameter("pwd");
    try {
      Map<String, RequestParameter[]> profilemap = new HashMap<String, RequestParameter[]>();
      profilemap
          .put("email", new RequestParameter[]{new Parameters(request.getParameter("email"))});
      profilemap
          .put("familyName", new RequestParameter[]{new Parameters(request.getParameter("lname"))});

     boolean success =  accountManagementService
          .requestAccount(fname, password, profilemap, "http://localhost:4502",
              "/content/properties");
      if(success) {
        response.getWriter()
            .print("Please Check your EmailId and verify the mail for complete account creation");
      }
      else
      {
        response.getWriter()
            .print("User Already Exists");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
