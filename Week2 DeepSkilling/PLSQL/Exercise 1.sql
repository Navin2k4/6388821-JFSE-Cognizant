-- Control Structures

-- Apply discount to interest rates for customers over 60
BEGIN
    FOR rec IN (SELECT c.CustomerID, l.LoanID, l.InterestRate, c.DOB
                FROM Customers c
                JOIN Loans l ON c.CustomerID = l.CustomerID)
    LOOP
        IF MONTHS_BETWEEN(SYSDATE, rec.DOB) / 12 > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE LoanID = rec.LoanID;
        END IF;
    END LOOP;
END;
/

-- Promote customers to VIP based on balance
ALTER TABLE Customers ADD IsVIP CHAR(1);

BEGIN
    FOR rec IN (SELECT CustomerID, Balance FROM Customers)
    LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'Y'
            WHERE CustomerID = rec.CustomerID;
        ELSE
            UPDATE Customers
            SET IsVIP = 'N'
            WHERE CustomerID = rec.CustomerID;
        END IF;
    END LOOP;
END;
/

-- Reminders for loans due within 30 days
BEGIN
    FOR rec IN (SELECT l.LoanID, c.Name, l.EndDate
                FROM Loans l
                JOIN Customers c ON c.CustomerID = l.CustomerID
                WHERE l.EndDate <= SYSDATE + 30)
    LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan ' || rec.LoanID || ' for ' || rec.Name || ' is due on ' || TO_CHAR(rec.EndDate, 'DD-MON-YYYY'));
    END LOOP;
END;
/