--Base tables for quartz
--
-- TOC entry 249 (class 1259 OID 46717)
-- Name: qrtz_blob_triggers; Type: TABLE; Schema: quartz; Owner: -
--

CREATE TABLE quartz.qrtz_blob_triggers
(
    sched_name    character varying(120) NOT NULL,
    trigger_name  character varying(200) NOT NULL,
    trigger_group character varying(200) NOT NULL,
    blob_data     bytea
);


--
-- TOC entry 250 (class 1259 OID 46723)
-- Name: qrtz_calendars; Type: TABLE; Schema: quartz; Owner: -
--

CREATE TABLE quartz.qrtz_calendars
(
    sched_name    character varying(120) NOT NULL,
    calendar_name character varying(200) NOT NULL,
    calendar      bytea                  NOT NULL
);


--
-- TOC entry 251 (class 1259 OID 46729)
-- Name: qrtz_cron_triggers; Type: TABLE; Schema: quartz; Owner: -
--

CREATE TABLE quartz.qrtz_cron_triggers
(
    sched_name      character varying(120) NOT NULL,
    trigger_name    character varying(200) NOT NULL,
    trigger_group   character varying(200) NOT NULL,
    cron_expression character varying(120) NOT NULL,
    time_zone_id    character varying(80)
);


--
-- TOC entry 252 (class 1259 OID 46735)
-- Name: qrtz_fired_triggers; Type: TABLE; Schema: quartz; Owner: -
--

CREATE TABLE quartz.qrtz_fired_triggers
(
    sched_name        character varying(120) NOT NULL,
    entry_id          character varying(95)  NOT NULL,
    trigger_name      character varying(200) NOT NULL,
    trigger_group     character varying(200) NOT NULL,
    instance_name     character varying(200) NOT NULL,
    fired_time        bigint                 NOT NULL,
    sched_time        bigint                 NOT NULL,
    priority          integer                NOT NULL,
    state             character varying(16)  NOT NULL,
    job_name          character varying(200),
    job_group         character varying(200),
    is_nonconcurrent  boolean,
    requests_recovery boolean
);


--
-- TOC entry 253 (class 1259 OID 46741)
-- Name: qrtz_job_details; Type: TABLE; Schema: quartz; Owner: -
--

CREATE TABLE quartz.qrtz_job_details
(
    sched_name        character varying(120) NOT NULL,
    job_name          character varying(200) NOT NULL,
    job_group         character varying(200) NOT NULL,
    description       character varying(250),
    job_class_name    character varying(250) NOT NULL,
    is_durable        boolean                NOT NULL,
    is_nonconcurrent  boolean                NOT NULL,
    is_update_data    boolean                NOT NULL,
    requests_recovery boolean                NOT NULL,
    job_data          bytea
);


--
-- TOC entry 254 (class 1259 OID 46747)
-- Name: qrtz_locks; Type: TABLE; Schema: quartz; Owner: -
--

CREATE TABLE quartz.qrtz_locks
(
    sched_name character varying(120) NOT NULL,
    lock_name  character varying(40)  NOT NULL
);


--
-- TOC entry 255 (class 1259 OID 46750)
-- Name: qrtz_paused_trigger_grps; Type: TABLE; Schema: quartz; Owner: -
--

CREATE TABLE quartz.qrtz_paused_trigger_grps
(
    sched_name    character varying(120) NOT NULL,
    trigger_group character varying(200) NOT NULL
);


--
-- TOC entry 256 (class 1259 OID 46753)
-- Name: qrtz_scheduler_state; Type: TABLE; Schema: quartz; Owner: -
--

CREATE TABLE quartz.qrtz_scheduler_state
(
    sched_name        character varying(120) NOT NULL,
    instance_name     character varying(200) NOT NULL,
    last_checkin_time bigint                 NOT NULL,
    checkin_interval  bigint                 NOT NULL
);


--
-- TOC entry 257 (class 1259 OID 46756)
-- Name: qrtz_simple_triggers; Type: TABLE; Schema: quartz; Owner: -
--

CREATE TABLE quartz.qrtz_simple_triggers
(
    sched_name      character varying(120) NOT NULL,
    trigger_name    character varying(200) NOT NULL,
    trigger_group   character varying(200) NOT NULL,
    repeat_count    bigint                 NOT NULL,
    repeat_interval bigint                 NOT NULL,
    times_triggered bigint                 NOT NULL
);


--
-- TOC entry 258 (class 1259 OID 46762)
-- Name: qrtz_simprop_triggers; Type: TABLE; Schema: quartz; Owner: -
--

CREATE TABLE quartz.qrtz_simprop_triggers
(
    sched_name    character varying(120) NOT NULL,
    trigger_name  character varying(200) NOT NULL,
    trigger_group character varying(200) NOT NULL,
    str_prop_1    character varying(512),
    str_prop_2    character varying(512),
    str_prop_3    character varying(512),
    int_prop_1    integer,
    int_prop_2    integer,
    long_prop_1   bigint,
    long_prop_2   bigint,
    dec_prop_1    numeric(13, 4),
    dec_prop_2    numeric(13, 4),
    bool_prop_1   boolean,
    bool_prop_2   boolean
);


--
-- TOC entry 259 (class 1259 OID 46768)
-- Name: qrtz_triggers; Type: TABLE; Schema: quartz; Owner: -
--

