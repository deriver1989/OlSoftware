create or replace NONEDITIONABLE PACKAGE PKS_COMERCIANTE AS 

  -- Crear COMERCIANTE
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
  );

  -- ACTUALIZAR COMERCIANTE
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
  );

  -- ELIMINAR COMERCIANTE
   PROCEDURE eliminar_comerciante(
        p_comerciante_id IN NUMBER,
        out_codigoerror OUT NUMBER,
        out_mensajeerror OUT VARCHAR2);
        
         
    TYPE comerciante_rec IS RECORD   (
        id NUMBER,
        nombre VARCHAR2(100),
        departamento VARCHAR2(100),
        municipio VARCHAR2(100),
        telefono VARCHAR2(100),
        correo VARCHAR2(100),
        fecha_registro DATE,
        estado VARCHAR2(100),
        total_activo NUMBER(20,2),
        cantidad_empleado NUMBER
    );
    
    TYPE comerciante_tab IS TABLE OF comerciante_rec;

    FUNCTION consultar_comerciante_id (id_comerciante in NUMBER) RETURN comerciante_tab;
    
    
    FUNCTION consultar (p_pagina IN NUMBER,         -- Número de página
                    p_tamano_pagina IN NUMBER,
                    p_nombre in VARCHAR2 DEFAULT null, 
                    p_municipio in VARCHAR2 DEFAULT null, 
                    p_fecha in DATE DEFAULT null, 
                    p_estado in VARCHAR2 DEFAULT null ) RETURN comerciante_tab;


    FUNCTION consultar_comerciantes_cursor RETURN SYS_REFCURSOR;
    
END PKS_COMERCIANTE;