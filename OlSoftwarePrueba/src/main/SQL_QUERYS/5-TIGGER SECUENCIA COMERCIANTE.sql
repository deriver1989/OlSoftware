CREATE OR REPLACE TRIGGER TRG_COMERCIANTE_ID
BEFORE INSERT ON COMERCIANTE
FOR EACH ROW
BEGIN
   -- Asignar el valor de la secuencia a la columna COMERCIANTE_id
   :NEW.ID := COMERCIANTE_SEQUENCE.NEXTVAL;
END;