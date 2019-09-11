import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "searchServlet", urlPatterns="/search")
public class searchServlet extends HttpServlet {
    private final String DRIVER_NAME = "jdbc:derby:";
    private final String DATABASE_PATH = "/WEB-INF/lib/distributed_java";
    private final String USER = "jimmy";
    private final String PASS = "jimmy";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try{
            String searchTerm = request.getParameter("searchTerm");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            String path = getServletContext().getRealPath(DATABASE_PATH);

            conn = DriverManager.getConnection(DRIVER_NAME + path, USER, PASS);

            String sql = "SELECT item_name FROM item WHERE item_name = ?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, searchTerm);

            rset = pstmt.executeQuery();

            StringBuilder html = new StringBuilder("<html><body>");

            while(rset.next()){
                String str = rset.getString(1);
                html.append("<p>").append(str).append("</p>");
            }

            html.append("</body></html>");

            response.getWriter().print(html.toString());

        }catch(SQLException | ClassNotFoundException e){

        }finally{
            if(rset != null){
                try{
                    rset.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(pstmt != null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }
}
