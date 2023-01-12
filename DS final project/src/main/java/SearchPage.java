import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchPage
 */
@WebServlet("/SearchPage")
public class SearchPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected String input;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchPage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) 回應get request&送post表單
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Googoal</title>");
		out.println("</head>");
		out.println("<body background = \"https://img.599ku.com/element2/0ec4beaa91a59145d246d921f3ba5dd4.jpg\" style = 'background-repeat: no-repeat; background-size : cover'>");
		out.println("<form action='" + requestUri + "' method='post'>");
		out.println("<div style='height:300px'>");
		out.println("<h2 style='font-size: 700%; position: absolute; left: 35%; top: 7%; font-family:sans-serif'><span style='color:#0000FF'>G</span><span style='color:#FF0000'>o</span><span style='color:#FFD700'>o</span><span style='color:#0000FF'>g</span><span style='color:#8B00FF'>o</span><span style='color:#FF4D00'>a</span><span style='color:#003399'>l</span></h2>");
		out.println("<input type='text' name = 'input' style = 'border-radius:75px/90px; font-size:120%; position:absolute; left:50%; top:48%; margin-top:-47px; margin-left:-400px; width:800px; height:45px'"
				+ "placeholder='  enter the keyword you want to search'"
				+ "onfocus=\"placeholder= '' \" onblur=\"placeholder='  enter the keyword you want to search'\" />");
		
		out.println(
				"<input type='image' src=\"images/loupe-2.png\" \r\n"
				+ "style='position:absolute;width:37px;height:37px;left:49%;top:50%;margin-top:-55px;\r\n"
				+ "margin-left:368px '/>");
		out.println("<div>");
		out.println("<img src = 'images/player1.png'   style=' position: absolute; left: 3%; top: 10%; '");
		out.println("</div>");
		out.println("<div>");
		out.println("<img src = 'images/player2.png'   style=' position: absolute; left: 78%; top: 50%; '");
		out.println("</div>");
		out.println("</form>");
		out.println("</div>");
		out.println("<img src = 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Soccerball.svg/220px-Soccerball.svg.png' style='position: absolute; left: 43%; top: 55%; '");
		out.println("</body>");
		out.println("</html>");

		out.flush();
		out.close();
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		input = request.getParameter("input");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		
		Controller c = new Controller(input);

		try {
			c.search();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Googoal result</title>");
		out.println("</head>");
		
		out.println("<body style = background-color:#93E174;'>");
		out.println("<input type='text' name='input' style='border-radius:75px/90px; position: absolute; left: 22%; top: 9%;  width: 600px; height: 25px; font-size:120%'\r\n"
					+ "placeholder = " + input + ">");
		out.println("<h2 style='font-size: 300%; position: absolute; left: 8%; font-family:sans-serif;'><span style='color:#0000FF'>G</span><span style='color:#FF0000'>o</span><span style='color:#FFD700'>o</span><span style='color:#0000FF'>g</span><span style='color:#8B00FF'>o</span><span style='color:#FF4D00'>a</span><span style='color:#003399'>l</span></h2>");
		out.println("<div>");
		out.println("<img src = 'images/player3.png'   style=' position: absolute; left:1%; top: 25%; '");
		out.println("</div>");
		out.println("<div style=' position: absolute; left: 22%; top: 20%; font-size:200%'>");

		for(int i = 0 ;i < c.getTreeList().size(); i++) {
			out.println("<a href='" + c.getTreeList().get(i).getNodeUrl() + "'>" + c.getTreeList().get(i).getNodeName() + "</a>");
			
			out.println("<span style = 'font-size:70%'>");
			if(c.getTreeList().get(i).getChildUrl(0) == null) {
				out.println("<br>");
				out.print("&nbsp");
				out.print("&nbsp");
				out.print("&nbsp");
				out.print("&nbsp");
				out.println("<label>找不到更多子網頁</label>");
			} else {
				out.println("<br>");
				out.print("&nbsp");
				out.print("&nbsp");
				out.print("&nbsp");
				out.print("&nbsp");
				out.println("<a href='" + c.getTreeList().get(i).getChildUrl(0) + "'>" + "子網頁1" + "</a>");
					
				if(c.getTreeList().get(i).getChildUrl(1) == null) {
					out.println("<br>");
					out.print("&nbsp");
					out.print("&nbsp");
					out.print("&nbsp");
					out.print("&nbsp");
					out.println("<label>找不到更多子網頁</label>");
				} else {
					out.println("<br>");
					out.print("&nbsp");
					out.print("&nbsp");
					out.print("&nbsp");
					out.print("&nbsp");
					out.println("<a href='" + c.getTreeList().get(i).getChildUrl(1) + "'>" + "子網頁2" + "</a>");
					
				}
			}
			
			out.println("</span>");
			
			out.println("<br>");
			out.println("<br>");
		}
		
		try {
			int count = 1;
			out.println("<h4>你可能也會想搜尋: </h4>");
			for(Entry<String, String> entry : c.getRelativeKeywords().entrySet()) {
				
				if(count % 3 == 0) {
					out.print("<a href='" + entry.getValue() + "'>" + entry.getKey() + "</a>");
					out.println("<br>");
				}else {
					out.print("<a href='" + entry.getValue() + "'>" + entry.getKey() + "</a>");
					out.print("&nbsp");
					out.print("&nbsp");
					out.print("&nbsp");
					out.print("&nbsp");
				}
				count++;
			}
			out.println("<br>");
			out.println("<br>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("<input type = 'button' name = 'back' onclick = history.back() value = 回到搜尋頁面  style='border-radius: 30px; width: 150px; height: 30px; font-size: 60%;'");

		out.println("</div>");
		out.println("<br>");
		out.println("<br>");
		out.println("</body>");
		out.println("</html>");

		out.flush();
		out.close();
	}

	public String getInput() {
		return input;
	}

}