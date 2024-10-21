CREATE OR REPLACE TRIGGER TRG_ESTABLECIMIENTO_ID
BEFORE INSERT ON ESTABLECIMIENTO
FOR EACH ROW
BEGIN
   -- Asignar el valor de la secuencia a la columna empleado_id
   :NEW.ID := ESTABLECIMIENTO_SEQUENCE.NEXTVAL;
END;