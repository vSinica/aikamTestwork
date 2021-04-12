--
-- PostgreSQL database dump
--

-- Dumped from database version 13.0
-- Dumped by pg_dump version 13.0

-- Started on 2021-04-12 23:47:43

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 200 (class 1259 OID 33062)
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    first_name character varying NOT NULL,
    last_name character varying NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 33098)
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_id_seq OWNER TO postgres;

--
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 203
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- TOC entry 201 (class 1259 OID 33071)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    cost integer NOT NULL,
    name character varying NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 33118)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 204
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 202 (class 1259 OID 33080)
-- Name: purchases; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchases (
    date date NOT NULL,
    customer_id integer NOT NULL,
    product_id integer NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.purchases OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 33137)
-- Name: purchases_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.purchases_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.purchases_id_seq OWNER TO postgres;

--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 205
-- Name: purchases_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.purchases_id_seq OWNED BY public.purchases.id;


--
-- TOC entry 2864 (class 2604 OID 33100)
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- TOC entry 2865 (class 2604 OID 33120)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 2866 (class 2604 OID 33139)
-- Name: purchases id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases ALTER COLUMN id SET DEFAULT nextval('public.purchases_id_seq'::regclass);


--
-- TOC entry 3003 (class 0 OID 33062)
-- Dependencies: 200
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customer (first_name, last_name, id) VALUES ('иванов', 'антон', 1);
INSERT INTO public.customer (first_name, last_name, id) VALUES ('иванов', 'николай', 2);
INSERT INTO public.customer (first_name, last_name, id) VALUES ('петров', 'валентин', 3);
INSERT INTO public.customer (first_name, last_name, id) VALUES ('петров', 'валентин', 4);
INSERT INTO public.customer (first_name, last_name, id) VALUES ('Дмитрий', 'Васильев', 5);
INSERT INTO public.customer (first_name, last_name, id) VALUES ('Сергей', 'Иванов', 6);
INSERT INTO public.customer (first_name, last_name, id) VALUES ('Глеб', 'Иванов', 7);


--
-- TOC entry 3004 (class 0 OID 33071)
-- Dependencies: 201
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (cost, name, id) VALUES (22, 'Минеральная вода', 1);
INSERT INTO public.product (cost, name, id) VALUES (11, 'Хлеб', 2);
INSERT INTO public.product (cost, name, id) VALUES (3, 'молоко', 3);


--
-- TOC entry 3005 (class 0 OID 33080)
-- Dependencies: 202
-- Data for Name: purchases; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-01', 5, 1, 1);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 2, 2);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 2, 3);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 2, 4);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 2, 5);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 2, 6);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 2, 7);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 2, 8);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 2, 9);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 2, 10);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 1, 11);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 1, 12);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 1, 13);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 1, 14);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 1, 15);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-01', 6, 1, 16);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-01', 6, 1, 17);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-01', 6, 1, 18);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-01', 6, 1, 19);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-01', 6, 1, 20);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-01', 6, 1, 21);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-01', 6, 1, 22);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-01', 6, 1, 23);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-01', 6, 1, 24);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-01', 7, 3, 25);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-01', 7, 3, 26);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 3, 27);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 3, 28);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 3, 29);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 3, 30);
INSERT INTO public.purchases (date, customer_id, product_id, id) VALUES ('2021-01-02', 5, 3, 31);


--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 203
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_id_seq', 7, true);


--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 204
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 3, true);


--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 205
-- Name: purchases_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.purchases_id_seq', 31, true);


--
-- TOC entry 2868 (class 2606 OID 33108)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- TOC entry 2870 (class 2606 OID 33128)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2872 (class 2606 OID 33144)
-- Name: purchases purchases_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_pkey PRIMARY KEY (id);


-- Completed on 2021-04-12 23:47:45

--
-- PostgreSQL database dump complete
--

