package com.parking.parkinglot2.servlets;

import com.parking.parkinglot2.common.UserDto;
import com.parking.parkinglot2.ejb.UserBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> users = userBean.findAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("WEB-INF/pages/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
    }
    @Inject
    UserBean userBean;
}