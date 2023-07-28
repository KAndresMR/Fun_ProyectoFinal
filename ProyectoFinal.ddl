

CREATE TABLE far_cabecera_factura (
    fac_id              NUMBER(6) NOT NULL,
    fac_numero          NUMBER(6) NOT NULL UNIQUE,
    fac_fecha           DATE NOT NULL,
    fac_subtotal        NUMBER(8, 2) NOT NULL,
    fac_total_iva       NUMBER(7, 2) NOT NULL,
    fac_total           NUMBER(8, 2) NOT NULL,
    fac_estado          NUMBER(1) NOT NULL,
    far_usuarios_usu_id NUMBER(6) NOT NULL,
    far_personas_per_id NUMBER(6) NOT NULL
);

COMMENT ON COLUMN far_cabecera_factura.fac_id IS
    'Codigo unico de la entidad Cabecera de factura.';

COMMENT ON COLUMN far_cabecera_factura.fac_numero IS
    'Se registra el numero de factura correspondiente';

COMMENT ON COLUMN far_cabecera_factura.fac_fecha IS
    'Se registra la fecha de emison de la factura.';

COMMENT ON COLUMN far_cabecera_factura.fac_subtotal IS
    'Se registra el subtotal de la factura.';

COMMENT ON COLUMN far_cabecera_factura.fac_total_iva IS
    'Se registra el IVA total de la factura.';

COMMENT ON COLUMN far_cabecera_factura.fac_total IS
    'Se registra el total de la factura.';

COMMENT ON COLUMN far_cabecera_factura.fac_estado IS
    'Registra el estado de la factura : En caso de estar vigente (1) en caso contrario, estará anulado (0)';

ALTER TABLE far_cabecera_factura ADD CONSTRAINT far_cabecera_factura_pk PRIMARY KEY ( fac_id );

CREATE TABLE far_categorias (
    cat_id          NUMBER(5) NOT NULL,
    cat_descripcion CLOB NOT NULL,
    cat_nombre      VARCHAR2(60) NOT NULL
);

COMMENT ON COLUMN far_categorias.cat_id IS
    'Codigo unico de la entidad.';

COMMENT ON COLUMN far_categorias.cat_descripcion IS
    'Descripcion de la categoria.';

COMMENT ON COLUMN far_categorias.cat_nombre IS
    'Nombre de la categoria.';

ALTER TABLE far_categorias ADD CONSTRAINT far_categorias_pk PRIMARY KEY ( cat_id );

CREATE TABLE far_detalle_factura (
    det_id                      NUMBER(6) NOT NULL,
    det_cantidad                NUMBER(6) NOT NULL,
    det_preciounitario          NUMBER(6, 2) NOT NULL,
    det_subtotal                NUMBER(6, 2) NOT NULL,
    det_iva                     NUMBER(6, 2) NOT NULL,
    det_total                   NUMBER(8, 2) NOT NULL,
    far_cabecera_factura_fac_id NUMBER(6) NOT NULL,
    far_productos_pro_id        NUMBER(6) NOT NULL
);

COMMENT ON COLUMN far_detalle_factura.det_id IS
    'Se registra el codigo unico de la entidad.';

COMMENT ON COLUMN far_detalle_factura.det_cantidad IS
    'Cantidad de productos adquiridos.';

COMMENT ON COLUMN far_detalle_factura.det_preciounitario IS
    'Se registra el precio unitario de cada producto.';

COMMENT ON COLUMN far_detalle_factura.det_subtotal IS
    'Se registra el subtotal de los productos adquiridos.';

COMMENT ON COLUMN far_detalle_factura.det_iva IS
    'Se registra el IVA del producto
En caso de no tener iva, el valor debe de ser 0';

COMMENT ON COLUMN far_detalle_factura.det_total IS
    'Se registar el total del detalle de la factura.';

ALTER TABLE far_detalle_factura ADD CONSTRAINT far_detalle_factura_pk PRIMARY KEY ( det_id );

CREATE TABLE far_nota_ventas (
    not_id                  NUMBER(6) NOT NULL,
    not_numero_factura      NUMBER(6) NOT NULL UNIQUE,
    not_fecha_factura       DATE NOT NULL,
    not_valor_total         NUMBER(7, 2) NOT NULL,
    not_cantidad_producto   NUMBER(4) NOT NULL,
    far_proveedores_prov_id NUMBER(6) NOT NULL,
    far_productos_pro_id    NUMBER(6) NOT NULL
);

