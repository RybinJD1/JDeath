package controller;

import services.impl.AccountService;
import services.impl.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    private static final String INDEX = "/WEB-INF/jsp/index.jsp";
    private static final String RICHEST_USER = "/WEB-INF/jsp/richest.jsp";
    private static final String SUM_ACCOUNT = "/WEB-INF/jsp/sumAcc.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        String forward = "";
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "sum":
                    forward = SUM_ACCOUNT;
                    req.setAttribute("sum", accountService.sumAllAccounts());
                    break;
                case "richest":
                    forward = RICHEST_USER;
                    req.setAttribute("richest", userService.getUserWithMaxAccount());
                    break;
                default:
                    forward = INDEX;
                    req.setAttribute("userList", userService.getAllUserWithAccount());
            }
        } else {
            forward = INDEX;
            req.setAttribute("userList", userService.getAllUserWithAccount());
        }
        RequestDispatcher view = getServletContext().getRequestDispatcher(forward);
        view.forward(req, resp);
    }

}
