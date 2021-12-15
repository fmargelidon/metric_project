-- create sequence for table metric_type
CREATE SEQUENCE public.metric_type_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

-- create table metric_type
CREATE TABLE IF NOT EXISTS metric_type (
  id_metric_type INTEGER DEFAULT nextval('metric_type_seq') NOT NULL,
  name VARCHAR(250) NOT NULL,
  PRIMARY KEY (id_metric_type)
);

-- create sequence for table metrics
CREATE SEQUENCE public.metrics_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

-- create table metrics
CREATE TABLE IF NOT EXISTS metrics (
  id_metrics INTEGER DEFAULT nextval('metrics_seq') NOT NULL,
  process_name VARCHAR(250) NOT NULL,
  pid INTEGER NOT NULL,
  value NUMERIC(10,2) NOT NULL,
  event_date TIMESTAMP NOT NULL,
  hostname VARCHAR(50) NOT NULL,
  id_metric_type INTEGER NOT NULL,
  PRIMARY KEY (id_metrics),
  CONSTRAINT fk_metric_type
      FOREIGN KEY(id_metric_type)
    REFERENCES metric_type(id_metric_type)
);

-- create sequence for table node_load
CREATE SEQUENCE public.node_load_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

-- create table node_load
CREATE TABLE IF NOT EXISTS node_load (
  id_node_load INTEGER DEFAULT nextval('node_load_seq') NOT NULL,
  value NUMERIC(6,2) NOT NULL,
  event_date TIMESTAMP NOT NULL,
  hostname VARCHAR(50) NOT NULL,
  PRIMARY KEY (id_node_load)
);