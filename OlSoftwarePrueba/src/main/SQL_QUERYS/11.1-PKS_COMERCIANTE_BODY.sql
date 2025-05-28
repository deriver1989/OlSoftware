create or replace NONEDITIONABLE PACKAGE BODY PKS_COMERCIANTE AS
   
    ---consulta con cursor
    FUNCTION consultar_comerciantes_cursor RETURN SYS_REFCURSOR IS
        v_cursor SYS_REFCURSOR; -- Declarar el cursor referenciado
    BEGIN
        -- realizar consulta
            OPEN v_cursor FOR
                select 
                    nombre, 
                    municipio, 
                    telefono, 
                    correo_electronico, 
                    fecha_registro, 
                    estado,
                    cantidad_establecimientos,
                    ingresos,
                    num_empleados
                from (
                    SELECT  
                        nombre,  
                        municipio, 
                        telefono, 
                        correo_electronico,
                        fecha_registro, 
                        estado,
                        (SELECT count(*)as  cantidad_establecimientos FROM establecimiento where comerciante_id = t.id) as cantidad_establecimientos,
                        (SELECT sum(ingresos)as  ingresos FROM establecimiento where comerciante_id = t.id) as ingresos,
                        (SELECT sum(num_empleados)as  n_empleados FROM establecimiento where comerciante_id = t.id) as num_empleados
                    FROM comerciante t where estado = 'ACTIVO'
                )order by cantidad_establecimientos desc;

        -- Retornar el cursor
        RETURN v_cursor;
    END consultar_comerciantes_cursor;
    
END PKS_COMERCIANTE;