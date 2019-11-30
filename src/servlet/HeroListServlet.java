package servlet;

import bean.Hero;
import dao.HeroDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HeroListServlet")
public class HeroListServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int start = 0;
        int count = 5;

        try{
            start = Integer.parseInt(request.getParameter("start"));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        //下一页
        int next = start + count;
        //上一页
        int pre = start - count;
        //最后一个
        int last;
        int total = new HeroDAO().getTotal();
        if(0 == total % count){
            last = total - count;
        }else {
            last = total - total%count;
        }

        //
        pre = (pre<0 ?0:pre);
        next = (next>last ?last:next);

        request.setAttribute("next",next);
        request.setAttribute("pre",pre);
        request.setAttribute("last",last);

        List<Hero> heros = new HeroDAO().list(start,count);
        request.setAttribute("heros",heros);
        request.getRequestDispatcher("listHero.jsp").forward(request,response);
    }
}
