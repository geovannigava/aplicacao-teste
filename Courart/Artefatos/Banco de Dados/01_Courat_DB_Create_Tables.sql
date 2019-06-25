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
CREATE TABLE courart."tb_veiculos"(
	"id_veiculo" bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	"placa" varchar(10) NOT NULL,
	"ativo" integer NOT NULL DEFAULT 1,
	"ano_fabricacao" varchar(4) NOT NULL,
	"ano_modelo" varchar(4) NOT NULL,
	"chassi" varchar(40) NOT NULL,
	"data_cadastro" date,
	"data_desativacao" date,
	"modelo" varchar(30) NOT NULL,
	"cor" varchar(20),
	"consumo_medio" decimal NOT NULL,
	"quantidade_passageiros" integer NOT NULL DEFAULT 4,
	CONSTRAINT "id_veiculo" PRIMARY KEY ("id_veiculo")

);

-- ddl-end --
ALTER TABLE courart."tb_veiculos" OWNER TO postgres;
-- ddl-end --

-- object: courart."TB_FUNCIONARIOS" | type: TABLE --
-- DROP TABLE IF EXISTS courart."TB_FUNCIONARIOS" CASCADE;
CREATE TABLE courart."tb_funcionarios"(
	"id_funcionario" bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	"cpf" varchar(14) NOT NULL,
	"nome" varchar(40) NOT NULL,
	"data_nascimento" date,
	"login" varchar(12) NOT NULL,
	"senha" varchar(12) NOT NULL,
	CONSTRAINT "id_funcionario" PRIMARY KEY ("id_funcionario")

);
-- ddl-end --
ALTER TABLE courart."tb_funcionarios" OWNER TO postgres;
-- ddl-end --

