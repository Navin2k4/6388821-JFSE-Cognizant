-- Exercise 4: Functions
CREATE OR REPLACE FUNCTION CalculateAge(p_dob DATE) RETURN NUMBER IS
BEGIN
    RETURN FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END;
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(p_amount NUMBER, p_rate NUMBER, p_years NUMBER)
RETURN NUMBER IS
    v_monthly_rate NUMBER := p_rate / 12 / 100;
    v_months NUMBER := p_years * 12;
BEGIN
    RETURN (p_amount * v_monthly_rate) / (1 - POWER(1 + v_monthly_rate, -v_months));
END;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance(p_acc_id NUMBER, p_amt NUMBER) RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_acc_id;
    RETURN v_balance >= p_amt;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/

-- Triggers
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

CREATE TABLE AuditLog (
    LogID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
    AccountID NUMBER,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    LogDate DATE
);

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog(AccountID, Amount, TransactionType, LogDate)
    VALUES (:NEW.AccountID, :NEW.Amount, :NEW.TransactionType, SYSDATE);
END;
/

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    IF :NEW.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = :NEW.AccountID;
        IF v_balance < :NEW.Amount THEN
            RAISE_APPLICATION_ERROR(-20004, 'Insufficient balance for withdrawal.');
        END IF;
    ELSIF :NEW.TransactionType = 'Deposit' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20005, 'Deposit amount must be positive.');
        END IF;
    END IF;
END;
/


-- Packages

CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER);
    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2);
    FUNCTION GetBalance(p_id NUMBER) RETURN NUMBER;
END;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER) IS
    BEGIN
        INSERT INTO Customers VALUES(p_id, p_name, p_dob, p_balance, SYSDATE, 'N');
    END;

    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2) IS
    BEGIN
        UPDATE Customers SET Name = p_name WHERE CustomerID = p_id;
    END;

    FUNCTION GetBalance(p_id NUMBER) RETURN NUMBER IS
        v_bal NUMBER;
    BEGIN
        SELECT Balance INTO v_bal FROM Customers WHERE CustomerID = p_id;
        RETURN v_bal;
    END;
END;
/

CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_pos VARCHAR2, p_sal NUMBER, p_dept VARCHAR2);
    PROCEDURE UpdateEmployee(p_id NUMBER, p_name VARCHAR2);
    FUNCTION AnnualSalary(p_id NUMBER) RETURN NUMBER;
END;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_pos VARCHAR2, p_sal NUMBER, p_dept VARCHAR2) IS
    BEGIN
        INSERT INTO Employees VALUES(p_id, p_name, p_pos, p_sal, p_dept, SYSDATE);
    END;

    PROCEDURE UpdateEmployee(p_id NUMBER, p_name VARCHAR2) IS
    BEGIN
        UPDATE Employees SET Name = p_name WHERE EmployeeID = p_id;
    END;

    FUNCTION AnnualSalary(p_id NUMBER) RETURN NUMBER IS
        v_sal NUMBER;
    BEGIN
        SELECT Salary INTO v_sal FROM Employees WHERE EmployeeID = p_id;
        RETURN v_sal * 12;
    END;
END;
/

CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(p_id NUMBER, p_cust_id NUMBER, p_type VARCHAR2, p_balance NUMBER);
    PROCEDURE CloseAccount(p_id NUMBER);
    FUNCTION TotalBalance(p_cust_id NUMBER) RETURN NUMBER;
END;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount(p_id NUMBER, p_cust_id NUMBER, p_type VARCHAR2, p_balance NUMBER) IS
    BEGIN
        INSERT INTO Accounts VALUES(p_id, p_cust_id, p_type, p_balance, SYSDATE);
    END;

    PROCEDURE CloseAccount(p_id NUMBER) IS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_id;
    END;

    FUNCTION TotalBalance(p_cust_id NUMBER) RETURN NUMBER IS
        v_total NUMBER;
    BEGIN
        SELECT SUM(Balance) INTO v_total FROM Accounts WHERE CustomerID = p_cust_id;
        RETURN v_total;
    END;
END;
/
