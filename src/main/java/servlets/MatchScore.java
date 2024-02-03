package servlets;



import module.CurrentMatches;
import module.Players;
import service.MatchService;
import service.PlayerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MatchScore", value = "/match-score")
public class MatchScore extends HttpServlet {
    MatchService matchService = new MatchService();
    PlayerService playerService = new PlayerService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        CurrentMatches searchedMatch = matchService.getMatchById(id);
        String player1Name = searchedMatch.getPlayer1().getName();
        String player2Name = searchedMatch.getPlayer2().getName();
        request.setAttribute("player1Name",player1Name);
        request.setAttribute("player2Name",player2Name);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/match-score.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}