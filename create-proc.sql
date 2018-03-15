CREATE OR REPLACE PROCEDURE demoSp(param1 IN VARCHAR2, outParam1 OUT VARCHAR2)
IS
  BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello World IN parameter ' || param1);
    outParam1 := 'Hello World OUT parameter';
  END;
/