COMMENT ON COLUMN far_nota_ventas.not_id IS
    'Numero de identificacion de la entidad';

COMMENT ON COLUMN far_nota_ventas.not_numero_factura IS
    'Se registra el numero de la factura.';

COMMENT ON COLUMN far_nota_ventas.not_fecha_factura IS
    'Se registra la fecha de la factura.';

COMMENT ON COLUMN far_nota_ventas.not_valor_total IS
    'Valor total de la nota de venta';

COMMENT ON COLUMN far_nota_ventas.not_cantidad_producto IS
    'Cantidad de productos adquiridos';

ALTER TABLE far_nota_ventas ADD CONSTRAINT far_nota_ventas_pk PRIMARY KEY ( not_id );

CREATE TABLE far_personas (
    per_id        NUMBER(6) NOT NULL,
    per_cedula    VARCHAR2(10) NOT NULL UNIQUE,
    per_nombre    VARCHAR2(60) NOT NULL,
    per_apellido  VARCHAR2(60) NOT NULL,
    per_direccion VARCHAR2(100) NOT NULL,
    per_telefono  VARCHAR2(13) NOT NULL,
    per_correo    VARCHAR2(60) NOT NULL,
    per_tipo      CHAR(1) NOT NULL,
    per_estado    NUMBER(1) NOT NULL
);

COMMENT ON COLUMN far_personas.per_id IS
    'Numero de identificacion de la entidad.';

COMMENT ON COLUMN far_personas.per_cedula IS
    'Numero de cedula de la persona.';

COMMENT ON COLUMN far_personas.per_nombre IS
    'Nombre de la persona.';

COMMENT ON COLUMN far_personas.per_apellido IS
    'Apellidos de la persona.';

COMMENT ON COLUMN far_personas.per_direccion IS
    'Direccion de residencia de la persona.';

COMMENT ON COLUMN far_personas.per_telefono IS
    'Numero de telefeno convencional o celular  de la persona.';

COMMENT ON COLUMN far_personas.per_correo IS
    'Correo electronico de la persona.';

COMMENT ON COLUMN far_personas.per_tipo IS
    'Se registra el tipo de usuario si es Cliente (c), si es Empleado (e)';

COMMENT ON COLUMN far_personas.per_estado IS
    'Registra el estado de la persona: En caso de estar activo (1), caso contrario estará desactivado (0)';

ALTER TABLE far_personas ADD CONSTRAINT far_personas_pk PRIMARY KEY ( per_id );

CREATE TABLE far_productos (
    pro_id                NUMBER(6) NOT NULL,
    pro_codigo_barra      NUMBER(12) NOT NULL UNIQUE,
    pro_nombre            VARCHAR2(60) NOT NULL,
    pro_precio            NUMBER(6, 2) NOT NULL,
    pro_stock             NUMBER(6) NOT NULL,
    pro_iva               CHAR(1) NOT NULL,
    far_categorias_cat_id NUMBER(5) NOT NULL
);

COMMENT ON COLUMN far_productos.pro_id IS
    'Codigo unico de la entidad.';

COMMENT ON COLUMN far_productos.pro_codigo_barra IS
    'Codigo de barras del producto';

COMMENT ON COLUMN far_productos.pro_nombre IS
    'Nombre del producto.';

COMMENT ON COLUMN far_productos.pro_precio IS
    'Precio unitario del producto.';

COMMENT ON COLUMN far_productos.pro_stock IS
    'Cantidad de producto disponibles.';

COMMENT ON COLUMN far_productos.pro_iva IS
    'Si el producto tiene IVA(S)
Si el producto no tiene IVA (N)';

ALTER TABLE far_productos ADD CONSTRAINT far_productos_pk PRIMARY KEY ( pro_id );

CREATE TABLE far_proveedores (
    prov_id     NUMBER(6) NOT NULL,
    prov_nombre VARCHAR2(20) NOT NULL,
    prov_ruc    VARCHAR2(11) NOT NULL UNIQUE
);

COMMENT ON COLUMN far_proveedores.prov_id IS
    'Numero unico de la entidad.';

