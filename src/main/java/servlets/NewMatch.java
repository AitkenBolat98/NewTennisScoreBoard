package servlets;

import module.*;
import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewMatch", urlPatterns = "/new-match")
public class NewMatch extends HttpServlet {
    PlayerService playerService = new PlayerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello world");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/new-match.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1Name = request.getParameter("player1-name");
        String player2Name = request.getParameter("player2-name");
        if(playerService.isPlayerExist(player1Name) || playerService.isPlayerExist(player2Name)){
            response.sendError(400,"player with such name exists");
        }else{
            playerService.createPlayers(player1Name,player2Name);
        }

    }
}