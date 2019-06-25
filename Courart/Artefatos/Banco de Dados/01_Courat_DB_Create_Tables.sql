-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.io
-- Model Author: ---


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database;
-- -- ddl-end --
-- 

-- object: courart | type: SCHEMA --
-- DROP SCHEMA IF EXISTS courart CASCADE;
CREATE SCHEMA courart;
-- ddl-end --
ALTER SCHEMA courart OWNER TO postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,courart;
-- ddl-end --

-- object: courart."TB_VEICULOS" | type: TABLE --
-- DROP TABLE IF EXISTS courart."TB_VEICULOS" CASCADE;
CREATE TABLE courart."TB_VEICULOS"(
	"ID_VEICULO" bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	"PLACA" varchar(10) NOT NULL,
	"ATIVO" integer NOT NULL DEFAULT 1,
	"ANO_FABRICACAO" varchar(4) NOT NULL,
	"ANO_MODELO" varchar(4) NOT NULL,
	"CHASSI" varchar(40) NOT NULL,
	"DATA_CADASTRO" date,
	"DATA_DESATIVACAO" date,
	"MODELO" varchar(30) NOT NULL,
	"COR" varchar(20),
	"CONSUMO_MEDIO" decimal NOT NULL,
	"QUANTIDADE_PASSAGEIROS" integer NOT NULL DEFAULT 4,
	CONSTRAINT "ID_VEICULO" PRIMARY KEY ("ID_VEICULO")

);
-- ddl-end --
ALTER TABLE courart."TB_VEICULOS" OWNER TO postgres;
-- ddl-end --

-- object: courart."TB_FUNCIONARIOS" | type: TABLE --
-- DROP TABLE IF EXISTS courart."TB_FUNCIONARIOS" CASCADE;
CREATE TABLE courart."TB_FUNCIONARIOS"(
	"ID_FUNCIONARIO" bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	"CPF" varchar(14) NOT NULL,
	"NOME" varchar(40) NOT NULL,
	"DATA_NASCIMENTO" date,
	"LOGIN" varchar(12) NOT NULL,
	"SENHA" varchar(12) NOT NULL,
	CONSTRAINT "ID_FUNCIONARIO" PRIMARY KEY ("ID_FUNCIONARIO")

);
-- ddl-end --
ALTER TABLE courart."TB_FUNCIONARIOS" OWNER TO postgres;
-- ddl-end --


