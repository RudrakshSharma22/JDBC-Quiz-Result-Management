
import java.sql.*;
import java.util.Scanner;

public class oracle {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
        String username = "system";
        String password = "admin123";
        Scanner sc = new Scanner(System.in);
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Quiz Name: ");
            String quiz = sc.nextLine();
            System.out.print("Enter Total Questions: ");
            int total = sc.nextInt();
            System.out.print("Enter Score: ");
            int score = sc.nextInt();

            if (total <= 0 || score < 0 || score > total) {
                System.out.println("Invalid total or score.");
                con.close();
                sc.close();
                return;
            }

            String insert = "INSERT INTO QUIZ_RESULTS VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1, id);
            ps.setString(2, quiz);
            ps.setInt(3, total);
            ps.setInt(4, score);
            ps.executeUpdate();
            System.out.println("Quiz result inserted successfully!");

            String highest = "SELECT STUDENT_ID, QUIZ_NAME, SCORE, " + "TOTAL_QUESTIONS FROM QUIZ_RESULTS " + "WHERE SCORE = (SELECT MAX(SCORE) FROM QUIZ_RESULTS)";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(highest);
            System.out.println("\n--- HIGHEST SCORE ---");
            while (rs.next()) {
                System.out.println("Student ID: " +
                    rs.getInt("STUDENT_ID"));
                System.out.println("Quiz: " +
                    rs.getString("QUIZ_NAME"));
                System.out.println("Score: " +
                    rs.getInt("SCORE") + "/" +
                    rs.getInt("TOTAL_QUESTIONS"));
            }

            String above80 = "SELECT STUDENT_ID, QUIZ_NAME, SCORE, " +  "TOTAL_QUESTIONS FROM QUIZ_RESULTS " + "WHERE SCORE * 100.0 / TOTAL_QUESTIONS > 80";
            rs = st.executeQuery(above80);
            System.out.println("\n--- STUDENTS ABOVE 80% ---");
            boolean found = false;
            while (rs.next()) {
                found = true;
                double percentage = rs.getDouble("SCORE") * 100.0 / rs.getInt("TOTAL_QUESTIONS");
                System.out.println(
                    "Student ID: " + rs.getInt("STUDENT_ID") + ", Quiz: " + rs.getString("QUIZ_NAME") +", Score: " + rs.getInt("SCORE") +"/" + rs.getInt("TOTAL_QUESTIONS") +", Percentage: " +
                    String.format("%.2f", percentage) + "%");
            }

            if (!found) {
                System.out.println("No students scored above 80%.");
            }
            rs.close();
            st.close();
            ps.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
