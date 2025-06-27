--  Cursors

-- Monthly statements
DECLARE
    CURSOR cur_txn IS
        SELECT c.Name, a.AccountID, t.Amount, t.TransactionDate, t.TransactionType
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM');
BEGIN
    FOR rec IN cur_txn LOOP
        DBMS_OUTPUT.PUT_LINE('Customer: ' || rec.Name || ', Account: ' || rec.AccountID || ', ' || rec.TransactionType || ': ' || rec.Amount || ' on ' || TO_CHAR(rec.TransactionDate, 'DD-MON'));
    END LOOP;
END;
/

-- Annual fee deduction
DECLARE
    CURSOR cur_fee IS
        SELECT AccountID FROM Accounts;
BEGIN
    FOR acc IN cur_fee LOOP
        UPDATE Accounts
        SET Balance = Balance - 100
        WHERE AccountID = acc.AccountID;
    END LOOP;
    COMMIT;
END;
/

-- Update interest rate
DECLARE
    CURSOR cur_loans IS SELECT LoanID, InterestRate FROM Loans;
BEGIN
    FOR rec IN cur_loans LOOP
        UPDATE Loans
        SET InterestRate = rec.InterestRate * 1.05
        WHERE LoanID = rec.LoanID;
    END LOOP;
    COMMIT;
END;
/