CREATE TABLE quartz.qrtz_triggers
(
    sched_name     character varying(120) NOT NULL,
    trigger_name   character varying(200) NOT NULL,
    trigger_group  character varying(200) NOT NULL,
    job_name       character varying(200) NOT NULL,
    job_group      character varying(200) NOT NULL,
    description    character varying(250),
    next_fire_time bigint,
    prev_fire_time bigint,
    priority       integer,
    trigger_state  character varying(16)  NOT NULL,
    trigger_type   character varying(8)   NOT NULL,
    start_time     bigint                 NOT NULL,
    end_time       bigint,
    calendar_name  character varying(200),
    misfire_instr  smallint,
    job_data       bytea
);


--
-- TOC entry 3985 (class 2606 OID 46944)
-- Name: qrtz_blob_triggers qrtz_blob_triggers_pkey; Type: CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_blob_triggers
    ADD CONSTRAINT qrtz_blob_triggers_pkey PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- TOC entry 3987 (class 2606 OID 46946)
-- Name: qrtz_calendars qrtz_calendars_pkey; Type: CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_calendars
    ADD CONSTRAINT qrtz_calendars_pkey PRIMARY KEY (sched_name, calendar_name);


--
-- TOC entry 3989 (class 2606 OID 46948)
-- Name: qrtz_cron_triggers qrtz_cron_triggers_pkey; Type: CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_cron_triggers
    ADD CONSTRAINT qrtz_cron_triggers_pkey PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- TOC entry 3991 (class 2606 OID 46950)
-- Name: qrtz_fired_triggers qrtz_fired_triggers_pkey; Type: CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_fired_triggers
    ADD CONSTRAINT qrtz_fired_triggers_pkey PRIMARY KEY (sched_name, entry_id);


--
-- TOC entry 3993 (class 2606 OID 46952)
-- Name: qrtz_job_details qrtz_job_details_pkey; Type: CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_job_details
    ADD CONSTRAINT qrtz_job_details_pkey PRIMARY KEY (sched_name, job_name, job_group);


--
-- TOC entry 3995 (class 2606 OID 46954)
-- Name: qrtz_locks qrtz_locks_pkey; Type: CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_locks
    ADD CONSTRAINT qrtz_locks_pkey PRIMARY KEY (sched_name, lock_name);


--
-- TOC entry 3997 (class 2606 OID 46956)
-- Name: qrtz_paused_trigger_grps qrtz_paused_trigger_grps_pkey; Type: CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_paused_trigger_grps
    ADD CONSTRAINT qrtz_paused_trigger_grps_pkey PRIMARY KEY (sched_name, trigger_group);


--
-- TOC entry 3999 (class 2606 OID 46958)
-- Name: qrtz_scheduler_state qrtz_scheduler_state_pkey; Type: CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_scheduler_state
    ADD CONSTRAINT qrtz_scheduler_state_pkey PRIMARY KEY (sched_name, instance_name);


--
-- TOC entry 4001 (class 2606 OID 46960)
-- Name: qrtz_simple_triggers qrtz_simple_triggers_pkey; Type: CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_simple_triggers
    ADD CONSTRAINT qrtz_simple_triggers_pkey PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- TOC entry 4003 (class 2606 OID 46962)
-- Name: qrtz_simprop_triggers qrtz_simprop_triggers_pkey; Type: CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_simprop_triggers
    ADD CONSTRAINT qrtz_simprop_triggers_pkey PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- TOC entry 4005 (class 2606 OID 46964)
-- Name: qrtz_triggers qrtz_triggers_pkey; Type: CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_triggers
    ADD CONSTRAINT qrtz_triggers_pkey PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- TOC entry 4006 (class 2606 OID 50310)
-- Name: qrtz_blob_triggers qrtz_blob_triggers_sched_name_fkey; Type: FK CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_blob_triggers
    ADD CONSTRAINT qrtz_blob_triggers_sched_name_fkey FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES quartz.qrtz_triggers (sched_name, trigger_name, trigger_group);


--
-- TOC entry 4007 (class 2606 OID 50315)
-- Name: qrtz_cron_triggers qrtz_cron_triggers_sched_name_fkey; Type: FK CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_cron_triggers
    ADD CONSTRAINT qrtz_cron_triggers_sched_name_fkey FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES quartz.qrtz_triggers (sched_name, trigger_name, trigger_group);


--
-- TOC entry 4008 (class 2606 OID 50320)
-- Name: qrtz_simple_triggers qrtz_simple_triggers_sched_name_fkey; Type: FK CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_simple_triggers
    ADD CONSTRAINT qrtz_simple_triggers_sched_name_fkey FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES quartz.qrtz_triggers (sched_name, trigger_name, trigger_group);


--
-- TOC entry 4009 (class 2606 OID 50325)
-- Name: qrtz_simprop_triggers qrtz_simprop_triggers_sched_name_fkey; Type: FK CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_simprop_triggers
    ADD CONSTRAINT qrtz_simprop_triggers_sched_name_fkey FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES quartz.qrtz_triggers (sched_name, trigger_name, trigger_group);


--
-- TOC entry 4010 (class 2606 OID 50330)
-- Name: qrtz_triggers qrtz_triggers_sched_name_fkey; Type: FK CONSTRAINT; Schema: quartz; Owner: -
--

ALTER TABLE ONLY quartz.qrtz_triggers
    ADD CONSTRAINT qrtz_triggers_sched_name_fkey FOREIGN KEY (sched_name, job_name, job_group) REFERENCES quartz.qrtz_job_details (sched_name, job_name, job_group);
