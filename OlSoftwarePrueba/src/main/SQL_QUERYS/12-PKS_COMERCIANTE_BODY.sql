create or replace NONEDITIONABLE PACKAGE BODY PKS_COMERCIANTE AS

  -- Procedimiento para crear
  PROCEDURE crear_comerciante(
     p_nombre IN VARCHAR2,
      p_departamento IN VARCHAR2,
      p_municipio IN VARCHAR2,
      p_fecha_registro IN DATE,
      p_estado IN VARCHAR2,
      out_codigoerror OUT NUMBER,
      out_mensajeerror OUT VARCHAR2,
      p_telefono IN VARCHAR2 DEFAULT NULL,
      p_correo IN VARCHAR2 DEFAULT NULL
  ) IS
  v_pattern VARCHAR2(100) := '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$';
  
  BEGIN
  
    IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
        RAISE_APPLICATION_ERROR(-20001, 'Variable sin información: nombre'); 
    END IF;
    
    IF p_departamento IS NULL OR TRIM(p_departamento) = '' THEN
        RAISE_APPLICATION_ERROR(-20001, 'Variable sin información: departamento'); 
    END IF;
    
    IF p_municipio IS NULL OR TRIM(p_municipio) = '' THEN
        RAISE_APPLICATION_ERROR(-20001, 'Variable sin información: municipio'); 
    END IF;
    
    IF p_fecha_registro IS NULL OR TRIM(p_fecha_registro) = '' THEN
        RAISE_APPLICATION_ERROR(-20001, 'Variable sin información: fecha registro'); 
    END IF;
    
    IF p_estado IS NULL OR TRIM(p_estado) = '' THEN
        RAISE_APPLICATION_ERROR(-20001, 'Variable sin información: estado'); 
    END IF;
    
    IF p_correo IS NOT NULL OR TRIM(p_correo) != '' THEN
        IF NOT REGEXP_LIKE(p_correo, v_pattern) THEN
            RAISE_APPLICATION_ERROR(-20001, 'Error: El formato del correo electrónico es inválido.');
        END IF;
    END IF;
    
    INSERT INTO comerciante (nombre, departamento, municipio, telefono, email, fecha_registro, estado)
    VALUES (p_nombre, p_departamento, p_municipio, p_telefono, p_correo, p_fecha_registro,p_estado );
      
    
    out_codigoerror:= 0;
    out_mensajeerror:=NULL;
    
    COMMIT;  -- Confirmar la transacción
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error general: ' || SQLERRM);
            out_codigoerror:= 1;
            out_mensajeerror:='Error general: '|| SQLERRM;
  END crear_comerciante;

  
  -- Procedimiento para actualizar
  PROCEDURE actualizar_comerciante(
      p_id_comerciante IN NUMBER,
      p_nombre IN VARCHAR2,
      p_departamento IN VARCHAR2,
      p_municipio IN VARCHAR2,
      p_fecha_registro IN DATE,
      p_estado IN VARCHAR2,
      out_codigoerror OUT NUMBER,
      out_mensajeerror OUT VARCHAR2,
      p_telefono IN VARCHAR2 DEFAULT NULL,
      p_correo IN VARCHAR2 DEFAULT NULL
  ) IS
  BEGIN
    UPDATE comerciante
    SET 
        nombre = p_nombre, 
        departamento= p_departamento, 
        municipio= p_municipio, 
        telefono= p_telefono, 
        email = p_correo, 
        fecha_registro= p_fecha_registro, 
        estado= p_estado
    WHERE ID = p_id_comerciante;

    out_codigoerror:= 0;
    out_mensajeerror:=NULL;
    
    COMMIT;  -- Confirmar la transacción
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error general: ' || SQLERRM);
            out_codigoerror:= 1;
            out_mensajeerror:='Error general';
            
  END actualizar_comerciante;

  -- Procedimiento para eliminar
  PROCEDURE eliminar_comerciante(
        p_comerciante_id IN NUMBER,
        out_codigoerror OUT NUMBER,
        out_mensajeerror OUT VARCHAR2 ) IS
  BEGIN
    DELETE FROM comerciante WHERE id = p_comerciante_id;
    
    out_codigoerror:= 0;
    out_mensajeerror:= null;

    COMMIT;  -- Confirmar la transacción
    EXCEPTION
        WHEN OTHERS THEN
            IF SQLCODE = -2292 THEN
                out_codigoerror:= 1;
                out_mensajeerror:='El comerciante esta relacionado en otra tabla, imposible eliminar.';
            else
                DBMS_OUTPUT.PUT_LINE('Error general: ' || SQLERRM);
                out_codigoerror:= 1;
                out_mensajeerror:='Error general';
            end if;
  END eliminar_comerciante;
  
  
  --funcion consultar por ID
  FUNCTION consultar_comerciante_id (id_comerciante in NUMBER)
  RETURN comerciante_tab IS
        v_comerciante comerciante_tab;  -- Variable para almacenar los resultados
    BEGIN
        -- Inicializar la colección
        v_comerciante := comerciante_tab(); 

        -- Usar un cursor para recuperar los datos de la tabla comerciantes
        
        FOR r IN (SELECT t.id,
                        nombre, 
                        departamento, 
                        municipio, 
                        telefono, 
                        email, 
                        fecha_registro, 
                        estado,
                        (SELECT sum(ingresos)as  ingresos FROM establecimiento where comerciante_id = t.id) as activos,
                        (SELECT sum(num_empleados)as  n_empleados FROM establecimiento where comerciante_id = t.id) as empleados
                        FROM comerciante t WHERE ID = id_comerciante ) LOOP
        
            -- Agregar cada registro a la colección
            v_comerciante.EXTEND;  -- Aumentar el tamaño de la colección
            v_comerciante(v_comerciante.LAST) := comerciante_rec(r.id, 
                                                        r.nombre, 
                                                        r.departamento, 
                                                        r.municipio, 
                                                        r.telefono, 
                                                        r.email, 
                                                        r.fecha_registro, 
                                                        r.estado, 
                                                        r.activos,
                                                        r.empleados);
        END LOOP;

        RETURN v_comerciante;  -- Retornar la colección de comerciantes
    END consultar_comerciante_id;
    
    
    
    --funcion consultar por ID
  FUNCTION consultar (
                    p_pagina IN NUMBER,         -- Número de página
                    p_tamano_pagina IN NUMBER,
                    p_nombre in VARCHAR2 DEFAULT NULL, 
                    p_municipio in VARCHAR2 DEFAULT NULL, 
                    p_fecha in DATE DEFAULT NULL,
                    p_estado in VARCHAR2 DEFAULT NULL
                    )
  RETURN comerciante_tab IS
        v_comerciante comerciante_tab;  -- Variable para almacenar los resultados
        v_inicio NUMBER;
        v_fin NUMBER;
    BEGIN
        -- Inicializar la colección
        v_comerciante := comerciante_tab(); 
        
         -- Calcular el rango de filas que corresponden a la página solicitada
        v_inicio := (p_pagina - 1) * p_tamano_pagina + 1;
        v_fin := p_pagina * p_tamano_pagina;

        -- Usar un cursor para recuperar los datos de la tabla comerciantes
        FOR r IN (
        
                    SELECT 
                        id,
                        nombre, 
                        departamento, 
                        municipio, 
                        telefono, 
                        email, 
                        fecha_registro, 
                        estado,
                        activos,
                        empleados
                    FROM (
                        SELECT t.id,
                            nombre, 
                            departamento, 
                            municipio, 
                            telefono, 
                            email, 
                            fecha_registro, 
                            estado,
                            (SELECT sum(ingresos)as  ingresos FROM establecimiento where comerciante_id = t.id) as activos,
                            (SELECT sum(num_empleados)as  n_empleados FROM establecimiento where comerciante_id = t.id) as empleados,
                            ROW_NUMBER() OVER (ORDER BY id) AS rn
                        FROM comerciante t WHERE (t.nombre = p_nombre OR p_nombre IS NULL)
                                                and (t.municipio = p_municipio or p_municipio IS NULL)
                                                and (t.ESTADO = p_estado or p_estado IS NULL)
                                                and (t.fecha_registro = p_fecha or p_fecha IS NULL )
                        )
                    WHERE rn BETWEEN v_inicio AND v_fin
                    
                ) LOOP
        
            -- Agregar cada registro a la colección
            v_comerciante.EXTEND;  -- Aumentar el tamaño de la colección
            v_comerciante(v_comerciante.LAST) := comerciante_rec(r.id, 
                                                        r.nombre, 
                                                        r.departamento, 
                                                        r.municipio, 
                                                        r.telefono, 
                                                        r.email, 
                                                        r.fecha_registro, 
                                                        r.estado, 
                                                        r.activos,
                                                        r.empleados);
        END LOOP;

        RETURN v_comerciante;  -- Retornar la colección de comerciantes
    END consultar;
    
    ---consulta con cursor
    FUNCTION consultar_comerciantes_cursor RETURN SYS_REFCURSOR IS
        v_cursor SYS_REFCURSOR; -- Declarar el cursor referenciado
    BEGIN
        -- realizar consulta
            OPEN v_cursor FOR
                select 
                    nombre, 
                    departamento, 
                    municipio, 
                    telefono, 
                    email, 
                    fecha_registro, 
                    estado,
                    cantidad_establecimientos,
                    activos,
                    num_empleados
                from (
                    SELECT  
                        nombre, 
                        departamento, 
                        municipio, 
                        telefono, 
                        email, 
                        fecha_registro, 
                        estado,
                        (SELECT count(*)as  cantidad_establecimientos FROM establecimiento where comerciante_id = t.id) as cantidad_establecimientos,
                        (SELECT sum(ingresos)as  ingresos FROM establecimiento where comerciante_id = t.id) as activos,
                        (SELECT sum(num_empleados)as  n_empleados FROM establecimiento where comerciante_id = t.id) as num_empleados
                    FROM comerciante t where estado = 'ACTIVO'
                )order by cantidad_establecimientos desc;

        -- Retornar el cursor
        RETURN v_cursor;
    END consultar_comerciantes_cursor;


END PKS_COMERCIANTE;