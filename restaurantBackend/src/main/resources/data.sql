-- ==========================================
-- 1. TESTDATEN FÜR DIE MENÜKARTE
-- (Keine ID angeben, da GenerationType.IDENTITY)
-- ==========================================

INSERT INTO menu (menu_name, description, price, category, chef_choice, gluten_free, lactose_free, contains_nuts, vegan, vegetarian)
VALUES ('REST-Foods Trüffel Burger', 'Saftiges Rindfleisch mit frischem Trüffel, Alpkäse und hausgemachter Sauce.', 28.50, 'Burger', true, false, false, false, false, false);

INSERT INTO menu (menu_name, description, price, category, chef_choice, gluten_free, lactose_free, contains_nuts, vegan, vegetarian)
VALUES ('Zitronen-Spaghetti', 'Frische Pasta an einer leichten Zitronen-Sojasauce mit Cherrytomaten.', 21.00, 'Pasta', false, false, true, false, true, true);


-- ==========================================
-- 2. TESTDATEN FÜR DIE RESERVATIONEN
-- (Achtung: table_id und phone_number exakt nach deinem Regex-Muster!)
-- ==========================================

INSERT INTO reservation (customer_name, phone_number, number_of_persons, reserved_from, reserved_to, table_id)
VALUES ('Noah Müller', '+41791234567', 4, '2026-06-15 18:30:00', '2026-06-15 20:30:00', 'T-03');

INSERT INTO reservation (customer_name, phone_number, number_of_persons, reserved_from, reserved_to, table_id)
VALUES ('Emma Fischer', '+41769876543', 2, '2026-06-16 20:00:00', '2026-06-16 21:30:00', 'T-01');