package servlets;

import module.CurrentMatches;
import module.Players;
import service.MatchService;
import service.PlayerService;
import service.ScoreService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Player", value = "/player")
public class Player extends HttpServlet {
    MatchService matchService = new MatchService();
    ScoreService scoreService = new ScoreService(matchService);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/player.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("player-name");
        CurrentMatches searchedMatch = matchService.getMatchByPlayerName(name);
        Players player1 = searchedMatch.getPlayer1();
        Players player2 = searchedMatch.getPlayer2();
        request.setAttribute("player1Name",player1.getName());
        request.setAttribute("player2Name",player2.getName());
        request.setAttribute("player1sets",player1.getScore().getSetNumber());
        request.setAttribute("player1games",player1.getScore().getGamesWon());
        request.setAttribute("player1points",player1.getScore().getPointWon());
        request.setAttribute("player2sets",player2.getScore().getSetNumber());
        request.setAttribute("player2games",player2.getScore().getGamesWon());
        request.setAttribute("player2points",player2.getScore().getPointWon());

        if(scoreService.isPlayerWonMatch(searchedMatch.getPlayer1())){
            request.setAttribute("player1WL","W");
            request.setAttribute("player2WL","L");
            response.sendRedirect("match-score?id=" + searchedMatch.getId());
        } else if (scoreService.isPlayerWonMatch(searchedMatch.getPlayer2())) {
            request.setAttribute("player1WL","L");
            request.setAttribute("player2WL","W");
            response.sendRedirect("match-score?id=" + searchedMatch.getId());
        }else {
            request.setAttribute("player1WL","");
            request.setAttribute("player2WL","");
            response.sendRedirect("match-score?id=" + searchedMatch.getId());
        }

    }
}