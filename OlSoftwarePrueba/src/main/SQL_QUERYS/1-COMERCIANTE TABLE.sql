  CREATE TABLE "SYSTEM"."COMERCIANTE" 
   (	"ID" NUMBER, 
	"NOMBRE" VARCHAR2(200 BYTE), 
	"DEPARTAMENTO" VARCHAR2(20 BYTE), 
	"MUNICIPIO" VARCHAR2(20 BYTE), 
	"EMAIL" VARCHAR2(50 BYTE), 
	"FECHA_REGISTRO" DATE, 
	"ESTADO" VARCHAR2(20 BYTE), 
	"FECHA_ACTUALIZACION" DATE, 
	"USUARIO" VARCHAR2(20 BYTE), 
	"TELEFONO" VARCHAR2(20 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index ESTABLECIMIENTO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."ESTABLECIMIENTO_PK" ON "SYSTEM"."COMERCIANTE" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
  
  --------------------------------------------------------
--  Constraints for Table COMERCIANTE
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."COMERCIANTE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."COMERCIANTE" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."COMERCIANTE" MODIFY ("FECHA_REGISTRO" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."COMERCIANTE" ADD CONSTRAINT "ESTABLECIMIENTO_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;