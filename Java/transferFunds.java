import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public void transferFunds(Connection conn, int fromId, int toId, double amount) throws SQLException {
    conn.setAutoCommit(false);
    try (PreparedStatement debit = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE id = ?");
         PreparedStatement credit = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?")) {

        debit.setDouble(1, amount);
        debit.setInt(2, fromId);
        debit.executeUpdate();

        credit.setDouble(1, amount);
        credit.setInt(2, toId);
        credit.executeUpdate();

        conn.commit();
    } catch (SQLException e) {
        conn.rollback();
        throw e;
    } finally {
        conn.setAutoCommit(true);
    }
}
