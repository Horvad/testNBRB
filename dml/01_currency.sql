-- добавить валюту в таблицу
INSERT INTO curr.currency(
    id, parent_id, code, abbreviation, name, name_bel, name_eng, quot_name, quot_name_bel, quot_name_eng, name_multi, name_bel_multi, name_eng_multi, scale, periodicity, date_start, date_end)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

-- получить список валют
SELECT id, parent_id, code, abbreviation, name, name_bel, name_eng, quot_name, quot_name_bel, quot_name_eng, name_multi, name_bel_multi, name_eng_multi, scale, periodicity, date_start, date_end
FROM curr.currency;

-- получить список валют по аббревиатуре
SELECT id, parent_id, code, abbreviation, name, name_bel, name_eng, quot_name, quot_name_bel, quot_name_eng, name_multi, name_bel_multi, name_eng_multi, scale, periodicity, date_start, date_end
FROM curr.currency
WHERE abbreviation = ?;