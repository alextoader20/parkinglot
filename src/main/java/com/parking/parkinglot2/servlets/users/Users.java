package com.parking.parkinglot2.servlets.users;

import com.parking.parkinglot2.common.UserDto;
import com.parking.parkinglot2.ejb.InvoiceBean;
import com.parking.parkinglot2.ejb.UserBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@DeclareRoles({"READ_CARS", "WRITE_CARS","INVOICING"})
@WebServlet(name = "Users", value = "/Users")
@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"READ_USERS"}),
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_USERS"})
        }
)
public class Users extends HttpServlet {
    @Inject
    private InvoiceBean invoiceBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> users = userBean.findAllUsers();
        request.setAttribute("users", users);

        if(!invoiceBean.getUserIds().isEmpty()) {
            Collection<String> usernames=userBean.findUsernameByUserIds(invoiceBean.getUserIds());
            request.setAttribute("invoices", usernames);
        }

        request.getRequestDispatcher("WEB-INF/users/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        String[] userIdsAsString = request.getParameterValues("user_ids");
        if (userIdsAsString != null) {
            List<Long> userIds = new ArrayList<>();
            for(String userIdAsString : userIdsAsString) {
                userIds.add(Long.parseLong(userIdAsString));
            }
            invoiceBean.getUserIds().addAll(userIds);
        }
        response.sendRedirect(request.getContextPath() + "/Users");

    }
    @Inject
    UserBean userBean;
}