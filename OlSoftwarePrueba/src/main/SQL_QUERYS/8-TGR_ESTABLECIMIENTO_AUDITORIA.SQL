CREATE OR REPLACE TRIGGER TGR_ESTABLECIMIENTO_AUDITORIA
BEFORE INSERT OR UPDATE ON ESTABLECIMIENTO
FOR EACH ROW
BEGIN
   -- Si es una actualización, actualizamos el campo correspondiente
   IF UPDATING THEN
      :NEW.fecha_actualizacion := SYSDATE;
      :NEW.usuario := 1;
   END IF;
   
   -- Si es una inserción, actualizamos el campo correspondiente
   IF INSERTING THEN
      :NEW.fecha_actualizacion := SYSDATE;
      :NEW.usuario := 1;
   END IF;
END;