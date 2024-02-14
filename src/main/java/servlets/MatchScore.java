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
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "MatchScore", value = "/match-score")
public class MatchScore extends HttpServlet {
    MatchService matchService = new MatchService();
    ScoreService scoreService = new ScoreService(matchService);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer matchId = Integer.valueOf(request.getParameter("id"));
        CurrentMatches searchedMatch = matchService.getMatchById(matchId);
        String player1Name = searchedMatch.getPlayer1().getName();
        String player2Name = searchedMatch.getPlayer2().getName();
        HashMap<String, List<Integer>> map = scoreService.getSetGamePoint(matchId);
        request.setAttribute("player1Name",player1Name);
        request.setAttribute("player2Name",player2Name);
        request.setAttribute("player1sets",map.get("player1").get(0));
        request.setAttribute("player1games",map.get("player1").get(1));
        request.setAttribute("player1points",map.get("player1").get(2));
        request.setAttribute("player2sets",map.get("player2").get(0));
        request.setAttribute("player2games",map.get("player2").get(1));
        request.setAttribute("player2points",map.get("player2").get(2));
        if(scoreService.isPlayerWonMatch(searchedMatch.getPlayer1())){
            request.setAttribute("player1WL","W");
            request.setAttribute("player2WL","L");
        } else if (scoreService.isPlayerWonMatch(searchedMatch.getPlayer2())) {
            request.setAttribute("player1WL","L");
            request.setAttribute("player2WL","W");
        }else {
            request.setAttribute("player1WL","");
            request.setAttribute("player2WL","");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/match-score.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer playerScored = Integer.valueOf(request.getParameter("pscored"));
        Integer matchId = Integer.valueOf(request.getParameter("id"));
        CurrentMatches match = scoreService.updateMatchScore(matchId,playerScored);
        CurrentMatches searchedMatch = matchService.getMatchById(matchId);
        String player1Name = searchedMatch.getPlayer1().getName();
        String player2Name = searchedMatch.getPlayer2().getName();
        HashMap<String, List<Integer>> map = scoreService.getSetGamePoint(matchId);
        request.setAttribute("player1Name",player1Name);
        request.setAttribute("player2Name",player2Name);
        request.setAttribute("player1sets",map.get("player2").get(0));
        request.setAttribute("player1games",map.get("player2").get(1));
        request.setAttribute("player1points",map.get("player2").get(2));
        if(scoreService.isPlayerWonMatch(searchedMatch.getPlayer1())){
            request.setAttribute("player1WL","W");
            request.setAttribute("player2WL","L");
            response.sendRedirect("match-score?id=" + matchId);
        } else if (scoreService.isPlayerWonMatch(searchedMatch.getPlayer2())) {
            request.setAttribute("player1WL","L");
            request.setAttribute("player2WL","W");
            response.sendRedirect("match-score?id=" + matchId);
        }else {
            request.setAttribute("player1WL","");
            request.setAttribute("player2WL","");
            response.sendRedirect("match-score?id=" + matchId);

        }
    }
}