COMMENT ON COLUMN far_proveedores.prov_nombre IS
    'Nombre del proveeodr';

COMMENT ON COLUMN far_proveedores.prov_ruc IS
    'Numero de ruc de la empresa proveedor';

ALTER TABLE far_proveedores ADD CONSTRAINT far_proveedores_pk PRIMARY KEY ( prov_id );

CREATE TABLE far_usuarios (
    usu_id              NUMBER(6) NOT NULL,
    usu_nombre          VARCHAR2(30) NOT NULL,
    usu_contrasena      VARCHAR2(60) NOT NULL,
    usu_tipo            CHAR(1) NOT NULL,
    far_personas_per_id NUMBER(6) NOT NULL
);

COMMENT ON COLUMN far_usuarios.usu_id IS
    'Codigo unico del usuario';

COMMENT ON COLUMN far_usuarios.usu_nombre IS
    'Se registra el nombre de usuario';

COMMENT ON COLUMN far_usuarios.usu_contrasena IS
    'Se registra la contraseña del usuario.';

COMMENT ON COLUMN far_usuarios.usu_tipo IS
    'Se registra el tipo de usuario si es Empleado General(E) o Administrador(A).';

CREATE UNIQUE INDEX far_usuarios__idx ON
    far_usuarios (
        far_personas_per_id
    ASC );

ALTER TABLE far_usuarios ADD CONSTRAINT far_usuarios_pk PRIMARY KEY ( usu_id );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE far_cabecera_factura
    ADD CONSTRAINT far_cabecera_factura_far_personas_fk FOREIGN KEY ( far_personas_per_id )
        REFERENCES far_personas ( per_id );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE far_cabecera_factura
    ADD CONSTRAINT far_cabecera_factura_far_usuarios_fk FOREIGN KEY ( far_usuarios_usu_id )
        REFERENCES far_usuarios ( usu_id );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE far_detalle_factura
    ADD CONSTRAINT far_detalle_factura_far_cabecera_factura_fk FOREIGN KEY ( far_cabecera_factura_fac_id )
        REFERENCES far_cabecera_factura ( fac_id );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE far_detalle_factura
    ADD CONSTRAINT far_detalle_factura_far_productos_fk FOREIGN KEY ( far_productos_pro_id )
        REFERENCES far_productos ( pro_id );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE far_nota_ventas
    ADD CONSTRAINT far_nota_ventas_far_productos_fk FOREIGN KEY ( far_productos_pro_id )
        REFERENCES far_productos ( pro_id );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE far_nota_ventas
    ADD CONSTRAINT far_nota_ventas_far_proveedores_fk FOREIGN KEY ( far_proveedores_prov_id )
        REFERENCES far_proveedores ( prov_id );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE far_productos
    ADD CONSTRAINT far_productos_far_categorias_fk FOREIGN KEY ( far_categorias_cat_id )
        REFERENCES far_categorias ( cat_id );

ALTER TABLE far_usuarios
    ADD CONSTRAINT far_usuarios_far_personas_fk FOREIGN KEY ( far_personas_per_id )
        REFERENCES far_personas ( per_id );

CREATE SEQUENCE seq_fac_id
  START WITH 1
  INCREMENT BY 1
  MAXVALUE 999999
  CACHE 20;

CREATE SEQUENCE seq_cat_id
  START WITH 1
  INCREMENT BY 1
  MAXVALUE 99999
  NOCACHE;

CREATE SEQUENCE seq_det_id
  START WITH 1
  INCREMENT BY 1
  MAXVALUE 999999
  NOCACHE;

CREATE SEQUENCE seq_not_id
  START WITH 1
  INCREMENT BY 1
  MAXVALUE 999999
  NOCACHE;

CREATE SEQUENCE seq_per_id
  START WITH 1
  INCREMENT BY 1
  MAXVALUE 999999
  NOCACHE;

CREATE SEQUENCE seq_pro_id
  START WITH 1
  INCREMENT BY 1
  MAXVALUE 999999
  NOCACHE;

CREATE SEQUENCE seq_prov_id
  START WITH 1
  INCREMENT BY 1
  MAXVALUE 999999
  NOCACHE;

CREATE SEQUENCE seq_usu_id
  START WITH 1
  INCREMENT BY 1
  MAXVALUE 999999
  NOCACHE;

-- Drop all tables
