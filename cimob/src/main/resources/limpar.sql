-- ==========================
-- DESATIVAR CONSTRAINTS
-- ==========================
BEGIN
    FOR c IN (SELECT table_name, constraint_name FROM user_constraints WHERE constraint_type='R') LOOP
        EXECUTE IMMEDIATE 'ALTER TABLE ' || c.table_name || ' DISABLE CONSTRAINT ' || c.constraint_name;
    END LOOP;
END;
/

-- ==========================
-- DROPAR TABELAS
-- ==========================
BEGIN
    FOR t IN (SELECT table_name FROM user_tables) LOOP
        EXECUTE IMMEDIATE 'DROP TABLE ' || t.table_name || ' CASCADE CONSTRAINTS';
    END LOOP;
END;
/

-- ==========================
-- DROPAR SEQUÊNCIAS
-- ==========================
BEGIN
    FOR s IN (SELECT sequence_name FROM user_sequences) LOOP
        EXECUTE IMMEDIATE 'DROP SEQUENCE ' || s.sequence_name;
    END LOOP;
END;
/

-- ==========================
-- DROPAR TRIGGERS
-- ==========================
BEGIN
    FOR tr IN (SELECT trigger_name FROM user_triggers) LOOP
        EXECUTE IMMEDIATE 'DROP TRIGGER ' || tr.trigger_name;
    END LOOP;
END;
/

-- ==========================
-- DROPAR ÍNDICES (opcional, já são droppados com as tabelas)
-- ==========================
BEGIN
    FOR i IN (SELECT index_name FROM user_indexes WHERE table_name IS NOT NULL) LOOP
        EXECUTE IMMEDIATE 'DROP INDEX ' || i.index_name;
    END LOOP;
END;
/
