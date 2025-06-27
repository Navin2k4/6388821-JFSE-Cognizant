-- Stored Procedures

-- Monthly interest for savings accounts
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';
    COMMIT;
END;
/

-- Bonus scheme for employees
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(p_dept VARCHAR2, p_bonus_percent NUMBER) IS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_percent / 100)
    WHERE Department = p_dept;
    COMMIT;
END;
/

-- Transfer funds between accounts
CREATE OR REPLACE PROCEDURE TransferFunds(p_from NUMBER, p_to NUMBER, p_amount NUMBER) IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from FOR UPDATE;
    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20003, 'Insufficient funds.');
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to;
    COMMIT;
END;
/